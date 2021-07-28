package com.ferry.admin.controller;

import com.baidu.aip.util.Base64Util;
import com.ferry.admin.service.impl.FaceLoginServiceImpl;
import com.ferry.admin.util.BaiduAiUtil;
import com.ferry.admin.vo.FaceLoginResult;
import com.ferry.admin.vo.QRCode;
import com.ferry.common.utils.StringUtils;
import com.ferry.server.admin.entity.SysUser;
import com.ferry.admin.security.JwtAuthenticatioToken;
import com.ferry.admin.service.SysLoginLogService;
import com.ferry.admin.service.SysUserService;
import com.ferry.admin.util.IPUtils;
import com.ferry.admin.util.PasswordUtils;
import com.ferry.admin.util.SecurityUtils;
import com.ferry.admin.vo.LoginBean;
import com.ferry.common.utils.IOUtils;
import com.ferry.core.http.Result;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * 登录接口
 *
 * @Author: 摆渡人
 * @Date: 2021/4/26
 */
@Api(tags = "登录接口")
@RestController
public class LoginController {

    @Autowired
    private Producer producer;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SysLoginLogService sysLoginLogService;

    @Autowired
    private FaceLoginServiceImpl faceLoginService;

    @Autowired
    private BaiduAiUtil baiduAiUtil;

    @ApiOperation(value = "账号密码登陆")
    @PostMapping(value = "/login")
    public Result login(@RequestBody LoginBean loginBean, HttpServletRequest request) throws IOException {
        String username = loginBean.getAccount();
        String password = loginBean.getPassword();
        String captcha = loginBean.getCaptcha();
        // 从session中获取之前保存的验证码跟前台传来的验证码进行匹配
        Object kaptcha = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
//		if(kaptcha == null){
//			return HttpResult.error("验证码已失效");
//		}
//		if(!captcha.equals(kaptcha)){
//			return HttpResult.error("验证码不正确");
//		}
        // 用户信息
        SysUser user = sysUserService.findByName(username);
        // 账号不存在、密码错误
        if (user == null) {
            return Result.error("账号不存在");
        }
        if (!PasswordUtils.matches(user.getSalt(), password, user.getPassword())) {
            return Result.error("密码不正确");
        }
        // 账号锁定
        if (user.getStatus() == 0) {
            return Result.error("账号已被锁定,请联系管理员");
        }
        // 系统登录认证
        JwtAuthenticatioToken token = SecurityUtils.login(request, username, password, authenticationManager);
        // 记录登录日志
        sysLoginLogService.writeLoginLog(username, IPUtils.getIpAddr(request));
        return Result.ok(token);
    }

    @ApiOperation(value = "图形验证码")
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        // 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        BufferedImage image = producer.createImage(text);
        // 保存到验证码到 session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    @ApiOperation(value = "登录二维码链接")
    @RequestMapping(value = "/qrcode", method = RequestMethod.GET)
    public Result qrcode() throws Exception {
        QRCode qrCode = faceLoginService.getQRCode();
        return Result.ok(qrCode);
    }

    @ApiOperation(value = "验证登陆")
    @RequestMapping(value = "/qrcode/{code}", method = RequestMethod.GET)
    public Result qrcodeCeck(@PathVariable(name = "code") String code) throws Exception {
        FaceLoginResult result = faceLoginService.checkQRCode(code);
        return Result.ok(result);
    }

    @ApiOperation(value = "人脸验证")
    @RequestMapping(value = "/faceLogin/{code}", method = RequestMethod.POST)
    public Result loginByFace(@PathVariable(name = "code") String code,
                              @RequestParam(name = "file") MultipartFile attachment
            , HttpServletRequest request) throws Exception {
        if (StringUtils.isBlank(code) || Objects.equals("undefined", code)) {
            return Result.error();
        }
        FaceLoginResult token = faceLoginService.loginByFace(code, attachment, request);
        if (token != null) {
            return Result.ok(token);
        } else {
            return Result.error();
        }
    }

    @ApiOperation(value = "检测人脸")
    @RequestMapping(value = "/checkFace", method = RequestMethod.POST)
    public Result checkFace(@RequestParam(name = "file") MultipartFile file) throws Exception {
        String image = Base64Util.encode(file.getBytes());
        Boolean aBoolean = baiduAiUtil.faceCheck(image);
        if (aBoolean) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }
}

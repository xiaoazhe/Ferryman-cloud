package com.ferry.admin.service.impl;


import com.baidu.aip.util.Base64Util;
import com.ferry.admin.security.JwtAuthenticatioToken;
import com.ferry.admin.service.SysLoginLogService;
import com.ferry.admin.util.*;
import com.ferry.admin.vo.FaceLoginResult;
import com.ferry.admin.vo.QRCode;
import com.ferry.common.utils.IdWorker;
import com.ferry.server.admin.entity.SysUser;
import com.ferry.server.admin.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Service
public class FaceLoginServiceImpl {

    @Value("${qr.url}")
    private String url;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private FaceAiUtil faceAiUtil;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private QRCodeUtil qrCodeUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private SysLoginLogService sysLoginLogService;

    // 创建二维码
    public QRCode getQRCode() throws Exception {
        String code = idWorker.nextId() + "";
        String content = url + "?code=" + code;
        String file = qrCodeUtil.crateQRCode(content);
        FaceLoginResult result = new FaceLoginResult("-1");
        redisTemplate.boundValueOps(getCacheKey(code)).set(result, 10, TimeUnit.MINUTES);
        return new QRCode(code, file);
    }

    // 根据唯一标识，查询用户是否登录成功
    public FaceLoginResult checkQRCode(String code) {
        String key = getCacheKey(code);
        return (FaceLoginResult) redisTemplate.opsForValue().get(key);
    }

    //扫描二维码之后，使用拍摄照片进行登录
    public FaceLoginResult loginByFace(String code, MultipartFile attachment,
                                       HttpServletRequest request) throws Exception {
        String userId = faceAiUtil.faceSearch(Base64Util.encode(attachment.getBytes()), true);
        //自动登录（tonken）
        FaceLoginResult result = new FaceLoginResult("0");
        if (userId != null) {
            //自己模拟登录
            SysUser user = userMapper.selectById(userId);
            if (user != null) {
                // 系统登录认证
                JwtAuthenticatioToken token = SecurityUtils.login(request, user.getName(),
                        user.getPwd(), authenticationManager);
                // 记录登录日志
                sysLoginLogService.writeLoginLog(user.getName(), IPUtils.getIpAddr(request));
                result = new FaceLoginResult("1", token.getToken(), user);
                redisTemplate.boundValueOps(getCacheKey(code)).set(result, 10, TimeUnit.MINUTES);
                return result;
            }
        }
        return null;
    }

    //构造缓存key
    private String getCacheKey(String code) {
        return "qrcode_" + code;
    }
}

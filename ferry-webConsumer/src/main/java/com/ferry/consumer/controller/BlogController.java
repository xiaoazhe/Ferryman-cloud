package com.ferry.consumer.controller;

import com.ferry.common.enums.FieldStatusEnum;
import com.ferry.common.enums.StateEnums;
import com.ferry.common.utils.StringUtils;
import com.ferry.consumer.http.PageRequest;
import com.ferry.consumer.http.Result;
import com.ferry.consumer.interceptor.JwtUtil;
import com.ferry.consumer.service.BlogService;
import com.ferry.consumer.service.LabelService;
import com.ferry.consumer.service.impl.BlogDealService;
import com.ferry.server.blog.entity.BlBlog;
import com.ferry.server.blog.entity.BlCollect;
import com.ferry.server.blog.mapper.BlCollectMapper;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author: 摆渡人
 * @Date: 2021/4/29
 */

@Api(tags = "博客")
@CrossOrigin
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogDealService blogDealService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BlCollectMapper collectMapper;

    @Autowired
    private LabelService labelService;

    @ApiOperation(value = "分页查询")
    @PostMapping(value="/findPage")
    public Result findPage(@RequestBody PageRequest pageRequest) {
        Result result = blogService.findPage(pageRequest);
        return result;
    }

    @ApiOperation(value = "个人查询")
    @PostMapping(value="/findUserPage/{userId}")
    public Result findUserPage(@RequestBody PageRequest pageRequest, @PathVariable String userId) {
        if (StringUtils.isBlank(userId)) {
            try {
                String token = request.getHeader(FieldStatusEnum.HEARD).substring(7);
                Claims claims = jwtUtil.parseJWT(token);
                userId = claims.getId();
            } catch (Exception e) {
                return null;
            }
        }
        return blogService.findUserPage(userId, pageRequest);
    }

    @ApiOperation(value = "根据ID获取")
    @PostMapping(value="/getBlogById")
    public Result getBlogById(@RequestParam(value = "id") String id) {
        String userId = null;
        String token = request.getHeader(FieldStatusEnum.HEARD);
        if (!StringUtils.isBlank(token)) {
            try {
                Claims claims = jwtUtil.parseJWT(token.substring(7));
                userId = claims.getId();
                BlCollect collect = new BlCollect();
                collect.setStatus(3);
                collect.setBlogId(id);
                collect.setUserId(userId);
                collect.setCreateBy(userId);
                collect.setCreateTime(new Date());
                collectMapper.insert(collect);
            } catch (Exception e) {
                throw new RuntimeException(StateEnums.REQUEST_ERROR.getMsg());
            } finally {
                Result result = blogService.getBlogById(id);
                return result;
            }
        }
        Result result = blogService.getBlogById(id);
        return result;
    }

    @ApiOperation(value = "热门推荐")
    @PostMapping(value="/hotBlog")
    public Result hotBlog() {
        return blogService.hotBlog();
    }

    @ApiOperation(value = "用户添加博客")
    @PostMapping("/saveBlog")
    public Result saveBlog(@RequestBody BlBlog blBlog) {
        String userId = null;
        try {
            String token = request.getHeader(FieldStatusEnum.HEARD).substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            userId = claims.getId();
        } catch (Exception e) {
            return Result.error(StateEnums.REQUEST_ERROR.getMsg());
        }
        blBlog.setUserUid(userId);
        return blogDealService.saveBlog(blBlog);
    }

    @ApiOperation(value = "删除")
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable String id) {
        return blogService.deleteById(id);
    }

    /**
     *音乐列表
     *
     * @return
     */
    @ApiOperation(value = "前台查询")
    @RequestMapping(value = "/getMusicList", method = RequestMethod.GET)
    public Result getList() {
        return Result.ok(labelService.getMusicList());
    }
}

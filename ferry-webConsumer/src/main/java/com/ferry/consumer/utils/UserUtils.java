package com.ferry.consumer.utils;

import com.ferry.common.enums.FieldStatusEnum;
import com.ferry.consumer.interceptor.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/10
 */
public class UserUtils {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JwtUtil jwtUtil;

    public static String getUserId(HttpServletRequest request, JwtUtil jwtUtil) {
        String token = (String) request.getAttribute(FieldStatusEnum.ROLE_USER);
        Claims claims = jwtUtil.parseJWT(token);
        String userId = claims.getId();
        return userId;
    }
}

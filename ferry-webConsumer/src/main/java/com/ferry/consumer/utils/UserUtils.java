package com.ferry.consumer.utils;

import com.ferry.common.enums.FieldStatusEnum;
import com.ferry.common.enums.StateEnums;
import com.ferry.consumer.http.Result;
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

    public String getUserId() {
        String userId = null;
        try {
            String token = request.getHeader(FieldStatusEnum.HEARD).substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            userId = claims.getId();
        } catch (Exception e) {
            return null;
        }
        return userId;
    }
}

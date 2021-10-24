package com.ferry.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 描述：应用上下文工具类
 */
public class HttpContextUtil {

    /**
     * 获取 request 请求
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    /**
     * 判断是否是 Ajax 请求类型
     *
     * @param req HttpServletRequest
     * @return boolean
     */
    public static boolean isAjax(HttpServletRequest req) {
        //判断是否为ajax请求，默认不是
        boolean isAjaxRequest = false;
        if (!StringUtils.isBlank(req.getHeader("x-requested-with")) && req.getHeader("x-requested-with").equals("XMLHttpRequest")) {
            isAjaxRequest = true;
        }
        return isAjaxRequest;
    }

    /**
     * 判断是否来自浏览器端发起的服务请求
     *
     * @param req
     * @return
     */
    public static boolean isFromBrowse(HttpServletRequest req) {
        //判断是否来自浏览器端发起的服务请求，默认不是
        return req.getHeader("User-Agent").startsWith("Mozilla");

    }
}

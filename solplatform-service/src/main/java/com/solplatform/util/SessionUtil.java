package com.solplatform.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取session
 *
 * @author sol
 * @create 2020-02-17  17:36
 */
public class SessionUtil {
    /**
     * 获取session
     *
     * @param sessionKkey
     * @return
     */
    public static String getSession(String sessionKkey) {
        // 获取userId
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes ();
        HttpServletRequest request = requestAttributes.getRequest ();
        String sessionValue = (String) request.getSession ().getAttribute (sessionKkey);
        return sessionValue;
    }
}

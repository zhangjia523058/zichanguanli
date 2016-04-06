/**
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved
 */
package com.baidu.fbu.asset.interceptor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.baidu.fbu.asset.constants.Constants.Base;
import com.baidu.fbu.common.model.ResultError;
import com.baidu.fbu.common.service.FormatService;
import com.baidu.fbu.common.util.DigestUtil;
import com.baidu.fbu.common.util.RemoteUtil;

/**
 * 权限拦截器
 * @author
 */
public class PermissionInterceptor implements HandlerInterceptor {
    /** log */
    private static final Logger LOG = LoggerFactory.getLogger(PermissionInterceptor.class);

    @Autowired
    private MappingJackson2HttpMessageConverter converter;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
       
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }

    /**
     * 错误处理.
     *
     * @param response 响应
     * @param errorCode 错误号
     * @param errorMsg 错误信息
     */
    private void fail(HttpServletRequest request, HttpServletResponse response, int errorCode, String errorMsg) {
        try {
            LOG.error(FormatService.logFormat("PermissionFail: errorCode=" + errorCode + ", errorMsg=" + errorMsg));

            // 判断前台是否为Ajax请求
            boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
            String ajaxFlag = null == request.getParameter("ajax") ? "false" : request.getParameter("ajax");
            boolean isAjax = ajax || ajaxFlag.equalsIgnoreCase("true");

            if (isAjax) { // 是Ajax请求就向前台返回json消息，不是则跳转到无权限页面
                response.setCharacterEncoding("UTF-8");
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                converter.write(new ResultError(errorCode, errorMsg), MediaType.APPLICATION_JSON,
                        new ServletServerHttpResponse(response));
            } else {
                String sendRedirectUrl = "/page/otherpages/permissions.html";
                switch (errorCode) {
                    case -1:
                        sendRedirectUrl = "/page/otherpages/500.html";
                        break;
                    case -2:
                        sendRedirectUrl = "/page/otherpages/permissions.html";
                        break;
                    case -3:
                        sendRedirectUrl = "/page/otherpages/500.html";
                        break;
                    case -4:
                        sendRedirectUrl = "/page/otherpages/permissions.html";
                        break;
                    case -5:
                        sendRedirectUrl = "/page/otherpages/500.html";
                        break;
                    case -6:
                        sendRedirectUrl = "/page/otherpages/500.html";
                        break;
                    case -7:
                        sendRedirectUrl = "/page/otherpages/permissions.html";
                        break;
                    case -8:
                        sendRedirectUrl = "/page/otherpages/500.html";
                        break;
                    case -9:
                        sendRedirectUrl = "/page/otherpages/500.html";
                        break;
                    case -10:
                        sendRedirectUrl = "/page/otherpages/500.html";
                        break;
                    case -11:
                        sendRedirectUrl = "/page/otherpages/500.html";
                        break;
                    case -12:
                        sendRedirectUrl = "/page/otherpages/permissions.html";
                        break;
                    case -13:
                        sendRedirectUrl = "/page/otherpages/500.html";
                        break;
                    default:
                        sendRedirectUrl = "/page/otherpages/permissions.html";
                        break;
                }
                response.sendRedirect(sendRedirectUrl);
            }
        } catch (Throwable e) {
            LOG.error(FormatService.logFormat("ajax error"), e);
        }
    }

}
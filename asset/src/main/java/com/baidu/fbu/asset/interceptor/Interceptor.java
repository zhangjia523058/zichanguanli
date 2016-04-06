/**
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.fbu.asset.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.baidu.fbu.common.service.CacheService;
import com.baidu.fbu.common.service.FormatService;
import com.baidu.fbu.common.service.FormatService.LOGSTEP;
import com.baidu.fbu.common.util.RequestUtil;

/** controller拦截器 */
public class Interceptor implements HandlerInterceptor {

    /**  log     */
    private static final Logger LOG = LoggerFactory.getLogger(Interceptor.class);

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) throws Exception {
        // UUAP跨域访问
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 请求返回状态设置，可设置成功，失败，默认，可选
        FormatService.accMark(LOGSTEP.SUCCESS);
        // 访问日志，每个请求仅可打印一次，且必须打印一次，必选
        LOG.info(FormatService.accFormat(RequestUtil.getRequestParams(request)));
        // 缓存信息清理，访问结束时执行，必选
        CacheService.destroy();
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // UUAP 跨域访问
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // UUAP 跨域访问
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 初始化日志服务，参数为请求方法，必选
        FormatService.init(request.getRequestURI());
        // 设置请求bid，可选
        CacheService.putBid(request.getRemoteUser());
        return true;
    }

}
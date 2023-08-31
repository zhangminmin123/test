//package com.syswin.systoon.police.web.interceptor;
//
//import com.alibaba.csp.sentinel.Entry;
//import com.syswin.systoon.framework.exception.BusinessException;
//import com.syswin.systoon.police.constant.ExceptionCodeConsts;
//import com.syswin.systoon.police.property.PoliceProperties;
//import com.syswin.systoon.police.util.RequestUtils;
//import com.syswin.systoon.police.util.SMUtils;
//import com.syswin.systoon.police.util.SignUtils;
//
//import java.util.HashMap;
//import java.util.Map;
//import javax.annotation.PostConstruct;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.syswin.systoon.police.util.sentinel.SentinelUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
///**
// * api接口拦截器，检验调用方的合法性
// *
// * @author xuhao
// * @date 2019/01/30 Copyright by zhengtoon
// */
//@Slf4j
//public class ApiInterceptor extends HandlerInterceptorAdapter {
//
//    @Autowired
//    private PoliceProperties policeProperties;
//
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
//        Map<String, String> params = RequestUtils.getRequestParameterMap(request);
//        log.error("开始请求接口");
//        String sign = params.get("sign");
//        String appId = params.get("appId");
//
//        if (StringUtils.isAnyBlank(appId, sign)) {
//            throw new BusinessException(ExceptionCodeConsts.PARAM_MISS_ERROR_CODE, "参数 appId, sign 不能为空", false);
//        }
//        if (!appId.equals(policeProperties.getAppId())) {
//            throw new BusinessException(ExceptionCodeConsts.PARAM_APP_ERROR_CODE, "应用信息错误", false);
//        }
//
//        params.remove("sign");
//        params.put("appSecret", policeProperties.getAppSecret());
//        String signContent = SignUtils.getSignContent(params);
//        String signSM3 = SMUtils.digestWithSM3(signContent);
//        if (sign.equalsIgnoreCase(signSM3)) {
//            return true;
//        }
//        throw new BusinessException(ExceptionCodeConsts.PARAM_SIGN_ERROR_CODE, "签名错误", false);
//    }
//
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//                           ModelAndView modelAndView) throws Exception {
//        super.postHandle(request, response, handler, modelAndView);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//            throws Exception {
//        super.afterCompletion(request, response, handler, ex);
//    }
//}
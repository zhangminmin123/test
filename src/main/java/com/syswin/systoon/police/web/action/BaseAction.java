package com.syswin.systoon.police.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * action基类
 */
public abstract class BaseAction {

//  /**
//   * 获取request
//   */
//  protected HttpServletRequest getRequest() throws BusinessException {
//
//    HttpServletRequest req = RequestThreadLocal.getRequest();
//    if (null == req) {
//      throw new BusinessException(CoreConstants.REQUEST_PROGRAM_ERROR_CODE, "获取request失败！", false);
//    }
//    return req;
//  }
//
//  /**
//   * 获取response
//   */
//  protected HttpServletResponse getResponse() throws BusinessException {
//
//    HttpServletResponse res = ResponseThreadLocal.getResponse();
//    if (null == res) {
//      throw new BusinessException(CoreConstants.REQUEST_PROGRAM_ERROR_CODE, "获取response失败！", false);
//    }
//    return res;
//  }

}

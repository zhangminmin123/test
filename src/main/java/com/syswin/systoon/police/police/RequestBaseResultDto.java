package com.syswin.systoon.police.police;


/**
 * 请求返回结果基础数据
 */
public class RequestBaseResultDto extends BaseAuthenticationDataDto {

  /**
   * 申请结果,字符串true表示服务访问成功,字符串false表示服务访问失败 最大长度:5bytes 范例:true/false
   */
  private String success;
  /**
   * 错误信息描述 最大长度:256bytes 范例:错误码：EU50003 ERROR INFO：用户签名验签失败
   */
  private String errorDesc;

  @Override
  public String toString() {
    return "RequestBaseResultDto{" +
        "success='" + success + '\'' +
        ", errorDesc='" + errorDesc + '\'' +
        '}' + super.toString();
  }

  public String getSuccess() {
    return success;
  }

  public void setSuccess(String success) {
    this.success = success;
  }

  public String getErrorDesc() {
    return errorDesc;
  }

  public void setErrorDesc(String errorDesc) {
    this.errorDesc = errorDesc;
  }
}

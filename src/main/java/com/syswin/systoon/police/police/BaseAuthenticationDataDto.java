package com.syswin.systoon.police.police;


/**
 * 身份认证基础数据信息
 */
public class BaseAuthenticationDataDto {

  /**
   * 应用名称 最大长度:32bytes
   */
  private String appName;
  /**
   * 时间戳,格式:YYYYMMddHHmmssSSS 最大长度:17bytes
   */
  private String timeStamp;

  @Override
  public String toString() {
    return "BaseAuthenticationDataDto{" +
        "appName='" + appName + '\'' +
        ", timeStamp='" + timeStamp + '\'' +
        '}';
  }

  public String getAppName() {
    return appName;
  }

  public void setAppName(String appName) {
    this.appName = appName;
  }

  public String getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
  }
}

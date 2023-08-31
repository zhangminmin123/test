package com.syswin.systoon.police.police;


/**
 * 身份认证请求业务参数
 */
public class AuthenticationResultDto extends RequestBaseResultDto {

  /**
   * 客户号 最大长度:16bytes
   */
  private String customNumber;
  /**
   * 申请获取的业务流水号 最大长度:18bytes 范例:000000000000000000
   */
  private String businessSerialNumber;
  /**
   * 认证结果 最大长度:4bytes 范例:0000
   */
  private String authResult;
  /**
   * 认证结果保留数据,其字符串可能为JSON 最大长度:250bytes 范例:{"rxfs":"800"}，不需要人像返回时为"0"
   */
  //TODO 返回结果需要仔细确认,什么情况返回什么值
  //当前不会返回,后期功能升级后会返回人像相似度的分数
  private String authResultRetainData;

  @Override
  public String toString() {
    return "AuthenticationResultDto{" +
        "customNumber='" + customNumber + '\'' +
        ", businessSerialNumber='" + businessSerialNumber + '\'' +
        ", authResult='" + authResult + '\'' +
        ", authResultRetainData='" + authResultRetainData + '\'' +
        '}' + super.toString();
  }

  public String getCustomNumber() {
    return customNumber;
  }

  public void setCustomNumber(String customNumber) {
    this.customNumber = customNumber;
  }

  public String getBusinessSerialNumber() {
    return businessSerialNumber;
  }

  public void setBusinessSerialNumber(String businessSerialNumber) {
    this.businessSerialNumber = businessSerialNumber;
  }

  public String getAuthResult() {
    return authResult;
  }

  public void setAuthResult(String authResult) {
    this.authResult = authResult;
  }

  public String getAuthResultRetainData() {
    return authResultRetainData;
  }

  public void setAuthResultRetainData(String authResultRetainData) {
    this.authResultRetainData = authResultRetainData;
  }
}

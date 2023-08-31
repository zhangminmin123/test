package com.syswin.systoon.police.police;

/**
 * 身份认证申请请求参数DTO
 */
public class AuthenticationApplicationResultDto extends RequestBaseResultDto {

  /**
   * 客户号 最大长度:16bytes
   */
  private String customerNumber;
  /**
   * 业务流水号 最大长度:18bytes 范例:000000000000000000
   */
  private String businessSerialNumber;
  /**
   * 随机数,返回的数据为base64编码串,客户端控件使用其进行激活时需要进行base64解码操作 最大长度:90bytes 范例:AYYXSGDHC…HHXXBX=
   */
  //TODO 使用方式待确认
  private String randomNumber;

  @Override
  public String toString() {
    return "AuthenticationApplicationResultDto{" +
        "customerNumber='" + customerNumber + '\'' +
        ", businessSerialNumber='" + businessSerialNumber + '\'' +
        ", randomNumber='" + randomNumber + '\'' +
        '}' + super.toString();
  }

  public String getCustomerNumber() {
    return customerNumber;
  }

  public void setCustomerNumber(String customerNumber) {
    this.customerNumber = customerNumber;
  }

  public String getBusinessSerialNumber() {
    return businessSerialNumber;
  }

  public void setBusinessSerialNumber(String businessSerialNumber) {
    this.businessSerialNumber = businessSerialNumber;
  }

  public String getRandomNumber() {
    return randomNumber;
  }

  public void setRandomNumber(String randomNumber) {
    this.randomNumber = randomNumber;
  }
}

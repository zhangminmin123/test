package com.syswin.systoon.police.police;


import org.apache.commons.lang3.StringUtils;

/**
 * 请求及返回结果基础信息
 */
public class BaseRequestAndResultDto<T> {

  /**
   * 请求详细数据集 最大长度:申请时:100bytes 请求时:50KB
   */
  //TODO 由于实际请求时,用户头像和保留数据的的最大长度均为40KB,故对于此参数的最大长度需要进行确认
  private T bizPackage;
  /**
   * 参数bizPackage签名值得base64编码串 最大长度:900bytes 范例:uUnKi5QdBsoZEAbMXVMmRWjsuUj+y48A2DvWAVVBuYkiBj13CFDHu2vZQvmOfkjE
   */
  private String sign;

  public boolean checkBlankParam() {
    return StringUtils.isBlank(sign) || bizPackage == null;
  }

  @Override
  public String toString() {
    return "BaseRequestAndResultDto{" +
        "bizPackage=" + bizPackage +
        ", sign='" + sign + '\'' +
        '}';
  }

  public T getBizPackage() {
    return bizPackage;
  }

  public void setBizPackage(T bizPackage) {
    this.bizPackage = bizPackage;
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }
}

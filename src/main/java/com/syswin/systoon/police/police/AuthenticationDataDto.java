package com.syswin.systoon.police.police;

/**
 * 身份认证请求业务参数
 */
public class AuthenticationDataDto extends BaseAuthenticationDataDto {

  /**
   * 客户号 最大长度:16bytes
   */
  private String customNumber;
  /**
   * 申请获取的业务流水号 最大长度:18bytes 范例:000000000000000000
   */
  private String businessSerialNumber;
  /**
   * 认证模式,需要和申请时所传的模式一致 最大长度:4bytes 范例：0x40
   */
  private String authMode;
  /**
   * 照片base64编码串,非必传参数,人像对比时,在此处传递头像 最大长度:40KB 范例:/9j/4AAQSkZJRgA……BAQAAAQAB
   */
  //TODO 需对此数据大小进行确认
  private String photoData;
  /**
   * 认证码数据,非必传参数 最大长度:1KB 范例:wgFNSUlCUndZ......S0tvRAAAAVH
   */
  //TODO 获取方式待确认
  private String authCode;
  /**
   * ID验证数据,非必传参数 最大长度:1KB 范例:TUlJQjFnWUtLb0......jFVR0FRUNBN
   */
  //TODO 获取方式待确认
  private String idcardAuthData;
  /**
   * 认证保留数据加密后的base64编码串,非必传参数 最大长度:40KB 范例:TUlJQjFnWUtLb0..........AGEVjejFV
   */
  private String authApplyRetainData;

  @Override
  public String toString() {
    return "AuthenticationDataDto{" +
        "customNumber='" + customNumber + '\'' +
        ", businessSerialNumber='" + businessSerialNumber + '\'' +
        ", authMode='" + authMode + '\'' +
        ", photoData='" + photoData + '\'' +
        ", authCode='" + authCode + '\'' +
        ", idcardAuthData='" + idcardAuthData + '\'' +
        ", authApplyRetainData='" + authApplyRetainData + '\'' +
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

  public String getAuthMode() {
    return authMode;
  }

  public void setAuthMode(String authMode) {
    this.authMode = authMode;
  }

  public String getPhotoData() {
    return photoData;
  }

  public void setPhotoData(String photoData) {
    this.photoData = photoData;
  }

  public String getAuthCode() {
    return authCode;
  }

  public void setAuthCode(String authCode) {
    this.authCode = authCode;
  }

  public String getIdcardAuthData() {
    return idcardAuthData;
  }

  public void setIdcardAuthData(String idcardAuthData) {
    this.idcardAuthData = idcardAuthData;
  }

  public String getAuthApplyRetainData() {
    return authApplyRetainData;
  }

  public void setAuthApplyRetainData(String authApplyRetainData) {
    this.authApplyRetainData = authApplyRetainData;
  }
}

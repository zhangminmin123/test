package com.syswin.systoon.police.police;

/**
 * 身份认证申请请求参数DTO
 */
public class AuthenticationApplicationDataDto extends BaseAuthenticationDataDto {

  /**
   * 客户号 最大长度:16bytes
   */
  private String customerNumber;
  /**
   * 读卡控件版本 最大长度:19bytes 范例:0501.0001.0101.000
   */
  private String cardReaderVersion;
  /**
   * 活体控件版本 最大长度:19bytes 范例:0501.0001.0101.000
   */
  private String liveDetectionControlVersion;
  /**
   * 认证码控件版本 最大长度:19bytes 范例:0501.0001.0101.000
   */
  private String authCodeControlVersion;
  /**
   * 认证模式,申请通过后,发起认证请求时的认证模式必须与此处一致 最大长度:4bytes 范例：0x40
   */
  private String authMode;

  @Override
  public String toString() {
    return "AuthenticationApplicationDataDto{" +
        "customerNumber='" + customerNumber + '\'' +
        ", cardReaderVersion='" + cardReaderVersion + '\'' +
        ", liveDetectionControlVersion='" + liveDetectionControlVersion + '\'' +
        ", authCodeControlVersion='" + authCodeControlVersion + '\'' +
        ", authMode='" + authMode + '\'' +
        '}' + super.toString();
  }

  public String getCustomerNumber() {
    return customerNumber;
  }

  public void setCustomerNumber(String customerNumber) {
    this.customerNumber = customerNumber;
  }

  public String getCardReaderVersion() {
    return cardReaderVersion;
  }

  public void setCardReaderVersion(String cardReaderVersion) {
    this.cardReaderVersion = cardReaderVersion;
  }

  public String getLiveDetectionControlVersion() {
    return liveDetectionControlVersion;
  }

  public void setLiveDetectionControlVersion(String liveDetectionControlVersion) {
    this.liveDetectionControlVersion = liveDetectionControlVersion;
  }

  public String getAuthCodeControlVersion() {
    return authCodeControlVersion;
  }

  public void setAuthCodeControlVersion(String authCodeControlVersion) {
    this.authCodeControlVersion = authCodeControlVersion;
  }

  public String getAuthMode() {
    return authMode;
  }

  public void setAuthMode(String authMode) {
    this.authMode = authMode;
  }
}

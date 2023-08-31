package com.syswin.systoon.police.police;

/**
 * 认证保留数据
 */
public class AuthApplyRetainDataDto {

  /**
   * 身份信息
   */
  private ShengFengXinXiDto sFXX;
  /**
   * 门户网站信息
   */
  private WangZhanXinXiDto wZXX;
  /**
   * 照片信息
   */
  private ZhaoPianDto zP;

  public AuthApplyRetainDataDto() {
  }

  public AuthApplyRetainDataDto(ShengFengXinXiDto sFXX, WangZhanXinXiDto wZXX, ZhaoPianDto zP) {
    this.sFXX = sFXX;
    this.wZXX = wZXX;
    this.zP = zP;
  }

  @Override
  public String toString() {
    return "AuthApplyRetainDataDto{" +
        "sFXX=" + sFXX +
        ", wZXX=" + wZXX +
        ", zP=" + zP +
        '}';
  }

  public ShengFengXinXiDto getsFXX() {
    return sFXX;
  }

  public void setsFXX(ShengFengXinXiDto sFXX) {
    this.sFXX = sFXX;
  }

  public WangZhanXinXiDto getwZXX() {
    return wZXX;
  }

  public void setwZXX(WangZhanXinXiDto wZXX) {
    this.wZXX = wZXX;
  }

  public ZhaoPianDto getzP() {
    return zP;
  }

  public void setzP(ZhaoPianDto zP) {
    this.zP = zP;
  }
}

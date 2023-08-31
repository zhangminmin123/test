package com.syswin.systoon.police.police;

/**
 * 照片
 */
public class ZhaoPianDto {

  /**
   * ?照片
   */
  //TODO 此照片参数含义未知,参数传递条件未知
  private String wLTZP;

  public ZhaoPianDto() {
  }

  public ZhaoPianDto(String wLTZP) {
    this.wLTZP = wLTZP;
  }

  @Override
  public String toString() {
    return "ZhaoPianDto{" +
        "wLTZP='" + wLTZP + '\'' +
        '}';
  }

  public String getwLTZP() {
    return wLTZP;
  }

  public void setwLTZP(String wLTZP) {
    this.wLTZP = wLTZP;
  }
}

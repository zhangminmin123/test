package com.syswin.systoon.police.police;

/**
 * 身份信息
 */
//TODO 缺少实际参数范例
public class ShengFengXinXiDto {

  /**
   * 姓名
   */
  private String xM;
  /**
   * 公民身份证号
   */
  private String gMSFZHM;
  /**
   * 有效截止日期
   */
  private String yXQJZRQ;
  /**
   * 有效起始日期
   */
  private String yXQQSRQ;
  /**
   * DN信息
   */
  //TODO 此信息内容待确认
  private String dN;
  /**
   * 民族代码
   */
  //TODO 缺少对应代码规则
  private String mZDM;
  /**
   * 签发机关
   */
  private String qFJG;
  /**
   * 性别代码
   */
  //TODO 缺少对应代码规则
  private String xBDM;
  /**
   * 出生日期
   */
  //TODO 缺少对应范例格式
  private String cSRQ;
  /**
   * 住址
   */
  private String zZ;

  public ShengFengXinXiDto() {
  }

  public ShengFengXinXiDto(
      String xM, String gMSFZHM, String yXQJZRQ, String yXQQSRQ, String dN, String mZDM, String qFJG, String xBDM,
      String cSRQ, String zZ) {
    this.xM = xM;
    this.gMSFZHM = gMSFZHM;
    this.yXQJZRQ = yXQJZRQ;
    this.yXQQSRQ = yXQQSRQ;
    this.dN = dN;
    this.mZDM = mZDM;
    this.qFJG = qFJG;
    this.xBDM = xBDM;
    this.cSRQ = cSRQ;
    this.zZ = zZ;
  }

  @Override
  public String toString() {
    return "ShengFengXinXiDto{" +
        "xM='" + xM + '\'' +
        ", gMSFZHM='" + gMSFZHM + '\'' +
        ", yXQJZRQ='" + yXQJZRQ + '\'' +
        ", yXQQSRQ='" + yXQQSRQ + '\'' +
        ", dN='" + dN + '\'' +
        ", mZDM='" + mZDM + '\'' +
        ", qFJG='" + qFJG + '\'' +
        ", xBDM='" + xBDM + '\'' +
        ", cSRQ='" + cSRQ + '\'' +
        ", zZ='" + zZ + '\'' +
        '}';
  }

  public String getxM() {
    return xM;
  }

  public void setxM(String xM) {
    this.xM = xM;
  }

  public String getgMSFZHM() {
    return gMSFZHM;
  }

  public void setgMSFZHM(String gMSFZHM) {
    this.gMSFZHM = gMSFZHM;
  }

  public String getyXQJZRQ() {
    return yXQJZRQ;
  }

  public void setyXQJZRQ(String yXQJZRQ) {
    this.yXQJZRQ = yXQJZRQ;
  }

  public String getyXQQSRQ() {
    return yXQQSRQ;
  }

  public void setyXQQSRQ(String yXQQSRQ) {
    this.yXQQSRQ = yXQQSRQ;
  }

  public String getdN() {
    return dN;
  }

  public void setdN(String dN) {
    this.dN = dN;
  }

  public String getmZDM() {
    return mZDM;
  }

  public void setmZDM(String mZDM) {
    this.mZDM = mZDM;
  }

  public String getqFJG() {
    return qFJG;
  }

  public void setqFJG(String qFJG) {
    this.qFJG = qFJG;
  }

  public String getxBDM() {
    return xBDM;
  }

  public void setxBDM(String xBDM) {
    this.xBDM = xBDM;
  }

  public String getcSRQ() {
    return cSRQ;
  }

  public void setcSRQ(String cSRQ) {
    this.cSRQ = cSRQ;
  }

  public String getzZ() {
    return zZ;
  }

  public void setzZ(String zZ) {
    this.zZ = zZ;
  }
}

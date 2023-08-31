package com.syswin.systoon.police.police;

/**
 * 门户网站信息
 */
public class WangZhanXinXiDto {

  /**
   * 门户网站
   */
  //TODO 缺少对应范例,无法确定门户网站这一参数实际参数值
  private String businessType;
  /**
   * 处理时间? 范例:20171009
   */
  private String dealDate;
  /**
   * 网站名称 范例:www.chnctid.cn
   */
  private String venderName;
  /**
   * 网站IP
   */
  //TODO 需要确认此参数是否支持传入域名,以及是否支持多级域名
  private String vendorIp;

  public WangZhanXinXiDto() {
  }

  public WangZhanXinXiDto(String businessType, String dealDate, String venderName, String vendorIp) {
    this.businessType = businessType;
    this.dealDate = dealDate;
    this.venderName = venderName;
    this.vendorIp = vendorIp;
  }

  @Override
  public String toString() {
    return "WangZhanXinXiDto{" +
        "businessType='" + businessType + '\'' +
        ", dealDate='" + dealDate + '\'' +
        ", venderName='" + venderName + '\'' +
        ", vendorIp='" + vendorIp + '\'' +
        '}';
  }

  public String getBusinessType() {
    return businessType;
  }

  public void setBusinessType(String businessType) {
    this.businessType = businessType;
  }

  public String getDealDate() {
    return dealDate;
  }

  public void setDealDate(String dealDate) {
    this.dealDate = dealDate;
  }

  public String getVenderName() {
    return venderName;
  }

  public void setVenderName(String venderName) {
    this.venderName = venderName;
  }

  public String getVendorIp() {
    return vendorIp;
  }

  public void setVendorIp(String vendorIp) {
    this.vendorIp = vendorIp;
  }
}

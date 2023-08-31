package com.syswin.systoon.police.web.param;

import lombok.Data;

/**
 * 认证参数类
 *
 * @Author xuhao
 * @Create 2019/01/29 15:36 Copyright by syswin
 */
@Data
public class CertBizParam {

  private String certName;
  private String certNo;
  private String validDateStart;
  private String validDateEnd;
  private String photo;
}

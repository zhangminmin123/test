package com.syswin.systoon.police.service;

import com.syswin.systoon.police.police.AuthenticationResultDto;

/**
 * 认证接口
 *
 * @Author xuhao
 * @Create 2019/01/29 16:10 Copyright by syswin
 */
public interface ICertService {

	/**
	 * 姓名身份证号校验
	 */
	AuthenticationResultDto checkCard(String certName, String certNo) throws Exception;

	/**
	 * 姓名身份证号+活体照片校验
	 */
	AuthenticationResultDto checkFace(String certName, String certNo, String photo) throws Exception;

	/**
	 * 姓名身份证号起止时间四项校验
	 */
	AuthenticationResultDto checkCardAndDate(String certName, String certNo, String validDateStart, String validDateEnd) throws Exception;

	/**
	 * 姓名身份证号起止时间四项+人脸校验
	 */
	AuthenticationResultDto checkFaceAndDate(String certName, String certNo, String validDateStart, String validDateEnd, String photo) throws Exception;
}

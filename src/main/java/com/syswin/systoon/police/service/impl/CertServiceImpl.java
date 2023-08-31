//package com.syswin.systoon.police.service.impl;
//
//import com.alibaba.fastjson.JSONObject;
//import com.syswin.systoon.police.service.ICertService;
//import com.syswin.systoon.police.util.HttpClientUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import sun.misc.BASE64Encoder;
//
//import java.nio.charset.StandardCharsets;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * 认证实现类
// *
// * @Author xuhao
// * @Create 2019/01/29 16:11 Copyright by syswin
// */
//@Slf4j
//@Service
//public class CertServiceImpl implements ICertService {
//
//	private BASE64Encoder be = new BASE64Encoder();
//
//	@Autowired
//	private PoliceProperties policeProperties;
//	@Autowired
//	private ILocalImageFile localImageFile;
//
//	@Override
//	public AuthenticationResultDto checkCard(String certName, String certNo) throws Exception {
//		return doPoliceCert(policeProperties.getCardAuthMode(), certName, certNo, null, null, "");
//	}
//
//	@Override
//	public AuthenticationResultDto checkFace(String certName, String certNo, String photo) throws Exception {
//		photo = compressPhoto(photo);
//		return doPoliceCert(policeProperties.getFaceAuthMode(), certName, certNo, null, null, photo);
//	}
//
//	@Override
//	public AuthenticationResultDto checkCardAndDate(String certName, String certNo, String validDateStart, String validDateEnd) throws Exception {
//		return doPoliceCert(policeProperties.getCardDateAuthMode(), certName, certNo, validDateStart, validDateEnd, null);
//	}
//
//	@Override
//	public AuthenticationResultDto checkFaceAndDate(String certName, String certNo, String validDateStart, String validDateEnd, String photo) throws Exception {
//		photo = compressPhoto(photo);
//		return doPoliceCert(policeProperties.getFaceDateAuthMode(), certName, certNo, validDateStart, validDateEnd, photo);
//	}
//
//	private AuthenticationResultDto doPoliceCert(String authMode, String certName, String certNo, String validDateStart, String validDateEnd, String photo)
//			throws Exception {
//		//构造【申请】请求参数信息
//		BaseRequestAndResultDto<AuthenticationApplicationDataDto> applicationRequestData = buildApplicationRequestData(authMode);
//		//发起身份认证【申请】请求,并提取返回结果数据
//
//		AuthenticationApplicationResultDto authenticationApplicationResultDto = requestApplicationApi(applicationRequestData);
//
//
//		//获取业务流水号
//		String businessSerialNumber = authenticationApplicationResultDto.getBusinessSerialNumber();
//		//构造身份认证请求参数
//		BaseRequestAndResultDto<AuthenticationDataDto> authRequestData = buildAuthRequestData(businessSerialNumber, photo, certName, certNo, validDateStart, validDateEnd, authMode);
//		//发起身份认证【请求】
//
//		AuthenticationResultDto authenticationResultDto = requestAuthApi(authRequestData);
//
//		if(log.isInfoEnabled()){
//          log.info("++-------------------++请求结果++-------------------++");
//          log.info(JSONObject.toJSONString(authenticationResultDto));
//        }
//		return authenticationResultDto;
//	}
//
//	/**
//	 * 构造身份认证申请请求参数信息
//	 *
//	 * @return 身份认证申请请求参数信息
//	 */
//	private BaseRequestAndResultDto<AuthenticationApplicationDataDto> buildApplicationRequestData(String authMode)
//			throws Exception {
//		AuthenticationApplicationDataDto authenticationApplicationDataDto = buildAuthApplicationRequestData(authMode);
//		BaseRequestAndResultDto<AuthenticationApplicationDataDto> applicationRequestData = new BaseRequestAndResultDto<>();
//		applicationRequestData.setBizPackage(authenticationApplicationDataDto);
//		//身份认证申请业务数据
//		String applyDataJson = JSONObject.toJSONString(authenticationApplicationDataDto);
//		//身份认证申请签名
//		applicationRequestData.setSign(signData(applyDataJson));
//
//		return applicationRequestData;
//	}
//
//	/**
//	 * 构造身份认证申请请求数据
//	 *
//	 * @return 身份认证申请请求数据
//	 */
//	private AuthenticationApplicationDataDto buildAuthApplicationRequestData(String authMode) {
//		AuthenticationApplicationDataDto authenticationApplicationDataDto = new AuthenticationApplicationDataDto();
//		authenticationApplicationDataDto.setCustomerNumber(policeProperties.getCustomNumber());
//		authenticationApplicationDataDto.setAppName(policeProperties.getAppName());
//		authenticationApplicationDataDto.setTimeStamp(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
//		authenticationApplicationDataDto.setCardReaderVersion(policeProperties.getCardReaderVersion());
//		authenticationApplicationDataDto.setLiveDetectionControlVersion(policeProperties.getLiveDetectionControlVersion());
//		authenticationApplicationDataDto.setAuthCodeControlVersion(policeProperties.getAuthCodeControlVersion());
//		authenticationApplicationDataDto.setAuthMode(authMode);
//
//		return authenticationApplicationDataDto;
//	}
//
//	/**
//	 * 对数据进行签名
//	 *
//	 * @param waitSignData 待签名数据
//	 * @return 数据签名
//	 */
//	private String signData(String waitSignData) throws Exception {
//		log.debug("++-------------------++签名入参++-------------------++");
//		log.debug(waitSignData);
//		log.debug("++-------------------++--------++-------------------++");
//		String sign = null;
//		try {
//			//北京CA签名服务器签名
//			sign = DataSignature.signDataByP7DetachForJit(waitSignData.getBytes(StandardCharsets.UTF_8));
//		} catch (Exception e) {
//			log.error("使用BJCA签名对请求参数进行签名时发生异常,异常信息为:" + e.getMessage(), e);
//			throw new Exception("使用BJCA签名对请求参数进行签名时发生异常:" + e.getMessage());
//		}
//		if (StringUtils.isBlank(sign)) {
//			log.error("使用BJCA签名对请求参数签名失败,待签名信息为:" + waitSignData);
//			throw new Exception("使用BJCA签名对请求参数签名失败");
//		}
//		log.debug("++-------------------++签名结果++-------------------++");
//		log.debug(sign);
//		log.debug("++-------------------++--------++-------------------++");
//		return sign;
//	}
//
//	/**
//	 * 发起身份认证申请请求
//	 *
//	 * @param applicationRequestData 身份认证申请请求参数
//	 * @return 请求返回结果
//	 */
//	private AuthenticationApplicationResultDto requestApplicationApi(
//			BaseRequestAndResultDto<AuthenticationApplicationDataDto> applicationRequestData) throws Exception {
//		String applicationResultData = null;
//		try {
//			applicationResultData = HttpClientUtils
//					.doPostJson("http://MA1.chnctid.cn:8100/ctid/api/v1/request", JSONObject.toJSONString(applicationRequestData), null);
//		} catch (Exception e) {
//			log.error("发生身份认证申请请求时发生异常,请求地址为:" + policeProperties.getRequestUrl()
//					+ "\n请求参数为:" + JSONObject.toJSONString(applicationRequestData)
//					+ "\n异常信息为:" + e.getMessage(), e);
//			throw new RuntimeException("发生身份认证申请请求时发生异常:" + e.getMessage());
//		}
//		if (StringUtils.isBlank(applicationResultData)) {
//			log.error("发生身份认证申请请求时出现错误,请求返回结果为空,请求地址为:"
//					+ policeProperties.getRequestUrl() + "\n请求参数为:" + JSONObject.toJSONString(applicationRequestData));
//			throw new RuntimeException("发生身份认证申请请求时出现错误,请求返回结果为空");
//		}
//		BaseRequestAndResultDto<AuthenticationApplicationResultDto> applicationResult =
//				JSONObject.parseObject(
//						applicationResultData,
//						new TypeReference<BaseRequestAndResultDto<AuthenticationApplicationResultDto>>() {
//						});
//		if (applicationResult == null || applicationResult.checkBlankParam()) {
//			log.error("发生身份认证申请请求时出现错误,请求返回结果转化结果关键参数为空,请求地址为:"
//					+ policeProperties.getRequestUrl() + "\n请求参数为:" + JSONObject.toJSONString(applicationRequestData)
//					+ "\n请求返回结果为:" + applicationResultData);
//			throw new RuntimeException("发生身份认证申请请求时出现错误,请求返回结果转化结果关键参数为空");
//		}
//		if (!"true".equalsIgnoreCase(applicationResult.getBizPackage().getSuccess())) {
//			log.error("发生身份认证申请请求时接口返回请求失败,请求地址为:"
//					+ policeProperties.getRequestUrl() + "\n请求参数为:" + JSONObject.toJSONString(applicationRequestData)
//					+ "\n请求返回结果为:" + applicationResultData);
//		}
//		return applicationResult.getBizPackage();
//	}
//
//	/**
//	 * 构造身份认证请求参数信息
//	 *
//	 * @return 身份认证请求参数信息
//	 */
//	private BaseRequestAndResultDto<AuthenticationDataDto> buildAuthRequestData(String businessSerialNumber,
//																				String photo, String name, String certNo, String validDateStart, String validDateEnd, String authMode) throws Exception {
//		AuthenticationDataDto authenticationDataDto = buildAuthenticationRequestData(photo, authMode);
//		authenticationDataDto.setBusinessSerialNumber(businessSerialNumber);
//		String authApplyRetainData = buildAuthApplyRetainData(name, certNo, validDateStart, validDateEnd);
//		authenticationDataDto.setAuthApplyRetainData(authApplyRetainData);
//
//		BaseRequestAndResultDto<AuthenticationDataDto> authRequestData = new BaseRequestAndResultDto<>();
//		authRequestData.setBizPackage(authenticationDataDto);
//		//身份认证请求业务数据
//		String applyDataJson = JSONObject.toJSONString(authenticationDataDto);
//		//身份认证请求数据签名
//		authRequestData.setSign(signData(applyDataJson));
//
//		return authRequestData;
//	}
//
//	/**
//	 * 构造身份认证请求基础数据
//	 *
//	 * @return 身份认证请求数据
//	 */
//	private AuthenticationDataDto buildAuthenticationRequestData(String photo, String authMode) {
//		AuthenticationDataDto authenticationDataDto = new AuthenticationDataDto();
//		authenticationDataDto.setCustomNumber(policeProperties.getCustomNumber());
//		authenticationDataDto.setAppName(policeProperties.getAppName());
//		authenticationDataDto.setTimeStamp(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
//		authenticationDataDto.setAuthMode(authMode);
//		if(StringUtils.isNotBlank(photo)){
//          authenticationDataDto.setPhotoData(photo);
//        }
//		authenticationDataDto.setAuthCode(policeProperties.getAuthCode());
//		authenticationDataDto.setIdcardAuthData(policeProperties.getIdcardAuthData());
//
//		return authenticationDataDto;
//	}
//
//	/**
//	 * 构架身份认证保留数据加密bas64串
//	 *
//	 * @return 身份认证保留数据加密base64串
//	 */
//	private String buildAuthApplyRetainData(String name, String certNo, String validDateStart, String validDateEnd) throws Exception {
//		AuthApplyRetainDataDto authApplyRetainDataDto = buildAuthApplyRetainObjectData(name, certNo, validDateStart, validDateEnd);
//		String authApplyRetainDataDtoJsonStr = JSONObject.toJSONString(authApplyRetainDataDto);
//		log.debug("++-------------------++保留数据加密前++-------------------++");
//		log.debug("待加密的身份认证保留信息为:" + authApplyRetainDataDtoJsonStr);
//		log.debug("++-------------------++--------------++-------------------++");
//		byte[] encDataByteArray = null;
//		try {
//			encDataByteArray =
//					EncryptedReservedData.encodeEnvelopedDataForJit(
//							authApplyRetainDataDtoJsonStr.getBytes(StandardCharsets.UTF_8));
//		} catch (Exception e) {
//			log.error("加密身份认证保留数据:" + authApplyRetainDataDtoJsonStr + "时发生异常,异常信息为:"
//					+ e.getMessage(), e);
//			throw e;
//		}
//		if (encDataByteArray == null) {
//			log.error("加密身份认证保留数据:" + authApplyRetainDataDtoJsonStr + "失败,加密结果为空!");
//			throw new RuntimeException("加密身份认证保留数据失败,加密结果为空!");
//		}
//		//TODO 目前对保留数据加密后使用的仍是JDK自带的base64编码包,编号时会进行分块编码,分块结果之间会多一个/r/n
//		// 且编码结果为url不安全的,但这样的编码结果才是对方需要的，否则调用接口时会返回“系统错误”的错误信息
//		String authApplyRetainData = be.encode(encDataByteArray);
//		if (StringUtils.isBlank(authApplyRetainData)) {
//			log.error("加密身份认证保留数据:" + authApplyRetainDataDtoJsonStr
//					+ "失败,加密后转换为base64结果为空!");
//		}
//		log.debug("++-------------------++加密保留结束++-------------------++");
//		log.debug("加密结果:" + authApplyRetainData);
//		log.debug("++-------------------++------------++-------------------++");
//		return authApplyRetainData;
//	}
//
//	/**
//	 * 构造认证保留数据信息
//	 *
//	 * @return 认证保留数据
//	 */
//	private AuthApplyRetainDataDto buildAuthApplyRetainObjectData(String name, String certNo, String validDateStart, String validDateEnd) {
//		ShengFengXinXiDto sFXX = new ShengFengXinXiDto();
//		sFXX.setxM(name);
//		sFXX.setgMSFZHM(certNo);
//		if (StringUtils.isNoneBlank(validDateStart, validDateEnd)) {
//			sFXX.setyXQQSRQ(validDateStart);
//			sFXX.setyXQJZRQ(validDateEnd);
//		} else {
//			sFXX.setyXQQSRQ("");
//			sFXX.setyXQJZRQ("");
//		}
//		//其他信息均暂不做校验，传空字符串
//		sFXX.setdN("");
//		sFXX.setmZDM("");
//		sFXX.setqFJG("");
//		sFXX.setxBDM("");
//		sFXX.setcSRQ("");
//		sFXX.setzZ("");
//
//		WangZhanXinXiDto wZXX = new WangZhanXinXiDto();
//		wZXX.setBusinessType(policeProperties.getWzBusinessType());
//		wZXX.setDealDate(policeProperties.getWzDealDate());
//		wZXX.setVenderName(policeProperties.getWzVenderName());
//		wZXX.setVendorIp(policeProperties.getWzVenderIp());
//
//		ZhaoPianDto zP = new ZhaoPianDto();
//		zP.setwLTZP(policeProperties.getWltzp());
//
//		return new AuthApplyRetainDataDto(sFXX, wZXX, zP);
//	}
//
//	/**
//	 * 发起身份认证请求
//	 *
//	 * @param authRequestData 身份认证请求参数
//	 * @return 请求返回结果
//	 */
//	private AuthenticationResultDto requestAuthApi(
//			BaseRequestAndResultDto<AuthenticationDataDto> authRequestData) {
//		String authResultData = null;
//		try {
//			authResultData = HttpClientUtils.doPostJson(policeProperties.getAuthUrl(), JSONObject.toJSONString(authRequestData), null);
//		} catch (Exception e) {
//			log.error("发生身份认证请求时发生异常,请求地址为:" + policeProperties.getAuthUrl()
//					+ "\n请求参数为:" + JSONObject.toJSONString(authRequestData)
//					+ "\n异常信息为:" + e.getMessage(), e);
//			throw new RuntimeException("发生身份认证请求时发生异常:" + e.getMessage());
//		}
//		if (StringUtils.isBlank(authResultData)) {
//			log.error("发生身份认证请求时出现错误,请求返回结果为空,请求地址为:"
//					+ policeProperties.getAuthUrl() + "\n请求参数为:" + JSONObject.toJSONString(authRequestData));
//			throw new RuntimeException("发生身份认证请求时出现错误,请求返回结果为空");
//		}
//		BaseRequestAndResultDto<AuthenticationResultDto> authResult =
//				JSONObject.parseObject(
//						authResultData,
//						new TypeReference<BaseRequestAndResultDto<AuthenticationResultDto>>() {
//						});
//		if (authResult == null || authResult.checkBlankParam()) {
//			log.error("发生身份认证请求时出现错误,请求返回结果转化结果关键参数为空,请求地址为:"
//					+ policeProperties.getAuthUrl() + "\n请求参数为:" + JSONObject.toJSONString(authRequestData)
//					+ "\n请求返回结果为:" + authResultData);
//			throw new RuntimeException("发生身份认证请求时出现错误,请求返回结果转化结果关键参数为空");
//		}
//		if (!"true".equalsIgnoreCase(authResult.getBizPackage().getSuccess())) {
//			log.error("发生身份认证请求时接口返回请求失败,请求地址为:"
//					+ policeProperties.getAuthUrl() + "\n请求参数为:" + JSONObject.toJSONString(authRequestData)
//					+ "\n请求返回结果为:" + authResultData);
//		}
//		return authResult.getBizPackage();
//	}
//
//	/**
//	 * 压缩图片
//	 *
//	 * @param photo 照片
//	 * @return 压缩后的photo
//	 * @throws Exception
//	 */
//	private String compressPhoto(String photo) throws Exception {
//		if (StringUtils.isNotBlank(photo) && photo.length() > 100 * 1024) {
//			//100K以上的图片压缩
//			String imagePath = localImageFile.compressToLocal(photo);
//			if (StringUtils.isBlank(imagePath)) {
//				throw new BusinessException("图片压缩发生异常", true);
//			}
//			photo = ImageUtils.getImageBinary(imagePath);
//			localImageFile.deleteLocalFile(imagePath);
//		}
//		return photo;
//	}
//}

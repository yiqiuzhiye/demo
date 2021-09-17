package com.demo.xyz.common.core.constant;

/**
 * 错误码父类，定义系统错误码以及各服务的起始错误编码，服务错误码应用不能直接使用，仅供框架异常处理，各微服务需要继承ApiRetCodeEnum,自定义错误码
 * 
 * @see UpmsApiRetCodeEnum
 * @see OAuthApiRetCodeEnum
 * @author charles
 *
 */

public class ApiRetCodeEnum {

	// *************************************通用错误码定义*****************************************************************//

	public final static int CODE_SUCCESS = 0;

	public final static String MESSAGE_SUCCESS = "success";

	public final static int CODE_EXCEPTION = 1;


	public final static String MESSAGE_EXCEPTION = "fail";

	// 数据违反唯一约束
	public final static int CODE_DATA_INTEGRITY_VIOLATION_EXCEPTION = 2;

	public final static int CODE_NO_PERMISSION = 3;

	public final static String MESSAGE_NO_PERMISSION = "operate.no-permission";

	// *************************************服务模块起始错误码定义********************************************************************//
	// 格式：CODE_${spring.application.name}.toUpperCase().replace("crss-","").replace("-","_")_EXCEPTION
	//
	// ************************************************************************************************************************//

	/*
	 * crss-openplatform错误码起始值
	 */
	public final static int CODE_OPENPLATFORM_EXCEPTION = 100000;
	/*
	 * crss-oauth错误码起始值
	 */
	public final static int CODE_AUTH_EXCEPTION = 101000;
	/**
	 * crss-upms错误码起始值
	 */
	public final static int CODE_UPMS_EXCEPTION = 102000;
	/**
	 * crss-device-api错误码起始值
	 */
	public final static int CODE_DEVICE_API_EXCEPTION = 103000;
	/**
	 * crss-ucms错误码起始值
	 */
	public final static int CODE_UCMS_EXCEPTION = 104000;
	/**
	 * crss-cvms错误码起始值
	 */
	public final static int CODE_CVMS_EXCEPTION = 105000;
	/**
	 * crss-cxms错误码起始值
	 */
	public final static int CODE_CXMS_EXCEPTION = 106000;
	/**
	 * crss-usms错误码起始值
	 */
	public final static int CODE_USMS_EXCEPTION = 107000;
	/**
	 * crss-mcms错误码起始值
	 */
	public final static int CODE_MCMS_EXCEPTION = 108000;
	/**
	 * crss-mgms错误码起始值
	 */
	public final static int CODE_MGMS_EXCEPTION = 109000;
	/**
	 * crss-cdms错误码起始值
	 */
	public final static int CODE_CDMS_EXCEPTION = 110000;
	/**
	 * crss-dbms错误码起始值
	 */
	public final static int CODE_DBMS_EXCEPTION = 111000;
	/**
	 * crss-grpc-server错误码起始值
	 */
	public final static int CODE_GRPC_SERVER_EXCEPTION = 112000;
	/**
	 * crss-grpc-client错误码起始值
	 */
	public final static int CODE_GRPC_CLIENT_EXCEPTION = 113000;
	/**
	 * crss-mams错误码起始值
	 */
	public final static int CODE_MAMS_EXCEPTION = 114000;

	/**
	 * crss-qa错误码起始值
	 */
	public final static int CODE_QA_EXCEPTION = 121000;

	/**
	 * crss-task错误码起始值
	 */
	public final static int CODE_TASK_API_EXCEPTION = 122000;

	/**
	 * crss-reception错误码起始值
	 */
	public final static int CODE_RECEPTION_EXCEPTION = 123000;

	/**
	 * crss-cds 错误码起始值
	 */
	public final static int CODE_CDS_EXCEPTION = 124000;


	/**
	 * crss-elevator 错误码起始值
	 */
	public final static int CODE_ELEVATOR_EXCEPTION = 125000;


	/**
	 * crss-uces错误码起始值
	 */
	public final static int CODE_UCES_EXCEPTION = 201000;

	/**
	 * crss-uses错误码起始值
	 */
	public final static int CODE_USES_EXCEPTION = 202000;
	/**
	 * crss-cves错误码起始值
	 */
	public final static int CODE_CVES_EXCEPTION = 203000;

	/**
	 * crss-cves错误码起始值
	 */
	public final static int CODE_MESSAGE_EXCEPTION = 204000;
	/**
	 * crss-map-api错误码起始值
	 */
	public final static int CODE_MAP_API = 205000;

}

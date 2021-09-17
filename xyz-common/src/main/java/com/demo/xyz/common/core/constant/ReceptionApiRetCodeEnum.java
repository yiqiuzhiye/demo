package com.demo.xyz.common.core.constant;

/**
 * Reception模块
 * @author Ethan
 */
public class ReceptionApiRetCodeEnum extends ApiRetCodeEnum {

	/*----  空异常   ----*/
	public final static int CODE_EMPTY_CONFIG_TEMPLATE_ROBOTCODE = 123001;
	public final static String MESSAGE_EMPTY_CONFIG_TEMPLATE_ROBOTCODE = "reception.config_template.empty-robotCode";

	public final static int CODE_EMPTY_CONFIG_TEMPLATE_TYPE = 123002;
	public final static String MESSAGE_EMPTY_CONFIG_TEMPLATE_TYPE = "reception.config_template.empty-type";

	public final static int CODE_EMPTY_LIFT_ID = 123003;
	public final static String MESSAGE_EMPTY_LIFT_ID = "reception.lift.empty-id";

	public final static int CODE_EMPTY_LIFT_LIFTID = 123004;
	public final static String MESSAGE_EMPTY_LIFT_LIFTID = "reception.lift.empty-liftId";

	public final static int CODE_EMPTY_LIFT_LIFTGROUP = 123005;
	public final static String MESSAGE_EMPTY_LIFT_LIFTGROUP = "reception.lift.empty-liftGroup";

	public final static int CODE_EMPTY_LIFT_TENANTID = 123006;
	public final static String MESSAGE_EMPTY_LIFT_TENANTID = "reception.lift.empty-tenantId";

	public final static int CODE_EMPTY_TYPE = 123007;
	public final static String MESSAGE_EMPTY_TYPE = "reception.empty-type";

	public final static int CODE_EMPTY_MAINTIP = 123008;
	public final static String MESSAGE_EMPTY_MAINTIP = "reception.empty-mainTip";

	public final static int CODE_EMPTY_GREETTIP = 123009;
	public final static String MESSAGE_EMPTY_GREETTIP = "reception.empty-greetTip";

	public final static int CODE_EMPTY_TENANTID = 123010;
	public final static String MESSAGE_EMPTY_TENANTID = "reception.empty-tenantId";

	public final static int CODE_EMPTY_NAME = 123011;
	public final static String MESSAGE_EMPTY_NAME = "reception.empty-name";

	public final static int CODE_EMPTY_APPIDS = 123012;
	public final static String MESSAGE_EMPTY_APPIDS = "reception.empty-appIds";

	public final static int CODE_EMPTY_ID = 123013;
	public final static String MESSAGE_EMPTY_ID = "reception.empty-id";

	public final static int CODE_EMPTY_CONFIGTEMPLATEID = 123014;
	public final static String MESSAGE_EMPTY_CONFIGTEMPLATEID = "reception.empty-configTemplateId";

	public final static int CODE_EMPTY_DEVICEIDS = 123015;
	public final static String MESSAGE_EMPTY_DEVICEIDS = "reception.empty-deviceIds";

	public final static int CODE_EMPTY_TENANT_CONFIGS = 123016;
	public final static String MESSAGE_EMPTY_TENANT_CONFIGS = "reception.empty-TenantConfigs";

	public final static int CODE_EMPTY_PERSONID = 123017;
	public final static String MESSAGE_EMPTY_PERSONID = "reception.empty-personId";

	public final static int CODE_EMPTY_BIZTYPE = 123018;
	public final static String MESSAGE_EMPTY_BIZTYPE = "reception.empty-bizType";

	public final static int CODE_EMPTY_ROBOTCODE = 123019;
	public final static String MESSAGE_EMPTY_ROBOTCODE = "reception.empty_robotCode";

	public final static int CODE_EMPTY_OPID = 123020;
	public final static String MESSAGE_EMPTY_OPID = "reception.empty_opId";

	public final static int CODE_EMPTY_PARENTID = 123021;
	public final static String MESSAGE_EMPTY_PARENTID = "reception.empty_parentId";

	public final static int CODE_EMPTY_ICON = 123022;
	public final static String MESSAGE_EMPTY_ICON = "reception.empty_icon";



	/*----  无效异常   ----*/
	public final static int CODE_INVALID_ROBOTCODE = 123101;
	public final static String MESSAGE_INVALID_ROBOTCODE = "reception.invalid_robotCode";

	public final static int CODE_INVALID_TENANTID = 123102;
	public final static String MESSAGE_INVALID_TENANTID = "reception.invalid_tenantId";

	public final static int CODE_INVALID_LIFTID = 123103;
	public final static String MESSAGE_INVALID_LIFTID = "reception.invalid_liftId";

	public final static int CODE_INVALID_ID = 123104;
	public final static String MESSAGE_INVALID_ID = "reception.invalid_id";

	public final static int CODE_INVALID_CONFIGTEMPLATEID = 123105;
	public final static String MESSAGE_INVALID_CONFIGTEMPLATEID = "reception.invalid-configTemplateId";

	public final static int CODE_INVALID_CONFIG_TEMPLATE = 123106;
	public final static String MESSAGE_INVALID_CONFIG_TEMPLATE = "reception.invalid-config_template";

	public final static int CODE_INVALID_BASE64 = 123107;
	public final static String MESSAGE_INVALID_BASE64 = "reception.base64";

	public final static int CODE_INVALID_PARENTID = 123108;
	public final static String MESSAGE_INVALID_PARENTID = "reception.invalid_parentId";

	/*----  已存在异常   ----*/
	public final static int CODE_EXIST_NAME = 123201;
	public final static String MESSAGE_EXIST_NAME = "reception.exist_name";

	public final static int CODE_EXIST_CHILDREN = 123202;
	public final static String MESSAGE_EXIST_CHILDREN = "reception.exist_children";

	/*---- 远程调用异常 --- */
	public final static int CODE_REMOTE_MCS = 123301;
	public final static String MESSAGE_REMOTE_MCS = "reception.remote.mcs: ";

	public final static int CODE_REMOTE_DEVICE = 123302;
	public final static String MESSAGE_REMOTE_DEVICE = "reception.remote.device: ";

	public final static int CODE_REMOTE_UCES = 123303;
	public final static String MESSAGE_REMOTE_UCES = "reception.remote.uces: ";

	public final static int CODE_REMOTE_GRPC = 123304;
	public final static String MESSAGE_REMOTE_GRPC = "调用HARI异常: ";

	/*---- 其他异常 --- */
	public final static int CODE_OTHER_CONFIG_TEMPLATE_NOT_BIND = 123401;
	public final static String MESSAGE_OTHER_CONFIG_TEMPLATE_NOT_BIND = "reception.config_template_not_bind";

	public final static int CODE_OTHER_NOT_SAME_TENANT = 123402;
	public final static String MESSAGE_OTHER_NOT_SAME_TENANT = "reception.not_same_tenant";

	public final static int CODE_HAS_BIND_DEVICE = 123403;
	public final static String MESSAGE_HAS_BIND_DEVICE = "reception.has_bind_devices";
}

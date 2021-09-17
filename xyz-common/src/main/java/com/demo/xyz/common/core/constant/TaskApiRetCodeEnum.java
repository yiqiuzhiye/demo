package com.demo.xyz.common.core.constant;

/**
 * Task模块
 * @author Ethan
 */
public class TaskApiRetCodeEnum extends ApiRetCodeEnum {

	/*----  空异常   ----*/
	public final static int CODE_EMPTY_TEMPLATE_NAME = 122001;
	public final static String MESSAGE_EMPTY_TEMPLATE_NAME = "task.template.empty_name";

	public final static int CODE_EMPTY_TEMPLATE_LANGUAGE = 122002;
	public final static String MESSAGE_EMPTY_TEMPLATE_LANGUAGE = "task.template.empty_language";

	public final static int CODE_EMPTY_TEMPLATE_TYPE = 122003;
	public final static String MESSAGE_EMPTY_TEMPLATE_TYPE = "task.template.empty_type";

	public final static int CODE_EMPTY_TEMPLATE_ID = 122004;
	public final static String MESSAGE_EMPTY_TEMPLATE_ID = "task.template.empty_id";

	public final static int CODE_EMPTY_TEMPLATE_CONTENT = 122005;
	public final static String MESSAGE_EMPTY_TEMPLATE_CONTENT = "task.template.empty_content";

	public final static int CODE_EMPTY_TEMPLATE = 122006;
	public final static String MESSAGE_EMPTY_TEMPLATE = "task.template.empty";

	public final static int CODE_EMPTY_TEMPLATE_TENANTID = 122007;
	public final static String MESSAGE_EMPTY_TEMPLATE_TENANTID = "task.template.empty_tenantId";

	public final static int CODE_EMPTY_TASKLOG = 122008;
	public final static String MESSAGE_EMPTY_TASKLOG = "task.empty_taskLog";

	public final static int CODE_EMPTY_ROBOT_CODE = 122009;
	public final static String MESSAGE_EMPTY_ROBOT_CODE = "task.empty_robotCode";


	/*----  无效异常   ----*/
	public final static int CODE_INVALID_TEMPLATE_ID = 122101;
	public final static String MESSAGE_INVALID_TEMPLATE_ID = "task.template.invalid_id";

	public final static int CODE_INVALID_CONTENT = 122102;
	public final static String MESSAGE_INVALID_CONTENT = "task.invalid_content";

	public final static int CODE_INVALID_TASKUUID = 122103;
	public final static String MESSAGE_INVALID_TASKUUID = "task.invalid_taskUuid";

	public final static int CODE_INVALID_RCU_PARAM = 122104;
	public final static String MESSAGE_INVALID_RCU_PARAM = "task.invalid_rcu_param";

	public final static int CODE_INVALID_RCU_OPID = 122105;
	public final static String MESSAGE_INVALID_RCU_OPID = "task.invalid_rcu_opId";

	public final static int CODE_INVALID_ROBOT_CODE = 122106;
	public final static String MESSAGE_INVALID_ROBOT_CODE = "task.invalid_robotCode";

	/*----  已存在异常   ----*/
	public final static int CODE_EXIST_NAME = 122201;
	public final static String MESSAGE_EXIST_NAME = "task.template.exist_name";

	public final static int CODE_EXIST_TENANT_TEMPLATE = 122202;
	public final static String MESSAGE_EXIST_TENANT_TEMPLATE = "task.template.exist_tenant_template";

	/*---- 远程调用异常 --- */
	public final static int CODE_REMOTE_GRPC = 122301;
	public final static String MESSAGE_REMOTE_GRPC = "task.remote.grpc: ";

	public final static int CODE_REMOTE_DEVICE = 122302;
	public final static String MESSAGE_REMOTE_DEVICE = "task.remote.device: ";

	public final static int CODE_REMOTE_MAP_API = 122303;
	public final static String MESSAGE_REMOTE_MAP_API = "task.remote.map-api: ";

	public final static int CODE_REMOTE_HARI = 122304;
	public final static String MESSAGE_REMOTE_HARI = "调用HARI异常: ";

	public final static int CODE_REMOTE_RECEPTION = 122305;
	public final static String MESSAGE_REMOTE_RECEPTION = "task.remote.reception: ";

	public final static int CODE_NOT_CURRENT_TASK = 122306;
	public final static String MESSAGE_NOT_CURRENT_TASK = "task: not current task";

	public final static int CODE_TASK_CANT_NOT_RETRY = 122307;
	public final static String MESSAGE_TASK_CANT_NOT_RETRY = "task: can not retry, please refresh";

	public final static int CODE_REMOTE_UPMS = 122307;
	public final static String MESSAGE_REMOTE_UPMS = "task.remote.upms: ";
}

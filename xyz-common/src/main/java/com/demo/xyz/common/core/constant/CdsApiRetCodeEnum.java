package com.demo.xyz.common.core.constant;

/**
 * qa模块
 * @author Ethan
 */
public class CdsApiRetCodeEnum extends ApiRetCodeEnum {

	/*----  空异常   ----*/
	public final static int CODE_EMPTY_BRANCHID = 124001;
	public final static String MESSAGE_EMPTY_BRANCHID = "cds.empty-branchId";

	public final static int CODE_EMPTY_NAME = 124002;
	public final static String MESSAGE_EMPTY_NAME = "cds.empty-name";

	public final static int CODE_EMPTY_SORT = 124003;
	public final static String MESSAGE_EMPTY_SORT = "cds.empty-sort";

	public final static int CODE_EMPTY_PARENTID = 124004;
	public final static String MESSAGE_EMPTY_PARENTID = "cds.empty-parentId";

	public final static int CODE_EMPTY_LANGUAGE = 124005;
	public final static String MESSAGE_EMPTY_LANGUAGE = "cds.empty-language";

	public final static int CODE_EMPTY_ID = 124006;
	public final static String MESSAGE_EMPTY_ID = "cds.empty-id";

	public final static int CODE_EMPTY_BIZCODE = 124007;
	public final static String MESSAGE_EMPTY_BIZCODE = "cds.empty-bizCode";

	/*----  无效异常   ----*/
	public final static int CODE_INVALID_PARENTID = 124101;
	public final static String MESSAGE_INVALID_PARENTID = "cds.invalid_parentId";

	public final static int CODE_INVALID_ID = 124102;
	public final static String MESSAGE_INVALID_ID = "cds.invalid_id";

	public final static int CODE_INVALID_BIZCODE = 124103;
	public final static String MESSAGE_INVALID_BIZCODE = "cds.invalid_bizCode";


	/*----  已存在异常   ----*/
	public final static int CODE_EXIST_NAME = 124201;
	public final static String MESSAGE_EXIST_NAME = "cds.exist_name";

	public final static int CODE_EXIST_CHILDREN = 124202;
	public final static String MESSAGE_EXIST_CHILDREN = "cds.exist_children";

	/*---- 远程调用异常 --- */
	public final static int CODE_REMOTE_MCS = 124301;
	public final static String MESSAGE_REMOTE_MCS = "cds.remote.mcs: ";
}

package com.demo.xyz.common.core.constant;

/**
 * qa模块
 * @author Ethan
 */
public class QaApiRetCodeEnum extends ApiRetCodeEnum {

	/*----  空异常   ----*/
	public final static int CODE_EMPTY_BRANCHID = 121001;
	public final static String MESSAGE_EMPTY_BRANCHID = "qa.category.empty-branchId";

	public final static int CODE_EMPTY_NAME = 121002;
	public final static String MESSAGE_EMPTY_NAME = "qa.category.empty-name";

	public final static int CODE_EMPTY_SORT = 121003;
	public final static String MESSAGE_EMPTY_SORT = "qa.category.empty-sort";

	public final static int CODE_EMPTY_PARENTID = 121004;
	public final static String MESSAGE_EMPTY_PARENTID = "qa.category.empty-parentId";

	public final static int CODE_EMPTY_LANGUAGE = 121005;
	public final static String MESSAGE_EMPTY_LANGUAGE = "qa.category.empty-language";

	public final static int CODE_EMPTY_ID = 121006;
	public final static String MESSAGE_EMPTY_ID = "qa.category.empty-id";

	public final static int CODE_EMPTY_CONTENTBRANCHID = 121007;
	public final static String MESSAGE_EMPTY_CONTENTBRANCHID = "qa.content.empty-branchId";

	public final static int CODE_EMPTY_CATEGORYID = 121008;
	public final static String MESSAGE_EMPTY_CATEGORYID = "qa.content.empty-categoryId";

	public final static int CODE_EMPTY_QUESTIONS = 121009;
	public final static String MESSAGE_EMPTY_QUESTIONS = "qa.content.empty-questions";

	public final static int CODE_EMPTY_ANSWERS = 121010;
	public final static String MESSAGE_EMPTY_ANSWERS = "qa.content.empty-answers";

	public final static int CODE_EMPTY_TAGS = 121011;
	public final static String MESSAGE_EMPTY_TAGS = "qa.content.empty-tags";

	public final static int CODE_EMPTY_EMOJI = 121012;
	public final static String MESSAGE_EMPTY_EMOJI = "qa.content.empty-emoji";

	public final static int CODE_EMPTY_APPROVE = 121013;
	public final static String MESSAGE_EMPTY_APPROVE = "qa.content.empty-approve";

	public final static int CODE_EMPTY_CONTENT_ID = 121014;
	public final static String MESSAGE_EMPTY_CONTENT_ID = "qa.content.empty-id";

	public final static int CODE_EMPTY_CONTENT_LANGUAGE = 121015;
	public final static String MESSAGE_EMPTY_CONTENT_LANGUAGE = "qa.content.empty-language";

	/*----  无效异常   ----*/
	public final static int CODE_INVALID_PARENTID = 121101;
	public final static String MESSAGE_INVALID_PARENTID = "qa.category.invalid_parentId";

	public final static int CODE_INVALID_ID = 121102;
	public final static String MESSAGE_INVALID_ID = "qa.category.invalid_id";

	public final static int CODE_INVALID_CATEGORYID = 121103;
	public final static String MESSAGE_INVALID_CATEGORYID = "qa.content.invalid_categoryId";

	public final static int CODE_INVALID_CONTENT_ID = 121104;
	public final static String MESSAGE_INVALID_CONTENT_ID = "qa.content.invalid_id";

	/*----  已存在异常   ----*/
	public final static int CODE_EXIST_NAME = 121201;
	public final static String MESSAGE_EXIST_NAME = "qa.category.exist_name";

	public final static int CODE_EXIST_CHILDREN = 121202;
	public final static String MESSAGE_EXIST_CHILDREN = "qa.category.exist_children";

	/*---- 远程调用异常 --- */
	public final static int CODE_REMOTE_MCS = 121301;
	public final static String MESSAGE_REMOTE_MCS = "qa.remote.cms: ";
}

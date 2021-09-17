package com.demo.xyz.common.core.constant;

/**
 * 梯控模块
 *
 * @author: Charles.ouyang
 **/
public class ElevatorRetCodeEnum extends ApiRetCodeEnum {

  public final static int CODE_TENANT_ID_NOT_NULL = 125001;
  public final static String MESSAGE_TENANT_ID_NOT_NULL = "elevator.tenantId.not-null";

  public final static int CODE_NAME_NOT_BLANK = 125002;
  public final static String MESSAGE_NAME_NOT_BLANK = "elevator.name.not-blank";

  public final static int CODE_FLOOR_NOT_NULL = 125003;
  public final static String MESSAGE_FLOOR_NOT_NULL = "elevator.floor.not-null";

  public final static int CODE_HARDKEY_EXISTS = 125004;
  public final static String MESSAGE_HARDKEY_EXISTS = "elevator.hardkey.exists";

  public final static int CODE_ELEVATOR_ID_NOT_NULL = 125005;
  public final static String MESSAGE_ELEVATOR_ID_NOT_NULL = "elevator.id.not-null";

  public final static int CODE_CALL_TASK_NOT_FOUND = 125006;
  public final static String MESSAGE_CALL_TASK_NOT_FOUND = "elevator.call-task.not-found";

  public final static int CODE_INVALID_APP_ID = 125007;
  public final static String MESSAGE_INVALID_APP_ID = "elevator.app-id.invalid";

  public final static int CODE_INVALID_SIGN = 125008;
  public final static String MESSAGE_INVALID_SIGN = "elevator.sign.invalid";

  public final static int CODE_INVALID_TOKEN = 125009;
  public final static String MESSAGE_INVALID_TOKEN = "elevator.token.invalid";

}
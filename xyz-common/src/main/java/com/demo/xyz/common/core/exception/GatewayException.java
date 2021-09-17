package com.demo.xyz.common.core.exception;

/**
 * 网关异常
 * 
 * @author charles
 *
 */
public class GatewayException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3077634428937289808L;
	private Integer code;
	private String message;

	public GatewayException() {
		super();
	}

	public GatewayException(Integer code, String message) {
		super(message);
		this.code = code;
	}

	public GatewayException(String message) {
		super(message);
	}

	public GatewayException(Throwable cause) {
		super(cause);
	}

	public GatewayException(Integer code, Throwable e) {
		super(e);
		this.code = code;
	}

	public GatewayException(String message, Throwable cause) {
		super(message, cause);
	}

	public Integer getCode() {
		return code;
	}

}

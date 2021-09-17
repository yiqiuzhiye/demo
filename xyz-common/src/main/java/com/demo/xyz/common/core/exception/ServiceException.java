package com.demo.xyz.common.core.exception;

/**
 * Service层公用的Exception.
 * 
 * @author charles
 *
 */
public class ServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1191541920182893393L;
	private Integer code;
	private String message;
	private Object data;

	public ServiceException() {
		super();
	}

	public ServiceException(Integer code, String message) {
		super(message);
		this.code = code;
	}

	public ServiceException(Integer code, String message, Object data) {
		super(message);
		this.code = code;
		this.data = data;
	}

	public ServiceException(Integer code) {
		super();
		this.code = code;
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(Integer code, Throwable e) {
		super(e);
		this.code = code;
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public Integer getCode() {
		return code;
	}

	public Object getData() {
		return data;
	}

}

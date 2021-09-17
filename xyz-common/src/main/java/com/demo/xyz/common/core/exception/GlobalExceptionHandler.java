
package com.demo.xyz.common.core.exception;

import com.demo.xyz.common.core.constant.ApiRetCodeHolder;
import com.demo.xyz.common.core.constant.SignConstants;
import com.demo.xyz.common.core.util.R;
import com.demo.xyz.common.core.util.ThreadLocalUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import java.net.BindException;
import java.util.List;

/**
 * 全局的的异常处理器
 * 
 * @author admin
 *
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static String MISSING_REQ_PARAMETER_DESC = "缺少请求参数";
	private static String DATA_INTEGRITY_VIOLATION_ERROR_DESC = "操作数据库出现异常：主键约束,唯一约束,非空约束异常等";

	/**
	 * 全局异常.
	 *
	 * @param e the e
	 * @return R
	 */
	@ExceptionHandler(Exception.class)
	// @ResponseStatus(HttpStatus.OK)
	public R exception(Exception e) {
		log.error("全局异常信息 ex={}", e.getMessage(), e);
		ThreadLocalUtils.remove();
		return new R<>(e);
	}

	/**
	 * validation Exception 捕获Spring validation @Validated请求参数验证异常和参数绑定异常
	 * 
	 * @param exception
	 * @return R
	 */
	@ExceptionHandler({ MethodArgumentNotValidException.class, BindException.class })
	// @ResponseStatus(HttpStatus.BAD_REQUEST)
	public R bodyValidExceptionHandler(MethodArgumentNotValidException exception) {
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		String msg = fieldErrors.get(0).getDefaultMessage();

		Integer code = ApiRetCodeHolder.getCode(msg);
		log.info(">>>>getCode,msg:{} return code:{}", code, msg);
		R result = new R(code, msg);
		log.warn(msg);
		ThreadLocalUtils.remove();
		return result;
	}

	/**
	 * <p>
	 * Title: validationExceptionHandler<／p>
	 * <p>
	 * Description:参数验证失败异常<／p>
	 * 
	 * @param request
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(ValidationException.class)
	public R validationExceptionHandler(HttpServletRequest request, ValidationException exception) {
		log.error(request.getContextPath() + request.getRequestURI() + SignConstants.SIGN_COLON
				+ exception.getLocalizedMessage(), exception);
		ThreadLocalUtils.remove();
		return new R<>(exception.getMessage());
	}

	/**
	 * <p>
	 * Title: missingServletRequestParameterExceptionHandler<／p>
	 * <p>
	 * Description:缺少请求参数异常处理 <／p>
	 * 
	 * @param request
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public R missingServletRequestParameterExceptionHandler(HttpServletRequest request,
			MissingServletRequestParameterException exception) {
		log.error(request.getContextPath() + request.getRequestURI() + SignConstants.SIGN_COLON
				+ exception.getLocalizedMessage(), exception);
		ThreadLocalUtils.remove();
		return new R<>(MISSING_REQ_PARAMETER_DESC);
	}

	/**
	 * <p>
	 * Title: handleException<／p>
	 * <p>
	 * Description: 操作数据库出现异常:唯一约束，非空约束异常等<／p>
	 * 
	 * @param request
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	public R dataIntegrityViolationExceptionHandler(HttpServletRequest request,
			DataIntegrityViolationException exception) {
		log.error(request.getContextPath() + request.getRequestURI() + SignConstants.SIGN_COLON
				+ exception.getLocalizedMessage(), exception);
		ThreadLocalUtils.remove();
		return new R<>(DATA_INTEGRITY_VIOLATION_ERROR_DESC);
	}

	@ExceptionHandler(GatewayException.class)
	@ResponseBody
	public R customExceptionHandle(final HttpServletRequest request, final HttpServletResponse response,
			final GatewayException exception) {
		log.error("GatewayException Handler invoked");
		ThreadLocalUtils.remove();
		return new R<>(exception.getMessage());
	}

	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public R serviceExceptionHandle(final HttpServletRequest request, final HttpServletResponse response,
			final ServiceException exception) {
		log.error("ServiceException Handler invoked");
		ThreadLocalUtils.remove();
		return new R<>(exception.getCode(), exception.getMessage(), exception.getData());
	}

}

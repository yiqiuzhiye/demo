
package com.demo.xyz.common.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import java.util.Arrays;
import java.util.Optional;

/**
 * 全局的的异常处理器
 *
 * @author admin
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.demo.xyz.gateway")
public class GlobalExceptionHandler {

//    /**
//     * 参数验证失败异常，后续需要废弃主动抛此类异常
//     *
//     * @param request
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(ValidationException.class)
//    public ResponseEntity<R<Void>> validationExceptionHandler(HttpServletRequest request, ValidationException e) {
//        log.warn("{} 参数检验异常---> ({}){}", request.getRequestURI(), e.getLocalizedMessage());
//        return ResponseEntity.ok(R.error(400, e.getLocalizedMessage()));
//    }
//
//    /**
//     * GET请求校验异常
//     *
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(value = BindException.class)
//    public ResponseEntity<R<Void>> validExceptionHandler(HttpServletRequest request, BindException e) {
//        final FieldError fieldError = e.getFieldError();
//        if (fieldError == null) {
//            log.warn("{} 参数异常---> {}", request.getRequestURI(), e.getMessage());
//            return ResponseEntity.ok(R.error("参数异常"));
//        }
//        final Optional<String> optional = Arrays.stream(fieldError.getCodes())
//                .filter(code -> StringUtils.equals(code, "typeMismatch")).findFirst();
//        if (optional.isPresent()) {
//            log.warn("{} 参数异常---> {}", request.getRequestURI(), fieldError.getDefaultMessage());
//            return ResponseEntity.ok(R.error(400, "field[" + fieldError.getField() + "] type mismatch"));
//        }
//        return this.handFieldError(request, fieldError);
//    }
//
//    /**
//     * POST请求带Body的数校验异常
//     *
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<R<Void>> validExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) {
//        final FieldError fieldError = e.getBindingResult().getFieldError();
//        if (fieldError != null) {
//            return this.handFieldError(request, fieldError);
//        }
//        return ResponseEntity.ok(R.success());
//    }
//
//    /**
//     * 处理字段错误
//     *
//     * @param request
//     * @param fieldError
//     * @return
//     */
//    private ResponseEntity<R<Void>> handFieldError(final HttpServletRequest request, final FieldError fieldError) {
//        final String message = fieldError.getDefaultMessage();
//        log.warn("{} 参数检验异常---> errorCode={}, ({}){}", request.getRequestURI(), 400, fieldError.getField(),
//                fieldError.getDefaultMessage());
//        return ResponseEntity.ok(R.error(400, message));
//    }
//
//
//    /**
//     * 请求参数异常
//     *
//     * @param request
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(value = ServletRequestBindingException.class)
//    public ResponseEntity<R<Void>> validParameterExceptionHandler(final HttpServletRequest request, final Exception e) {
//        log.warn("{} 参数异常---> {}", request.getRequestURI(), e.getMessage());
//        return ResponseEntity.ok(R.error("请求参数异常"));
//    }
//
//    /**
//     * 请求方式错误处理
//     *
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
//    public Object methodNotSupportedHandler(final HttpRequestMethodNotSupportedException e) {
//        log.warn("错误的请求方式，message={}", e.getMessage());
//        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(R.error(400,"不支持的请求方式"));
//    }

    /**
     * 业务基础异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = ServiceException.class)
    public R<Object> validExceptionHandler(HttpServletRequest request, ServiceException e) {
        log.warn("{} 业务异常---> {}", request.getRequestURI(), e.getMessage());
        return new R<>(e.getCode(),e.getMessage());
    }

}

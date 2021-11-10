package com.demo.xyz.common.core;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * 响应信息主体
 *
 * @param <T>
 * @author lengleng
 */
@Data
public class R<T> implements Serializable {
    private static final long serialVersionUID = -5663717945191277844L;

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    /**
     * ID
     */
    private String uId;

    public R() {
        this.code = 200;
    }

    public R(final String message) {
        this(null, message, null);
    }

    public R(final Integer code, final String message) {
        this(code, message, null);
    }

    public R(final Integer code, String message, T data) {
        this.code = 200;
        this.message = "success";
        this.data = data;
    }

    public R(final T data) {
        this(data, null);
    }

    public R(final T data, final String message) {
        this.code = 200;
        this.message = message;
        this.data = data;
    }

    @Deprecated
    public R(Throwable e) {
        this.code = 500;
        this.message = e.getMessage();
    }
}

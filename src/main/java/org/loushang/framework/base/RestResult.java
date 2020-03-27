package org.loushang.framework.base;

import java.io.Serializable;
import java.util.Collections;

public class RestResult<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code = "200";
	private String message = "success";
	private T data;

	public RestResult() {
		this(null);
	}

	public RestResult(T data) {
		this.data = data;
	}

	public RestResult(String code, String message) {
		this(code, message, null);
	}

	public RestResult(String code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public RestResult(T data, long total) {
		this.data = data;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return (T) this.data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static RestResult<?> success() {
		return new RestResult<>(Boolean.TRUE);
	}

	public static RestResult<?> success(Object data) {
		return new RestResult<>(data);
	}

	public static RestResult<?> response(String code, String message, Object data) {
		return new RestResult<>(code, message, data);
	}

	public static RestResult<?> error(String code, String message) {
		return new RestResult<>(code, message, Collections.emptyMap());
	}

	public static RestResult<?> error(String message) {
		return error("500", message);
	}

}

package org.loushang.framework.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

public class RestPageResult<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code = "200";
	private String message = "success";
	private Collection<T> data;
	private long total;

	public RestPageResult(Collection<T> data, long total) {
		this.data = data;
		this.total = total;
	}

	public RestPageResult(String code, String message, Collection<T> data, long total) {
		this.code = code;
		this.message = message;
		this.data = data;
		this.total = total;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Collection<T> getData() {
		return data;
	}

	public void setData(Collection<T> data) {
		this.data = data;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public static RestPageResult<?> success(Collection<?> data, long total) {
		return new RestPageResult<>(data, total);
	}

	public static RestPageResult<?> response(String code, String message, Collection<?> data, long total) {
		return new RestPageResult<>(code, message, data, total);
	}

	public static RestPageResult<?> error(String code, String message) {
		return new RestPageResult<>(code, message, Collections.emptyList(), 0);
	}

	public static RestPageResult<?> error(String message) {
		return error("500", message);
	}

}

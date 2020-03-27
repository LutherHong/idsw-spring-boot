package org.loushang.framework.base;

import java.util.Collection;

public class PagedList<T> {
	private long total;
	private Collection<T> data;

	public PagedList(Collection<T> data, long total) {
		this.data = data;
		this.total = total;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public Collection<T> getData() {
		return data;
	}

	public void setData(Collection<T> data) {
		this.data = data;
	}
	

}

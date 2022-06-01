package com.liferay.headless.delivery.internal.resource.v1_0;

public class WikiPageRuntimeException extends RuntimeException {

	public WikiPageRuntimeException() {
	}

	public WikiPageRuntimeException(String msg) {
		super(msg);
	}

	public WikiPageRuntimeException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public WikiPageRuntimeException(Throwable throwable) {
		super(throwable);
	}

}
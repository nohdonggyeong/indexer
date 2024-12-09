package me.donggyeong.indexer.enums;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {
	OPENSEARCH_OPERATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR.value(), "OpenSearch operation failed");

	private final int statusCode;
	private final String message;

	ErrorCode(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}
}

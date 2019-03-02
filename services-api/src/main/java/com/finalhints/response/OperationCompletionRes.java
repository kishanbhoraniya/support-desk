package com.finalhints.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Common response for operation that return just message about completion
 * 
 * @author Henadzi_Vrubleuski
 * 
 */
@JsonInclude(Include.NON_NULL)
public class OperationCompletionRes {

	@JsonProperty("msg")
	private String resultMessage;

	public OperationCompletionRes() {

	}

	public OperationCompletionRes(String message) {
		this.resultMessage = message;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
}

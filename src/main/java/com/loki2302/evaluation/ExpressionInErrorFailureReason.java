package com.loki2302.evaluation;

public class ExpressionInErrorFailureReason implements FailureReason {
	public final ExpressionResult result;
	
	public ExpressionInErrorFailureReason(ExpressionResult result) {
		this.result = result;
	}
}
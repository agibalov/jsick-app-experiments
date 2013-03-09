package com.loki2302.evaluation;

public class StatementInErrorFailureReason implements FailureReason {
	public final StatementResult result;
	
	public StatementInErrorFailureReason(StatementResult result) {
		this.result = result;
	}
}
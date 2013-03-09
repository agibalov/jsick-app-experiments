package com.loki2302.evaluation;

public class TypeInErrorFailureReason implements FailureReason {
	public final TypeResult result;
	
	public TypeInErrorFailureReason(TypeResult result) {
		this.result = result;
	}
}
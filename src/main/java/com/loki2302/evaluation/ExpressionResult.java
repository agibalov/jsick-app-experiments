package com.loki2302.evaluation;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.program.Expression;

public class ExpressionResult {
	public boolean ok;
	public Expression expression;
	public List<FailureReason> failureReasons;
	
	public static ExpressionResult ok(Expression expression) {
		ExpressionResult result = new ExpressionResult();
		result.ok = true;
		result.expression = expression;
		return result;    		
	}
	
	public static ExpressionResult fail(FailureReason reason) {
		List<FailureReason> reasons = new ArrayList<FailureReason>();
		reasons.add(reason);
		return fail(reasons);
	}
	
	public static ExpressionResult fail(List<FailureReason> reasons) {
		ExpressionResult result = new ExpressionResult();
		result.ok = false;
		result.failureReasons = reasons;
		return result;
	}
}
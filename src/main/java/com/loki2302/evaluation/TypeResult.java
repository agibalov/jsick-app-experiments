package com.loki2302.evaluation;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.type.Type;

public class TypeResult {
	public boolean ok;
	public Type type;
	public List<FailureReason> failureReasons;
	
	public static TypeResult ok(Type type) {
		TypeResult result = new TypeResult();
		result.ok = true;
		result.type = type;
		return result;    		
	}
	
	public static TypeResult fail(FailureReason reason) {
		List<FailureReason> reasons = new ArrayList<FailureReason>();
		reasons.add(reason);
		return fail(reasons);
	}
	
	public static TypeResult fail(List<FailureReason> reasons) {
		TypeResult result = new TypeResult();
		result.ok = false;
		result.failureReasons = reasons;
		return result;
	}
}
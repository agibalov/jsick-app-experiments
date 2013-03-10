package com.loki2302.evaluation;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.program.Statement;

public class StatementResult {
	public boolean ok;
	public Statement statement;
	public List<FailureReason> failureReasons;
	
	public static StatementResult ok(Statement statement) {
		StatementResult result = new StatementResult();
		result.ok = true;
		result.statement = statement;
		return result;    		
	}
	
	public static StatementResult fail(FailureReason reason) {
		List<FailureReason> reasons = new ArrayList<FailureReason>();
		reasons.add(reason);
		return fail(reasons);
	}
	
	public static StatementResult fail(List<FailureReason> reasons) {
		StatementResult result = new StatementResult();
		result.ok = false;
		result.failureReasons = reasons;
		return result;
	}
}
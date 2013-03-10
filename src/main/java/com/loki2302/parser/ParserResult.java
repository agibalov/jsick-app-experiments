package com.loki2302.parser;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.dom.DOMProgram;
import com.loki2302.evaluation.FailureReason;

public class ParserResult {
	public boolean ok;
	public DOMProgram program;
	public List<FailureReason> failureReasons;
	
	public static ParserResult ok(DOMProgram program) {
		ParserResult result = new ParserResult();
		result.ok = true;
		result.program = program;
		return result;    		
	}
	
	public static ParserResult fail(FailureReason reason) {
		List<FailureReason> reasons = new ArrayList<FailureReason>();
		reasons.add(reason);
		return fail(reasons);
	}
	
	public static ParserResult fail(List<FailureReason> reasons) {
		ParserResult result = new ParserResult();
		result.ok = false;
		result.failureReasons = reasons;
		return result;
	}
}
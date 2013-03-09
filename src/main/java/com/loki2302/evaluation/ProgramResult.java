package com.loki2302.evaluation;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.program.Program;

public class ProgramResult {
	public boolean ok;
	public Program program;
	public List<FailureReason> failureReasons;
	
	public static ProgramResult ok(Program program) {
		ProgramResult result = new ProgramResult();
		result.ok = true;
		result.program = program;
		return result;    		
	}
	
	public static ProgramResult fail(FailureReason reason) {
		List<FailureReason> reasons = new ArrayList<FailureReason>();
		reasons.add(reason);
		return fail(reasons);
	}
	
	public static ProgramResult fail(List<FailureReason> reasons) {
		ProgramResult result = new ProgramResult();
		result.ok = false;
		result.failureReasons = reasons;
		return result;
	}
}
package com.loki2302;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.loki2302.dom.DOMProgram;
import com.loki2302.dom.statement.DOMStatement;
import com.loki2302.evaluation.Context;
import com.loki2302.evaluation.FailureReason;
import com.loki2302.evaluation.ProgramResult;
import com.loki2302.evaluation.StatementInErrorFailureReason;
import com.loki2302.evaluation.StatementResult;
import com.loki2302.program.Program;
import com.loki2302.program.Statement;

public class DOMProgramEvaluator {    	
	private final DOMStatementEvaluator domStatementEvaluator;
	
	@Inject
	public DOMProgramEvaluator(DOMStatementEvaluator domStatementEvaluator) {
		this.domStatementEvaluator = domStatementEvaluator;
	}
	
	public ProgramResult evaluateDOMProgram(DOMProgram domProgram) {
		Context context = new Context();    		
		List<DOMStatement> domStatements = domProgram.getStatements();
    	List<FailureReason> failureReasons = new ArrayList<FailureReason>();
    	List<Statement> statements = new ArrayList<Statement>();
    	for(DOMStatement domStatement : domStatements) {
    		StatementResult statementResult = 
    				domStatementEvaluator.evaluateDOMStatement(context, domStatement);
    		
    		if(!statementResult.ok) {
    			failureReasons.add(new StatementInErrorFailureReason(statementResult));
    			continue;
    		}    		
    		
    		statements.add(statementResult.statement);
    	}
    	
    	if(!failureReasons.isEmpty()) {
    		return ProgramResult.fail(failureReasons);
    	}
    	
    	Program program = new Program(statements);    	
    	return ProgramResult.ok(program);
	}
}
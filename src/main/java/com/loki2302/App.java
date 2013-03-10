package com.loki2302;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.loki2302.dom.DOMProgram;
import com.loki2302.dom.statement.DOMStatement;
import com.loki2302.evaluation.FailureReason;
import com.loki2302.evaluation.ProgramResult;
import com.loki2302.parser.Parser;
import com.loki2302.parser.ParserResult;
import com.loki2302.program.Statement;

public class App {
    public static void main(String[] args) {
    	Parser parser = new Parser();
    	ParserResult parserResult = parser.parse("123;222;");
    	if(!parserResult.ok) {
    		System.out.println("parser says it's bad");
    		return;
    	}
    	
    	System.out.println("parser says it's ok:");    	
    	DOMProgram domProgram = parserResult.program;
    	for(DOMStatement statement : domProgram.getStatements()) {
    		System.out.println(statement);
    	}
    	
    	Injector injector = Guice.createInjector();
    	DOMProgramEvaluator domProgramEvaluator = injector.getInstance(DOMProgramEvaluator.class);
    	
    	ProgramResult result = domProgramEvaluator.evaluateDOMProgram(domProgram);    	
    	if(!result.ok) {
    		System.out.println("evaluator says it's bad:");
    		for(FailureReason failureReason : result.failureReasons) {
    			System.out.println(failureReason);
    		}
    		return;
    	} else {
    		System.out.println("evaluator says it's ok:");
    		for(Statement statement : result.program.getStatements()) {
    			System.out.println(statement);
    		}
    	}
    }
}
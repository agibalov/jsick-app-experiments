package com.loki2302;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.loki2302.dom.DOMNamedTypeDescriptor;
import com.loki2302.dom.DOMProgram;
import com.loki2302.dom.expression.DOMAddExpression;
import com.loki2302.dom.expression.literal.DOMDoubleLiteralExpression;
import com.loki2302.dom.expression.literal.DOMIntLiteralExpression;
import com.loki2302.dom.statement.DOMStatement;
import com.loki2302.dom.statement.DOMVariableDefinitionStatement;
import com.loki2302.evaluation.FailureReason;
import com.loki2302.evaluation.ProgramResult;

public class App {
    public static void main(String[] args) {
    	List<DOMStatement> domStatements = new ArrayList<DOMStatement>();
    	
    	domStatements.add(new DOMVariableDefinitionStatement(
    			new DOMNamedTypeDescriptor("int"),
    			"x",
    			new DOMAddExpression(
    					new DOMAddExpression(
    							new DOMIntLiteralExpression("1"),
    							new DOMIntLiteralExpression("2")),
    					new DOMDoubleLiteralExpression("3.14"))
    			));
    	
    	DOMProgram domProgram = new DOMProgram(domStatements);    	
    	Injector injector = Guice.createInjector();
    	DOMProgramEvaluator domProgramEvaluator = injector.getInstance(DOMProgramEvaluator.class);
    	
    	ProgramResult result = domProgramEvaluator.evaluateDOMProgram(domProgram);
    	System.out.printf("result ok: %b\n", result.ok);
    	if(result.ok) {
    		System.out.println(result.program);
    	} else {
    		for(FailureReason failureReason : result.failureReasons) {
    			System.out.println(failureReason);
    		}
    	}
    }
}
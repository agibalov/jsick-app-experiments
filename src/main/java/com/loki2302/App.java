package com.loki2302;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.loki2302.dom.DOMAddExpression;
import com.loki2302.dom.DOMDoubleLiteralExpression;
import com.loki2302.dom.DOMIntLiteralExpression;
import com.loki2302.dom.DOMNamedTypeDescriptor;
import com.loki2302.dom.DOMProgram;
import com.loki2302.dom.DOMStatement;
import com.loki2302.dom.DOMVariableDefinitionStatement;
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
    							new DOMIntLiteralExpression(),
    							new DOMIntLiteralExpression()),
    					new DOMDoubleLiteralExpression())
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

/*
int, double, char, string, bool, void, arrays
functions, structs (by ref)
predefined funcs: print
*/
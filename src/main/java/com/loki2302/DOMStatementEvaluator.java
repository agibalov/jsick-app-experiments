package com.loki2302;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.loki2302.dom.DOMExpression;
import com.loki2302.dom.DOMExpressionStatement;
import com.loki2302.dom.DOMStatement;
import com.loki2302.dom.DOMStatementVisitor;
import com.loki2302.dom.DOMTypeDescriptor;
import com.loki2302.dom.DOMVariableDefinitionStatement;
import com.loki2302.evaluation.Context;
import com.loki2302.evaluation.ExpressionInErrorFailureReason;
import com.loki2302.evaluation.ExpressionResult;
import com.loki2302.evaluation.FailureReason;
import com.loki2302.evaluation.IncompatibleTypesFailureReason;
import com.loki2302.evaluation.StatementResult;
import com.loki2302.evaluation.TypeInErrorFailureReason;
import com.loki2302.evaluation.TypeResult;
import com.loki2302.program.CastExpression;
import com.loki2302.program.Expression;
import com.loki2302.program.ExpressionStatement;
import com.loki2302.program.VariableDefinitionStatement;
import com.loki2302.type.Type;

public class DOMStatementEvaluator {
	private final DOMTypeDescriptorEvaluator domTypeDescriptorEvaluator;
	private final DOMExpressionEvaluator domExpressionEvaluator;
	
	@Inject
	public DOMStatementEvaluator(    			
			DOMTypeDescriptorEvaluator domTypeDescriptorEvaluator,
			DOMExpressionEvaluator domExpressionEvaluator) {
		this.domTypeDescriptorEvaluator = domTypeDescriptorEvaluator;
		this.domExpressionEvaluator = domExpressionEvaluator;
	}
	
	public StatementResult evaluateDOMStatement(final Context context, DOMStatement domStatement) {
    	return domStatement.accept(new DOMStatementVisitor<StatementResult>() {
			public StatementResult visitVariableDefinitionStatement(DOMVariableDefinitionStatement statement) {
				DOMTypeDescriptor domTypeDescriptor = statement.getTypeDescriptor();
				TypeResult typeResult = 
						domTypeDescriptorEvaluator.evaluateDOMTypeDescriptor(context, domTypeDescriptor);
				
				String variableName = statement.getVariableName();
				
				DOMExpression domInitializerExpression = statement.getInitializerExpression();
				
				ExpressionResult expressionResult = 
						domExpressionEvaluator.evaluateDOMExpression(context, domInitializerExpression);
				
				List<FailureReason> reasons = new ArrayList<FailureReason>();
				if(!typeResult.ok) {
					reasons.add(new TypeInErrorFailureReason(typeResult));
				}
				
				if(!expressionResult.ok) {
					reasons.add(new ExpressionInErrorFailureReason(expressionResult));
				}
				
				if(!reasons.isEmpty()) {
					return StatementResult.fail(reasons);
				}
				
				Type variableType = typeResult.type;
				Expression initializerExpression = expressionResult.expression;
				Type initializerExpressionType = initializerExpression.getType();
				
				// design with Or(way1, way2, way3) with no viable alternative error?
				if(variableType == initializerExpressionType) {
					VariableDefinitionStatement variableDefinitionStatement = new VariableDefinitionStatement(
							typeResult.type, 
							variableName, 
							expressionResult.expression);
					return StatementResult.ok(variableDefinitionStatement);
				} else if(variableType == context.doubleType && initializerExpressionType == context.intType) {					
					VariableDefinitionStatement variableDefinitionStatement = new VariableDefinitionStatement(
							typeResult.type,
							variableName,
							new CastExpression(domInitializerExpression, expressionResult.expression, context.doubleType));
					return StatementResult.ok(variableDefinitionStatement);
				}
				
				return StatementResult.fail(new IncompatibleTypesFailureReason());
			}

			public StatementResult visitExpressionStatement(DOMExpressionStatement statement) {
				ExpressionResult expressionResult = 
						domExpressionEvaluator.evaluateDOMExpression(context, statement.getExpression());
				if(!expressionResult.ok) {
					return StatementResult.fail(new ExpressionInErrorFailureReason(expressionResult));
				}
				
				return StatementResult.ok(new ExpressionStatement(expressionResult.expression));
			}    		
    	});
    }	
}
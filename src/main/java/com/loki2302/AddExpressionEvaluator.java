package com.loki2302;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.dom.expression.DOMAddExpression;
import com.loki2302.evaluation.Context;
import com.loki2302.evaluation.ExpressionInErrorFailureReason;
import com.loki2302.evaluation.ExpressionResult;
import com.loki2302.evaluation.FailureReason;
import com.loki2302.evaluation.InternalErrorFailureReason;
import com.loki2302.program.AddExpression;
import com.loki2302.program.CastExpression;
import com.loki2302.program.Expression;

public class AddExpressionEvaluator {
	public ExpressionResult evaluateAddExpression(Context context, DOMAddExpression domExpression, DOMExpressionEvaluator expressionEvaluator) {
		ExpressionResult leftResult = expressionEvaluator.evaluateDOMExpression(context, domExpression.getLeftExpression());
		ExpressionResult rightResult = expressionEvaluator.evaluateDOMExpression(context, domExpression.getRightExpression());
		
		List<FailureReason> reasons = new ArrayList<FailureReason>();
		if(!leftResult.ok) {
			reasons.add(new ExpressionInErrorFailureReason(leftResult));
		}
		
		if(!rightResult.ok) {
			reasons.add(new ExpressionInErrorFailureReason(rightResult));
		}
		
		if(!reasons.isEmpty()) {
			return ExpressionResult.fail(reasons);
		}
		
		Expression left = leftResult.expression;
		Expression right = rightResult.expression;
		
		if(left.getType() == right.getType()) {
			return ExpressionResult.ok(new AddExpression(domExpression, left.getType(), left, right));
		} else if(left.getType() == context.intType && right.getType() == context.doubleType) {
			return ExpressionResult.ok(new AddExpression(
					domExpression,
					right.getType(), 
					new CastExpression(domExpression.getLeftExpression(), left, right.getType()), 
					right));
		} else if(left.getType() == context.doubleType && right.getType() == context.intType) {
			return ExpressionResult.ok(new AddExpression(
					domExpression,
					left.getType(), 
					left, 
					new CastExpression(domExpression.getRightExpression(), right, left.getType())));
		}
		
		return ExpressionResult.fail(new InternalErrorFailureReason());
	}
}
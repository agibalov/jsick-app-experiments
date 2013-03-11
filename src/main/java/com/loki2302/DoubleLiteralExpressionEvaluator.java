package com.loki2302;

import com.loki2302.dom.expression.literal.DOMDoubleLiteralExpression;
import com.loki2302.evaluation.BadDoubleLiteralFailureReason;
import com.loki2302.evaluation.Context;
import com.loki2302.evaluation.ExpressionResult;
import com.loki2302.program.DoubleLiteralExpression;

public class DoubleLiteralExpressionEvaluator {
	public ExpressionResult evaluateDoubleLiteralExpression(Context context, DOMDoubleLiteralExpression domExpression) {
		String stringValue = domExpression.getStringValue();
		try {
			double intValue = Double.parseDouble(stringValue);
			return ExpressionResult.ok(new DoubleLiteralExpression(domExpression, context.doubleType, intValue)); 
		} catch(NumberFormatException e) {}
		
		return ExpressionResult.fail(new BadDoubleLiteralFailureReason());
	}
}
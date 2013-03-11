package com.loki2302;

import com.loki2302.dom.expression.literal.DOMIntLiteralExpression;
import com.loki2302.evaluation.BadIntLiteralFailureReason;
import com.loki2302.evaluation.Context;
import com.loki2302.evaluation.ExpressionResult;
import com.loki2302.program.IntLiteralExpression;

public class IntLiteralExpressionEvaluator {
	public ExpressionResult evaluateIntLiteralExpression(Context context, DOMIntLiteralExpression domExpression) {
		String stringValue = domExpression.getStringValue();
		try {
			int intValue = Integer.parseInt(stringValue);
			return ExpressionResult.ok(new IntLiteralExpression(domExpression, context.intType, intValue)); 
		} catch(NumberFormatException e) {}
		
		return ExpressionResult.fail(new BadIntLiteralFailureReason());
	}
}
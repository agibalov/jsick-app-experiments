package com.loki2302.dom.expression;

import com.loki2302.dom.expression.literal.DOMDoubleLiteralExpression;
import com.loki2302.dom.expression.literal.DOMFalseBoolLiteralExpression;
import com.loki2302.dom.expression.literal.DOMIntLiteralExpression;
import com.loki2302.dom.expression.literal.DOMNullLiteralExpression;
import com.loki2302.dom.expression.literal.DOMTrueBoolLiteralExpression;

public interface DOMExpressionVisitor<T> {
	T visitIntLiteralExpression(DOMIntLiteralExpression domExpression);
	T visitDoubleLiteralExpression(DOMDoubleLiteralExpression domExpression);
	T visitAddExpression(DOMAddExpression domExpression);
	T visitSubExpression(DOMSubExpression domExpression);
	T visitMulExpression(DOMMulExpression domExpression);
	T visitDivExpression(DOMDivExpression domExpression);
	T visitNullLiteralExpression(DOMNullLiteralExpression domExpression);
	T visitTrueBoolLiteralExpression(DOMTrueBoolLiteralExpression domExpression);
	T visitFalseBoolLiteralExpression(DOMFalseBoolLiteralExpression domExpression);	
	T visitLessExpression(DOMLessExpression domExpression);
	T visitLessOrEqualExpression(DOMLessOrEqualExpression domExpression);
	T visitEqualExpression(DOMEqualExpression domExpression);
	T visitNotEqualExpression(DOMNotEqualExpression domExpression);
	T visitGreaterExpression(DOMGreaterExpression domExpression);
	T visitGreaterOrEqualExpression(DOMGreaterOrEqualExpression domExpression);
	T visitAndExpression(DOMAndExpression domExpression);
	T visitOrExpression(DOMOrExpression domExpression);
	T visitNotExpression(DOMNotExpression domExpression);
	T visitMinusSignExpression(DOMMinusSignExpression domExpression);
	T visitPlusSignExpression(DOMPlusSignExpression domExpression);
	T visitPrefixIncrementExpression(DOMPrefixIncrementExpression domExpression);
	T visitPostfixIncrementExpression(DOMPostfixIncrementExpression domExpression);
	T visitPrefixDecrementExpression(DOMPrefixDecrementExpression domExpression);
	T visitPostfixDecrementExpression(DOMPostfixDecrementExpression domExpression);
	T visitAddAndAssignExpression(DOMAddAndAssignExpression domExpression);
	T visitSubAndAssignExpression(DOMSubAndAssignExpression domExpression);
	T visitMulAndAssignExpression(DOMMulAndAssignExpression domExpression);
	T visitDivAndAssignExpression(DOMDivAndAssignExpression domExpression);
	T visitAssignmentExpression(DOMAssignmentExpression domExpression);
}
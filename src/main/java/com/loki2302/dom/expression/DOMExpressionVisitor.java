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
}
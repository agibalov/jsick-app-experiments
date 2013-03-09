package com.loki2302.dom;

public interface DOMExpressionVisitor<T> {
	T visitIntLiteralExpression(DOMIntLiteralExpression domExpression);
	T visitDoubleLiteralExpression(DOMDoubleLiteralExpression domExpression);
	T visitAddExpression(DOMAddExpression domExpression);
}
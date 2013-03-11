package com.loki2302.dom.expression;

public class DOMAndExpression extends DOMBinaryExpression {
	public DOMAndExpression(DOMExpression leftExpression, DOMExpression rightExpression) {
		super(leftExpression, rightExpression);
	}

	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitAndExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("(%s&&%s)", getLeftExpression(), getRightExpression());
	}	
}
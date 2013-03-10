package com.loki2302.dom.expression;

public class DOMAddExpression extends DOMBinaryExpression {	
	public DOMAddExpression(DOMExpression leftExpression, DOMExpression rightExpression) {
		super(leftExpression, rightExpression);
	}

	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitAddExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("(%s+%s)", getLeftExpression(), getRightExpression());
	}
}
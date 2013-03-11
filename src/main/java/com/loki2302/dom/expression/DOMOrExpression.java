package com.loki2302.dom.expression;

public class DOMOrExpression extends DOMBinaryExpression {
	public DOMOrExpression(DOMExpression leftExpression, DOMExpression rightExpression) {
		super(leftExpression, rightExpression);
	}

	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitOrExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("(%s||%s)", getLeftExpression(), getRightExpression());
	}
}
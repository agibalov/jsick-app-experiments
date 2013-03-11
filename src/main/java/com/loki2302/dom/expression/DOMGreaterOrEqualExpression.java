package com.loki2302.dom.expression;

public class DOMGreaterOrEqualExpression extends DOMBinaryExpression {
	public DOMGreaterOrEqualExpression(DOMExpression leftExpression, DOMExpression rightExpression) {
		super(leftExpression, rightExpression);
	}

	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitGreaterOrEqualExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("(%s>=%s)", getLeftExpression(), getRightExpression());
	}
}
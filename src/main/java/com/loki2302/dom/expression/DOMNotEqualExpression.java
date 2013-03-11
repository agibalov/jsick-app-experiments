package com.loki2302.dom.expression;

public class DOMNotEqualExpression extends DOMBinaryExpression {
	public DOMNotEqualExpression(DOMExpression leftExpression, DOMExpression rightExpression) {
		super(leftExpression, rightExpression);
	}

	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitNotEqualExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("(%s!=%s)", getLeftExpression(), getRightExpression());
	}
}
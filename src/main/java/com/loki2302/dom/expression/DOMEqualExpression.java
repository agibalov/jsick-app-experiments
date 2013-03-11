package com.loki2302.dom.expression;

public class DOMEqualExpression extends DOMBinaryExpression {
	public DOMEqualExpression(DOMExpression leftExpression, DOMExpression rightExpression) {
		super(leftExpression, rightExpression);
	}

	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitEqualExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("(%s==%s)", getLeftExpression(), getRightExpression());
	}
}
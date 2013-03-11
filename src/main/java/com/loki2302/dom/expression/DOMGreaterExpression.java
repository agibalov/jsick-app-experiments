package com.loki2302.dom.expression;

public class DOMGreaterExpression extends DOMBinaryExpression {
	public DOMGreaterExpression(DOMExpression leftExpression, DOMExpression rightExpression) {
		super(leftExpression, rightExpression);
	}

	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitGreaterExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("(%s>%s)", getLeftExpression(), getRightExpression());
	}
}
package com.loki2302.dom.expression;

public class DOMLessOrEqualExpression extends DOMBinaryExpression {
	public DOMLessOrEqualExpression(DOMExpression leftExpression, DOMExpression rightExpression) {
		super(leftExpression, rightExpression);
	}

	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitLessOrEqualExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("(%s<=%s)", getLeftExpression(), getRightExpression());
	}
}
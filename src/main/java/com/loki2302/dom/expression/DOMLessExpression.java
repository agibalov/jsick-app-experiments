package com.loki2302.dom.expression;

public class DOMLessExpression extends DOMBinaryExpression {
	public DOMLessExpression(DOMExpression leftExpression, DOMExpression rightExpression) {
		super(leftExpression, rightExpression);
	}

	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitLessExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("(%s<%s)", getLeftExpression(), getRightExpression());
	}
}
package com.loki2302.dom.expression;

public class DOMDivExpression extends DOMBinaryExpression {	
	public DOMDivExpression(DOMExpression leftExpression, DOMExpression rightExpression) {
		super(leftExpression, rightExpression);
	}

	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitDivExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("(%s/%s)", getLeftExpression(), getRightExpression());
	}
}
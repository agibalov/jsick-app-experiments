package com.loki2302.dom.expression;

public class DOMSubExpression implements DOMExpression {
	private final DOMExpression leftExpression;
	private final DOMExpression rightExpression;
	
	public DOMSubExpression(DOMExpression leftExpression, DOMExpression rightExpression) {
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
	}
	
	public DOMExpression getLeftExpression() {
		return leftExpression;
	}
	
	public DOMExpression getRightExpression() {
		return rightExpression;
	}

	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitSubExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("(%s-%s)", leftExpression, rightExpression);
	}
}
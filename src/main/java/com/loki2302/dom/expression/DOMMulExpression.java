package com.loki2302.dom.expression;

public class DOMMulExpression implements DOMExpression {
	private final DOMExpression leftExpression;
	private final DOMExpression rightExpression;
	
	public DOMMulExpression(DOMExpression leftExpression, DOMExpression rightExpression) {
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
		return visitor.visitMulExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("(%s*%s)", leftExpression, rightExpression);
	}
}
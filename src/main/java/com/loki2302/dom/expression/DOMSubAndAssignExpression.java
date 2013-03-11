package com.loki2302.dom.expression;

public class DOMSubAndAssignExpression extends DOMBinaryExpression {
	public DOMSubAndAssignExpression(
			DOMExpression leftExpression, 
			DOMExpression rightExpression) {
		super(leftExpression, rightExpression);
	}
	
	@Override
	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitSubAndAssignExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("(%s-=%s)", getLeftExpression(), getRightExpression());
	}
}
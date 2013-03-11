package com.loki2302.dom.expression;

public class DOMMulAndAssignExpression extends DOMBinaryExpression {
	public DOMMulAndAssignExpression(
			DOMExpression leftExpression, 
			DOMExpression rightExpression) {
		super(leftExpression, rightExpression);
	}
	
	@Override
	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitMulAndAssignExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("(%s*=%s)", getLeftExpression(), getRightExpression());
	}
}
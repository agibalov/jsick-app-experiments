package com.loki2302.dom.expression;

public class DOMAddAndAssignExpression extends DOMBinaryExpression {
	public DOMAddAndAssignExpression(
			DOMExpression leftExpression, 
			DOMExpression rightExpression) {
		super(leftExpression, rightExpression);
	}
	
	@Override
	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitAddAndAssignExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("(%s+=%s)", getLeftExpression(), getRightExpression());
	}
}
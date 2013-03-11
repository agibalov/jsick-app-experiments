package com.loki2302.dom.expression;

public class DOMDivAndAssignExpression extends DOMBinaryExpression {
	public DOMDivAndAssignExpression(
			DOMExpression leftExpression, 
			DOMExpression rightExpression) {
		super(leftExpression, rightExpression);
	}
	
	@Override
	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitDivAndAssignExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("(%s/=%s)", getLeftExpression(), getRightExpression());
	}
}
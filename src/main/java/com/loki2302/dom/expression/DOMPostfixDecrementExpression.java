package com.loki2302.dom.expression;

public class DOMPostfixDecrementExpression extends DOMUnaryExpression {
	public DOMPostfixDecrementExpression(DOMExpression innerExpression) {
		super(innerExpression);
	}

	@Override
	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitPostfixDecrementExpression(this);
	}	
	
	@Override
	public String toString() {
		return String.format("(%s--)", getInnerExpression());
	}
}
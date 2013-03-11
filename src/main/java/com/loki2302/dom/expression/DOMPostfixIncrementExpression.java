package com.loki2302.dom.expression;

public class DOMPostfixIncrementExpression extends DOMUnaryExpression {
	public DOMPostfixIncrementExpression(DOMExpression innerExpression) {
		super(innerExpression);
	}

	@Override
	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitPostfixIncrementExpression(this);
	}	
	
	@Override
	public String toString() {
		return String.format("(%s++)", getInnerExpression());
	}
}
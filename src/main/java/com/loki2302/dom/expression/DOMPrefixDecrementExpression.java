package com.loki2302.dom.expression;

public class DOMPrefixDecrementExpression extends DOMUnaryExpression {
	public DOMPrefixDecrementExpression(DOMExpression innerExpression) {
		super(innerExpression);
	}

	@Override
	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitPrefixDecrementExpression(this);
	}	
	
	@Override
	public String toString() {
		return String.format("(--%s)", getInnerExpression());
	}
}
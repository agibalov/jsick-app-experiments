package com.loki2302.dom.expression;

public class DOMPrefixIncrementExpression extends DOMUnaryExpression {
	public DOMPrefixIncrementExpression(DOMExpression innerExpression) {
		super(innerExpression);
	}

	@Override
	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitPrefixIncrementExpression(this);
	}	
	
	@Override
	public String toString() {
		return String.format("(++%s)", getInnerExpression());
	}
}
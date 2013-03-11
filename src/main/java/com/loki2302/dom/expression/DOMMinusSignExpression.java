package com.loki2302.dom.expression;

public class DOMMinusSignExpression extends DOMUnaryExpression {
	public DOMMinusSignExpression(DOMExpression innerExpression) {
		super(innerExpression);
	}

	@Override
	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitMinusSignExpression(this);
	}	
	
	@Override
	public String toString() {
		return String.format("(-%s)", getInnerExpression());
	}
}
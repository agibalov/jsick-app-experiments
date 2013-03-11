package com.loki2302.dom.expression;

public class DOMNotExpression extends DOMUnaryExpression {
	public DOMNotExpression(DOMExpression innerExpression) {
		super(innerExpression);
	}

	@Override
	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitNotExpression(this);
	}	
	
	@Override
	public String toString() {
		return String.format("(!%s)", getInnerExpression());
	}
}
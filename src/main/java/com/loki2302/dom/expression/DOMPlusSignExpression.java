package com.loki2302.dom.expression;

public class DOMPlusSignExpression extends DOMUnaryExpression {
	public DOMPlusSignExpression(DOMExpression innerExpression) {
		super(innerExpression);
	}

	@Override
	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitPlusSignExpression(this);
	}	
	
	@Override
	public String toString() {
		return String.format("(+%s)", getInnerExpression());
	}
}
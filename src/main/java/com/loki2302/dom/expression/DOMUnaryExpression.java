package com.loki2302.dom.expression;

public abstract class DOMUnaryExpression implements DOMExpression {
	private final DOMExpression innerExpression;
	
	protected DOMUnaryExpression(DOMExpression innerExpression) {
		this.innerExpression = innerExpression;
	}
	
	public DOMExpression getInnerExpression() {
		return innerExpression;
	}
}
package com.loki2302.dom;

public class DOMDoubleLiteralExpression implements DOMExpression {
	public DOMDoubleLiteralExpression() {    		
	}

	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitDoubleLiteralExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("doubleLiteral");
	}
}
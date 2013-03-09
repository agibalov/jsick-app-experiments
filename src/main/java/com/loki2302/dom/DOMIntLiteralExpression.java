package com.loki2302.dom;

public class DOMIntLiteralExpression implements DOMExpression {
	public DOMIntLiteralExpression() {    		
	}

	public <T> T accept(DOMExpressionVisitor<T> visitor) {			
		return visitor.visitIntLiteralExpression(this);
	}    	
	
	@Override
	public String toString() {
		return String.format("intLiteral");
	}
}
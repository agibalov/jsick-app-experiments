package com.loki2302.dom.expression.literal;

import com.loki2302.dom.expression.DOMExpression;
import com.loki2302.dom.expression.DOMExpressionVisitor;

public class DOMIntLiteralExpression implements DOMExpression {
	private final String stringValue; 
	
	public DOMIntLiteralExpression(String stringValue) {
		this.stringValue = stringValue;
	}
	
	public String getStringValue() {
		return stringValue;
	}

	public <T> T accept(DOMExpressionVisitor<T> visitor) {			
		return visitor.visitIntLiteralExpression(this);
	}    	
	
	@Override
	public String toString() {
		return String.format("intLiteral");
	}
}
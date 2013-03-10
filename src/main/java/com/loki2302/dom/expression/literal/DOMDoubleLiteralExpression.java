package com.loki2302.dom.expression.literal;

import com.loki2302.dom.expression.DOMExpression;
import com.loki2302.dom.expression.DOMExpressionVisitor;

public class DOMDoubleLiteralExpression implements DOMExpression {
	private final String stringValue;
	
	public DOMDoubleLiteralExpression(String stringValue) {
		this.stringValue = stringValue;
	}
	
	public String getStringValue() {
		return stringValue;
	}

	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitDoubleLiteralExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("doubleLiteral");
	}
}
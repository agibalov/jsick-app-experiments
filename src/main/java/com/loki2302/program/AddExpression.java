package com.loki2302.program;

import com.loki2302.dom.DOMElement;
import com.loki2302.type.Type;

public class AddExpression implements Expression {
	private final DOMElement domElement;
	private final Type type;
	private final Expression leftExpression;
	private final Expression rightExpression;
	
	public AddExpression(DOMElement domElement, Type type, Expression leftExpression, Expression rightExpression) {
		this.domElement = domElement;
		this.type = type;
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
	}
	
	public DOMElement getDOMElement() {
		return domElement;
	}
	
	public Expression getLeftExpression() {
		return leftExpression;
	}
	
	public Expression getRightExpression() {
		return rightExpression;
	}
	
	public Type getType() {
		return type;
	}

	@Override
	public String toString() {
		return String.format("(%s+%s)", leftExpression, rightExpression);
	}	
}
package com.loki2302.program;

import com.loki2302.dom.DOMElement;
import com.loki2302.type.Type;

public class CastExpression implements Expression {
	private final DOMElement domElement;
	private final Expression expression;
	private final Type type;
	
	public CastExpression(DOMElement domElement, Expression expression, Type type) {
		this.domElement = domElement;
		this.expression = expression;
		this.type = type;
	}
	
	public DOMElement getDOMElement() {
		return domElement;
	}
	
	public Expression getExpression() {
		return expression;
	}
	
	public Type getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return String.format("cast(%s as %s)", expression, type);
	}
}
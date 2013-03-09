package com.loki2302.program;

import com.loki2302.dom.DOMElement;
import com.loki2302.type.Type;

public class IntLiteralExpression implements Expression {
	private final DOMElement domElement;
	private final Type type;
	
	public IntLiteralExpression(DOMElement domElement, Type type) {
		this.domElement = domElement;
		this.type = type;		
	}
	
	public DOMElement getDOMElement() {
		return domElement;
	}
	
	public Type getType()  {
		return type;
	}
	
	@Override
	public String toString() {
		return String.format("intLiteral");
	}
}
package com.loki2302.program;

import com.loki2302.dom.DOMElement;
import com.loki2302.type.Type;

public class IntLiteralExpression implements Expression {
	private final DOMElement domElement;
	private final Type type;
	private final int value;
	
	public IntLiteralExpression(DOMElement domElement, Type type, int value) {
		this.domElement = domElement;
		this.type = type;		
		this.value = value;
	}
	
	public DOMElement getDOMElement() {
		return domElement;
	}
	
	public Type getType()  {
		return type;
	}
	
	public int getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return String.format("int[%d]", value);
	}
}
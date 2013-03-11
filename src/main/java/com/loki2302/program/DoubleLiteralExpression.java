package com.loki2302.program;

import com.loki2302.dom.DOMElement;
import com.loki2302.type.Type;

public class DoubleLiteralExpression implements Expression {
	private final DOMElement domElement;
	private final Type type;
	private final double value;
	
	public DoubleLiteralExpression(DOMElement domElement, Type type, double value) {
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
	
	public double getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return String.format("double[%f]", getValue());
	}
}
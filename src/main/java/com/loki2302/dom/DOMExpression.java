package com.loki2302.dom;

public interface DOMExpression extends DOMElement {
	<T> T accept(DOMExpressionVisitor<T> visitor);
}
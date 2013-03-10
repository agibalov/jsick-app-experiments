package com.loki2302.dom.expression;

import com.loki2302.dom.DOMElement;

public interface DOMExpression extends DOMElement {
	<T> T accept(DOMExpressionVisitor<T> visitor);
}
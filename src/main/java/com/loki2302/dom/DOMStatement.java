package com.loki2302.dom;

public interface DOMStatement extends DOMElement {
	<T> T accept(DOMStatementVisitor<T> statementVisitor);
}
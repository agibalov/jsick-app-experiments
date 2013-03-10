package com.loki2302.dom.statement;

import com.loki2302.dom.DOMElement;

public interface DOMStatement extends DOMElement {
	<T> T accept(DOMStatementVisitor<T> statementVisitor);
}
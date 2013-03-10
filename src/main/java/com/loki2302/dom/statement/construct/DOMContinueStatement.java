package com.loki2302.dom.statement.construct;

import com.loki2302.dom.statement.DOMStatement;
import com.loki2302.dom.statement.DOMStatementVisitor;

public class DOMContinueStatement implements DOMStatement {
	@Override
	public <T> T accept(DOMStatementVisitor<T> statementVisitor) {
		return statementVisitor.visitContinueStatement(this);
	}	
}
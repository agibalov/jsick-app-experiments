package com.loki2302.dom.statement;

import com.loki2302.dom.expression.DOMExpression;

public class DOMPrintStatement implements DOMStatement {
	private final DOMExpression expression;
	
	public DOMPrintStatement(DOMExpression expression) {
		this.expression = expression;
	}
	
	public DOMExpression getExpression() {
		return expression;
	}

	@Override
	public <T> T accept(DOMStatementVisitor<T> statementVisitor) {			
		return statementVisitor.visitPrintStatement(this);
	}
}
package com.loki2302.dom;

public class DOMExpressionStatement implements DOMStatement {		
	private final DOMExpression expression;
	
	public DOMExpressionStatement(DOMExpression expression) {
		this.expression = expression;
	}
	
	public DOMExpression getExpression() {
		return expression;
	}

	public <T> T accept(DOMStatementVisitor<T> statementVisitor) {
		return statementVisitor.visitExpressionStatement(this);
	}		
}
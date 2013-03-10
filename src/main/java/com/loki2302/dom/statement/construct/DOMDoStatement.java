package com.loki2302.dom.statement.construct;

import com.loki2302.dom.expression.DOMExpression;
import com.loki2302.dom.statement.DOMStatement;
import com.loki2302.dom.statement.DOMStatementVisitor;

public class DOMDoStatement implements DOMStatement {	
	private final DOMExpression conditionExpression;
	private final DOMStatement bodyStatement;
	
	public DOMDoStatement(
			DOMExpression conditionExpression, 
			DOMStatement bodyStatement) {
		this.conditionExpression = conditionExpression;
		this.bodyStatement = bodyStatement;
	}
	
	public DOMExpression getConditionExpression() {
		return conditionExpression;
	}
	
	public DOMStatement getBodyStatement() {
		return bodyStatement;
	}
	
	@Override
	public <T> T accept(DOMStatementVisitor<T> statementVisitor) {
		return statementVisitor.visitDoStatement(this);
	}	
}
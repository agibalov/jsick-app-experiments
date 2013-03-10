package com.loki2302.dom.statement.construct;

import com.loki2302.dom.expression.DOMExpression;
import com.loki2302.dom.statement.DOMStatement;
import com.loki2302.dom.statement.DOMStatementVisitor;
import com.loki2302.dom.statement.DOMVariableDefinitionStatement;

public class DOMForStatement implements DOMStatement {
	private final DOMVariableDefinitionStatement initializerStatement;
	private final DOMExpression conditionExpression;
	private final DOMStatement stepStatement;
	private final DOMStatement bodyStatement;
	
	public DOMForStatement(
			DOMVariableDefinitionStatement initializerStatement,
			DOMExpression conditionExpression,
			DOMStatement stepStatement,
			DOMStatement bodyStatement) {
		this.initializerStatement = initializerStatement;
		this.conditionExpression = conditionExpression;
		this.stepStatement = stepStatement;
		this.bodyStatement = bodyStatement;
	}
	
	public DOMVariableDefinitionStatement getInitializerStatement() {
		return initializerStatement;
	}
	
	public DOMExpression getConditionExpression() {
		return conditionExpression;
	}
	
	public DOMStatement getStepStatement() {
		return stepStatement;
	}
	
	public DOMStatement getBodyStatement() {
		return bodyStatement;
	}
	
	@Override
	public <T> T accept(DOMStatementVisitor<T> statementVisitor) {
		return statementVisitor.visitForStatement(this);
	}	
}
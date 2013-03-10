package com.loki2302.dom.statement.construct;

import com.loki2302.dom.expression.DOMExpression;
import com.loki2302.dom.statement.DOMStatement;
import com.loki2302.dom.statement.DOMStatementVisitor;

public class DOMIfStatement implements DOMStatement {
	private final DOMExpression conditionExpression;
	private final DOMStatement trueBranchBody;
	private final DOMStatement falseBranchBody;	
	
	public DOMIfStatement(
			DOMExpression conditionExpression,
			DOMStatement trueBranchBody,
			DOMStatement falseBranchBody) {
		this.conditionExpression = conditionExpression;
		this.trueBranchBody = trueBranchBody;
		this.falseBranchBody = falseBranchBody;
	}
	
	public DOMExpression getConditionExpression() {
		return conditionExpression;
	}
	
	public DOMStatement getTrueBranchBody() {
		return trueBranchBody;
	}
	
	public DOMStatement getFalseBranchBody() {
		return falseBranchBody;
	}
	
	@Override
	public <T> T accept(DOMStatementVisitor<T> statementVisitor) {
		return statementVisitor.visitIfStatement(this);
	}	
}
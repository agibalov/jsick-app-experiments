package com.loki2302.program;

public class ExpressionStatement implements Statement {
	private final Expression expression;
	
	public ExpressionStatement(Expression expression) {
		this.expression = expression;
	}
	
	public Expression getExpression() {
		return expression;
	}
}
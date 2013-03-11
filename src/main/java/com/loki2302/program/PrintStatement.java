package com.loki2302.program;

public class PrintStatement implements Statement {
	private final Expression expression;
	
	public PrintStatement(Expression expression) {
		this.expression = expression;
	}
	
	public Expression getExpression() {
		return expression;
	}
	
	@Override
	public String toString() {
		return String.format("print %s", getExpression());
	}
}
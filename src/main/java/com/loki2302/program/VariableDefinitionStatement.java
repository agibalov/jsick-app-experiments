package com.loki2302.program;

import com.loki2302.type.Type;

public class VariableDefinitionStatement implements Statement {
	private final Type type;
	private final String variableName;
	private final Expression initializerExpression;
	
	public VariableDefinitionStatement(Type type, String variableName, Expression initializerExpression) {
		this.type = type;
		this.variableName = variableName;
		this.initializerExpression = initializerExpression;
	}
	
	public Type getType() {
		return type;
	}
	
	public String getVariableName() {
		return variableName;
	}
	
	public Expression getInitializerExpression() {
		return initializerExpression;
	}
	
	@Override
	public String toString() {
		return String.format("%s[%s]=%s", variableName, type, initializerExpression);
	}
}
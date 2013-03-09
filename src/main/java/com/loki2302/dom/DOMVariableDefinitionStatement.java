package com.loki2302.dom;

public class DOMVariableDefinitionStatement implements DOMStatement {
	private final DOMTypeDescriptor typeDescriptor;
	private final String variableName;
	private final DOMExpression initializerExpression;
	
	public DOMVariableDefinitionStatement(DOMTypeDescriptor typeDescriptor, String variableName, DOMExpression initializerExpression) {
		this.typeDescriptor = typeDescriptor;
		this.variableName = variableName;
		this.initializerExpression = initializerExpression;
	}
	
	public DOMTypeDescriptor getTypeDescriptor() {
		return typeDescriptor;
	}
	
	public String getVariableName() {
		return variableName;
	}
	
	public DOMExpression getInitializerExpression() {
		return initializerExpression;
	}

	public <T> T accept(DOMStatementVisitor<T> statementVisitor) {
		return statementVisitor.visitVariableDefinitionStatement(this);
	}
}
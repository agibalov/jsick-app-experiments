package com.loki2302.dom;


public interface DOMStatementVisitor<T> {
	T visitExpressionStatement(DOMExpressionStatement statement);
	T visitVariableDefinitionStatement(DOMVariableDefinitionStatement statement);
}
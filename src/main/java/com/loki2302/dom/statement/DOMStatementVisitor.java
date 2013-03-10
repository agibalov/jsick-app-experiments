package com.loki2302.dom.statement;

import com.loki2302.dom.statement.construct.DOMBreakStatement;
import com.loki2302.dom.statement.construct.DOMContinueStatement;
import com.loki2302.dom.statement.construct.DOMDoStatement;
import com.loki2302.dom.statement.construct.DOMForStatement;
import com.loki2302.dom.statement.construct.DOMIfStatement;
import com.loki2302.dom.statement.construct.DOMWhileStatement;

public interface DOMStatementVisitor<T> {
	T visitExpressionStatement(DOMExpressionStatement statement);
	T visitVariableDefinitionStatement(DOMVariableDefinitionStatement statement);
	T visitPrintStatement(DOMPrintStatement statement);
	T visitIfStatement(DOMIfStatement statement);
	T visitForStatement(DOMForStatement statement);
	T visitWhileStatement(DOMWhileStatement statement);
	T visitDoStatement(DOMDoStatement statement);
	T visitContinueStatement(DOMContinueStatement statement);
	T visitBreakStatement(DOMBreakStatement statement);
}
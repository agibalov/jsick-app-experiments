package com.loki2302.dom;

import java.util.List;

import com.loki2302.dom.statement.DOMStatement;

public class DOMProgram implements DOMElement {
	private final List<DOMStatement> statements;
	
	public DOMProgram(List<DOMStatement> statements) {
		this.statements = statements;
	}
	
	public List<DOMStatement> getStatements() {
		return statements;
	}
}
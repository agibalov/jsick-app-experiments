package com.loki2302.dom;

import java.util.List;

public class DOMProgram {
	private final List<DOMStatement> statements;
	
	public DOMProgram(List<DOMStatement> statements) {
		this.statements = statements;
	}
	
	public List<DOMStatement> getStatements() {
		return statements;
	}
}
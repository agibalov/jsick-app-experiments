package com.loki2302.program;

import java.util.List;


public class Program {
	private final List<Statement> statements;
	
	public Program(List<Statement> statements) {
		this.statements = statements;
	}
	
	public List<Statement> getStatements() {
		return statements;
	}
}
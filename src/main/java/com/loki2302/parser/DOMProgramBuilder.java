package com.loki2302.parser;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.dom.DOMProgram;
import com.loki2302.dom.statement.DOMStatement;

public class DOMProgramBuilder {
	private final List<DOMStatement> statements = new ArrayList<DOMStatement>();
	
	public boolean appendStatement(DOMStatement statement) {
		statements.add(statement);
		return true;
	}
	
	public DOMProgram build() {
		return new DOMProgram(statements);
	}
}
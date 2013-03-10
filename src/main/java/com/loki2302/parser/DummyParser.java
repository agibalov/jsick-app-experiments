package com.loki2302.parser;

import org.parboiled.Parboiled;
import org.parboiled.parserunners.RecoveringParseRunner;
import org.parboiled.support.ParsingResult;

import com.loki2302.dom.expression.DOMExpression;

public class DummyParser {
	public static void main(String[] args) {
		GrammarDefinition grammar = Parboiled.createParser(GrammarDefinition.class);
		ParsingResult<DOMExpression> result = 
				new RecoveringParseRunner<DOMExpression>(grammar.program())
				.run("1;");
		
		System.out.printf("is ok: %b\n", !result.hasErrors());
	}
}

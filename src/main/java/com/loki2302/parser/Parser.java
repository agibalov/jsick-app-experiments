package com.loki2302.parser;

import org.parboiled.Parboiled;
import org.parboiled.parserunners.RecoveringParseRunner;
import org.parboiled.support.ParsingResult;

import com.loki2302.dom.DOMElement;
import com.loki2302.dom.DOMProgram;
import com.loki2302.evaluation.FailureReason;

public class Parser {
	public ParserResult parse(String code) {
		LanguageParser grammar = Parboiled.createParser(LanguageParser.class);
		ParsingResult<DOMElement> result = 
				new RecoveringParseRunner<DOMElement>(grammar.program())
				.run(code);
		
		if(result.hasErrors()) {
			return ParserResult.fail((FailureReason)null);
		}
		
		return ParserResult.ok((DOMProgram)result.valueStack.peek());
	}
}
package com.loki2302.parser;


import org.parboiled.BaseParser;
import org.parboiled.Rule;

import com.loki2302.dom.DOMElement;
import com.loki2302.dom.expression.DOMExpression;
import com.loki2302.dom.expression.literal.DOMDoubleLiteralExpression;
import com.loki2302.dom.expression.literal.DOMFalseBoolLiteralExpression;
import com.loki2302.dom.expression.literal.DOMIntLiteralExpression;
import com.loki2302.dom.expression.literal.DOMNullLiteralExpression;
import com.loki2302.dom.expression.literal.DOMTrueBoolLiteralExpression;
import com.loki2302.dom.statement.DOMExpressionStatement;
import com.loki2302.dom.statement.DOMStatement;
import com.loki2302.dom.statement.construct.DOMBreakStatement;

public class LanguageParser extends BaseParser<DOMElement> {	
	public Rule program() {
		DOMProgramBuilder programBuilder = new DOMProgramBuilder();
		return Sequence(
				OneOrMore(
						statement(),
						ACTION(programBuilder.appendStatement((DOMStatement)pop())),
						";"),
				EOI,
				push(programBuilder.build()));
	}	
	
	public Rule statement() {
		return FirstOf(
				ifStatement(),
				forStatement(),
				whileStatement(),
				doStatement(),
				continueStatement(),
				breakStatement(),
				variableDefinitionStatement(),
				printStatement(),
				expressionStatement());
	}
	
	public Rule variableDefinitionStatement() {
		return String("vardef");
	}
	
	public Rule printStatement() {
		return String("print");
	}
	
	public Rule expressionStatement() {
		return Sequence(
				expression(),
				push(new DOMExpressionStatement((DOMExpression)pop())));
	}
	
	public Rule ifStatement() {
		return String("if");
	}
	
	public Rule forStatement() {
		return String("for");
	}
	
	public Rule whileStatement() {
		return String("while");
	}
	
	public Rule doStatement() {
		return String("do");
	}
	
	public Rule continueStatement() {
		return Sequence(
				"continue",
				push(new DOMBreakStatement()));
	}
	
	public Rule breakStatement() {
		return Sequence(
				"break",
				push(new DOMBreakStatement()));
	}
	
	public Rule expression() {
		return literalExpression();
	}
	
	public Rule literalExpression() {
		return FirstOf(
				intLiteralExpression(),
				doubleLiteralExpression(),
				nullLiteralExpression(),
				boolLiteralExpression());
	}
	
	public Rule intLiteralExpression() {
		return Sequence(
				OneOrMore(CharRange('0', '9')),
				push(new DOMIntLiteralExpression(match())));		
	}
	
	public Rule doubleLiteralExpression() {
		return Sequence(
				OneOrMore(CharRange('0', '9')),
				".",
				OneOrMore(CharRange('0', '9')),
				push(new DOMDoubleLiteralExpression(match())));
	}
	
	public Rule nullLiteralExpression() {
		return Sequence(
				"null",
				push(new DOMNullLiteralExpression()));
	}
	
	public Rule boolLiteralExpression() {
		return FirstOf(
				trueLiteralExpression(),
				falseLiteralExpression());
	}
	
	public Rule trueLiteralExpression() {
		return Sequence(
				"true",
				push(new DOMTrueBoolLiteralExpression()));
	}
	
	public Rule falseLiteralExpression() {
		return Sequence(
				"false",
				push(new DOMFalseBoolLiteralExpression()));
	}
}

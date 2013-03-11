package com.loki2302.parser;


import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.support.StringVar;
import org.parboiled.support.Var;

import com.loki2302.dom.DOMElement;
import com.loki2302.dom.DOMNamedTypeDescriptor;
import com.loki2302.dom.DOMTypeDescriptor;
import com.loki2302.dom.expression.DOMExpression;
import com.loki2302.dom.expression.literal.DOMDoubleLiteralExpression;
import com.loki2302.dom.expression.literal.DOMFalseBoolLiteralExpression;
import com.loki2302.dom.expression.literal.DOMIntLiteralExpression;
import com.loki2302.dom.expression.literal.DOMNullLiteralExpression;
import com.loki2302.dom.expression.literal.DOMTrueBoolLiteralExpression;
import com.loki2302.dom.statement.DOMExpressionStatement;
import com.loki2302.dom.statement.DOMPrintStatement;
import com.loki2302.dom.statement.DOMStatement;
import com.loki2302.dom.statement.DOMVariableDefinitionStatement;
import com.loki2302.dom.statement.construct.DOMBreakStatement;
import com.loki2302.dom.statement.construct.DOMContinueStatement;
import com.loki2302.dom.statement.construct.DOMIfStatement;

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
		return Sequence(
				optionalGap(),
				FirstOf(
					ifStatement(),
					forStatement(),
					whileStatement(),
					doStatement(),
					continueStatement(),
					breakStatement(),
					variableDefinitionStatement(),
					printStatement(),
					expressionStatement()),
				optionalGap());
	}
	
	public Rule variableDefinitionStatement() {
		Var<DOMTypeDescriptor> typeDescriptor = new Var<DOMTypeDescriptor>();
		StringVar variableName = new StringVar();
		Var<DOMExpression> initializerExpression = new Var<DOMExpression>();
		return Sequence(
				typeName(),
				typeDescriptor.set((DOMTypeDescriptor)pop()),
				mandatoryGap(),
				Sequence(
						name(),
						variableName.set(match())
						),
				optionalGap(),
				'=',
				optionalGap(),
				Sequence(
						expression(),
						initializerExpression.set((DOMExpression)pop())
						),
				push(new DOMVariableDefinitionStatement(
						typeDescriptor.get(), 
						variableName.get(), 
						initializerExpression.get())));
	}
	
	public Rule printStatement() {		
		return Sequence(
				"print",
				optionalGap(),
				"(",
				expression(),
				")",
				push(new DOMPrintStatement((DOMExpression)pop())));
	}
	
	public Rule expressionStatement() {
		return Sequence(
				expression(),
				push(new DOMExpressionStatement((DOMExpression)pop())));
	}
	
	public Rule ifStatement() {
		Var<DOMExpression> conditionExpression = new Var<DOMExpression>();
		Var<DOMStatement> trueBranch = new Var<DOMStatement>();
		Var<DOMStatement> falseBranch = new Var<DOMStatement>();
		return Sequence(
				"if",
				optionalGap(),
				"(",
				Sequence(
						expression(),
						conditionExpression.set((DOMExpression)pop())
				),
				")",
				Sequence(
						statement(),
						trueBranch.set((DOMStatement)pop())
						),
				Optional(
						"else",
						Sequence(
								statement(),
								falseBranch.set((DOMStatement)pop()))),
				push(new DOMIfStatement(
						conditionExpression.get(), 
						trueBranch.get(), 
						falseBranch.get())));
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
				push(new DOMContinueStatement()));
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
	
	public Rule typeName() {
		return Sequence(
				name(),
				push(new DOMNamedTypeDescriptor(match())));
	}
	
	public Rule name() {
		return OneOrMore(CharRange('a', 'z'));
	}
	
	public Rule mandatoryGap() {
		return OneOrMore(gap());
	}
	
	public Rule optionalGap() {
		return ZeroOrMore(gap());
	}
	
	public Rule gap() {
		return FirstOf(' ', '\t', '\n', '\r');
	}
}

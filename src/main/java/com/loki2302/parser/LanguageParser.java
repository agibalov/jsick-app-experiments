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
import com.loki2302.dom.statement.construct.DOMForStatement;
import com.loki2302.dom.statement.construct.DOMIfStatement;

public class LanguageParser extends BaseParser<DOMElement> {	
	public Rule program() {
		DOMProgramBuilder programBuilder = new DOMProgramBuilder();
		return Sequence(
				OneOrMore(
						statement(),
						ACTION(programBuilder.appendStatement((DOMStatement)pop())),
						statementSepTerm()),
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
				assignmentTerm(),
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
				openParensTerm(),
				expression(),
				closeParensTerm(),
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
				ifTerm(),
				openParensTerm(),
				Sequence(
						expression(),
						conditionExpression.set((DOMExpression)pop())
				),
				closeParensTerm(),
				Sequence(
						statement(),
						trueBranch.set((DOMStatement)pop())
						),
				Optional(
						elseTerm(),
						Sequence(
								statement(),
								falseBranch.set((DOMStatement)pop()))),
				push(new DOMIfStatement(
						conditionExpression.get(), 
						trueBranch.get(), 
						falseBranch.get())));
	}
	
	public Rule forStatement() {
		Var<DOMStatement> initializerStatement = new Var<DOMStatement>();
		Var<DOMExpression> conditionExpression = new Var<DOMExpression>();
		Var<DOMStatement> stepStatement = new Var<DOMStatement>();
		Var<DOMStatement> body = new Var<DOMStatement>();
		return Sequence(
				forTerm(),
				optionalGap(),
				openParensTerm(),
				Optional(
						Sequence(
								statement(),
								initializerStatement.set((DOMStatement)pop()))),
				statementSepTerm(),
				Optional(
						Sequence(
								expression(),
								conditionExpression.set((DOMExpression)pop()))),
				statementSepTerm(),
				Optional(
						Sequence(
								statement(),
								stepStatement.set((DOMStatement)pop()))),
				closeParensTerm(),
				Optional(
						Sequence(
								statement(),
								body.set((DOMStatement)pop()))),
				push(new DOMForStatement(
						initializerStatement.get(), 
						conditionExpression.get(), 
						stepStatement.get(), 
						body.get())));
	}
	
	public Rule whileStatement() {
		return String("while");
	}
	
	public Rule doStatement() {
		return String("do");
	}
	
	public Rule continueStatement() {
		return Sequence(
				continueTerm(),
				push(new DOMContinueStatement()));
	}
	
	public Rule breakStatement() {
		return Sequence(
				breakTerm(),
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
				nullTerm(),
				push(new DOMNullLiteralExpression()));
	}
	
	public Rule boolLiteralExpression() {
		return FirstOf(
				trueLiteralExpression(),
				falseLiteralExpression());
	}
	
	public Rule trueLiteralExpression() {
		return Sequence(
				trueTerm(),
				push(new DOMTrueBoolLiteralExpression()));
	}
	
	public Rule falseLiteralExpression() {
		return Sequence(
				falseTerm(),
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
	
	public Rule assignmentTerm() {
		return Sequence(optionalGap(), "=", optionalGap());
	}
	
	public Rule trueTerm() {
		return Sequence(optionalGap(), "true", optionalGap());
	}
	
	public Rule falseTerm() {
		return Sequence(optionalGap(), "false", optionalGap());
	}
	
	public Rule nullTerm() {
		return Sequence(optionalGap(), "null", optionalGap());
	}
	
	public Rule continueTerm() {
		return Sequence(optionalGap(), "continue", optionalGap());
	}
	
	public Rule breakTerm() {
		return Sequence(optionalGap(), "break", optionalGap());
	}
	
	public Rule forTerm() {
		return Sequence(optionalGap(), "for", optionalGap());
	}
	
	public Rule ifTerm() {
		return Sequence(optionalGap(), "if", optionalGap());
	}
	
	public Rule elseTerm() {
		return Sequence(optionalGap(), "else", optionalGap());
	}
	
	public Rule statementSepTerm() {
		return Sequence(optionalGap(), ";", optionalGap());
	}
	
	public Rule openParensTerm() {
		return Sequence(optionalGap(), "(", optionalGap());
	}
	
	public Rule closeParensTerm() {
		return Sequence(optionalGap(), ")", optionalGap());
	}
}

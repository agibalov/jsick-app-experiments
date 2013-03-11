package com.loki2302;


import com.google.inject.Inject;
import com.loki2302.dom.expression.DOMAddAndAssignExpression;
import com.loki2302.dom.expression.DOMAddExpression;
import com.loki2302.dom.expression.DOMAndExpression;
import com.loki2302.dom.expression.DOMAssignmentExpression;
import com.loki2302.dom.expression.DOMDivAndAssignExpression;
import com.loki2302.dom.expression.DOMDivExpression;
import com.loki2302.dom.expression.DOMEqualExpression;
import com.loki2302.dom.expression.DOMExpression;
import com.loki2302.dom.expression.DOMExpressionVisitor;
import com.loki2302.dom.expression.DOMGreaterExpression;
import com.loki2302.dom.expression.DOMGreaterOrEqualExpression;
import com.loki2302.dom.expression.DOMLessExpression;
import com.loki2302.dom.expression.DOMLessOrEqualExpression;
import com.loki2302.dom.expression.DOMMinusSignExpression;
import com.loki2302.dom.expression.DOMMulAndAssignExpression;
import com.loki2302.dom.expression.DOMMulExpression;
import com.loki2302.dom.expression.DOMNotEqualExpression;
import com.loki2302.dom.expression.DOMNotExpression;
import com.loki2302.dom.expression.DOMOrExpression;
import com.loki2302.dom.expression.DOMPlusSignExpression;
import com.loki2302.dom.expression.DOMPostfixDecrementExpression;
import com.loki2302.dom.expression.DOMPostfixIncrementExpression;
import com.loki2302.dom.expression.DOMPrefixDecrementExpression;
import com.loki2302.dom.expression.DOMPrefixIncrementExpression;
import com.loki2302.dom.expression.DOMSubAndAssignExpression;
import com.loki2302.dom.expression.DOMSubExpression;
import com.loki2302.dom.expression.literal.DOMDoubleLiteralExpression;
import com.loki2302.dom.expression.literal.DOMFalseBoolLiteralExpression;
import com.loki2302.dom.expression.literal.DOMIntLiteralExpression;
import com.loki2302.dom.expression.literal.DOMNullLiteralExpression;
import com.loki2302.dom.expression.literal.DOMTrueBoolLiteralExpression;
import com.loki2302.evaluation.Context;
import com.loki2302.evaluation.ExpressionResult;

public class DOMExpressionEvaluator {	
	@Inject IntLiteralExpressionEvaluator intLiteralExpressionEvaluator;
	@Inject DoubleLiteralExpressionEvaluator doubleLiteralExpressionEvaluator;
	@Inject AddExpressionEvaluator addExpressionEvaluator;
	@Inject SubExpressionEvaluator subExpressionEvaluator;
	@Inject MulExpressionEvaluator mulExpressionEvaluator;
	@Inject DivExpressionEvaluator divExpressionEvaluator;
	@Inject NullLiteralExpressionEvaluator nullLiteralExpressionEvaluator;
	@Inject TrueLiteralExpressionEvaluator trueLiteralExpressionEvaluator;
	@Inject FalseLiteralExpressionEvaluator falseLiteralExpressionEvaluator;
	@Inject LessExpressionEvaluator lessExpressionEvaluator;
	@Inject LessOrEqualExpressionEvaluator lessOrEqualExpressionEvaluator;
	@Inject GreaterExpressionEvaluator greaterExpressionEvaluator;
	@Inject GreaterOrEqualExpressionEvaluator greaterOrEqualExpressionEvaluator;
	@Inject EqualExpressionEvaluator equalExpressionEvaluator;
	@Inject NotEqualExpressionEvaluator notEqualExpressionEvaluator;	
	@Inject NotExpressionEvaluator notExpressionEvaluator;
	@Inject AndExpressionEvaluator andExpressionEvaluator;
	@Inject OrExpressionEvaluator orExpressionEvaluator;
	@Inject MinusSignExpressionEvaluator minusSignExpressionEvaluator;
	@Inject PlusSignExpressionEvaluator plusSignExpressionEvaluator;
	@Inject PrefixIncrementExpressionEvaluator prefixIncrementExpressionEvaluator;
	@Inject PostfixIncrementExpressionEvaluator postfixIncrementExpressionEvaluator;
	@Inject PrefixDecrementExpressionEvaluator prefixDecrementExpressionEvaluator;
	@Inject PostfixDecrementExpressionEvaluator postfixDecrementExpressionEvaluator;
	@Inject AssignmentExpressionEvaluator assignmentExpressionEvaluator;
	@Inject AddAndAssignExpressionEvaluator addAndAssignExpressionEvaluator;
	@Inject SubAndAssignExpressionEvaluator subAndAssignExpressionEvaluator;
	@Inject MulAndAssignExpressionEvaluator mulAndAssignExpressionEvaluator;
	@Inject DivAndAssignExpressionEvaluator divAndAssignExpressionEvaluator;
	
	public ExpressionResult evaluateDOMExpression(final Context context, DOMExpression domExpression) {    	
    	return domExpression.accept(new DOMExpressionVisitor<ExpressionResult>() {
			public ExpressionResult visitIntLiteralExpression(DOMIntLiteralExpression domExpression) {	
				return intLiteralExpressionEvaluator.evaluateIntLiteralExpression(context, domExpression);
			}

			public ExpressionResult visitDoubleLiteralExpression(DOMDoubleLiteralExpression domExpression) {
				return doubleLiteralExpressionEvaluator.evaluateDoubleLiteralExpression(context, domExpression);
			}

			public ExpressionResult visitAddExpression(DOMAddExpression domExpression) {
				return addExpressionEvaluator.evaluateAddExpression(context, domExpression, DOMExpressionEvaluator.this);
			}

			public ExpressionResult visitSubExpression(DOMSubExpression domExpression) {
				return subExpressionEvaluator.evaluateSubExpression(domExpression);
			}

			public ExpressionResult visitMulExpression(DOMMulExpression domExpression) {
				return mulExpressionEvaluator.evaluateMulExpression(domExpression);
			}

			public ExpressionResult visitDivExpression(DOMDivExpression domExpression) {
				return divExpressionEvaluator.evaluateDivExpression(domExpression);
			}

			public ExpressionResult visitNullLiteralExpression(DOMNullLiteralExpression domExpression) {
				return nullLiteralExpressionEvaluator.evaluateNullLiteralExpression(domExpression);
			}

			public ExpressionResult visitTrueBoolLiteralExpression(DOMTrueBoolLiteralExpression domExpression) {
				return trueLiteralExpressionEvaluator.evaluateTrueLiteralExpression(domExpression);
			}

			public ExpressionResult visitFalseBoolLiteralExpression(DOMFalseBoolLiteralExpression domExpression) {
				return falseLiteralExpressionEvaluator.evaluateFalseLiteralExpression(domExpression);
			}

			public ExpressionResult visitLessExpression(DOMLessExpression domExpression) {
				return lessExpressionEvaluator.evaluateLessExpression(domExpression);
			}
			
			public ExpressionResult visitLessOrEqualExpression(DOMLessOrEqualExpression domExpression) {
				return lessOrEqualExpressionEvaluator.evaluateLessOrEqualExpression(domExpression);
			}
			
			public ExpressionResult visitEqualExpression(DOMEqualExpression domExpression) {
				return equalExpressionEvaluator.evaluateEqualExpression(domExpression);
			}
			
			public ExpressionResult visitNotEqualExpression(DOMNotEqualExpression domExpression) {
				return notEqualExpressionEvaluator.evaluateNotEqualExpression(domExpression);
			}

			public ExpressionResult visitGreaterExpression(DOMGreaterExpression domExpression) {
				return greaterExpressionEvaluator.evaluateGreaterExpression(domExpression);
			}
			
			public ExpressionResult visitGreaterOrEqualExpression(DOMGreaterOrEqualExpression domExpression) {
				return greaterOrEqualExpressionEvaluator.evaluateGreaterOrEqualExpression(domExpression);
			}

			public ExpressionResult visitAndExpression(DOMAndExpression domExpression) {
				return andExpressionEvaluator.evaluateAndExpression(domExpression);
			}

			public ExpressionResult visitOrExpression(DOMOrExpression domExpression) {
				return orExpressionEvaluator.evaluateOrExpression(domExpression);
			}

			public ExpressionResult visitNotExpression(DOMNotExpression domExpression) {
				return notExpressionEvaluator.evaluateNotExpression(domExpression);
			}
			
			public ExpressionResult visitMinusSignExpression(DOMMinusSignExpression domExpression) {
				return minusSignExpressionEvaluator.evaluateMinusSignExpression(domExpression);
			}

			public ExpressionResult visitPlusSignExpression(DOMPlusSignExpression domExpression) {
				return plusSignExpressionEvaluator.evaluatePlusSignExpression(domExpression);
			}
			
			public ExpressionResult visitPrefixIncrementExpression(DOMPrefixIncrementExpression domExpression) {
				return prefixIncrementExpressionEvaluator.evaluatePrefixIncrementExpression(domExpression);
			}
			
			public ExpressionResult visitPostfixIncrementExpression(DOMPostfixIncrementExpression domExpression) {
				return postfixIncrementExpressionEvaluator.evaluatePostfixIncrementExpression(domExpression);
			}

			public ExpressionResult visitPrefixDecrementExpression(DOMPrefixDecrementExpression domExpression) {
				return prefixDecrementExpressionEvaluator.evaluatePrefixDecrementExpression(domExpression);
			}

			public ExpressionResult visitPostfixDecrementExpression(DOMPostfixDecrementExpression domExpression) {
				return postfixDecrementExpressionEvaluator.evaluatePostfixDecrementExpression(domExpression);
			}

			public ExpressionResult visitAddAndAssignExpression(DOMAddAndAssignExpression domExpression) {
				return addAndAssignExpressionEvaluator.evaluateAddAndAssignExpression(domExpression);
			}

			public ExpressionResult visitSubAndAssignExpression(DOMSubAndAssignExpression domExpression) {
				return subAndAssignExpressionEvaluator.evaluateSubAndAssignExpression(domExpression);
			}
			
			public ExpressionResult visitMulAndAssignExpression(DOMMulAndAssignExpression domExpression) {
				return mulAndAssignExpressionEvaluator.evaluateMulAndAssignExpression(domExpression);
			}
			
			public ExpressionResult visitDivAndAssignExpression(DOMDivAndAssignExpression domExpression) {
				return divAndAssignExpressionEvaluator.evaluateDivAndAssignExpression(domExpression);
			}
			
			public ExpressionResult visitAssignmentExpression(DOMAssignmentExpression domExpression) {
				return assignmentExpressionEvaluator.evaluateAssignmentExpression(domExpression);
			}    		
    	});
    }
}
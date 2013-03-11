package com.loki2302;

import java.util.ArrayList;
import java.util.List;

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
import com.loki2302.evaluation.BadDoubleLiteralFailureReason;
import com.loki2302.evaluation.BadIntLiteralFailureReason;
import com.loki2302.evaluation.Context;
import com.loki2302.evaluation.ExpressionInErrorFailureReason;
import com.loki2302.evaluation.ExpressionResult;
import com.loki2302.evaluation.FailureReason;
import com.loki2302.evaluation.InternalErrorFailureReason;
import com.loki2302.program.AddExpression;
import com.loki2302.program.CastExpression;
import com.loki2302.program.DoubleLiteralExpression;
import com.loki2302.program.Expression;
import com.loki2302.program.IntLiteralExpression;

public class DOMExpressionEvaluator {
    public ExpressionResult evaluateDOMExpression(final Context context, DOMExpression domExpression) {    	
    	return domExpression.accept(new DOMExpressionVisitor<ExpressionResult>() {
			public ExpressionResult visitIntLiteralExpression(DOMIntLiteralExpression domExpression) {	
				String stringValue = domExpression.getStringValue();
				try {
					int intValue = Integer.parseInt(stringValue);
					return ExpressionResult.ok(new IntLiteralExpression(domExpression, context.intType, intValue)); 
				} catch(NumberFormatException e) {}
				
				return ExpressionResult.fail(new BadIntLiteralFailureReason());
			}

			public ExpressionResult visitDoubleLiteralExpression(DOMDoubleLiteralExpression domExpression) {
				String stringValue = domExpression.getStringValue();
				try {
					double intValue = Double.parseDouble(stringValue);
					return ExpressionResult.ok(new DoubleLiteralExpression(domExpression, context.doubleType, intValue)); 
				} catch(NumberFormatException e) {}
				
				return ExpressionResult.fail(new BadDoubleLiteralFailureReason());
			}

			public ExpressionResult visitAddExpression(DOMAddExpression domExpression) {
				ExpressionResult leftResult = domExpression.getLeftExpression().accept(this);
				ExpressionResult rightResult = domExpression.getRightExpression().accept(this);
				
				List<FailureReason> reasons = new ArrayList<FailureReason>();
				if(!leftResult.ok) {
					reasons.add(new ExpressionInErrorFailureReason(leftResult));
				}
				
				if(!rightResult.ok) {
					reasons.add(new ExpressionInErrorFailureReason(rightResult));
				}
				
				if(!reasons.isEmpty()) {
					return ExpressionResult.fail(reasons);
				}
				
				Expression left = leftResult.expression;
				Expression right = rightResult.expression;
				
				if(left.getType() == right.getType()) {
					return ExpressionResult.ok(new AddExpression(domExpression, left.getType(), left, right));
				} else if(left.getType() == context.intType && right.getType() == context.doubleType) {
					return ExpressionResult.ok(new AddExpression(
							domExpression,
							right.getType(), 
							new CastExpression(domExpression.getLeftExpression(), left, right.getType()), 
							right));
				} else if(left.getType() == context.doubleType && right.getType() == context.intType) {
					return ExpressionResult.ok(new AddExpression(
							domExpression,
							left.getType(), 
							left, 
							new CastExpression(domExpression.getRightExpression(), right, left.getType())));
				}
				
				return ExpressionResult.fail(new InternalErrorFailureReason());
			}

			public ExpressionResult visitSubExpression(DOMSubExpression domExpression) {
				throw new RuntimeException();
			}

			public ExpressionResult visitMulExpression(DOMMulExpression domExpression) {
				throw new RuntimeException();
			}

			public ExpressionResult visitDivExpression(DOMDivExpression domExpression) {
				throw new RuntimeException();
			}

			public ExpressionResult visitNullLiteralExpression(DOMNullLiteralExpression domExpression) {
				throw new RuntimeException();
			}

			public ExpressionResult visitTrueBoolLiteralExpression(DOMTrueBoolLiteralExpression domExpression) {
				throw new RuntimeException();
			}

			public ExpressionResult visitFalseBoolLiteralExpression(DOMFalseBoolLiteralExpression domExpression) {
				throw new RuntimeException();
			}

			public ExpressionResult visitLessExpression(DOMLessExpression domExpression) {
				throw new RuntimeException();
			}
			
			public ExpressionResult visitLessOrEqualExpression(DOMLessOrEqualExpression domExpression) {
				throw new RuntimeException();
			}
			
			public ExpressionResult visitEqualExpression(DOMEqualExpression domExpression) {
				throw new RuntimeException();
			}
			
			public ExpressionResult visitNotEqualExpression(DOMNotEqualExpression domExpression) {
				throw new RuntimeException();
			}

			public ExpressionResult visitGreaterExpression(DOMGreaterExpression domExpression) {
				throw new RuntimeException();
			}
			
			public ExpressionResult visitGreaterOrEqualExpression(DOMGreaterOrEqualExpression domExpression) {
				throw new RuntimeException();
			}

			public ExpressionResult visitAndExpression(DOMAndExpression domExpression) {
				throw new RuntimeException();
			}

			public ExpressionResult visitOrExpression(DOMOrExpression domExpression) {
				throw new RuntimeException();
			}

			public ExpressionResult visitNotExpression(DOMNotExpression domExpression) {
				throw new RuntimeException();
			}
			
			public ExpressionResult visitMinusSignExpression(DOMMinusSignExpression domExpression) {
				throw new RuntimeException();
			}

			public ExpressionResult visitPlusSignExpression(DOMPlusSignExpression domExpression) {
				throw new RuntimeException();
			}
			
			public ExpressionResult visitPrefixIncrementExpression(DOMPrefixIncrementExpression domExpression) {
				throw new RuntimeException();
			}
			
			public ExpressionResult visitPostfixIncrementExpression(DOMPostfixIncrementExpression domExpression) {
				throw new RuntimeException();
			}

			public ExpressionResult visitPrefixDecrementExpression(DOMPrefixDecrementExpression domExpression) {
				throw new RuntimeException();
			}

			public ExpressionResult visitPostfixDecrementExpression(DOMPostfixDecrementExpression domExpression) {
				throw new RuntimeException();
			}

			public ExpressionResult visitAddAndAssignExpression(DOMAddAndAssignExpression domExpression) {
				throw new RuntimeException();
			}

			public ExpressionResult visitSubAndAssignExpression(DOMSubAndAssignExpression domExpression) {
				throw new RuntimeException();
			}
			
			public ExpressionResult visitMulAndAssignExpression(DOMMulAndAssignExpression domExpression) {
				throw new RuntimeException();
			}
			
			public ExpressionResult visitDivAndAssignExpression(DOMDivAndAssignExpression domExpression) {
				throw new RuntimeException();
			}
			
			public ExpressionResult visitAssignmentExpression(DOMAssignmentExpression domExpression) {
				throw new RuntimeException();
			}    		
    	});
    }
}
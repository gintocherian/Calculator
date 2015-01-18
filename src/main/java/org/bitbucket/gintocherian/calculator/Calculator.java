/*
 * The core logic for the Calculator 
 */

package org.bitbucket.gintocherian.calculator;

import org.apache.log4j.Logger;

public class Calculator {
	private static final Logger logger = Logger.getLogger(Calculator.class);
	public enum Operator {ADD, SUB, MULT, DIV, LET}
	
	
	/*
	 * Empty constructor, just for logging
	 * 
	*/
	public Calculator()
	{
		logger.info("Created calculator object");
	}
	

	/*
	 * Primary interface method
	 * @param expression being evaluated
	 * 
	 * This method handles all incoming exception, on exception null is returned
	*/
	public Long expressionParser(String expression)
	{
		logger.debug("Incoming Expression : " + expression);
		
		//Removing all spaces
		expression = expression.replaceAll("\\s+","");
		
		Long output = null;
		
		try{
			output = callExpressionEvaluator(expression);
		}
		catch(SyntaxException exc){
			logger.error("Caught Exception : " + exc.getMessage());
		}
		return output; 
	}
	
	/*
	 * Main function for the recursive call
	 * 
	 * 	LET Operator:
	 * 		Evaluates the value expression, recurses if necessary
	 * 		Replaces the variable in the expression with the computed value
	 * 		calls the expressionEvaluator for the transformed expression
	 * 		i.e. let(a, 5, add(a, a)) => add(5, 5)   
	 * 
	 *  Other Operators: ADD, DIV, SUB, MULT
	 *  	Evaluates left expression, recurses if necessary 
	 *  	Evaluates right expression, recurses if necessary 
	 *  	Returns the computed value
	 *  	i.e add(5, 5) => 10
	 */
	private long expressionEvaluator(char[] expression, Operator parent) throws SyntaxException
	{
		logger.debug("expressionEvaluator : Expression = " + new String(expression) + " Parent = " + parent.toString());

		if(parent==Operator.LET)
		{
			LetOperands theOperands = new LetOperands();
			theOperands.setOperands(expression);
			theOperands.setOperandB(Long.toString(evaluateOperand(theOperands.getOperandB())));
			theOperands.refactorExpression();
			return callExpressionEvaluator(theOperands.getOperandC());			
		}
		else
		{
			Operands theOperands = new Operands();
			theOperands.setOperands(expression);
			long operA = 0, operB = 0;
			operA = evaluateOperand(theOperands.getOperandA());
			operB = evaluateOperand(theOperands.getOperandB());
			return evaluateArithematicExpression(parent, operA, operB);
		}
	}

	/*
	 * Evaluate if a string is an Integer else calls callExpressionEvaluator
	 */
	private long evaluateOperand(String operand)
	{
		
		if(!isInteger(operand))
		{
			return callExpressionEvaluator(operand);
		}
		else
		{
			return Integer.parseInt(operand);
		}
		
	}

	/*
	 * Removes the outer operand and passes this info to recursiveEvaluater
	 * Example :callExpressionEvaluator("add(1,2)")
	 *          >>Returns  expressionEvaluator("1,2", Operand.ADD)
	 */
	private long callExpressionEvaluator(String expression) throws SyntaxException
	{
		logger.debug("callExpressionEvaluator : " + expression);

		//Check if expression ends with a closing brace
		if(!DelimiterHelper.checksIfExpressionEndsCorrectly(expression))
			throw new SyntaxException("Expression dosen't end with parentheses");

		StringBuilder oper = new StringBuilder();
		Operator current = null;
		String subExpression = "";
		boolean valuesAssigned = false;

		for(int i = 0; i< expression.length(); i++)
		{
			char c = expression.charAt(i);
			if(DelimiterHelper.isOpenParentheses(c))
			{
				current = getOperator(oper.toString());
				subExpression = expression.substring(i+1, expression.length() - 1);
				valuesAssigned = true;
				break;
			}
			else oper.append(c);
		}

		if(!valuesAssigned)
			throw new SyntaxException("Never reached an Open parentheses");

		if(current==null)
			throw new SyntaxException("Operator not assigned");

		return expressionEvaluator(subExpression.toCharArray(), current);
	}
	
	/*
	 * This function evaluates arithmetic expressions and returns the result
	 * 
	 * Since the input value are in range Integer.MIN_VALUE..Integer.MAX_VALUE
	 * long data type should be enough to evaluate expressions
	*/
	private long evaluateArithematicExpression(Operator operator, long a, long b)
	{
		logger.debug("evaluateArithematicExpression - Operator : " + operator.toString() + ", Arg 1-"+ a +", Arg 2-" + b);

		switch(operator)
		{
		case ADD : return a+b;
		case SUB : return a-b;
		case MULT : return a*b;
		case DIV :
			if(b==0)
			{
				throw new SyntaxException("Division by 0 is undefined");
			}
			return a/b;
		}
		throw new SyntaxException("Unrecognized arithematic operator");
	}

	/*
	 * This function gets the appropriate operator
	 * else throws error
	 * 
	*/
	private Operator getOperator(String value)
	{
		if(value.equals("add"))
			return Operator.ADD;
		else if (value.equals("sub"))
			return Operator.SUB;
		else if (value.equals("mult"))
			return Operator.MULT;
		else if (value.equals("div"))
			return Operator.DIV;
		else if (value.equals("let"))
			return Operator.LET;
		else
			throw new SyntaxException("Unrecognized operator : " + value);
	}
	
	/*
	 * Helper function to determine if a String is an integer
	 * 
	*/
	private boolean isInteger(String s) 
	{
	    try 
	    { 
	        Integer.parseInt(s); 
	    } 
	    catch(NumberFormatException e) 
	    { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}

	/*
	 * Main method
	 * @param args[0] = expression
	 * Prints the result
	 * 
	*/
	public static void main(String[] args) {
		Calculator current = new Calculator();
		if(args[0]!="")
			System.out.println(current.expressionParser(args[0]));
	}

}

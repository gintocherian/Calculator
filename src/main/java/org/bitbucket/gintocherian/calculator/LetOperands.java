/*
 * Defines a specific class of operands i.e. LetOperands
 * here, 
 * OperandA = variable
 * OperandB = value
 * OperandC = expression
 * 
 */
package org.bitbucket.gintocherian.calculator;

public class LetOperands extends Operands
{
	protected String operandC;
	
	public LetOperands()
	{
		super();
	}

	public String getOperandC() {
		return operandC;
	}


	public void setOperandC(String operC) {
		this.operandC = operC;
	}

	/*
	 * Parses an expression into LetOperands 
	 */
	public void setOperands(char[] expression)
	{
		int braceCount = 0;
		int commaCount = 0;
		StringBuilder current = new StringBuilder();
		for(char c : expression)
		{
			current.append(c);
			if(DelimiterHelper.isOpenParentheses(c)) ++braceCount;
			else if(DelimiterHelper.isCloseParentheses(c)) --braceCount;
			else if(DelimiterHelper.isComma(c) && braceCount == 0)
			{
				//get rid of last character i.e. comma
				current.setLength(current.length() - 1);
				
				++commaCount;
				if(commaCount == 1)
				{
					this.operandA = current.toString();
					
					//Verifies that the variable is as defined i.e. within a..z and A..Z
					if (!this.operandA.matches("^[a-zA-Z]+$"))
						throw new SyntaxException("Variable of let operation should be in a..z and A..Z");
					
					current.setLength(0);
				}
				else
				{
					this.operandB = current.toString();
					current.setLength(0);
				}
			}
		}
		this.operandC = current.toString();
	}

	/*
	 * For a let expression replaces the expression with the value of the variable 
	 */
	public void refactorExpression()
	{
		StringBuilder replaced = new StringBuilder();
		StringBuilder runner = new StringBuilder();
		for(char c : operandC.toCharArray())
		{
			if(DelimiterHelper.isDelimiter(c))
			{
				if(runner.toString().equals(operandA))
				{
					replaced.append(operandB);
				}
				else
				{
					replaced.append(runner);
				}
				replaced.append(c);
				runner.setLength(0);
			}
			else
				runner.append(c);
		}
		this.operandC = replaced.toString();
	}
	
}
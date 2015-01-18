/*
 * Defines a general class of operands with two terms
 * This class takes the operand within a regular expression i.e.
 *  div(1,2)
 * 
 */
package org.bitbucket.gintocherian.calculator;

public class Operands
{
	protected String operandA;
	protected String operandB;	

	public Operands()
	{
		
	}
	
	
	public void setOperandA(String operandA) {
		this.operandA = operandA;
	}


	public void setOperandB(String operandB) {
		this.operandB = operandB;
	}


	public String getOperandA() {
		return operandA;
	}


	public String getOperandB() {
		return operandB;
	}


	/*
	 * Splits an expression based on the middle comma
	 * @param = char array 
	 * 	i.e. "2,3" =>  operandA = "2" operandB = "3"
	 */
	public void setOperands(char[] expression)
	{
		int braceCount = 0;
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
				
				this.operandA = current.toString();
				current.setLength(0);
			}
		}
		this.operandB = current.toString();
	}

}
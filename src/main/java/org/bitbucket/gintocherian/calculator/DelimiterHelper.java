/*
 * This class defines some helper methods for handling delimiters
 */
package org.bitbucket.gintocherian.calculator;

public class DelimiterHelper {
	public static final char[] delimiters = {',', '(', ')'};
	public static boolean isOpenParentheses(char c)
	{
		return c == '(';
	}

	public static boolean isCloseParentheses(char c)
	{
		return c == ')';
	}
	
	public static boolean isComma(char c)
	{
		return c==',';
	}
	
	public static boolean checksIfExpressionEndsCorrectly(String expression)
	{
		return expression.endsWith(")");
	}
	
	public static boolean isDelimiter(char c)
	{
		for(char d : delimiters)
			if(c==d) return true;
		return false;
	}

}

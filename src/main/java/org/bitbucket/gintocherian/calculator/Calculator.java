/*
 * The core logic for the Project Calculator 
 */

package org.bitbucket.gintocherian.calculator;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Calculator {
	static final Logger logger = Logger.getLogger(Calculator.class);
	public enum Operator {ADD, SUB, MULT, DIV, LET}
	
	
	/*
	 * Empty constructor just to initialize the logging
	 * 
	*/
	public Calculator()
	{
        BasicConfigurator.configure();		
	}
	
	/*
	 * This function evaluates arithmetic expressions
	 * 
	*/
	public long evaluateArithematicExpression(Operator operator, long a, long b)
	{
		logger.debug("Operator : " + operator.toString() + ", Arg 1-"+ a +", Arg 2-" + b);

		switch(operator)
		{
		case ADD : return a+b;
		case SUB : return a-b;
		case MULT : return a*b;
		case DIV :
			if(b==0)
			{
				logger.error("evaluateArithematicExpression - Division by 0 is undefined");
				throw new IllegalArgumentException("Division by 0 is undefined");
			}

			return a/b;
		}
		logger.error("Unrecognized arithematic operator");
		return -1;
	}

	public static void main(String[] args) {
        System.out.println( "Hello World!" );
	}

}

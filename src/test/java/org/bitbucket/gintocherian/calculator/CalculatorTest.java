package org.bitbucket.gintocherian.calculator;

import org.apache.log4j.BasicConfigurator;

import junit.framework.TestCase;

public class CalculatorTest extends TestCase {

	public void testAdd() {
		Calculator current = new Calculator();
        assertEquals(new Long(3), current.expressionParser("add(1, 2)"));
	}

	public void testOneCorrectExpression() {
		Calculator current = new Calculator();
		assertEquals(new Long(3), current.expressionParser("add(1, 2)"));
		assertEquals(new Long(1), current.expressionParser("sub(3, 2)"));
		assertEquals(new Long(2), current.expressionParser("mult(1, 2)"));
		assertEquals(new Long(1), current.expressionParser("div(3, 2)"));
	}

	public void testCombinationCorrectExpression() {
		Calculator current = new Calculator();
		assertEquals(new Long(12), current.expressionParser("mult(add(2, 2), div(9, 3))"));
		assertEquals(new Long(7), current.expressionParser("add(1, mult(2, 3))"));
	}

	public void testDivideByZero() {
    	Calculator current = new Calculator();
		assertEquals(null, current.expressionParser("div(3, 0)"));
	}

	public void testIncorrectExpressions() {
    	Calculator current = new Calculator();
		assertEquals(null, current.expressionParser("  "));
		assertEquals(null, current.expressionParser("abcedf"));
		assertEquals(null, current.expressionParser("sqrt(4)"));
		assertEquals(null, current.expressionParser("div(3, 0))"));
		assertEquals(null, current.expressionParser("mult(add(2, 2), div(9, 3)"));
	}
	
	public void testPartitioningTwoOperands(){
		Operands current = new Operands();
		current.setOperands("2,27".toCharArray());
		assertEquals("2", current.operandA);
		assertEquals("27", current.operandB);
	}
}

package org.bitbucket.gintocherian.calculator;

import org.apache.log4j.BasicConfigurator;

import junit.framework.TestCase;

public class CalculatorTest extends TestCase {

	/*
	 * Add two numbers
	 */
	public void testAdd() {
		Calculator current = new Calculator();
        assertEquals(0, current.evaluateArithematicExpression(Calculator.Operator.ADD, 0, 0));
	}

}

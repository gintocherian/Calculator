package org.bitbucket.gintocherian.calculator;

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

	public void testPartitioningThreeOperands(){
		LetOperands current = new LetOperands();
		current.setOperands("a,27,div(a,3)".toCharArray());
		assertEquals("a", current.operandA);
		assertEquals("27", current.operandB);
		assertEquals("div(a,3)", current.operandC);
	}

	public void testIncorrectLetVariable(){
		LetOperands current = new LetOperands();
		try {
			current.setOperands("27,27,div(a,3)".toCharArray());
			fail( "Missing exception" );
		} catch( SyntaxException e ) {
		     assertEquals( "Variable of let operation should be in a..z and A..Z", e.getMessage() );
		}
	}

	public void testSingleLetStatements(){
    	Calculator current = new Calculator();
		assertEquals(new Long(10), current.expressionParser("let(a, 5, add(a, a))"));
	}

	public void testNestedLetStatements(){
    	Calculator current = new Calculator();
		assertEquals(new Long(55), current.expressionParser("let(a, 5, let(b, mult(a, 10), add(b, a)))"));
		assertEquals(new Long(40), current.expressionParser("let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b)))"));
		assertEquals(new Long(15), current.expressionParser("add(5, let(a, 5, add(a, a)))"));
	}
}

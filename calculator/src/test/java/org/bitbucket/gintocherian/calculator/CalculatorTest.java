package org.bitbucket.gintocherian.calculator;

import org.apache.log4j.BasicConfigurator;

import junit.framework.TestCase;

public class CalculatorTest extends TestCase {

	public void testTest() {
		Calculator current = new Calculator();
        BasicConfigurator.configure();
        assertEquals(0, current.test());
	}

}

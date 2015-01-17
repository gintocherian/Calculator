package org.bitbucket.gintocherian.calculator;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Calculator {
	static final Logger logger = Logger.getLogger(Calculator.class);
	
	public int test()
	{
		logger.debug("test method called");
		return 0;
	}
	
	public static void main(String[] args) {
        System.out.println( "Hello World!" );
        BasicConfigurator.configure();
        Calculator current = new Calculator();
        current.test();
	}

}

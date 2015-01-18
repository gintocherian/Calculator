package org.bitbucket.gintocherian.calculator;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * This exception is thrown when an error occurs due to syntax issues. 
 * This class is responsible for logging all errors.
 */
public class SyntaxException extends RuntimeException {

	private static final Logger logger = Logger.getLogger(SyntaxException.class);

    /**
     * Construct an exception with a message.
     *
     * @param message The reason for the exception
     */
    public SyntaxException(final String message) {
       super(message);
       logger.error(message);
    }

}
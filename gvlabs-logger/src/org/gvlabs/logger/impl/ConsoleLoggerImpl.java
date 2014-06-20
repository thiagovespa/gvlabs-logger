package org.gvlabs.logger.impl;

import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.gvlabs.logger.LoggerLevel;

/**
 * Console Logger Implementation
 * 
 * @author Thiago Galbiatti Vespa
 * 
 */
public class ConsoleLoggerImpl extends AbstractWriterLogger {

	/**
	 * Constructor
	 * 
	 * @param maxLogLevel
	 *            max log level
	 * @param prefix
	 *            prefix
	 * @throws UnsupportedEncodingException
	 *             utf-8 unsupported
	 */
	public ConsoleLoggerImpl(LoggerLevel maxLogLevel, String prefix)
			throws UnsupportedEncodingException {
		super(new OutputStreamWriter(System.err, "UTF-8"), maxLogLevel, prefix);
	}

}

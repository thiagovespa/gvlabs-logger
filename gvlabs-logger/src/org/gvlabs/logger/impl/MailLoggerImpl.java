package org.gvlabs.logger.impl;

import org.gvlabs.logger.Logger;
import org.gvlabs.logger.LoggerLevel;

/**
 * Mail Logger Implementation
 * 
 * @author Thiago Galbiatti Vespa
 * 
 */
public class MailLoggerImpl extends Logger {

	/**
	 * Constructor
	 * @param maxLogLevel max log level
	 * @param prefix prefix
	 */
	public MailLoggerImpl(LoggerLevel maxLogLevel, String prefix) {
		super(maxLogLevel, prefix);
	}

	@Override
	public void log(LoggerLevel level, String msg, Throwable e) {
		// TODO: Send e-mail
	}
}

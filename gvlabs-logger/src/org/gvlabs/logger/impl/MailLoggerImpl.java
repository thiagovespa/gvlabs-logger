package org.gvlabs.logger.impl;


import org.gvlabs.logger.Logger;
import org.gvlabs.logger.LoggerLevel;

public class MailLoggerImpl extends Logger {
	
	
	public MailLoggerImpl(LoggerLevel maxLogLevel, String prefix) {
		super(maxLogLevel, prefix);
	}



	@Override
	public void log(LoggerLevel level, String msg, Throwable e) {
		// TODO: Send e-mail
	}
}

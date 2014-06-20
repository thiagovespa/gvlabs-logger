package org.gvlabs.logger.impl;

import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.gvlabs.logger.LoggerLevel;

public class ConsoleLoggerImpl extends AbstractWriterLogger {

	public ConsoleLoggerImpl(LoggerLevel maxLogLevel, String prefix) throws UnsupportedEncodingException {
		super(new OutputStreamWriter(System.err, "UTF-8"), maxLogLevel, prefix);
	}

}

package org.gvlabs.logger.impl;

import java.io.OutputStreamWriter;

import org.gvlabs.logger.LoggerLevel;

public class ConsoleLoggerImpl extends AbstractWriterLogger {

	public ConsoleLoggerImpl(LoggerLevel maxLogLevel, String prefix) {
		super(new OutputStreamWriter(System.out), maxLogLevel, prefix);
	}

}

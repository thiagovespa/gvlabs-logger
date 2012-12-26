package org.gvlabs.logger.impl;

import java.io.IOException;
import java.lang.annotation.Annotation;

import org.gvlabs.logger.ConsoleLogger;
import org.gvlabs.logger.FileLogger;
import org.gvlabs.logger.Logger;
import org.gvlabs.logger.MailLogger;
import org.gvlabs.logger.engine.LoggerLevel;

public final class LoggerFactory {

	private LoggerFactory() {

	}

	public static Logger createLogger(Annotation ann, LoggerLevel maxLogLevel,
			Class<?> classToLog, String prefix) {
		if (ann.annotationType().equals(ConsoleLogger.class)) {
			ConsoleLogger annInst = (ConsoleLogger) ann;
			if (annInst.enabled()) {
				return new ConsoleLoggerImpl(maxLogLevel, prefix);
			}
		} else if (ann.annotationType().equals(FileLogger.class)) {
			FileLogger annInst = (FileLogger) ann;
			if (annInst.enabled()) {
				try {
					return new FileLoggerImpl(annInst.filePath(), maxLogLevel,
							prefix);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else if (ann.annotationType().equals(MailLogger.class)) {
			MailLogger annInst = (MailLogger) ann;
			if (annInst.enabled()) {
				return new MailLoggerImpl(maxLogLevel, prefix);
			}
		}
		return null;
	}
}
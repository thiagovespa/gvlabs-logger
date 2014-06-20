package org.gvlabs.logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.util.logging.Level;

import org.gvlabs.logger.impl.ConsoleLoggerImpl;
import org.gvlabs.logger.impl.FileLoggerImpl;
import org.gvlabs.logger.impl.MailLoggerImpl;

public final class LoggerFactory {

	private static final java.util.logging.Logger JLOGGER = java.util.logging.Logger.getAnonymousLogger();
	
	private LoggerFactory() {

	}

	public static Logger createLogger(Annotation ann, LoggerLevel maxLogLevel,
			Class<?> classToLog, String prefix) {
		if (ann.annotationType().equals(ConsoleLogger.class)) {
			ConsoleLogger annInst = (ConsoleLogger) ann;
			if (annInst.enabled()) {
				try {
					return new ConsoleLoggerImpl(maxLogLevel, prefix);
				} catch (UnsupportedEncodingException e) {
					JLOGGER.log(Level.SEVERE, "Unsupported Encoding", e);
				}
			}
		} else if (ann.annotationType().equals(FileLogger.class)) {
			FileLogger annInst = (FileLogger) ann;
			if (annInst.enabled()) {
				try {
					return new FileLoggerImpl(annInst.filePath(), maxLogLevel,
							prefix);
				} catch (IOException e) {
					JLOGGER.log(Level.SEVERE, "Unexpected error", e);
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

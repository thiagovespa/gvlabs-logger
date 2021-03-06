package org.gvlabs.logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.logging.Level;

import org.gvlabs.logger.impl.ConsoleLoggerImpl;
import org.gvlabs.logger.impl.FileLoggerImpl;
import org.gvlabs.logger.impl.MailLoggerImpl;

/**
 * Inject all log classes
 * 
 * @author Thiago Galbiatti Vespa
 * 
 */
public final class LoggerInjector {

	private static final String UNEXPECTED_ERROR_DESC = "Unexpected error";
	private static final java.util.logging.Logger JLOGGER = java.util.logging.Logger
			.getAnonymousLogger();

	private LoggerInjector() {

	}

	/**
	 * Default injection
	 */
	public static void initLog() {
		initLog(null);
	}

	/**
	 * Injection with prefix
	 * 
	 * @param prefix
	 *            prefix to log
	 */
	public static void initLog(String prefix) {
		Class<?> classToLog = getClassToInject();
		initLog(classToLog, prefix);
	}

	private static void initLog(Class<?> classToLog, String prefix) {

		LoggerLevel maxLogLevel = LoggerLevel.INFO; // Default
		if (classToLog.isAnnotationPresent(LoggerSettings.class)) {
			LoggerSettings classAnn = classToLog
					.getAnnotation(LoggerSettings.class);
			maxLogLevel = classAnn.level();
			// TODO: Pegar de arquivo tem preferencia
		}
		Field[] fields = classToLog.getDeclaredFields();
		for (final Field field : fields) {
			Logger loggerImpl = null;
			if (field.isAnnotationPresent(ConsoleLogger.class)) {
				loggerImpl = createLogger(
						field.getAnnotation(ConsoleLogger.class), maxLogLevel,
						classToLog, prefix);
			} else if (field.isAnnotationPresent(FileLogger.class)) {
				loggerImpl = createLogger(
						field.getAnnotation(FileLogger.class), maxLogLevel,
						classToLog, prefix);

			} else if (field.isAnnotationPresent(MailLogger.class)) {
				loggerImpl = createLogger(
						field.getAnnotation(MailLogger.class), maxLogLevel,
						classToLog, prefix);
			}
			if (loggerImpl != null) {

				// Injection
				final Logger loggerToSet = loggerImpl;
				java.security.AccessController
						.doPrivileged(new java.security.PrivilegedAction<Object>() {
							public Object run() {
								try {
									boolean isAccessible = field.isAccessible();
									field.setAccessible(true);
									field.set(null, loggerToSet);
									field.setAccessible(isAccessible);
								} catch (IllegalArgumentException e) {
									JLOGGER.log(Level.SEVERE,
											UNEXPECTED_ERROR_DESC, e);
								} catch (IllegalAccessException e) {
									JLOGGER.log(Level.SEVERE,
											UNEXPECTED_ERROR_DESC, e);
								}
								return null;
							}
						});

			}
		}

	}

	private static Class<?> getClassToInject() {
		final StackTraceElement[] stes = Thread.currentThread().getStackTrace();
		boolean foundLogClass = false;
		String className = null;
		for (StackTraceElement ste : stes) {
			className = ste.getClassName();
			if (className.equals(LoggerInjector.class.getCanonicalName())) {
				foundLogClass = true;
			} else {
				if (foundLogClass
						&& !className.startsWith("java.lang.reflect.")
						&& !className.startsWith("sun.reflect.")) {
					try {
						return Class.forName(className);
					} catch (ClassNotFoundException e) {
						JLOGGER.log(Level.SEVERE, UNEXPECTED_ERROR_DESC, e);
					}
				}

			}

		}

		return null;
	}

	private static Logger createLogger(Annotation ann, LoggerLevel maxLogLevel,
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
					JLOGGER.log(Level.SEVERE, UNEXPECTED_ERROR_DESC, e);
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

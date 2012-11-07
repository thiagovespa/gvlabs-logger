package org.gvlabs.logger.engine;

import static org.gvlabs.logger.engine.LoggerFactory.createLogger;

import java.lang.reflect.Field;
import java.security.AccessControlContext;

import org.gvlabs.logger.ConsoleLogger;
import org.gvlabs.logger.FileLogger;
import org.gvlabs.logger.Logger;
import org.gvlabs.logger.LoggerSettings;
import org.gvlabs.logger.MailLogger;

public final class LoggerInjector {

	private LoggerInjector() {

	}

	public static void initLog() {
		initLog(null);
	}

	public static void initLog(String prefix) {
		Class<?> classToLog = getClassToInject();
		initLog(classToLog, prefix);
	}

	public static void initLog(Class<?> classToLog, String prefix) {

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
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									e.printStackTrace();
								}
								return null;
							}
						});

			}
		}

	}

	public static Class<?> getClassToInject() {
		final StackTraceElement[] stes = Thread.currentThread().getStackTrace();
		boolean foundLogClass = false;
		String className = null;
		for (StackTraceElement ste : stes) {
			className = ste.getClassName();
			if (className.startsWith(LoggerInjector.class.getCanonicalName())) {
				foundLogClass = true;
			} else {
				if (foundLogClass) {
					if (!className.startsWith("java.lang.reflect.")
							&& !className.startsWith("sun.reflect.")) {
						try {
							return Class.forName(className);
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
				}
			}

		}

		return null;
	}
}

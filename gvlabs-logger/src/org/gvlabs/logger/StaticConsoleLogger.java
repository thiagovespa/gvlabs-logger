package org.gvlabs.logger;

import static org.gvlabs.logger.LoggerInjector.initLog;

import org.gvlabs.logger.LoggerLevel;

/**
 * Static Console Logger
 * 
 * @author Thiago Galbiatti Vespa
 * 
 */
public final class StaticConsoleLogger {

	private StaticConsoleLogger() {

	}

	static {
		initLog();
	}

	@ConsoleLogger
	private static Logger consoleLogManager;

	/**
	 * Log
	 * 
	 * @param level
	 *            level to log
	 * @param msg
	 *            message to log
	 * @param e
	 *            exception
	 */
	public static void log(LoggerLevel level, String msg, Throwable e) {
		consoleLogManager.log(level, msg, e);
	}

	/**
	 * Log - Error
	 * 
	 * @param msg
	 *            message to log
	 */
	public static void error(String msg) {
		consoleLogManager.error(msg);
	}

	/**
	 * Log - Error
	 * 
	 * @param msg
	 *            message to log
	 * @param e
	 *            exception
	 */
	public static void error(String msg, Throwable e) {
		consoleLogManager.error(msg, e);
	}

	/**
	 * Log - Info
	 * 
	 * @param msg
	 *            message to log
	 */
	public static void info(String msg) {
		consoleLogManager.info(msg);
	}

	/**
	 * Log - Debug
	 * 
	 * @param msg
	 *            message to log
	 */
	public static void debug(String msg) {
		consoleLogManager.debug(msg);
	}

	/**
	 * Log - Trace
	 * 
	 * @param msg
	 *            message to log
	 */
	public static void trace(String msg) {
		consoleLogManager.trace(msg);
	}

	/**
	 * Log - Warning
	 * 
	 * @param msg
	 *            message to log
	 */
	public static void warn(String msg) {
		consoleLogManager.warn(msg);
	}

}

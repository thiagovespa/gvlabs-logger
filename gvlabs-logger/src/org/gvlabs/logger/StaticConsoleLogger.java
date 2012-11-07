package org.gvlabs.logger;

import static org.gvlabs.logger.engine.LoggerInjector.initLog;

import org.gvlabs.logger.engine.LoggerLevel;

public final class StaticConsoleLogger {

	private StaticConsoleLogger() {

	}

	static {
		initLog("[StaticLoger]");
	}

	@ConsoleLogger
	private static Logger consoleLogManager;

	public static void log(LoggerLevel level, String msg, Throwable e) {
		consoleLogManager.log(level, msg, e);
	}

	public static void error(String msg) {
		consoleLogManager.error(msg);
	}

	public static void error(String msg, Throwable e) {
		consoleLogManager.error(msg, e);
	}

	public static void info(String msg) {
		consoleLogManager.info(msg);
	}

	public static void debug(String msg) {
		consoleLogManager.debug(msg);
	}

	public static void trace(String msg) {
		consoleLogManager.trace(msg);
	}

	public static void warn(String msg) {
		consoleLogManager.warn(msg);
	}

}

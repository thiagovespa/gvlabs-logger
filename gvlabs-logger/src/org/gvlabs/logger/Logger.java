package org.gvlabs.logger;

/**
 * Logger base class
 * 
 * @author Thiago Galbiatti Vespa
 * 
 */
public abstract class Logger {
	private LoggerLevel maxLogLevel;

	private String prefix;

	/**
	 * Base constructor
	 * 
	 * @param maxLogLevel
	 *            max log level
	 * @param prefix
	 *            prefix to trace
	 */
	public Logger(LoggerLevel maxLogLevel, String prefix) {
		super();
		this.maxLogLevel = maxLogLevel;
		this.prefix = prefix;
	}

	/**
	 * Max Logger Level
	 * 
	 * @return max logger level
	 */
	public LoggerLevel getMaxLogLevel() {
		return maxLogLevel;
	}

	/**
	 * Prefix to trace
	 * 
	 * @return prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * Do the log
	 * 
	 * @param level
	 *            log level
	 * @param msg
	 *            message to log
	 * @param e
	 *            exception
	 */
	public abstract void log(LoggerLevel level, String msg, Throwable e);

	/**
	 * Do the log
	 * 
	 * @param level
	 *            log level
	 * @param msg
	 *            message to log
	 */
	public void log(LoggerLevel level, String msg) {
		log(level, msg, null);
	}

	/**
	 * Log - level: error
	 * 
	 * @param msg
	 *            messsage to log
	 */
	public void error(String msg) {
		this.log(LoggerLevel.ERROR, msg);
	}

	/**
	 * Log - level: error
	 * 
	 * @param msg
	 *            message to log
	 * @param e
	 *            exception
	 */
	public void error(String msg, Throwable e) {
		this.log(LoggerLevel.ERROR, msg, e);
	}

	/**
	 * Log - level: info
	 * 
	 * @param msg
	 *            message to log
	 */
	public void info(String msg) {
		this.log(LoggerLevel.INFO, msg);
	}

	/**
	 * Log - level: debug
	 * 
	 * @param msg
	 *            message to log
	 */
	public void debug(String msg) {
		this.log(LoggerLevel.DEBUG, msg);
	}

	/**
	 * Log - level: trace
	 * 
	 * @param msg
	 *            message to log
	 */
	public void trace(String msg) {
		this.log(LoggerLevel.TRACE, msg);
	}

	/**
	 * Log - level: warn
	 * 
	 * @param msg
	 *            message to log
	 */
	public void warn(String msg) {
		this.log(LoggerLevel.WARN, msg);
	}

	/**
	 * Return the last stacktrace
	 * 
	 * @return last stacktrace
	 */
	public StackTraceElement getLastStackTrace() {
		final StackTraceElement[] stes = Thread.currentThread().getStackTrace();
		int i = stes.length - 1;
		for (; i >= 0; --i) {
			StackTraceElement ste = stes[i];
			String className = ste.getClassName();
			if (className.equals(StaticConsoleLogger.class.getCanonicalName())
					|| className.equals(Logger.class.getCanonicalName())) {
				break;
			}

		}
		return stes[i + 1];
	}

}

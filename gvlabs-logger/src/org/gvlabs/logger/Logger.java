package org.gvlabs.logger;

import org.gvlabs.logger.engine.LoggerLevel;

public abstract class Logger {
	protected LoggerLevel maxLogLevel;

	protected String prefix;

	// TODO: Colocar resource bundle?
	
	public Logger(LoggerLevel maxLogLevel, String prefix) {
		super();
		this.maxLogLevel = maxLogLevel;
		this.prefix = prefix;
	}

	public LoggerLevel getMaxLogLevel() {
		return maxLogLevel;
	}

	public String getPrefix() {
		return prefix;
	}

	public abstract void log(LoggerLevel level, String msg, Throwable e);

	public void log(LoggerLevel level, String msg) {
		log(level, msg, null);
	}

	public void error(String msg) {
		this.log(LoggerLevel.ERROR, msg);
	}

	public void error(String msg, Throwable e) {
		this.log(LoggerLevel.ERROR, msg, e);
	}

	public void info(String msg) {
		this.log(LoggerLevel.INFO, msg);
	}

	public void debug(String msg) {
		this.log(LoggerLevel.DEBUG, msg);
	}

	public void trace(String msg) {
		this.log(LoggerLevel.TRACE, msg);
	}

	public void warn(String msg) {
		this.log(LoggerLevel.WARN, msg);
	}
	
	public StackTraceElement getLastStackTrace() {
		final StackTraceElement[] stes = Thread.currentThread().getStackTrace();
		boolean foundLogClass = false;
		int i=stes.length-1;
		for(;i>=0;--i) {
			StackTraceElement ste = stes[i];
			String className = ste.getClassName();
			if(className.startsWith(Logger.class.getCanonicalName())) {
				foundLogClass = true;
			} else {
				if(foundLogClass) {
					if (!className.startsWith("java.lang.reflect.") && !className.startsWith("sun.reflect.")) {
						break;
					}
				}
			} 
		}
		return stes[i+1];
	}

}

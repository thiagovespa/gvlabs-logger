package org.gvlabs.logger.engine;

public enum LoggerLevel {
	TRACE, DEBUG, INFO, WARN, ERROR, NONE;

	public boolean greaterThanOrEqual(LoggerLevel level) {
		return this.ordinal() >= level.ordinal();
	}
}

package org.gvlabs.logger;

/**
 * 
 * Logger Level
 * 
 * @author Thiago Galbiatti Vespa
 * 
 */
public enum LoggerLevel {
	TRACE, DEBUG, INFO, WARN, ERROR, NONE;

	/**
	 * Compare log level
	 * 
	 * @param level
	 *            level to compar
	 * @return true if a level is greater than or equal this
	 */
	public boolean greaterThanOrEqual(LoggerLevel level) {
		return this.ordinal() >= level.ordinal();
	}
}

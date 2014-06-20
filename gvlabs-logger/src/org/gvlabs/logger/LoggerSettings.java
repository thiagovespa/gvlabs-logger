package org.gvlabs.logger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Logger Settings
 * 
 * @author Thiago Galbiatti Vespa
 * 
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface LoggerSettings {
	/**
	 * Max level to log
	 * 
	 * @return max level to log
	 */
	LoggerLevel level() default LoggerLevel.INFO;
}

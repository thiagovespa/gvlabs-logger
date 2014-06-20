package org.gvlabs.logger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Console Logger Annotation
 * 
 * @author Thiago Galbiatti Vespa
 *
 */
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ConsoleLogger {
	/**
	 * Is it enabled?
	 * 
	 * @return true if it is enabled, false otherwise
	 */
	boolean enabled() default true;
	
}

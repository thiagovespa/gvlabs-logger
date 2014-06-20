package org.gvlabs.logger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * File Logger Annotation
 * 
 * @author Thiago Galbiatti Vespa
 *
 */
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FileLogger {
	boolean enabled() default true;
	String filePath();
}

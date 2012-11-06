package org.gvlabs.logger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.gvlabs.logger.engine.LoggerLevel;

@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface LoggerSettings {
	LoggerLevel level() default LoggerLevel.INFO;
}

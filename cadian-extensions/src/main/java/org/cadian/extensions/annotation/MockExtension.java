package org.cadian.extensions.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The MockExtension annotation allows you to inject a mock of a given extension in place.
 * 
 * If you use MockExtension to inject a mock implementation, you must launch your unit test with
 * JVM arg -Dorg.cadian.ExtensionStore.mock=true
 * 
 * @author Joshua Hornsby
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MockExtension {
	Extension extension();
}

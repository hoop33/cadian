package org.cadian.extensions.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Extension annotation allows you to mark a subtype of an ExtensionPoint so that the
 * Extensions framework will make it available for query. The queryStrings field allows
 * you to specify query terms to associate with the annotated class.
 * 
 * @author Joshua Hornsby
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Extension {
	String[] queryStrings() default {};
}
package org.cadian.extensions.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The ExtensionPoint annotation is used to indicate that a class or interface will be the top level
 * type of an extension set. Subtypes of the annotated class or interface can be marked with the
 * Extension annotation to allow it to be returned as the result of an ExtensionQuery. 
 * 
 * @author Joshua Hornsby
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ExtensionPoint {
}
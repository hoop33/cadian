package org.cadian.extensions.core.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;

/**
 * A utility class for creating or accessing instances of classes.
 * 
 * @author Joshua Hornsby
 */
public final class InstanceUtil {
	/**
	 * Get an instance of the given class. If the class is a singleton, it will call the public static T getInstance() method. Otherwise, it will attempt to call the default no-arg constructor on the class.
	 * @param target Target Class
	 * @return Instance of the Target Class
	 * @throws CreateInstanceException A CreateInstanceException will be thrown if there was a problem creating or accessing the instance.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(Class<T> target) throws CreateInstanceException {
		T result = null;
		try {
			if(isSingleton(target)) {
				result = (T) target.getMethod("getInstance").invoke(target);
			} else {
				result = target.newInstance();
			}
		} catch (InstantiationException e) {
			throw new CreateInstanceException(target, "Is it a concrete class?", e);
		} catch (IllegalAccessException e) {
			throw new CreateInstanceException(target, "Is its default constructor visible?", e);
		} catch (Exception e) {
			throw new CreateInstanceException(target, "Does your singleton have a zero arg static getInstance method?", e);
		}
		return result;
	}

	/**
	 * Get an instance of the given class by calling the constructor that takes the arguments provided.
	 * @param target Target Class
	 * @param args Arguments - Order is Important
	 * @return Instance of the Target Class
	 * @throws CreateInstanceException  A CreateInstanceException will be thrown if there was a problem creating the instance.
	 */
	public static <T> T newInstance(Class<T> target, Object... args) throws CreateInstanceException {
		T result = null;
		try {
			result = target.getConstructor(getClassesOf(args)).newInstance(args);
		} catch (IllegalArgumentException e) {
			throw new CreateInstanceException(target, "Did your argument array contain the correct types arguments?", e);
		} catch (SecurityException e) {
			throw new CreateInstanceException(target, "", e);
		} catch (InstantiationException e) {
			throw new CreateInstanceException(target, "Is it a concrete class?", e);
		} catch (IllegalAccessException e) {
			throw new CreateInstanceException(target, "Is it a visible class?", e);
		} catch (InvocationTargetException e) {
			throw new CreateInstanceException(target, "", e);
		} catch (NoSuchMethodException e) {
			throw new CreateInstanceException(target, "Did your argument array contain the correct types arguments?", e);
		}
		return result;
	}

	/**
	 * Convert an array of Objects into an array of their Classes.
	 * @param args Objects
	 * @return Classes
	 */
	@SuppressWarnings("unchecked")
	protected static Class<?>[] getClassesOf(Object[] args) {
		Class<?>[] result = null;
		if(args != null) {
			result = (Class<?>[]) CollectionUtils.collect(Arrays.asList(args), new Transformer() {
				public Object transform(Object arg0) {
					return arg0.getClass();
				}
			}).toArray(new Class<?>[]{});	
		} else {
			result = new Class<?>[]{};
		}
		return result;
	}

	/**
	 * Determines if a Class is a singleton - get a getInstance method.
	 * @param target Target Class
	 * @return Singleton or Not
	 */
	protected static <T> boolean isSingleton(Class<T> target) {
		Method singletonMethod = null;
		try {
			singletonMethod = target.getMethod("getInstance");
		} catch (Exception e) {
			//
		}
		return singletonMethod != null && singletonMethod.getReturnType().isAssignableFrom(target);
	}

	/**
	 * Hidden constructor.
	 */
	private InstanceUtil(){}
}
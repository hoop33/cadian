package org.cadian.extensions.core;

import java.io.Serializable;

import org.cadian.extensions.query.ExactMatchQuery;
import org.cadian.extensions.query.ExtensionQuery;
import org.cadian.extensions.query.SimplePartialMatchQuery;

/**
 * Singleton front end for the Extensions framework.
 * 
 * @author Joshua Hornsby
 */
public class Extensions implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Get the Class that matches the query.
	 * @param query The Query
	 * @return The Class
	 */
	public static <T> Class<? extends T> getClassFor(ExtensionQuery<T> query) {
		return Instance.INSTANCE.get().getClassFor(query);
	}
	
	/**
	 * Get an instance of the Class that matches the query.
	 * @param query The Query
	 * @return The Instance
	 */
	public static <T> T getInstanceFor(ExtensionQuery<T> query) {
		return Instance.INSTANCE.get().getInstanceFor(query);
	}
	
	/**
	 * Get an instance of the Class that matches the given query, passing the provided arguments into its constructor.
	 * @param query The Query
	 * @param args The Arguments
	 * @return The Instance
	 */
	public static <T> T getInstanceFor(ExtensionQuery<T> query, Object... args) {
		return Instance.INSTANCE.get().getInstanceFor(query, args);
	}
	
	/**
	 * Get the Class that extends the given the ExtensionPoint and has all of the query Strings provided.
	 * @param extensionPoint The Extension Point
	 * @param queryStrings The Query Strings
	 * @return The Class
	 */
	public static <T> Class<? extends T> getExactClassFor(Class<T> extensionPoint, String... queryStrings) {
		return getClassFor(new ExactMatchQuery<T>(extensionPoint, queryStrings));
	}
	
	/**
	 * Get an instance of the Class that extends the given ExtensionPoint and has all of the query Strings provided.
	 * @param extensionPoint The Extension Point
	 * @param queryStrings The Query String
	 * @return The Instance
	 */
	public static <T> T getExactInstanceFor(Class<T> extensionPoint, String... queryStrings) {
		return getInstanceFor(new ExactMatchQuery<T>(extensionPoint, queryStrings));
	}
	
	/**
	 * Get an instance of the Class that extends the given ExtensionPoint and has all of the query String provided, passing the provided arguments into its constructor.
	 * @param extensionPoint The Extension Point
	 * @param args The Arguments
	 * @param queryStrings The Query Strings
	 * @return The Instance
	 */
	public static <T> T getExactInstanceFor(Class<T> extensionPoint, Object[] args, String... queryStrings) {
		return getInstanceFor(new ExactMatchQuery<T>(extensionPoint, queryStrings), args);
	}
	
	/**
	 * Get the Class that extends the given ExtensionPoint and is the best match for the query Strings (see SimplePartialMatchQuery).
	 * @param extensionPoint The Extension Point
	 * @param queryStrings The Query Strings
	 * @return The Instance
	 */
	public static <T> Class<? extends T> getBestClassFor(Class<T> extensionPoint, String... queryStrings) {
		return getClassFor(new SimplePartialMatchQuery<T>(extensionPoint, queryStrings));
	}
	
	/**
	 * Get an instance of the Class that extends the given ExtensionPoint and is the best match for the query Strings (see SimplePartialMatchQuery).
	 * @param extensionPoint The Extension Point
	 * @param queryStrings The Query Strings
	 * @return The Instance
	 */
	public static <T> T getBestInstanceFor(Class<T> extensionPoint, String... queryStrings) {
		return getInstanceFor(new SimplePartialMatchQuery<T>(extensionPoint, queryStrings));
	}
	
	/**
	 * Get an instance of the Class that extends the given ExtensionPoint and is the best match for the query Strings (see SimplePartialMatchQuery),
	 * passing the provided arguments into its constructor.
	 * @param extensionPoint The Extension Point
	 * @param args The Arguments
	 * @param queryStrings The Query Strings
	 * @return The Instance
	 */
	public static <T> T getBestInstanceFor(Class<T> extensionPoint, Object[] args, String... queryStrings) {
		return getInstanceFor(new SimplePartialMatchQuery<T>(extensionPoint, queryStrings), args);
	}
	
	/**
	 * Reinitialize the ExtensionPointStore contained in this singleton.
	 * @param rescanClassPath Rescan the classpath for ExtensionPoints and their Extensions
	 */
	public static void reinitialize(boolean rescanClassPath) {
		Instance.INSTANCE.get().init(rescanClassPath);
	}
	
	/**
	 * Reinitialize the ExtensionPointStore contained in this singleton, rescanning the classpath for ExtensionPoints and their Extensions.
	 */
	public static void reinitialize() {
		reinitialize(true);
	}
	
	/**
	 * Tell Extensions whether or not to use MockExtensions.
	 * @param injectMocks Inject Mocks or Not
	 */
	public static void injectMocks(boolean injectMocks) {
		if(injectMocks) {
			System.setProperty("org.cadian.ExtensionStore.mock", "true");
		} else {
			System.setProperty("org.cadian.ExtensionStore.mock", "false");
		}
	}
	
	/**
	 * Singleton enumeration.
	 * 
	 * @author Joshua Hornsby
	 */
	private enum Instance {
		INSTANCE();
		private ExtensionPointStore instance;
		private Instance() {
			instance = new ExtensionPointStore();
		}
		private ExtensionPointStore get() {
			return instance;
		}
	}
}
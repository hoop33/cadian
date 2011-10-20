package org.cadian.properties.core;

import org.cadian.properties.query.FallbackPropertyQuery;
import org.cadian.properties.query.PropertyQuery;
import org.cadian.properties.query.SimplePropertyQuery;

/**
 * Cadian Properties
 * 
 * This singleton object provides access to all of the properties contained
 * in property files (ending in .properties) found in the classpath.
 * 
 * @author Joshua Hornsby
 */
public class Properties {

	/**
	 * Execute the PropertyQuery on the PropertyStore.
	 * @param query A Query
	 * @return Resulting Value
	 */
	public static String get(PropertyQuery query) {
		return query.execute(getPropertyStore());
	}
	
	/**
	 * Get the property value identified by the given key.
	 * @param key The Key
	 * @return Resulting Value
	 */
	public static String get(String key) {
		return get(new SimplePropertyQuery(key));
	}
	
	/**
	 * Get the property value identified by the given value. If no value is found, the default value will be returned.
	 * @param key The Key
	 * @param defaultValue The Default Value
	 * @return Resulting Value
	 */
	public static String get(String key, String defaultValue) {
		return get(new SimplePropertyQuery(key, defaultValue));
	}
	
	/**
	 * Get the property value for the target class and key. The PropertyStore will be searched in the following way:
	 * <ol>
	 * 	<li>class+key</li>
	 * 	<li>all super classes+key</li>
	 * 	<li>all interfaces implemented by the class and its super classes+key</li>
	 * 	<li>key</li>
	 * </ol>
	 * For example, if you have classes:
	 * <ul>
	 * 	<li>class org.cadian.example.A implements org.cadian.example.I</li>
	 * 	<li>class org.cadian.example.B extends org.cadian.example.A implements org.cadian.example.F, java.io.Serializable</li>
	 * 	<li>class org.cadian.example.C extends org.cadian.example.B</li>
	 * </ul>
	 * And search for key "key", then the following keys will be tried:
	 * <ol>
	 * 	<li>org.cadian.example.A.key</li>
	 * 	<li>org.cadian.example.B.key</li>
	 * 	<li>org.cadian.example.C.key</li>
	 * 	<li>org.cadian.example.I.key</li>
	 * 	<li>org.cadian.example.F.key</li>
	 * 	<li>java.io.Serializable.key</li>
	 * 	<li>key</li>
	 * </ol>
	 * @param target Target Class
	 * @param key Key Suffix
	 * @return Resulting Value
	 */
	public static String get(Class<?> target, String key) {
		return get(new FallbackPropertyQuery(target, key));
	}
	
	/**
	 * Get the property value for the target class and key. The PropertyStore will be searched in the following way:
	 * <ol>
	 * 	<li>class+key</li>
	 * 	<li>all super classes+key</li>
	 * 	<li>all interfaces implemented by the class and its super classes+key</li>
	 * 	<li>key</li>
	 * </ol>
	 * For example, if you have classes:
	 * <ul>
	 * 	<li>class org.cadian.example.A implements org.cadian.example.I</li>
	 * 	<li>class org.cadian.example.B extends org.cadian.example.A implements org.cadian.example.F, java.io.Serializable</li>
	 * 	<li>class org.cadian.example.C extends org.cadian.example.B</li>
	 * </ul>
	 * And search for key "key", then the following keys will be tried:
	 * <ol>
	 * 	<li>org.cadian.example.A.key</li>
	 * 	<li>org.cadian.example.B.key</li>
	 * 	<li>org.cadian.example.C.key</li>
	 * 	<li>org.cadian.example.I.key</li>
	 * 	<li>org.cadian.example.F.key</li>
	 * 	<li>java.io.Serializable.key</li>
	 * 	<li>key</li>
	 * </ol>
	 * If the value still has not been found, the default value will be used.
	 * 
	 * @param target Target Class
	 * @param key Key Suffix
	 * @param defaultValue The Default Value
	 * @return Resulting Value
	 */
	public static String get(Class<?> target, String key, String defaultValue) {
		return get(new FallbackPropertyQuery(target, key, defaultValue));
	}
	
	/**
	 * Search the property store for the key using the full class name as the prefix. For example,
	 * if you use org.cadian.example.A.class and key "key", the full key will be
	 * "org.cadian.example.A.key".
	 * @param target Target Class
	 * @param key Key Suffix
	 * @return Resulting Value
	 */
	public static String getNoFallback(Class<?> target, String key) {
		return get(new SimplePropertyQuery(target, key));
	}
	
	/**
	 * Search the property store for the key using the full class name as the prefix. For example,
	 * if you use org.cadian.example.A.class and key "key", the full key will be
	 * "org.cadian.example.A.key".
	 * If the value still has not been found, the default value will be used.
	 * @param target Target Class
	 * @param key Key Suffix
	 * @param defaultValue The Default Value
	 * @return Resulting Value
	 */
	public static String getNoFallback(Class<?> target, String key, String defaultValue) {
		return get(new SimplePropertyQuery(target, key, defaultValue));
	}
	
	/**
	 * Get a reference to the singleton PropertyStore.
	 * @return The PropertyStore
	 */
	public static PropertyStore getPropertyStore() {
		return Instance.INSTANCE.get();
	}
	
	/**
	 * Singleton enumeration.
	 * 
	 * @author Joshua Hornsby
	 */
	private enum Instance {
		INSTANCE();
		private PropertyStore instance;
		private Instance() {
			instance = new PropertyStore();
		}
		private PropertyStore get() {
			return instance;
		}
	}
}
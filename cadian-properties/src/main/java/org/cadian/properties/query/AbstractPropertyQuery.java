package org.cadian.properties.query;

import org.cadian.properties.core.PropertyStore;

/**
 * An abstract PropertyQuery that provides some basic behavior for PropertyQuery types.
 * 
 * @author Joshua Hornsby
 */
public abstract class AbstractPropertyQuery implements PropertyQuery {
	private static final long serialVersionUID = 1L;
	private final Class<?> target;
	private final String key;
	private final String defaultValue;
	
	/**
	 * Create a new AbstractPropertyQuery.
	 * @param key The Key Suffix
	 */
	public AbstractPropertyQuery(String key) {
		this(null, key, null);
	}
	
	/**
	 * Create a new AbstractPropertyQuery.
	 * @param key The Key Suffix
	 * @param defaultValue The Default Value
	 */
	public AbstractPropertyQuery(String key, String defaultValue) {
		this(null, key, defaultValue);
	}
	
	/**
	 * Create a new AbstractPropertyQuery.
	 * @param target The Target Class
	 * @param key The Key Suffix
	 */
	public AbstractPropertyQuery(Class<?> target, String key) {
		this(target, key, null);
	}
	
	/**
	 * Create a new AbstractPropertyQuery.
	 * @param target The Target Class
	 * @param key The Key Suffix
	 * @param defaultValue The Default Value
	 */
	public AbstractPropertyQuery(Class<?> target, String key, String defaultValue) {
		this.target = target;
		this.key = key;
		this.defaultValue = defaultValue;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public abstract String execute(PropertyStore store);
	
	/**
	 * Build a key using the full name of the target class as the prefix and the key as the suffix.
	 * @param target The Target Class
	 * @param key The Key
	 * @return The Full Key
	 */
	protected String buildKey(Class<?> target, String key) {
		StringBuilder buffer = new StringBuilder();
		if(target != null) {
			buffer.append(target.getName()).append(".");
		}
		return buffer.append(key).toString();
	}

	/**
	 * @return the target
	 */
	protected Class<?> getTarget() {
		return target;
	}

	/**
	 * @return the key
	 */
	protected String getKey() {
		return key;
	}

	/**
	 * @return the defaultValue
	 */
	protected String getDefaultValue() {
		return defaultValue;
	}
}
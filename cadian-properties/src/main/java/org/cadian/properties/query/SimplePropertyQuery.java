package org.cadian.properties.query;

import org.cadian.properties.core.PropertyStore;

/**
 * A simple PropertyQuery that looks for the exact key provided.
 * 
 * @author Joshua Hornsby
 */
public class SimplePropertyQuery extends AbstractPropertyQuery {
	private static final long serialVersionUID = 1L;

	/**
	 * Create a new SimplePropertyQuery.
	 * @param target The Target Class
	 * @param key The Key Suffix
	 * @param defaultValue The Default Value
	 */
	public SimplePropertyQuery(Class<?> target, String key, String defaultValue) {
		super(target, key, defaultValue);
	}

	/**
	 * Create a new SimplePropertyQuery.
	 * @param target The Target Class
	 * @param key The Key Suffix
	 */
	public SimplePropertyQuery(Class<?> target, String key) {
		super(target, key);
	}

	/**
	 * Create a new SimplePropertyQuery.
	 * @param key The Key
	 * @param defaultValue The Default
	 */
	public SimplePropertyQuery(String key, String defaultValue) {
		super(key, defaultValue);
	}

	/**
	 * Create a new SimplePropertyQuery.
	 * @param key The Key
	 */
	public SimplePropertyQuery(String key) {
		super(key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String execute(PropertyStore store) {
		String result = store.get(buildKey(getTarget(), getKey()));
		if(result == null) {
			result = getDefaultValue();
		}
		return result;
	}
}
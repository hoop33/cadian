package org.cadian.extensions.query;

import java.util.Arrays;
import java.util.Map;

import org.cadian.extensions.core.ExtensionKey;

/**
 * An ExtensionQuery that finds the "best" match in the map for
 * the query strings provided.
 * 
 * @author Joshua Hornsby
 */
public abstract class MatchQuery<T> implements ExtensionQuery<T> {
	private static final long serialVersionUID = 1L;
	private final Class<T> extensionPoint;
	private final String[] queryStrings;
	private final boolean fallbackToDefault;

	/**
	 * Create a MatchQuery that allows for fallback to a base entry if it exists.
	 * @param extensionPoint The Extension Point
	 * @param queryStrings The Query Strings
	 */
	public MatchQuery(Class<T> extensionPoint, String... queryStrings) {
		this(extensionPoint, true, queryStrings);
	}
	
	/**
	 * Create a MatchQuery that allows for fallback to a base entry if it exists.
	 * @param extensionPoint The Extension Point
	 * @param fallbackToDefault Allow Fallback to Default
	 * @param queryStrings The Query Strings
	 */
	public MatchQuery(Class<T> extensionPoint, boolean fallbackToDefault, String... queryStrings) {
		this.extensionPoint = extensionPoint;
		this.fallbackToDefault = fallbackToDefault;
		this.queryStrings = queryStrings;
	}

	/**
	 * {@inheritDoc}
	 */
	public Class<? extends T> execute(Map<ExtensionKey, Class<? extends T>> map) throws ExtensionQueryException {
		Class<? extends T> result = getBestMatch(map, new ExtensionKey(getQueryStrings()));
		if(result == null) {
			result = getBaseImplementation(map);
		}
		if(result == null) {
			throw new ExtensionQueryException("Attempting to find a match for " + Arrays.toString(queryStrings) + " but no match found.");
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	public Class<T> getExtensionPoint() {
		return extensionPoint;
	}
	
	/**
	 * Get the best match in the map for the given key.
	 * @param map
	 * @param key
	 * @return
	 */
	protected abstract Class<? extends T> getBestMatch(Map<ExtensionKey, Class<? extends T>> map, ExtensionKey key);
	
	/**
	 * Get the base implementation if available and allowed.
	 * @param map
	 * @return
	 */
	protected Class<? extends T> getBaseImplementation(Map<ExtensionKey, Class<? extends T>> map) {
		return fallbackToDefault ? getBestMatch(map, ExtensionKey.DEFAULT_BASE) : null;
	}
	
	/**
	 * Get the query strings.
	 * @return The Query Strings
	 */
	protected String[] getQueryStrings() {
		return queryStrings;
	}
}
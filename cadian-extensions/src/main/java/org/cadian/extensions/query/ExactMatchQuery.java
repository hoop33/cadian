package org.cadian.extensions.query;

import java.util.Map;

import org.cadian.extensions.core.ExtensionKey;

/**
 * An ExtensionQuery that seeks an exact match in the map. By default, if an exact match is not found, it will
 * fall back and look for a base implementation (with no query strings in its key). This behavior can be
 * overridden use the optional constructor.
 * 
 * @author Joshua Hornsby
 */
public class ExactMatchQuery<T> extends MatchQuery<T> {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create a ExactMatchQuery that allows for fallback to a base entry if it exists.
	 * @param extensionPoint The Extension Point
	 * @param queryStrings The Query Strings
	 */
	public ExactMatchQuery(Class<T> extensionPoint, String... queryStrings) {
		this(extensionPoint, true, queryStrings);
	}
	
	/**
	 * Create a ExactMatchQuery that allows for fallback to a base entry if it exists.
	 * @param extensionPoint The Extension Point
	 * @param fallbackToDefault Allow Fallback to Default
	 * @param queryStrings The Query Strings
	 */
	public ExactMatchQuery(Class<T> extensionPoint, boolean fallbackToDefault, String... queryStrings) {
		super(extensionPoint, fallbackToDefault, queryStrings);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Class<? extends T> getBestMatch(Map<ExtensionKey, Class<? extends T>> map, ExtensionKey key) {
		Class<? extends T> result = null;
		if(map.containsKey(key)) {
			result = map.get(key);
		}
		return result;
	}
}
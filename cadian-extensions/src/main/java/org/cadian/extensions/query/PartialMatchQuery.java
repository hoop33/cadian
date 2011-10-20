package org.cadian.extensions.query;

import java.util.Map;

import org.cadian.extensions.core.ExtensionKey;

/**
 * An ExtensionQuery that seeks the closest match in the map.
 * 
 * @author Joshua Hornsby
 */
public abstract class PartialMatchQuery<T> extends MatchQuery<T> {
	private static final long serialVersionUID = 1L;

	/**
	 * Create a PartialMatchQuery that allows for fallback to a base entry if it exists.
	 * @param extensionPoint The Extension Point
	 * @param queryStrings The Query Strings
	 */
	public PartialMatchQuery(Class<T> extensionPoint, String... queryStrings) {
		this(extensionPoint, true, queryStrings);
	}
	
	/**
	 * Create a PartialMatchQuery that allows for fallback to a base entry if it exists.
	 * @param extensionPoint The Extension Point
	 * @param fallbackToDefault Allow Fallback to Default
	 * @param queryStrings The Query Strings
	 */
	public PartialMatchQuery(Class<T> extensionPoint, boolean fallbackToDefault, String... queryStrings) {
		super(extensionPoint, fallbackToDefault, queryStrings);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Class<? extends T> getBestMatch(Map<ExtensionKey, Class<? extends T>> map, ExtensionKey key) {
		Class<? extends T> result = map.get(key);
		if(result == null) {
			Double max = new Double(0);
			for(ExtensionKey otherKey : map.keySet()) {
				Double similarity = measureSimilarity(otherKey, key);
				if(similarity > max) {
					max = similarity;
					result = map.get(otherKey);
				}
			}
		}
		return result;
	}

	/**
	 * Measure the similarity between two ExtensionKeys.
	 * @param a
	 * @param b
	 * @return 
	 */
	protected abstract Double measureSimilarity(ExtensionKey otherKey, ExtensionKey key);
}
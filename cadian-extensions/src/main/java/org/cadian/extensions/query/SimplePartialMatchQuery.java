package org.cadian.extensions.query;

import java.util.Arrays;

import org.apache.commons.collections.CollectionUtils;
import org.cadian.extensions.core.ExtensionKey;

/**
 * An ExtensionQuery that seeks the closest match in the map in a very naive way.
 * 
 * @author Joshua Hornsby
 */
public class SimplePartialMatchQuery<T> extends PartialMatchQuery<T> {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create a MatchQuery that allows for fallback to a base entry if it exists.
	 * @param extensionPoint The Extension Point
	 * @param queryStrings The Query Strings
	 */
	public SimplePartialMatchQuery(Class<T> extensionPoint, String... queryStrings) {
		this(extensionPoint, true, queryStrings);
	}
	
	/**
	 * Create a MatchQuery that allows for fallback to a base entry if it exists.
	 * @param extensionPoint The Extension Point
	 * @param fallbackToDefault Allow Fallback to Default
	 * @param queryStrings The Query Strings
	 */
	public SimplePartialMatchQuery(Class<T> extensionPoint, boolean fallbackToDefault, String... queryStrings) {
		super(extensionPoint, fallbackToDefault, queryStrings);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Double measureSimilarity(ExtensionKey a, ExtensionKey b) {
		return Double.valueOf(CollectionUtils.intersection(Arrays.asList(a.queryStrings()), Arrays.asList(b.queryStrings())).size() * 1024 - a.queryStrings().length);
	}
}
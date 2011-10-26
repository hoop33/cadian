package org.cadian.extensions.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A key that is used to identify an class annotated with the Extension annotation.
 * 
 * @author Joshua Hornsby
 */
public final class ExtensionKey implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String[] queryStrings;
	
	/**
	 * Create a new ExtensionKey.
	 * @param queryStrings The Query Strings
	 */
	public ExtensionKey(String... queryStrings) {
		this.queryStrings = filterNulls(queryStrings);
		Arrays.sort(this.queryStrings);
	}
	
	/**
	 * Get the queryStrings associated with this key.
	 * @return The Query Strings
	 */
	public String[] queryStrings() {
		return queryStrings;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(queryStrings);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExtensionKey other = (ExtensionKey) obj;
		if (!Arrays.equals(queryStrings, other.queryStrings))
			return false;
		return true;
	}
	
	private static String[] filterNulls(String[] strings) {
		List<String> result = new ArrayList<String>();
		for(String s : strings) {
			if(s != null) {
				result.add(s);
			}
		}
		return result.toArray(new String[]{});
	}
	
	/**
	 * The empty key.
	 */
	public static final ExtensionKey DEFAULT_BASE = new ExtensionKey();
}
package org.cadian.extensions.query;

import java.io.Serializable;
import java.util.Map;

import org.cadian.extensions.core.ExtensionKey;

/**
 * An ExtensionQuery is used to 
 * 
 * @author Joshua Hornsby
 */
public interface ExtensionQuery<T> extends Serializable {

	/**
	 * Execute the query to locate the best match in the map for the query strings.
	 * @param map The Map
	 * @return The Best Match
	 */
	public Class<? extends T> execute(Map<ExtensionKey, Class<? extends T>> map) throws ExtensionQueryException;
	
	/**
	 * Get the ExtensionPoint.
	 * @return The Extension Point
	 */
	public Class<T> getExtensionPoint();
}
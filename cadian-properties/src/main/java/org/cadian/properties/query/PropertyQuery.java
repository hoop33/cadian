package org.cadian.properties.query;

import java.io.Serializable;

import org.cadian.properties.core.PropertyStore;

/**
 * A PropertyQuery can be used to locate a property value in a
 * PropertyStore.
 * 
 * @author Joshua Hornsby
 */
public interface PropertyQuery extends Serializable {
	
	/**
	 * Execute the query, searching the PropertyStore for a value.
	 * @param store The Store
	 * @return The Value or Null
	 */
	public String execute(PropertyStore store);
}
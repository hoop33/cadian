package org.cadian.extensions.core;

import java.io.Serializable;

/**
 * A hash key for ExtensionPoints.
 * 
 * @author Joshua Hornsby
 */
public class ExtensionPointKey implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String key;
	
	/**
	 * Create a new ExtensionPointKey.
	 * @param targetClass The Target Class
	 */
	public ExtensionPointKey(Class<?> targetClass) {
		this.key = targetClass.getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		ExtensionPointKey other = (ExtensionPointKey) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
}
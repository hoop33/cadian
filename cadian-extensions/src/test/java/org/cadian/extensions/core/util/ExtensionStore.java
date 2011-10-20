package org.cadian.extensions.core.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.cadian.extensions.core.ExtensionKey;

public class ExtensionStore<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Map<ExtensionKey, Class<? extends T>> store;
	private final Class<? extends T> targetClass;
	
	public ExtensionStore(Class<? extends T> targetClass) {
		this.targetClass = targetClass;
		store = new HashMap<ExtensionKey, Class<? extends T>>();
	}
}
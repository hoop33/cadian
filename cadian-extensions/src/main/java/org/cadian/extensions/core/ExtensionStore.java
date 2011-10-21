package org.cadian.extensions.core;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.cadian.extensions.annotation.Extension;
import org.cadian.extensions.annotation.MockExtension;
import org.cadian.extensions.query.ExtensionQuery;
import org.cadian.extensions.query.ExtensionQueryException;
import org.cadian.reflections.ReflectionsUtil;

/**
 * An ExtensionStore holds all references to all of the Classes that implements a particular
 * ExtensionPoint.
 * 
 * @author Joshua Hornsby
 *
 * @param <T> ExtensionPoint Type
 */
public class ExtensionStore<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Class<T> targetClass;
	private final Map<ExtensionKey, Class<? extends T>> store;
	
	
	/**
	 * Create a new ExtensionStore.
	 * @param targetClass The ExtensionPoint Class
	 */
	public ExtensionStore(Class<T> targetClass) {
		this.targetClass = targetClass;
		store = Collections.synchronizedMap(new HashMap<ExtensionKey, Class<? extends T>>());
		init();
	}
	
	/**
	 * Reinitialize this ExtensionStore.
	 */
	public void init() {
		store.clear();
		Set<Class<? extends T>> subTypes = ReflectionsUtil.getSubTypesOf(targetClass);
		for(Class<? extends T> subType : subTypes) {
			Extension annotation = subType.getAnnotation(Extension.class);
			if(annotation != null) {
				ExtensionKey key = new ExtensionKey(annotation.queryStrings());
				if(!store.containsKey(key)) {
					store.put(key, subType);
				} else {
					handleMock(subType);
				}
			} else {
				handleMock(subType);
			}
		}
	}
	
	/**
	 * Execute a query against this extension store.
	 * @param query The Query to Execute
	 * @return The Result
	 * @throws ExtensionQueryException If there is a problem executing the query
	 */
	public Class<? extends T> execute(ExtensionQuery<T> query) throws ExtensionQueryException {
		return query.execute(store);
	}
	
	/**
	 * Check for a MockExtension and add it to the store if found.
	 */
	private void handleMock(Class<? extends T> subType) {
		if("true".equalsIgnoreCase(System.getProperty("org.cadian.ExtensionStore.mock"))) {
			MockExtension mockAnnotation = subType.getAnnotation(MockExtension.class);
			if(mockAnnotation != null) {
				store.put(new ExtensionKey(mockAnnotation.extension().queryStrings()), subType);
			}
		}
	}
}
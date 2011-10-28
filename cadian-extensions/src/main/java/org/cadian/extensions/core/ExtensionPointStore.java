package org.cadian.extensions.core;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.cadian.extensions.annotation.ExtensionPoint;
import org.cadian.extensions.core.util.InstanceUtil;
import org.cadian.extensions.query.ExtensionQuery;
import org.cadian.extensions.query.ExtensionQueryException;
import org.cadian.reflections.ReflectionsUtil;

/**
 * An ExtensionPointStore stores ExtensionStores for all ExtensionPoints found in
 * the classpath.
 * 
 * @author Joshua Hornsby
 */
public class ExtensionPointStore implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Map<ExtensionPointKey, ExtensionStore<?>> store;
	
	/**
	 * Create a new ExtensionPointStore.
	 */
	public ExtensionPointStore() {
		store = Collections.synchronizedMap(new HashMap<ExtensionPointKey, ExtensionStore<?>>());
		init(false);
	}
	
	/**
	 * Reinitialize this ExtensionPointStore.
	 */
	public void init(boolean rescanClassPath) {
		if(rescanClassPath) {
			ReflectionsUtil.init();
		}
		store.clear();
		Set<Class<?>> extensionPoints = ReflectionsUtil.getTypesAnnotatedWith(ExtensionPoint.class);
		for(Class<?> extensionPoint : extensionPoints) {
			store.put(new ExtensionPointKey(extensionPoint), createExtensionStore(extensionPoint));
		}
	}

	/**
	 * Get the Class that matches the given query.
	 * @param query The Query
	 * @return The Class
	 */
	@SuppressWarnings("unchecked")
	public <T> Class<? extends T> getClassFor(ExtensionQuery<T> query) {
		if(store.containsKey(query.getExtensionPointKey())) {
			return ((ExtensionStore<T>)store.get(query.getExtensionPointKey())).execute(query);
		} else {
			throw new ExtensionQueryException("Unable to locate an ExtensionPointStore for the given query.");
		}
	}

	/**
	 * Get an instance of the Class that matches the given query.
	 * @param query The Query
	 * @return The Instance
	 */
	public <T> T getInstanceFor(ExtensionQuery<T> query) {
		return InstanceUtil.newInstance(getClassFor(query));
	}
	
	/**
	 * Get an instance of the Class that matches the given query, passing the provided arguments into its constructor.
	 * @param query The Query
	 * @param args The Arguments
	 * @return The Instance
	 */
	public <T> T getInstanceFor(ExtensionQuery<T> query, Object... args) {
		return InstanceUtil.newInstance(getClassFor(query), args);
	}
	
	/**
	 * Helper method for creating a new ExtensionStore instance.
	 * @param extensionPoint The ExtensionPoint Class
	 * @return A New ExtensionStore
	 */
	private <T> ExtensionStore<T> createExtensionStore(Class<T> extensionPoint) {
		return new ExtensionStore<T>(extensionPoint);
	}
}
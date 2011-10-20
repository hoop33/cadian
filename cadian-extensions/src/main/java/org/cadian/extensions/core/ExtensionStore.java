package org.cadian.extensions.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.cadian.extensions.annotation.Extension;
import org.cadian.extensions.query.ExtensionQuery;
import org.cadian.extensions.query.ExtensionQueryException;
import org.cadian.reflections.ReflectionsUtil;

/**
 * 
 * @author Joshua Hornsby
 *
 * @param <T> ExtensionPoint Type
 */
public class ExtensionStore<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Class<T> targetClass;
	private final Map<ExtensionKey, Class<? extends T>> store;
	
	public ExtensionStore(Class<T> targetClass) {
		this.targetClass = targetClass;
		store = new HashMap<ExtensionKey, Class<? extends T>>();
	}
	
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
					
				}
			}
		}
	}
	
	public Class<? extends T> execute(ExtensionQuery<T> query) throws ExtensionQueryException {
		return query.execute(store);
	}
}
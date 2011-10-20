package org.cadian.properties.query;

import org.cadian.properties.core.PropertyStore;

/**
 * A PropertyQuery that searches for keys based on the inheritance hierarchy.
 * 
 * The PropertyStore will be searched in the following way:
 * <ol>
 * 	<li>class+key</li>
 * 	<li>all super classes+key</li>
 * 	<li>all interfaces implemented by the class and its super classes+key</li>
 * 	<li>key</li>
 * </ol>
 * For example, if you have classes:
 * <ul>
 * 	<li>class org.cadian.example.A implements org.cadian.example.I</li>
 * 	<li>class org.cadian.example.B extends org.cadian.example.A implements org.cadian.example.F, java.io.Serializable</li>
 * 	<li>class org.cadian.example.C extends org.cadian.example.B</li>
 * </ul>
 * And search for key "key", then the following keys will be tried:
 * <ol>
 * 	<li>org.cadian.example.A.key</li>
 * 	<li>org.cadian.example.B.key</li>
 * 	<li>org.cadian.example.C.key</li>
 * 	<li>org.cadian.example.I.key</li>
 * 	<li>org.cadian.example.F.key</li>
 * 	<li>java.io.Serializable.key</li>
 * 	<li>key</li>
 * </ol>
 * If the value still has not been found, the default value will be used.
 * 
 * @author Joshua Hornsby
 */
public class FallbackPropertyQuery extends AbstractPropertyQuery {
	private static final long serialVersionUID = 1L;

	/**
	 * Create a new FallbackPropertyQuery.
	 * @param target The Target Class
	 * @param key The Key Suffix
	 * @param defaultValue The Default Value
	 */
	public FallbackPropertyQuery(Class<?> target, String key, String defaultValue) {
		super(target, key, defaultValue);
	}

	/**
	 * Create a new FallbackPropertyQuery.
	 * @param target The Target Class
	 * @param key The Key Suffix
	 */
	public FallbackPropertyQuery(Class<?> target, String key) {
		super(target, key);
	}

	/**
	 * Create a new FallbackPropertyQuery.
	 * @param key The Key
	 * @param defaultValue The Default Value
	 */
	public FallbackPropertyQuery(String key, String defaultValue) {
		super(key, defaultValue);
	}

	/**
	 * Create a new FallbackPropertyQuery.
	 * @param key The Key
	 */
	public FallbackPropertyQuery(String key) {
		super(key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String execute(PropertyStore store) {
		String result = store.get(buildKey(getTarget(), getKey())); //look for the standard property
		if(result == null) { //try all of the super classes
			result = checkSuperClassChain(store, getTarget(), getKey());
		}
		if(result == null) { //try all of the interfaces
			result = checkInterfaceChain(store, getTarget(), getKey());
		}
		if(result == null) { //try just the key
			result = store.get(getKey());
		}
		if(result == null) { //use the default value
			result = getDefaultValue();
		}
		return result;
	}
	
	/**
	 * Recursively search the class inheritance chain for the desired property.
	 * @param store The Property Store
	 * @param target The Target Class
	 * @param key The Key
	 * @return The Value or Null
	 */
	protected String checkSuperClassChain(PropertyStore store, Class<?> target, String key) {
		String result = null;
		if(target.getSuperclass() != null) {
			if(store.contains(buildKey(target.getSuperclass(), key))) {
				result = store.get(buildKey(target.getSuperclass(), key));	
			} else {
				result = checkSuperClassChain(store, target.getSuperclass(), key);
			}
		}
		return result;
	}
	
	/**
	 * Recursively search the interfaces of each class in the inheritance chain for the desired property.
	 * @param store The Property Store
	 * @param target The Target Class
	 * @param key The Key
	 * @return The Value or Null
	 */
	protected String checkInterfaceChain(PropertyStore store, Class<?> target, String key) {
		String result = null;
		for(Class<?> iface : target.getInterfaces()) {
			if(store.contains(buildKey(iface, key))) {
				result = store.get(buildKey(iface, key));
				break;
			}
		}
		if(result == null && target.getSuperclass() != null) {
			result = checkInterfaceChain(store, target.getSuperclass(), key);
		}
		return result;
	}
}
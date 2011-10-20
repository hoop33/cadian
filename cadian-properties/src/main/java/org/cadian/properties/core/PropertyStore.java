package org.cadian.properties.core;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import org.cadian.reflections.ReflectionsUtil;

/**
 * The PropertyStore loads the properties from all .properties files found anywhere
 * in the classpath.
 * 
 * @author Joshua Hornsby
 */
public class PropertyStore implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Properties properties;
	
	/**
	 * Create and load a new PropertyStore.
	 */
	public PropertyStore() {
		properties = new Properties();
		load();
	}
	
	/**
	 * Load the PropertyStore. Can be called later to reload the PropertyStore.
	 */
	public void load() {
		properties.clear();
		for(String propertyFileName : ReflectionsUtil.getResources(Pattern.compile(".*\\.properties"))) {
			try {
				ResourceBundle bundle = ResourceBundle.getBundle(propertyFileName.replaceAll("\\.properties", "").replaceAll("/", "."));
				Enumeration<String> keys = bundle.getKeys();
				while(keys.hasMoreElements()) {
					String key = keys.nextElement();
					properties.put(key, bundle.getObject(key));
				}
			} catch(MissingResourceException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Get the property, if available, identified by the key.
	 * @param key The Key
	 * @return The Property or Null
	 */
	public String get(String key) {
		return properties.getProperty(key);
	}

	/**
	 * Check if this PropertyStore contains an entry for the key.
	 * @param key The Key
	 * @return Contains the Key or Not
	 */
	public boolean contains(String key) {
		return properties.containsKey(key);
	}
	
	/**
	 * Get the Properties object.
	 * @return The Properties Object
	 */
	public Properties getProperties() {
		return properties;
	}
}
package org.cadian.properties.query;

import org.cadian.properties.core.PropertyStore;
import org.junit.BeforeClass;

/**
 * Base test for PropertyQueries.
 * 
 * @author Joshua Hornsby
 */
public abstract class PropertyQueryTest {
	protected static PropertyStore store;
	
	@BeforeClass
	public static void init() {
		store = new PropertyStore();
	}
	
	protected interface I {
	}
	protected class A implements I {
	}
	protected class B extends A {
	}
	protected class C extends B {
	}
}
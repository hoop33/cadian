package org.cadian.extensions.query;

import java.util.HashMap;
import java.util.Map;

import org.cadian.extensions.core.ExtensionKey;

/**
 * Base test for extension query tests.
 * 
 * @author Joshua Hornsby
 */
public class ExtensionQueryTestBase {
	protected Map<ExtensionKey, Class<? extends TestExtensionPoint>> map;
	
	public void init() {
		map = new HashMap<ExtensionKey, Class<? extends TestExtensionPoint>>();
		map.put(new ExtensionKey("a"), A.class);
		map.put(new ExtensionKey("a", "b"), B.class);
		map.put(new ExtensionKey("a", "b", "c"), C.class);
	}
	
	protected static interface TestExtensionPoint {
	}
	protected static class A implements TestExtensionPoint {
	}
	protected static class B implements TestExtensionPoint {
	}
	protected static class C implements TestExtensionPoint {
	}
}
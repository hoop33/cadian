package org.cadian.extensions.core;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Tests for ExtensionPointKey.
 * 
 * @author Joshua Hornsby
 */
public class ExtensionPointKeyTest {

	//basically all we care about...
	@Test
	public void testHashKey() {
		Assert.assertFalse("hashCodes shouldn't be the same", new ExtensionPointKey(Object.class).hashCode() == new ExtensionPointKey(String.class).hashCode());
		Assert.assertFalse("hashCodes shouldn't be the same", new ExtensionPointKey(Object.class).hashCode() == new ExtensionPointKey(Long.class).hashCode());
		Assert.assertTrue("hashCodes should be the same", new ExtensionPointKey(Object.class).hashCode() == new ExtensionPointKey(Object.class).hashCode());
		Assert.assertTrue("hashCodes should be the same", new ExtensionPointKey(String.class).hashCode() == new ExtensionPointKey(String.class).hashCode());
	}
}
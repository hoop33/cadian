package org.cadian.extensions.core;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for ExtensionKey.
 * 
 * @author Joshua Hornsby
 */
public class ExtensionKeyTest {

	@Test
	public void testConstructorSort() {
		ExtensionKey key = new ExtensionKey("z", "c", "a");
		Assert.assertArrayEquals(new String[] {"a", "c", "z"}, key.queryStrings());
	}
	
	@Test
	public void testHashCode() {
		Assert.assertEquals(new ExtensionKey("z", "c", "a").hashCode(), new ExtensionKey("c", "z", "a").hashCode());
		Assert.assertFalse(new ExtensionKey("z", "c", "a").hashCode() == new ExtensionKey("c", "z", "z").hashCode());
	}
	
	@Test
	public void testFilterNullQueryStrings() {
		Assert.assertEquals(0, new ExtensionKey((String)null).queryStrings().length);
		Assert.assertEquals(2, new ExtensionKey("a", null, "b").queryStrings().length);
	}
}
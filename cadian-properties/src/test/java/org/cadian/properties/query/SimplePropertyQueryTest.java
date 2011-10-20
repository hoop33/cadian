package org.cadian.properties.query;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Tester for SimplePropertyQueries.
 * 
 * @author Joshua Hornsby
 */
public class SimplePropertyQueryTest extends PropertyQueryTest {
	@Test
	public void testGetClassRelative() {
		Assert.assertEquals("Boron", new SimplePropertyQuery(C.class, "testC").execute(store));
	}
	
	@Test
	public void testGetNonClassRelative() {
		Assert.assertEquals("Hydrogen", new SimplePropertyQuery("test").execute(store));
	}
	
	@Test
	public void testNoFallback() {
		Assert.assertNull(new SimplePropertyQuery(B.class, "testC").execute(store));
	}
	
	@Test
	public void testDefaultValue() {
		Assert.assertEquals("Polonium", new SimplePropertyQuery(B.class, "NOT-A-REAL-PROPERTY!", "Polonium").execute(store));
	}
}
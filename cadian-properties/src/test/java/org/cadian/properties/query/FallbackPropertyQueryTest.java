package org.cadian.properties.query;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Tester for FallbackPropertyQueries.
 * 
 * @author Joshua Hornsby
 */
public class FallbackPropertyQueryTest extends PropertyQueryTest {
	@Test
	public void testGetClassRelative() {
		Assert.assertEquals("Boron", new SimplePropertyQuery(C.class, "testC").execute(store));
	}
	
	@Test
	public void testGetNonClassRelative() {
		Assert.assertEquals("Hydrogen", new FallbackPropertyQuery("test").execute(store));
	}
	
	@Test
	public void testFallbackToSuperClass() {
		Assert.assertEquals("Beryllium", new FallbackPropertyQuery(C.class, "testB").execute(store));
	}
	
	@Test
	public void testFallbackToSuperSuperClass() {
		Assert.assertEquals("Lithium", new FallbackPropertyQuery(C.class, "testA").execute(store));
	}
	
	@Test
	public void testFallbackToInterface() {
		Assert.assertEquals("Helium", new FallbackPropertyQuery(C.class, "testI").execute(store));
	}
	
	@Test
	public void testFallbackToUnqualified() {
		Assert.assertEquals("Hydrogen", new FallbackPropertyQuery(C.class, "test").execute(store));
	}
	
	@Test
	public void testDefaultValue() {
		Assert.assertEquals("Polonium", new SimplePropertyQuery(B.class, "NOT-A-REAL-PROPERTY!", "Polonium").execute(store));
	}
}
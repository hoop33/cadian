package org.cadian.extensions.core;

import junit.framework.Assert;

import org.cadian.extensions.annotation.Extension;
import org.cadian.extensions.annotation.MockExtension;
import org.cadian.extensions.query.ExactMatchQuery;
import org.cadian.extensions.query.ExtensionQueryException;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for ExtensionStore.
 * 
 * @author Joshua Hornsby
 */
public class ExtensionStoreTest {
	private ExtensionStore<I> store;
	
	@Before
	public void init() {
		Extensions.injectMocks(true);
		store = new ExtensionStore<I>(I.class);
	}
	
	@Test
	public void testDirectSubType() throws ExtensionQueryException {
		Assert.assertEquals(A.class, store.execute(new ExactMatchQuery<I>(I.class)));
	}
	
	@Test
	public void testAnotherDirectSubType() throws ExtensionQueryException {
		Assert.assertEquals(B.class, store.execute(new ExactMatchQuery<I>(I.class, "z")));
	}
	
	@Test
	public void testIndirectSubType() throws ExtensionQueryException {
		Assert.assertEquals(C.class, store.execute(new ExactMatchQuery<I>(I.class, "a", "b")));
	}
	
	@Test
	public void testFallback() throws ExtensionQueryException {
		Assert.assertEquals(A.class, store.execute(new ExactMatchQuery<I>(I.class, "x", "y")));
	}
	
	@Test
	public void testGetMock() throws ExtensionQueryException {
		Assert.assertEquals(MockD.class, store.execute(new ExactMatchQuery<I>(I.class, "c", "z")));
	}
	
	protected interface I {}
	@Extension
	private class A implements I {}
	@Extension(queryStrings="z")
	private class B implements I {}
	@Extension(queryStrings={"a", "b"})
	private class C extends B {}
	@SuppressWarnings("unused")
	@Extension(queryStrings={"c", "z"})
	private class D extends C {}
	@MockExtension(extension=@Extension(queryStrings={"c", "z"}))
	private class MockD extends C {}
}
package org.cadian.extensions.query;

import junit.framework.Assert;

import org.cadian.extensions.core.ExtensionKey;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for SimplePartialMatchQuery.
 * 
 * @author Joshua Hornsby
 */
public class SimplePartialMatchQueryTest extends ExtensionQueryTestBase {
	@Before
	public void init() {
		super.init();
	}
	
	@Test
	public void testThatExactMatchesAreFavored() throws ExtensionQueryException {
		Assert.assertEquals(A.class, new SimplePartialMatchQuery<TestExtensionPoint>(TestExtensionPoint.class, "a").execute(map));
		Assert.assertEquals(B.class, new SimplePartialMatchQuery<TestExtensionPoint>(TestExtensionPoint.class, "a", "b").execute(map));
		Assert.assertEquals(C.class, new SimplePartialMatchQuery<TestExtensionPoint>(TestExtensionPoint.class, "a", "b", "c").execute(map));
	}
	
	@Test
	public void testThatTheClosestMatchIsFoundAsLongAsTheyHaveSomethingInCommon() throws ExtensionQueryException {
		Assert.assertEquals(A.class, new SimplePartialMatchQuery<TestExtensionPoint>(TestExtensionPoint.class, "a", "z", "G", "9").execute(map));
		Assert.assertEquals(B.class, new SimplePartialMatchQuery<TestExtensionPoint>(TestExtensionPoint.class, "a", "z", "G", "b").execute(map));
		Assert.assertEquals(C.class, new SimplePartialMatchQuery<TestExtensionPoint>(TestExtensionPoint.class, "a", "z", "G", "c").execute(map));
		Assert.assertEquals(C.class, new SimplePartialMatchQuery<TestExtensionPoint>(TestExtensionPoint.class, "a", "z", "G", "c", "4", "e", "f", "b", "b").execute(map));
	}
	
	@Test
	public void testThatIfNothingMatchesWeGetTheBaseImplementation() throws ExtensionQueryException {
		map.put(ExtensionKey.DEFAULT_BASE, A.class);
		Assert.assertEquals(A.class, new SimplePartialMatchQuery<TestExtensionPoint>(TestExtensionPoint.class, "3", "z", "G", "9").execute(map));
	}
	
	@Test
	public void testThatIfTheresNoDefaultItFails() {
		try {
			new SimplePartialMatchQuery<TestExtensionPoint>(TestExtensionPoint.class, "3", "z", "G", "9").execute(map);
			Assert.fail();
		} catch (ExtensionQueryException e) {
			Assert.assertTrue(e.getMessage().contains("no match found"));
		}
	}
	
	@Test
	public void testThatIfWeDisableFallbackItFails() {
		map.put(ExtensionKey.DEFAULT_BASE, A.class);
		try {
			new SimplePartialMatchQuery<TestExtensionPoint>(TestExtensionPoint.class, false, "3", "z", "G", "9").execute(map);
			Assert.fail();
		} catch (ExtensionQueryException e) {
			Assert.assertTrue(e.getMessage().contains("no match found"));
		}
	}
}
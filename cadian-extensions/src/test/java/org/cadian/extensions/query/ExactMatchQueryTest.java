package org.cadian.extensions.query;

import junit.framework.Assert;

import org.cadian.extensions.core.ExtensionKey;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for ExactMatchQuery.
 * 
 * @author Joshua Hornsby
 */
public class ExactMatchQueryTest extends ExtensionQueryTestBase {
	@Before
	public void init() {
		super.init();
	}
	
	@Test
	public void testThatAnExactQueryStringMatchSucceeds() {
		Assert.assertEquals(A.class, new ExactMatchQuery<TestExtensionPoint>(TestExtensionPoint.class, "a").execute(map));
		Assert.assertEquals(B.class, new ExactMatchQuery<TestExtensionPoint>(TestExtensionPoint.class, "a", "b").execute(map));
		Assert.assertEquals(C.class, new ExactMatchQuery<TestExtensionPoint>(TestExtensionPoint.class, "a", "b", "c").execute(map));
	}
	
	@Test
	public void testThatAPartialMatchFails() {
		try {
			new ExactMatchQuery<TestExtensionPoint>(TestExtensionPoint.class, "a", "c").execute(map);
			Assert.fail();
		} catch (ExtensionQueryException e) {
			Assert.assertTrue(e.getMessage().contains("no match found"));
		}
	}
	
	@Test
	public void testThatWeCanFallBackToABaseImplementation() {
		map.put(new ExtensionKey(), A.class); //add a fallback
		Assert.assertEquals(A.class, new ExactMatchQuery<TestExtensionPoint>(TestExtensionPoint.class, "z").execute(map));
	}
	
	@Test
	public void testThatWeCanDisableTheFallBackBehavior() {
		map.put(new ExtensionKey(), A.class); //add a fallback
		try {
			new ExactMatchQuery<TestExtensionPoint>(TestExtensionPoint.class, false, "z").execute(map);
			Assert.fail();
		} catch (ExtensionQueryException e) {
			Assert.assertTrue(e.getMessage().contains("no match found"));
		}
	}
}
package org.cadian.extensions.core;

import junit.framework.Assert;

import org.cadian.extensions.annotation.Extension;
import org.cadian.extensions.annotation.ExtensionPoint;
import org.cadian.extensions.query.ExactMatchQuery;
import org.cadian.extensions.query.ExtensionQueryException;
import org.cadian.extensions.query.SimplePartialMatchQuery;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for ExtensionPointStore.
 * 
 * @author Joshua Hornsby
 */
public class ExtensionPointStoreTest {
	private ExtensionPointStore store;
	
	@Before
	public void init() {
		store = new ExtensionPointStore();
	}
	
	@Test
	public void testGetBaseClass() {
		Assert.assertEquals(Hydrogen.class, store.getClassFor(new SimplePartialMatchQuery<Element>(Element.class)));
	}
	
	@Test
	public void testGetPartialMatchClass() {
		Assert.assertEquals(Carbon.class, store.getClassFor(new SimplePartialMatchQuery<Element>(Element.class, "delicious ice cream")));
	}
	
	@Test
	public void testGetBaseInstance() {
		Assert.assertEquals("Hydrogen", store.getInstanceFor(new SimplePartialMatchQuery<Element>(Element.class)).getName());
	}
	
	@Test
	public void testGetSingletonInstance() {
		Assert.assertEquals("Carbon", store.getInstanceFor(new SimplePartialMatchQuery<Element>(Element.class, "people", "delicious ice cream")).getName());
	}
	
	@Test
	public void testGetInstanceWithConstructorArgs() {
		Assert.assertEquals("Hello :)", store.getInstanceFor(new SimplePartialMatchQuery<Element>(Element.class, "balloons", "silly voices"), "Hello :)").getName());
	}
	
	@Test(expected=ExtensionQueryException.class)
	public void testThatWeGetAnExceptionIfWeQueryForANonExistantExtensionPoint() {
		store.getClassFor(new ExactMatchQuery<Helium>(Helium.class));
	}
	
	@ExtensionPoint
	public static interface Element {
		public String getName();
	}
	@Extension
	public static class Hydrogen implements Element {
		public Hydrogen() {
		}
		public String getName() {
			return "Hydrogen";
		}
	}
	@Extension(queryStrings={"balloons", "silly voices"})
	public static class Helium implements Element {
		private String name;
		public Helium(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
	}
	@Extension(queryStrings={"people", "delicious ice cream"})
	public static class Carbon implements Element {
		private final static Carbon INSTANCE = new Carbon();
		private Carbon(){}
		public static Carbon getInstance() {
			return INSTANCE;
		}
		public String getName() {
			return "Carbon";
		}
	}
}
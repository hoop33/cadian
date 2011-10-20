package org.cadian.extensions.core.util;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Tests for InstanceUtil.
 * 
 * @author Joshua Hornsby
 */
public class InstanceUtilTest {

	@Test
	public void createSimpleInstance() {
		GoodClass instance = null;
		try {
			instance = InstanceUtil.newInstance(GoodClass.class);
		} catch (Exception e) {
			Assert.fail();
		}
		Assert.assertNotNull("Expected an instance, got null.", instance);
		Assert.assertTrue("Expected the instance to be an instanceof TestClass.", instance instanceof GoodClass);
	}
	
	@Test
	public void createSimpleInstance_abstractClass() {
		try {
			InstanceUtil.newInstance(AbstractClass.class);
			Assert.fail();
		} catch (CreateInstanceException e) {
			Assert.assertTrue("Expected exception's message to mention something about concrete.", e.getMessage().contains("concrete"));
		}
	}
	
	@Test
	public void createSimpleInstance_notVisibleClass() {
		try {
			InstanceUtil.newInstance(NotVisibleClass.class);
			Assert.fail();
		} catch (CreateInstanceException e) {
			Assert.assertTrue("Expected exception's message to mention something about visible.", e.getMessage().contains("visible"));
		}
	}
	
	@Test
	public void getClassesOf_null() {
		Assert.assertEquals("Null input should yield 0 length array.", 0, InstanceUtil.getClassesOf(null).length);
	}
	
	@Test
	public void getClassesOf_emptyArray() {
		Assert.assertEquals("Null input should yield 0 length array.", 0, InstanceUtil.getClassesOf(new Object[]{}).length);
	}
	
	@Test
	public void getClassesOf_objects() {
		Object[] args = new Object[] {"test", new Object(), 1983L};
		Class<?>[] classes = InstanceUtil.getClassesOf(args);
		Assert.assertEquals("Expected the first array position to contain String.class.", String.class, classes[0]);
		Assert.assertEquals("Expected the second array position to contain Object.class.", Object.class, classes[1]);
		Assert.assertEquals("Expected the third array position to contain Long.class.", Long.class, classes[2]);
	}
	
	@Test
	public void createParameterizedInstance() {
		String arg0 = "test";
		Object arg1 = new Object();
		Long arg2 = 1983L;
		GoodClass instance = null;
		try {
			instance = InstanceUtil.newInstance(GoodClass.class, new Object[] {arg0, arg1, arg2});
		} catch (Exception e) {
			Assert.fail();
		}
		Assert.assertNotNull("Expected an instance, got null.", instance);
		Assert.assertTrue("Expected the instance to be an instanceof TestClass", instance instanceof GoodClass);
		Assert.assertEquals(arg0, instance.arg0);
		Assert.assertEquals(arg1, instance.arg1);
		Assert.assertEquals(arg2, instance.arg2);
	}
	
	@Test
	public void createParameterizedInstance_abstractClass() {
		try {
			InstanceUtil.newInstance(AbstractClass.class, new Object[]{});
			Assert.fail();
		} catch (CreateInstanceException e) {
			Assert.assertTrue("Expected exception's message to mention something about concrete.", e.getMessage().contains("concrete"));
		}
	}
	
	@Test
	public void createParameterizedInstance_notVisibleClass() {
		try {
			InstanceUtil.newInstance(NotVisibleClass.class, new Object[]{"a"});
			Assert.fail();
		} catch (CreateInstanceException e) {
			Assert.assertTrue("Expected exception's message to mention something about visible.", e.getMessage().contains("arguments"));
		}
	}
	
	@Test
	public void createParameterizedInstance_argsInWrongOrder() {
		try {
			InstanceUtil.newInstance(GoodClass.class, new Object[]{new Object(), 1983L, "ASDF"});
			Assert.fail();
		} catch (CreateInstanceException e) {
			Assert.assertTrue("Expected exception's message to mention something about arguments.", e.getMessage().contains("arguments"));
		}
	}
	
	@Test
	public void createParameterizedInstance_wrongTypeOfArgs() {
		try {
			InstanceUtil.newInstance(GoodClass.class, new Object[]{new Object(), 1983L, new StringBuffer()});
			Assert.fail();
		} catch (CreateInstanceException e) {
			Assert.assertTrue("Expected exception's message to mention something about arguments.", e.getMessage().contains("arguments"));
		}
	}
	
	@Test
	public void isSingletonClass_notASingleton() {
		Assert.assertFalse("GoodClass isn't a singleton.", InstanceUtil.isSingleton(GoodClass.class));
		Assert.assertFalse("GoodClass isn't a singleton.", InstanceUtil.isSingleton(NotASingletonClass.class));
	}
	
	@Test
	public void isSingletonClass_aSingleton() {
		Assert.assertTrue("SingletonClass is a singleton.", InstanceUtil.isSingleton(SingletonClass.class));
	}
	
	@Test
	public void getSingletonInstance() throws CreateInstanceException {
		Assert.assertNotNull("Should have gotten something other than null.", InstanceUtil.newInstance(SingletonClass.class));
	}
	
	protected static class GoodClass {
		protected String arg0;
		protected Object arg1;
		protected Long arg2;
		public GoodClass(){}
		public GoodClass(String arg0, Object arg1, Long arg2) {
			this.arg0 = arg0;
			this.arg1 = arg1;
			this.arg2 = arg2;
		}
	}
	
	private static class NotVisibleClass {
		private NotVisibleClass() {}
		private NotVisibleClass(String z) {}
	}
	
	protected static abstract class AbstractClass {
		public AbstractClass() {}
	}
	
	protected static class SingletonClass {
		public static SingletonClass getInstance() {
			return new SingletonClass();
		}
		private SingletonClass(){}
	}
	
	protected static class NotASingletonClass {
		public static void getInstance(){}
	}
}
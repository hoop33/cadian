package org.cadian.reflections.org;

import junit.framework.Assert;

import org.cadian.reflections.ReflectionsUtil;
import org.junit.Test;

public class ReflectionsUtilTest {
	@Test
	public void test() {
		Assert.assertNotNull(ReflectionsUtil.getSubTypesOf(Object.class));
	}
}
package org.cadian.examples.extensions.ninjabattle.attack;

import junit.framework.Assert;

import org.cadian.examples.extensions.ninjabattle.util.MockRandomUtilImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for Punch.
 * 
 * @author Joshua Hornsby
 */
public class PunchTest {
	
	@Before
	public void init() {
		MockRandomUtilImpl.reset();
	}
	
	@Test
	public void testName() {
		Assert.assertEquals("Face Punch", new Punch().getName());
	}
	
	@Test
	public void testMinimumDamage() {
		MockRandomUtilImpl.RANDOM_INT.set(0);
		Assert.assertEquals(10, new Punch().getDamage());
	}
	
	@Test
	public void testMaximumDamage() {
		MockRandomUtilImpl.RANDOM_INT.set(90);
		Assert.assertEquals(100, new Punch().getDamage());
	}
}
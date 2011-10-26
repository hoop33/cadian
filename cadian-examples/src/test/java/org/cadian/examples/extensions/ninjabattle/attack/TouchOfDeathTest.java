package org.cadian.examples.extensions.ninjabattle.attack;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Tests for TouchOfDeath.
 * 
 * @author Joshua Hornsby
 */
public class TouchOfDeathTest {

	@Test
	public void testName() {
		Assert.assertEquals("Touch of Death", new TouchOfDeath().getName());
	}
	
	@Test
	public void testDamage() {
		Assert.assertEquals(Integer.MAX_VALUE, new TouchOfDeath().getDamage());
	}
}
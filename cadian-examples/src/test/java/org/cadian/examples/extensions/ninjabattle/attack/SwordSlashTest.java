package org.cadian.examples.extensions.ninjabattle.attack;

import junit.framework.Assert;

import org.cadian.examples.extensions.ninjabattle.util.MockRandomUtilImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for SwordSlash.
 * 
 * @author Joshua Hornsby
 */
public class SwordSlashTest {
	
	@Before
	public void init() {
		MockRandomUtilImpl.reset();
	}
	
	@Test
	public void testName() {
		Assert.assertEquals("Katana Slash", new SwordSlash().getName());
	}
	
	@Test
	public void testMinimumDamage() {
		MockRandomUtilImpl.RANDOM_INT.set(0);
		Assert.assertEquals(86, new SwordSlash().getDamage());
	}
	
	@Test
	public void testMaximumDamage() {
		MockRandomUtilImpl.RANDOM_INT.set(212);
		Assert.assertEquals(298, new SwordSlash().getDamage());
	}
}

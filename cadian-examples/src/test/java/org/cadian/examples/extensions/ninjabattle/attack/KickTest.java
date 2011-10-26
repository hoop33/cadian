package org.cadian.examples.extensions.ninjabattle.attack;

import junit.framework.Assert;

import org.cadian.examples.extensions.ninjabattle.util.MockRandomUtilImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for kick.
 * 
 * @author Joshua Hornsby
 */
public class KickTest {

	@Before
	public void init() {
		MockRandomUtilImpl.reset();
	}
	
	@Test
	public void testName() {
		Assert.assertEquals("Roundhouse Kick", new Kick().getName());
	}
	
	@Test
	public void testMinimumDamage() {
		MockRandomUtilImpl.RANDOM_INT.set(0);
		Assert.assertEquals(20, new Kick().getDamage());
	}
	
	@Test
	public void testMaximumDamage() {
		MockRandomUtilImpl.RANDOM_INT.set(140);
		Assert.assertEquals(160, new Kick().getDamage());
	}
}
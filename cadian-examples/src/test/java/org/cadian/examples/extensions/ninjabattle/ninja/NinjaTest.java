package org.cadian.examples.extensions.ninjabattle.ninja;

import junit.framework.Assert;

import org.cadian.examples.extensions.ninjabattle.attack.Attack;
import org.cadian.examples.extensions.ninjabattle.util.MockRandomUtilImpl;
import org.cadian.extensions.core.Extensions;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for Ninja.
 * 
 * @author Joshua Hornsby
 */
public class NinjaTest {
	private Ninja ninja;
	
	@Before
	public void init() {
		ninja = new Ninja("test-dummy", "black-belt", 100);
		Extensions.injectMocks(true);
		MockRandomUtilImpl.reset();
	}
	
	@Test
	public void testGetAttack() {
		MockRandomUtilImpl.RANDOM_DOUBLE.set(1.0);
		Assert.assertEquals(Integer.MAX_VALUE, ninja.attack().getDamage());
	}
	
	@Test
	public void testTakeDamage() {
		ninja.getAttacked(new Attack() {
			
			public String getName() {
				return "Fluffy Slipper to the Face";
			}
			
			public int getDamage() {
				return 2;
			}
		});
		Assert.assertEquals(98, ninja.getLife());
	}
	
	@Test
	public void testKill() {
		ninja.getAttacked(new Attack() {
			
			public String getName() {
				return "Nuke";
			}
			
			public int getDamage() {
				return 10000;
			}
		});
		Assert.assertTrue(0 > ninja.getLife());
	}
}
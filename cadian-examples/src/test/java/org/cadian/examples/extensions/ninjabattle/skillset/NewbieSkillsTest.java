package org.cadian.examples.extensions.ninjabattle.skillset;

import junit.framework.Assert;

import org.cadian.examples.extensions.ninjabattle.attack.Kick;
import org.cadian.examples.extensions.ninjabattle.attack.Punch;
import org.cadian.examples.extensions.ninjabattle.attack.SwordSlash;
import org.cadian.examples.extensions.ninjabattle.util.MockRandomUtilImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for NewbieSkills
 * 
 * @author Joshua Hornsby
 */
public class NewbieSkillsTest {
	private NewbieSkills skills;
	
	@Before
	public void init() {
		MockRandomUtilImpl.reset();
		skills = new NewbieSkills();
	}
	
	@Test
	public void testKick() {
		MockRandomUtilImpl.RANDOM_DOUBLE.set(0.0);
		Assert.assertEquals(Kick.class, skills.getRandomAttack().getClass());
		MockRandomUtilImpl.RANDOM_DOUBLE.set(0.119999);
		Assert.assertEquals(Kick.class, skills.getRandomAttack().getClass());
	}
	
	@Test
	public void testPunch() {
		MockRandomUtilImpl.RANDOM_DOUBLE.set(0.12);
		Assert.assertEquals(Punch.class, skills.getRandomAttack().getClass());
		MockRandomUtilImpl.RANDOM_DOUBLE.set(0.839999);
		Assert.assertEquals(Punch.class, skills.getRandomAttack().getClass());
	}
	
	@Test
	public void testSwordSlash() {
		MockRandomUtilImpl.RANDOM_DOUBLE.set(0.84);
		Assert.assertEquals(SwordSlash.class, skills.getRandomAttack().getClass());
		MockRandomUtilImpl.RANDOM_DOUBLE.set(1.00);
		Assert.assertEquals(SwordSlash.class, skills.getRandomAttack().getClass());
	}
}
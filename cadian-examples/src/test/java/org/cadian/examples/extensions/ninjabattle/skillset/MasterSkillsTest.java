package org.cadian.examples.extensions.ninjabattle.skillset;

import junit.framework.Assert;

import org.cadian.examples.extensions.ninjabattle.attack.Kick;
import org.cadian.examples.extensions.ninjabattle.attack.Punch;
import org.cadian.examples.extensions.ninjabattle.attack.SwordSlash;
import org.cadian.examples.extensions.ninjabattle.attack.TouchOfDeath;
import org.cadian.examples.extensions.ninjabattle.util.MockRandomUtilImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for MasterSkills.
 * 
 * @author Joshua Hornsby
 */
public class MasterSkillsTest {
	private MasterSkills skills;
	
	@Before
	public void init() {
		MockRandomUtilImpl.reset();
		skills = new MasterSkills();
	}
	
	@Test
	public void testKick() {
		MockRandomUtilImpl.RANDOM_DOUBLE.set(0.0);
		Assert.assertEquals(Kick.class, skills.getRandomAttack().getClass());
		MockRandomUtilImpl.RANDOM_DOUBLE.set(0.24999);
		Assert.assertEquals(Kick.class, skills.getRandomAttack().getClass());
	}
	
	@Test
	public void testPunch() {
		MockRandomUtilImpl.RANDOM_DOUBLE.set(0.3);
		Assert.assertEquals(Punch.class, skills.getRandomAttack().getClass());
		MockRandomUtilImpl.RANDOM_DOUBLE.set(0.39999);
		Assert.assertEquals(Punch.class, skills.getRandomAttack().getClass());
	}
	
	@Test
	public void testSwordSlash() {
		MockRandomUtilImpl.RANDOM_DOUBLE.set(0.4);
		Assert.assertEquals(SwordSlash.class, skills.getRandomAttack().getClass());
		MockRandomUtilImpl.RANDOM_DOUBLE.set(0.94999);
		Assert.assertEquals(SwordSlash.class, skills.getRandomAttack().getClass());
	}
	
	@Test
	public void testTouchOfDeath() {
		MockRandomUtilImpl.RANDOM_DOUBLE.set(0.95);
		Assert.assertEquals(TouchOfDeath.class, skills.getRandomAttack().getClass());
		MockRandomUtilImpl.RANDOM_DOUBLE.set(1.0);
		Assert.assertEquals(TouchOfDeath.class, skills.getRandomAttack().getClass());
	}
}
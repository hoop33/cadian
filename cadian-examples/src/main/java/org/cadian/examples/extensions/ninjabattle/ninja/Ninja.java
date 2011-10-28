package org.cadian.examples.extensions.ninjabattle.ninja;

import org.cadian.examples.extensions.ninjabattle.attack.Attack;
import org.cadian.examples.extensions.ninjabattle.skillset.SkillSet;
import org.cadian.extensions.core.Extensions;

/**
 * A Ninja.
 * 
 * @author Joshua Hornsby
 */
public class Ninja {
	private final String name;
	private final String skillLevel;
	private int life;
	
	public Ninja(String name, String skillLevel, int life) {
		this.name = name;
		this.skillLevel = skillLevel;
		this.life = life;
	}
	
	public Attack attack() {
		return getSkillSet().getRandomAttack();
	}
	
	public void getAttacked(Attack attack) {
		life -= attack.getDamage();
	}
	
	public String getName() {
		return name;
	}

	public String getSkillLevel() {
		return skillLevel;
	}
	
	public int getLife() {
		return life;
	}

	private SkillSet getSkillSet() {
		return Extensions.getBestInstanceFor(SkillSet.class, skillLevel);
	}
}
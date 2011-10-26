package org.cadian.examples.extensions.ninjabattle.ninja;

import org.cadian.examples.extensions.ninjabattle.attack.Attack;
import org.cadian.examples.extensions.ninjabattle.skillset.SkillSet;
import org.cadian.extensions.core.Extensions;
import org.cadian.extensions.core.util.CreateInstanceException;
import org.cadian.extensions.query.ExtensionQueryException;

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
		try {
			return getSkillSet().getRandomAttack();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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

	private SkillSet getSkillSet() throws ExtensionQueryException, CreateInstanceException {
		return Extensions.getBestInstanceFor(SkillSet.class, skillLevel);
	}
}
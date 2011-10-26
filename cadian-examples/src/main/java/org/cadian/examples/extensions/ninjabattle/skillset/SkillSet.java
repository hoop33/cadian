package org.cadian.examples.extensions.ninjabattle.skillset;

import java.util.List;

import org.cadian.examples.extensions.ninjabattle.attack.Attack;
import org.cadian.examples.extensions.ninjabattle.util.RandomUtil;
import org.cadian.extensions.annotation.ExtensionPoint;
import org.cadian.extensions.core.Extensions;
import org.cadian.extensions.core.util.CreateInstanceException;
import org.cadian.extensions.query.ExtensionQueryException;

/**
 * A ninja's skill set.
 * 
 * @author Joshua Hornsby
 */
@ExtensionPoint
public abstract class SkillSet {
	private final List<Skill> skillList;
	
	/**
	 * Create a new SkillSet.
	 */
	public SkillSet() {
		skillList = createSkillList();
	}
	
	/**
	 * Create this SkillSet's skill map.
	 * @return Skill Map
	 */
	protected abstract List<Skill> createSkillList();
	
	/**
	 * Get a random attack.
	 * @return An Attack
	 * @throws ExtensionQueryException
	 * @throws CreateInstanceException
	 */
	public final Attack getRandomAttack() throws ExtensionQueryException, CreateInstanceException {
		return Extensions.getBestInstanceFor(Attack.class, getSkillDescription(Extensions.getExactInstanceFor(RandomUtil.class).getRandomDouble()));
	}
	
	/**
	 * Find the skill description for the given value.
	 * @param value The Value
	 * @return The Description
	 */
	private String getSkillDescription(double value) {
		String result = null;
		for(Skill skill : skillList) {
			if((value >= skill.start && value < skill.end) || (value == 1 && skill.end == 1)) {
				result = skill.description;
			}
		}
		return result;
	}
	
	/**
	 * A skill.
	 * 
	 * @author Joshua Hornsby
	 */
	protected class Skill {
		private double start;
		private double end;
		private String description;
		public Skill(double start, double end, String description) {
			this.start = start;
			this.end = end;
			this.description = description;
		}
	}
}
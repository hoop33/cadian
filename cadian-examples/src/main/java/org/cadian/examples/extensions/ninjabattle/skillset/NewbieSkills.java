package org.cadian.examples.extensions.ninjabattle.skillset;

import java.util.Arrays;
import java.util.List;

import org.cadian.extensions.annotation.Extension;

/**
 * A beginner Ninja's skills.
 * 
 * @author Joshua Hornsby
 */
@Extension
public class NewbieSkills extends SkillSet {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Skill> createSkillList() {
		return Arrays.asList(new Skill[]{new Skill(0.0, 0.12, "medium-damage"), new Skill(0.84, 1.0, "high-damage")});
	}
}

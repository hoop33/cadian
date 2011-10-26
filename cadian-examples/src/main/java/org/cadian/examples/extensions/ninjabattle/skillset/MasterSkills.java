package org.cadian.examples.extensions.ninjabattle.skillset;

import java.util.Arrays;
import java.util.List;

import org.cadian.extensions.annotation.Extension;

/**
 * A master Ninja's skills.
 * 
 * @author Joshua Hornsby
 */
@Extension(queryStrings="black-belt")
public class MasterSkills extends SkillSet {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Skill> createSkillList() {
		return Arrays.asList(new Skill[]{new Skill(0.0, 0.25, "medium-damage"), new Skill(0.4, 0.95, "high-damage"), new Skill(0.95, 1.0, "insta-gib")});
	}
}
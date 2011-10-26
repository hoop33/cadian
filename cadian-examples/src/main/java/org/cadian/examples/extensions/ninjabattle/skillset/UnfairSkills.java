package org.cadian.examples.extensions.ninjabattle.skillset;

import java.util.Arrays;
import java.util.List;

import org.cadian.extensions.annotation.Extension;

/**
 * An unfair Ninja's skills.
 * 
 * @author Joshua Hornsby
 */
@Extension(queryStrings="white-lotus-master")
public class UnfairSkills extends SkillSet {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Skill> createSkillList() {
		return Arrays.asList(new Skill[]{new Skill(0.0, 0.2, "medium-damage"), new Skill(0.3, 0.4, "high-damage"), new Skill(0.4, 1.0, "insta-gib")});
	}
}
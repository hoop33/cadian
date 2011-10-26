package org.cadian.examples.extensions.ninjabattle.skillset;

import java.util.Arrays;
import java.util.List;

import org.cadian.extensions.annotation.Extension;

/**
 * An average Ninja's skills.
 * 
 * @author Joshua Hornsby
 */
@Extension(queryStrings="brown-belt")
public class AverageSkills extends SkillSet {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Skill> createSkillList() {
		return Arrays.asList(new Skill[]{new Skill(0.0, 0.6, "medium-damage"), new Skill(0.7, 1.0, "high-damage")});
	}
}
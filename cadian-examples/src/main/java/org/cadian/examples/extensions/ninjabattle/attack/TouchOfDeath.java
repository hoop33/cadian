package org.cadian.examples.extensions.ninjabattle.attack;

import org.cadian.extensions.annotation.Extension;

/**
 * The ultimate ninja attack.
 * 
 * @author Joshua Hornsby
 */
@Extension(queryStrings="insta-gib")
public class TouchOfDeath implements Attack {

	/**
	 * {@inheritDoc}
	 */
	public int getDamage() {
		return Integer.MAX_VALUE;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return "Touch of Death";
	}
}

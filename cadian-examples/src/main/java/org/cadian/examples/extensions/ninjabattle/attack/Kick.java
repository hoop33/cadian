package org.cadian.examples.extensions.ninjabattle.attack;

import org.cadian.extensions.annotation.Extension;

/**
 * A powerful kick.
 * 
 * @author Joshua Hornsby
 */
@Extension(queryStrings="medium-damage")
public class Kick extends RandomDamageAttack {
	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return "Roundhouse Kick";
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getMaximumDamage() {
		return 160;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getMinimumDamage() {
		return 20;
	}
}

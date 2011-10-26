package org.cadian.examples.extensions.ninjabattle.attack;

import org.cadian.extensions.annotation.Extension;

/**
 * A basic attack.
 * 
 * @author Joshua Hornsby
 */
@Extension
public class Punch extends RandomDamageAttack {
	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return "Face Punch";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getMaximumDamage() {
		return 100;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getMinimumDamage() {
		return 10;
	}
}
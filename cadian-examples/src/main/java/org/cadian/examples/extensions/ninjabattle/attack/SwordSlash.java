package org.cadian.examples.extensions.ninjabattle.attack;

import org.cadian.extensions.annotation.Extension;

/**
 * A simple attack with a sword.
 * 
 * @author Joshua Hornsby
 */
@Extension(queryStrings="high-damage")
public class SwordSlash extends RandomDamageAttack {
	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return "Katana Slash";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getMaximumDamage() {
		return 298;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getMinimumDamage() {
		return 86;
	}
}
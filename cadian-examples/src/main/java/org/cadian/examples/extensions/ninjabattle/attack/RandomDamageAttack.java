package org.cadian.examples.extensions.ninjabattle.attack;

import org.cadian.examples.extensions.ninjabattle.util.RandomUtil;
import org.cadian.extensions.core.Extensions;

/**
 * A ninja attack that does a random amount of damage.
 * 
 * @author Joshua Hornsby
 */
public abstract class RandomDamageAttack implements Attack {
	private final int damage;
	
	public RandomDamageAttack() {
		int t = 0;
		try {
			t = getMinimumDamage() + Extensions.getExactInstanceFor(RandomUtil.class).getRandomInt(getMaximumDamage() - getMinimumDamage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		damage = t;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final int getDamage() {
		return damage;
	}

	/**
	 * Get the maximum damage this attack can do.
	 * @return Maximum Damage
	 */
	protected abstract int getMaximumDamage();

	/**
	 * Get the minimum damage this attack can do.
	 * @return Minimum Damage
	 */
	protected abstract int getMinimumDamage();
}
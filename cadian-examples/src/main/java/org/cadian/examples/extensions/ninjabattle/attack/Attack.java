package org.cadian.examples.extensions.ninjabattle.attack;

import org.cadian.extensions.annotation.ExtensionPoint;

/**
 * A ninja attack.
 * 
 * @author Joshua Hornsby
 */
@ExtensionPoint
public interface Attack {

	/**
	 * Get the damage done by this attack.
	 * @return Damage
	 */
	public int getDamage();
	
	/**
	 * Get the name of this attack.
	 * @return Name
	 */
	public String getName();
}
package org.cadian.examples.extensions.ninjabattle.util;

import org.cadian.extensions.annotation.ExtensionPoint;

/**
 * A random number utility.
 * 
 * @author Joshua Hornsby
 */
@ExtensionPoint
public interface RandomUtil {

	/**
	 * Get a random boolean.
	 * @return Random Boolean
	 */
	public boolean getRandomBoolean();
	
	/**
	 * Get a random integer.
	 * @param max Max Value
	 * @return Random Integer
	 */
	public int getRandomInt(int max);
	
	/**
	 * Get a random double.
	 * @return Random Double
	 */
	public double getRandomDouble();
}
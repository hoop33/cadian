package org.cadian.examples.extensions.ninjabattle.util;

import java.util.Random;

import org.cadian.extensions.annotation.Extension;

/**
 * Implementation of a RandomUtil.
 * 
 * @author Joshua Hornsby
 */
@Extension
public class RandomUtilImpl implements RandomUtil {
	
	/**
	 * Get singleton instance.
	 * @return Singleton Instance
	 */
	public static RandomUtil getInstance() {
		return Instance.INSTANCE.get();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean getRandomBoolean() {
		return random.nextBoolean();
	}

	/**
	 * {@inheritDoc}
	 */
	public int getRandomInt(int max) {
		return random.nextInt(max);
	}

	/**
	 * {@inheritDoc}
	 */
	public double getRandomDouble() {
		return random.nextDouble();
	}
	
	private Random random;
	
	protected RandomUtilImpl() {
		random = new Random();
	}
	
	private enum Instance {
		INSTANCE();
		private RandomUtil instance;
		private Instance() {
			instance = new RandomUtilImpl();
		}
		private RandomUtil get() {
			return instance;
		}
	}
}
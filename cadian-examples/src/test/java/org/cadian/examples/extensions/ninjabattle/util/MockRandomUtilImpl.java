package org.cadian.examples.extensions.ninjabattle.util;

import org.cadian.extensions.annotation.Extension;
import org.cadian.extensions.annotation.MockExtension;

/**
 * A mock random util. There are many ways to do MockExtensions. This is just one.
 * 
 * @author Joshua Hornsby
 */
@MockExtension(extension=@Extension)
public class MockRandomUtilImpl extends RandomUtilImpl {
	public static final ThreadLocal<Boolean> RANDOM_BOOLEAN = new ThreadLocal<Boolean>();
	public static final ThreadLocal<Integer> RANDOM_INT = new ThreadLocal<Integer>();
	public static final ThreadLocal<Double> RANDOM_DOUBLE = new ThreadLocal<Double>();
	public static final ThreadLocal<Boolean> RETURN_RANDOM_VALUES = new ThreadLocal<Boolean>();
	
	/**
	 * Get an instance of this mock.
	 * @return Mock
	 */
	public static RandomUtilImpl getInstance() {
		return new MockRandomUtilImpl();
	}
	
	public static void reset() {
		RANDOM_BOOLEAN.set(false);
		RANDOM_INT.set(0);
		RANDOM_DOUBLE.set(0.0);
		RETURN_RANDOM_VALUES.set(false);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean getRandomBoolean() {
		return RETURN_RANDOM_VALUES.get() ? super.getRandomBoolean() : RANDOM_BOOLEAN.get();
	}

	/**
	 * {@inheritDoc}
	 */
	public int getRandomInt(int max) {
		return RETURN_RANDOM_VALUES.get() ? super.getRandomInt(max) : RANDOM_INT.get();
	}

	/**
	 * {@inheritDoc}
	 */
	public double getRandomDouble() {
		return RETURN_RANDOM_VALUES.get() ? super.getRandomDouble() : RANDOM_DOUBLE.get();
	}
}
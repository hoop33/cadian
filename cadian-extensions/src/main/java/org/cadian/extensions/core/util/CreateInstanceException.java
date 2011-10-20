package org.cadian.extensions.core.util;

/**
 * Exception to encapsulate problems encountered when trying to create an or access an instance of a class.
 * 
 * @author Joshua Hornsby
 */
public class CreateInstanceException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "An exception occurred while trying to instantiate %s. %s";
	
	/**
	 * {@inheritDoc}
	 */
	public CreateInstanceException() {
	}

	/**
	 * {@inheritDoc}
	 */
	public CreateInstanceException(Class<?> target, String message, Throwable cause) {
		super(String.format(MESSAGE, target.getName(), message), cause);
	}

	/**
	 * {@inheritDoc}
	 */
	public CreateInstanceException(Class<?> target, String message) {
		super(String.format(MESSAGE, target, message));
	}

	/**
	 * {@inheritDoc}
	 */
	public CreateInstanceException(Throwable cause) {
		super(cause);
	}
}
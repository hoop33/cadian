package org.cadian.extensions.query;

/**
 * An exception to encapsulate failures encountered while executing an ExtensionQuery.
 * 
 * @author Joshua Hornsby
 */
public class ExtensionQueryException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc}
	 */
	public ExtensionQueryException() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ExtensionQueryException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * {@inheritDoc}
	 */
	public ExtensionQueryException(String message) {
		super(message);
	}

	/**
	 * {@inheritDoc}
	 */
	public ExtensionQueryException(Throwable cause) {
		super(cause);
	}
}
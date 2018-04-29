package data.resources;

/**
 * Custom exception class for Data file issues reading and writing
 * Gets passed to fron-tend components so they can display alerts with this info
 * @author Maya Messinger
 */
public class DataFileException extends Exception {
	/**
	 * No-arg constructor
	 */
	public DataFileException()	{
		super();
	}

	/**
	 * @param message	message to describe the problem
	 */
	public DataFileException(String message)	{
		super(message);
	}

	/**
	 * @param message	message to describe the problem
	 * @param cause		Exception cause
	 */
	public DataFileException(String message, Throwable cause)	{
		super(message, cause);
	}

	/**
	 * @param cause		Exception cause
	 */
	public DataFileException(Throwable cause)	{
		super(cause);
	}


}
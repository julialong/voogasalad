package data.resources;

import java.lang.Exception;

public class DataFileException extends Exception {
	public DataFileException()	{
		super();
	}
	public DataFileException(String message)	{
		super(message);
	}
	public DataFileException(String message, Throwable cause)	{
		super(message, cause);
	}
	public DataFileException(Throwable cause)	{
		super(cause);
	}


}
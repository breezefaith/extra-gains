package assignment2020.codeprovided.dataloading;

/*
 * DataParsingException.java  	1.0  10/04/2020
 *
 * Copyright (c) University of Sheffield 2020
 */

/**
 * DataParsingException.java
 *
 * Exception denoting that a Parsing error has occurred. This class should NOT
 * be modified and should be kept as it is.
 * 
 * @version 1.0 10/04/2020
 *
 * @author Ben Clegg / Islam Elgendy / Maria-Cruz Villa-Uriol
 */
public class DataParsingException extends Exception {
	/**
	 * Generated serial ID
	 */
	private static final long serialVersionUID = 3616860147116156712L;

	/**
	 * A parsing exception to throw if an error occurs when parsing a Participant in
	 * DataLoader.
	 * 
	 * @param message a message to display for the exception.
	 */
	public DataParsingException(String message) {
		super(message);
	}
}

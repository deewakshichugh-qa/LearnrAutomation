package objects.exceptions;

import logger.ScriptLogger;

public class ApplicationException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApplicationException(String s){
		super(s);
	}
	
	public ApplicationException(Exception e,String s){
		super(s);
		ScriptLogger.error(e);
	}
}

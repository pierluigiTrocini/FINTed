package it.unical.demacs.enterprise.fintedapp.exception;


public class CredentialsAlreadyUsedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -103515510736533360L;

	public CredentialsAlreadyUsedException(String message){
        super(message);
    }
}
package Exceptions;

/**
 * This class represents an exception that will be triggered when the player dies.
 */
public class DeathException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DeathException() {
		super("Player has died. Resetting the game ...");
	}
	
}

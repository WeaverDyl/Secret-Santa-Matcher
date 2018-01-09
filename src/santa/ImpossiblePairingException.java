package santa;

/**
 * Thrown when a pairing is found to be impossible to make. This is
 * when we've tried 1000 times to create a pairing and have not
 * successfully found one.
 * 
 * Aside: This will only really become an issue when I implement the
 *        notion of exclusions, where certain people are GUARENTEED
 *        NOT to get a specific person, or specific people. Otherwise,
 *        currently, the only way this will be thrown is if the same
 *        person was added to the config file twice.
 * 
 * @author Dylan Weaver
 *
 */
public class ImpossiblePairingException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ImpossiblePairingException(String message) {
		super(message);
	}

}

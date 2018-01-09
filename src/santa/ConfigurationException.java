package santa;

/**
 * Thrown when the JSON array doesn't have a name and number for
 * each participant.
 * 
 * @author Dylan Weaver
 *
 */
public class ConfigurationException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ConfigurationException(String message) {
		super(message);
	}

}

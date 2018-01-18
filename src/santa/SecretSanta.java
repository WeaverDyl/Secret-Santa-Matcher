package santa;

import java.util.ArrayList;

/**
 * The main program. Calls methods to create the participants, create
 * the pairs, and alert each participant of who they are buying gifts for
 * 
 * @author Dylan Weaver
 *
 */
public class SecretSanta {
	// A list of the participants
	static ArrayList<Participant> participantsCopy = new ArrayList<>();
	
	// A list of the matched pairs of participants
	static ArrayList<Pair> pairsCopy = new ArrayList<>(); 
	
	public static void main(String[] args) throws ConfigurationException, 
		ImpossiblePairingException {

		ReadJSON.addParticipants(participantsCopy); // Add the participants
		Matcher.setPairs(participantsCopy, pairsCopy); // Create the pairs
		SendMessage.sendText(pairsCopy); // Send the messages
		System.out.println("DONE!"); // Alert user that all tasks accomplished
	}
}

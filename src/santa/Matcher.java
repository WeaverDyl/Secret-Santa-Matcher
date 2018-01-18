package santa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Creates pairs of participants so that everybody gives a gift and
 * everybody receives a gift. Exceptions are thrown when it is impossible
 * to create such a matching/pairing.
 * 
 * @author Dylan Weaver
 *
 */
public class Matcher {	
	
	/**
	 * Sets the pairings. This creates a copy of the participants, shuffles
	 * that copy, and makes pairs of equal elements from both the original
	 * and the shuffled list (e.g. element one of the original list is paired
	 * with element one of the shuffled list). If it's impossible to make a
	 * pairing, or somebody gets paired with themselves, the process will
	 * start over again. It will try to make a pairing a maximum of 1000
	 * times before asserting that it's impossible.
	 * 
	 * @param participants The list of all participants.
	 * @param copyPairsTo The list where the pairings will be put.
	 * @throws ImpossiblePairingException Thrown after 1000 failed attempts to
	 * 									  make a pairing.
	 */
	public static void setPairs(ArrayList<Participant> participants, 
			ArrayList<Pair> copyPairsTo) throws ImpossiblePairingException {
		
		// A list containing the shuffled participants
		ArrayList<Participant> shuffledParticipants = new ArrayList<>(); 
		
		// Used to test things such as "nobody gets themselves", 
		// "everybody gets somebody" are true
		boolean pairsAreValid = true;
		
		int listLength = participants.size();
		int iterations = 0; // The current number of pairing iterations.
						    // lower = better.
		
		if (!checkPhysicalDuplicates(participants)) {
			throw new ImpossiblePairingException("There appears to be "
					+ "duplicate elements in your config.json file. "
					+ "Ensure that nobody appears more than once!");
		}
		
		do {
			pairsAreValid = true; // Assume pairs are valid for now
			copyPairsTo.clear(); // Clear any existing pair members
			
			 // Shuffle the list and assign it to shuffledParticipants
			shuffledParticipants = shuffleParticipants(participants);
			
			// A loop to actually create the pairings.
			for (int i = 0; i < listLength; i++) {
				Pair pair = new Pair(participants.get(i), 
						shuffledParticipants.get(i)); // Assign the pair
				copyPairsTo.add(pair); // Add to list of pairs
			}
			
			// Check for duplicates. If they exist, redo the pairing.
			if (!checkPairDuplicates(copyPairsTo) 
					|| !checkExclusionSatisfied(copyPairsTo)) {
				
				pairsAreValid = false;
				iterations++;
			}
			
			// If the pairs contained a duplicate, redo the pairing.
		} while (!pairsAreValid && iterations < 1000);
		
		// If we've tried 1000 times, just assume it's impossible. 
		if (iterations == 1000) {
			throw new ImpossiblePairingException("Something is probably wrong "
					+ "with your setup, as no pairing can be made.");
		}
	}
	
	/**
	 * Calls collections.shuffle using a nanotime seed.
	 * 
	 * @param participant An arraylist of UNSHUFFLED participants
	 * @return An arraylist of SHUFFLED participants
	 */
	private static ArrayList<Participant> 
		shuffleParticipants(ArrayList<Participant> participant) {
		
		@SuppressWarnings("unchecked")
		ArrayList<Participant> shuffledList = 
			(ArrayList<Participant>) participant.clone();
		
		// Nanotime is used because it changes so quickly
		long seed = System.nanoTime();
		
		// Shuffle the list using the nanotime seed
		Collections.shuffle(shuffledList, new Random(seed));
		
		return shuffledList;
	}
	
	/**
	 * Checks if there are any duplicates within the current pairing. A 
	 * duplicate occurs when one person is matched with themself.
	 * 
	 * @param pairs The current list of santa/recipient pairings
	 * @return True if no duplicates, false if duplicates exist.
	 */
	private static boolean checkPairDuplicates(ArrayList<Pair> pairs) {
		boolean noDuplicates = true; // True: no duplicates, false: duplicates

		for (Pair p : pairs) {
			if (p.getSanta().equals(p.getRecipient())) {
				noDuplicates = false; // Return that duplicates WERE found.
			}
		}
		
		return noDuplicates;
	}
	
	/**
	 * Checks if there are two equivalent elements in the list of participants.
	 * 
	 * Aside: This could probably be made obselete if I instead wrote
	 * the pairs list as a set instead of an arraylist. I realized this
	 * after I finished, and will hopefully implement it properly soon.
	 * 
	 * @param pairs The current list of participants
	 * @return True if there is more than one element with 2+ occurrences,
	 * 		   false otherwise.
	 */
	private static boolean checkPhysicalDuplicates
		(ArrayList<Participant> participants) {
		
		boolean noDuplicates = true; // True: no duplicates, false: duplicates
		ArrayList<Participant> usedParticipants = new ArrayList<>();
		
		for (Participant p : participants) {
			// If the participant has already been added to the list,
			// it means that there's a duplicate. Return that fact
			if (usedParticipants.contains(p)) {
				noDuplicates = false;
				break;
			}
			
			// Otherwise, add the name so any subsequent duplicate is found
			usedParticipants.add(p);
		}
		
		return noDuplicates;
	}
	
	/**
	 * Checks if a valid pairing was created, taking into account
	 * the exclusion lists that somebody may have set up to guarentee
	 * that they don't get a certain person/people
	 * 
	 * @param pairs The current pairing of santas/recipients
	 * @return true if no participant received somebody on their exclusion list
	 */
	private static boolean checkExclusionSatisfied(ArrayList<Pair> pairs) {
		boolean satisfied = true;
		
		// Check that the santa didn't get somebody on their exclusion list
		for (Pair p : pairs) {
			// If the santa received somebody on their exclusion list, the
			// pairing is invalid.
			if (p.getSanta().getExclusion().contains(p.getRecipientName())) {
				satisfied = false;
			}
		}
		
		return satisfied;
	}
}

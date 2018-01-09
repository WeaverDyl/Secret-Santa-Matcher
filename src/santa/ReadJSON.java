package santa;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * A class that reads a JSON file and creates each participant from that data
 * 
 * @author Dylan Weaver
 * 
 */
public class ReadJSON {

	/**
	 *  Reads the participants from the JSON file and adds them to the 
	 *  participants arraylist
	 * 
	 * @param copyParticipantsTo The arraylist to copy each participant to
	 * @throws ConfigurationException Thrown when the JSON array is missing
	 * 								  either the name or number element
	 */
	public static void addParticipants(ArrayList<Participant> copyParticipantsTo) 
			throws ConfigurationException {
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader("config.json"));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray participantArray = (JSONArray) 
					jsonObject.get("participants");
			
			// Go through each array element in the JSON file and add each 
			// participant.
			for (int i = 0; i < participantArray.size(); i++) {
				JSONObject element = (JSONObject) participantArray.get(i);
				// Test that both a name and number exist for each element
				if (element.get("name") == null || 
						element.get("number") == null) {
					throw new ConfigurationException("Your JSON file is not "
							+ "configured correctly. Please make sure there "
							+ "is a 'name' and 'number' field for each "
							+ "participant.");
				}
				
				// Create the participant, add it to the list that was passed in
				Participant p = new Participant(element.get("name").toString(),
						element.get("number").toString());
				copyParticipantsTo.add(p);
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(ParseException e) {
			e.printStackTrace();
		}	
	}
	
}

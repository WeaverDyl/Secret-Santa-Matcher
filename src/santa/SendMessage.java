package santa;

import java.util.ArrayList;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * Handles sending the text message to each person telling them
 * who they are the secret santa for. Uses the Twilio API which
 * makes this process fairly trivial
 * 
 * @author Dylan Weaver
 *
 */
public class SendMessage {
  // Enabling dev mode will not send messages, but instead print out expected
  // output. If devMode is false, it WILL send messages!
  public static final boolean devMode = true; 
  
  public static final String ACCOUNT_SID = 
		  ""; // Put your Twilio Account SID here
  public static final String AUTH_TOKEN = 
		  ""; // Put your Twilio Auth Token Here

  /**
   * Sends a message via SMS if devMode is false, otherwise prints the message
   * to the console/command line.
   * 
   * @param pairs The list of santa/recipient pairs
   */
  public static void sendText(ArrayList<Pair> pairs) {
	    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	    
	    for (Pair p : pairs) {
	    	// Send messages if devMode is false
	    	if (!devMode) {
			    Message message = Message.creator(new PhoneNumber(p.getSantaNumber()),
			            new PhoneNumber("+12402153734"), "Hello " + p.getSantaName()
			            + "! You are the secret santa for: " + p.getRecipientName() 
			            + ". Their phone number is: " 
			            + p.getRecipientNumber()).create();
	    	} else {
	    		// If devMode is true, just print out what WOULD be sent.
	    		System.out.println("Hello " + p.getSantaName()
	            + "! You are the secret santa for: " + p.getRecipientName() 
	            + ". Their phone number is: " + p.getRecipientNumber());
	    	}
	    }
  }
}
package santa;

/**
 * Represents a pair of participants, e.g. one is the santa, and the other is 
 * the recipient of the gift. Everybody should have a santa, and everybody 
 * should be a receiver for somebody else.
 * 
 * @author Dylan Weaver
 *
 */
public class Pair {
	private Participant santa;
	private Participant recipient;
	
	public Pair(Participant santa, Participant recipient) {
		this.santa = santa;
		this.recipient = recipient;
	}
	
	public Participant getSanta() {
		return this.santa;
	}
	
	public Participant getRecipient() {
		return this.recipient;
	}
	
	public String getSantaName() {
		return this.santa.getName();
	}
	
	public String getSantaNumber() {
		return this.santa.getNumber();
	}
	
	public String getRecipientName() {
		return this.recipient.getName();
	}
	
	public String getRecipientNumber() {
		return this.recipient.getNumber();
	}
}

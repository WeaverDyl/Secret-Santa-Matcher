package santa;

/**
 * Represents a single participant, who has a name and a phone number.
 * They
 * 
 * @author Dylan Weaver
 *
 */
public class Participant {
	private String name;
	private String phoneNumber;
	
	public Participant(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public String getNumber() {
		return phoneNumber;
	}
	
	@Override
	public boolean equals(Object o) {
		Participant p;
		boolean result = false;
		
		if (o == this) {
			return true;
		} else {
			if (o instanceof Participant) {
				p = (Participant) o;
				
				result = getName().equals(p.name) 
						&& getNumber().equals(p.phoneNumber);
			}
		}
		
		return result;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		
		result = 31 * result + name.hashCode();
		result = 31 * result + phoneNumber.hashCode();
		
		return result;
	}
}

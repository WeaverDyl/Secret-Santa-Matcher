package santa;

import java.util.List;

/**
 * Represents a single participant, who has a name and a phone number.
 * 
 * @author Dylan Weaver
 *
 */
public class Participant {
	private String name;
	private String phoneNumber;
	private List<String> exclusions;
	
	public Participant(String name, String phoneNumber, List<String> exclusions) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.exclusions = exclusions;
	}
	
	public String getName() {
		return name;
	}
	
	public String getNumber() {
		return phoneNumber;
	}
	public List<String> getExclusion() {
		return exclusions;
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

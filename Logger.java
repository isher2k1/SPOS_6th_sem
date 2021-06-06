import java.util.Enumeration ;

public class Logger extends SyntxAlyz {
	public Logger(int fd, String separator) throws Exception {
		super(fd,separator);
	}
  
	public boolean passwordVerifier(String identifier ) {
		return get(identifier,0).equals("1");
	}

	public boolean membershipVerifier(String identifier, String groupName) {
		String groupIdentifier = get(identifier,2);
		return groupIdentifier.equals(groupName);
	}

	public String get(int id) {
		Enumeration<String>keys = hashTable.keys();
		while(keys.hasMoreElements()){
			String key = keys.nextElement() ;
			if(getID(key) == id)
				return key;
		}
		return "?";
	}
  
	public boolean belongs(int id) {
		Enumeration<String> keys = hashTable.keys();
		while(keys.hasMoreElements()){
			String key = keys.nextElement() ;
			if(getID(key) == id)
				return true ;
		}
		return false;
	}

	public  int getID(String key){
		return Integer.parseInt(get(key,1));
	}
}

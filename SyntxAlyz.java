import java.util.Hashtable ;
import java.util.Enumeration;
import java.util.StringTokenizer;

public class SyntxAlyz {
	protected int fd ;
	protected String separator ;
	protected Hashtable<String,String> hashTable;

	public SyntxAlyz(int fd,String sep) throws Exception {
		hashTable = new Hashtable<String,String>();
		this.fd = fd;
		separator = sep;
		buildHashTable();
	}

	public void setFd(int fd) {
		this.fd = fd;
	}
  
	public void inject() throws Exception {
		Kernel.lseek(fd,0,0);
		Enumeration <String> keys = hashTable.keys();
		while(keys.hasMoreElements()){
			String key = keys.nextElement();
			String value = hashTable.get(key);
			byte[] entry = (key + separator + value + "\n").getBytes();
			Kernel.write(fd,entry,entry.length);
		}
	}

	public void save(String key,String value){
		hashTable.put(key,value);
	}

	public boolean belongs(String key){
		return hashTable.containsKey(key);
	}

	public String get(String key){
		return hashTable.get(key);
	}
  
	public String get(String key,int index){
		String value = hashTable.get(key);
		StringTokenizer tokens = new StringTokenizer(value,separator);
		while(index-- > 0 && tokens.hasMoreTokens())
			tokens.nextToken();
		if(index < 0) 
			return tokens.nextToken();
		return null;
  }

	private void buildHashTable() throws Exception {
		byte c[] = new byte[1];
		StringBuffer buffer = new StringBuffer();

		while(true){ 
			int counter = Kernel.read(fd,c,1);
			String t = new String(c);
			if(counter <= 0)
				break;
			if(t.equals("\n")){
				StringTokenizer tokens = new StringTokenizer(buffer.toString(),separator);
				String key = tokens.nextToken();
				String value = buffer.substring(key.length() + 1,buffer.length());

				hashTable.put(key,value);
				buffer.delete(0,buffer.length());
				continue;
			}
			buffer.append(t);
		}
	}
}

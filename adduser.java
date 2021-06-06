import java.util.Vector;

/*
 * This program add user to group
 */

public class adduser {
	
	public static final String FUNCTION_NAME = "adduser";
	private static final String PASSWD_FILE = "/etc/passwd";
  
	public static void main(Vector<String> args) throws Exception{
		
	    int fd;
		// initialize the file system simulator kernel
		Kernel.initialize() ;
		//check the number of arguments
		if(args.size() != 2){
			System.out.println("[Error] " + FUNCTION_NAME + " : Invalid entry.") ;
			Kernel.exit( 1 ) ;
		}
		
		String username = (String) args.elementAt(0);//get the user name
	    String groupname = (String) args.elementAt(1);//get the group name
	    //check if the user name exist
		if(!Kernel.passwd.belongs(username)){
			System.out.println("[Error] " + FUNCTION_NAME + " : " + username + "does not exist.") ;
			Kernel.exit(3) ;
		}
		//check if the group name exist
		if(!Kernel.group.belongs(groupname)){
			System.out.println("[Error] " + FUNCTION_NAME + " : " + groupname + "does not exist.") ;
			Kernel.exit(3) ;
		}
		//create fd file from the password file(for write)
		fd = Kernel.creat(PASSWD_FILE,(short) (Kernel.S_IFREG | 0644));
		//check if the file is opened
		if(fd < 0){
			Kernel.perror(FUNCTION_NAME) ;
			System.out.println("[Error] " + FUNCTION_NAME + ": unable to open \"" + PASSWD_FILE + "\"" ) ;
			Kernel.exit( 2 ) ;
		}
		//set the file to the syntax analyzer
		Kernel.passwd.setFd(fd);
		//set the new entry line,save it in hash table with new key and add the new entry to the file
		String entry = Kernel.passwd.get(username,0) + ":" + Kernel.passwd.get(username,1) + ":" + groupname ;
		Kernel.passwd.save(username,entry) ;
		Kernel.passwd.inject();
		System.out.println("User added successfully to group");
		//close the file
		Kernel.close(fd) ;
		// exit with success
		//Kernel.exit(0);//disable the comment if you don't use the swing frame
	}
}

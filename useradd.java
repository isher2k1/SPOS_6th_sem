import java.util.Vector;

/*
 * This program add user
 */

public class useradd {
	
  public static final String FUNCTION_NAME = "useradd";
  public static final String PASSWD_FILE = "/etc/passwd" ;
  
  public static void main( Vector<String> args ) throws Exception
  {
    // initialize the file system simulator kernel
    Kernel.initialize() ;
    //check the number of arguments
    if(args.size() != 2){
    	System.out.println("[Error] " + FUNCTION_NAME + " : Invalid entry");
    	Kernel.exit(1) ;
    }

    String username = (String)args.elementAt(0);//get the user name
    
    int uid = 0;
    try {
    	uid = Integer.parseInt((String) args.elementAt(1));//get the user id
    }
    catch(NumberFormatException e){
    	System.out.println("[Error] " + FUNCTION_NAME + " : Invalid arguments");
    	Kernel.exit(1) ;
    }
    //check if the user name exist
    if(Kernel.passwd.belongs(username )){
    	System.out.println("[Error] " + FUNCTION_NAME + " : " + username + " already exists");
    	Kernel.exit(3) ;
    }
    //check if the user id exist
    if(Kernel.passwd.belongs(uid)){
    	System.out.println("[Error] " + FUNCTION_NAME + " : " + uid + " already used") ; 
    	Kernel.exit(3) ;
    }

    int fd;
    //create fd file from the password file(for write)
    fd = Kernel.creat(PASSWD_FILE,(short)(Kernel.S_IFREG | 0644));
    //check if the file is opened
    if(fd < 0){
    	Kernel.perror(FUNCTION_NAME) ;
    	System.out.println("[Error] " + FUNCTION_NAME + ": could not open the " + PASSWD_FILE +" file.");
    	Kernel.exit(2) ;
    }
    //set the file to the syntax analyzer
    Kernel.passwd.setFd(fd);
    //set the new entry line,save it in hash table with new key and add the new entry to the file
    Kernel.passwd.save(username,"0:" + uid + ":-") ;//set 0 => no password(Default)
    Kernel.passwd.inject();
    System.out.println("User successfully added");
    // exit with success
	//Kernel.exit(0);//disable the comment if you don't use the swing frame
  }

}

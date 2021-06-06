import java.io.Console;
import java.util.Vector;

/*
 * This program set a password to group
 */

public class gpasswd{
	
	public static final String FUNCTION_NAME = "gpasswd" ;
	public static final String SHADOW_FILE = "/etc/gshadow" ;
	public static final String GROUP_FILE = "/etc/group" ;
  
	public static void main( Vector<String> args ) throws Exception{
    // initialize the file system simulator kernel
    Kernel.initialize() ;
    //check the number of arguments
    if(args.size() != 2){
      System.out.println("[Error] " + FUNCTION_NAME + ": Invalid entry") ;
      Kernel.exit( 1 ) ;
    }

    String groupName = (String) args.elementAt(0);//get the group name
    String passwd = (String) args.elementAt(1);
    //check if the group name exist
    if(!Kernel.group.belongs( groupName ) )
    {
    	System.out.println("[Error] " + FUNCTION_NAME + " : " + groupName + " does not exist");
    	Kernel.exit(3) ;
    }

    int fd1, fd2;
    //create fd file from the group file
    fd1 = Kernel.creat(GROUP_FILE,(short) (Kernel.S_IFREG | 0600));
    //check if the file is opened
    if( fd1 < 0 ){
    	Kernel.perror(FUNCTION_NAME) ;
    	System.out.println("[Error] " + FUNCTION_NAME + ": could not open the " + GROUP_FILE +" file.") ;
    	Kernel.exit( 2 ) ;
    }
    //create fd file from the shadow file
    fd2 = Kernel.creat(SHADOW_FILE,(short) (Kernel.S_IFREG | 0600));
    //check if the file is opened
    if( fd2 < 0 ){
    	Kernel.perror(FUNCTION_NAME) ;
    	System.out.println("[Error] " + FUNCTION_NAME + ": could not open the " + SHADOW_FILE +" file.") ;
    	Kernel.exit(2) ;
    }
    //set the file to the syntax analyzer
    Kernel.group.setFd(fd1);
    Kernel.gshadow.setFd(fd2);
    //ask the user to write the group password
    /*Console console = System.console();
    String passwd = new String(console.readPassword("%s", "Write your password: "));
	String passwdRetyped = new String(console.readPassword("%s", "Rewrite your password: "));
	//check if the two password match
	if(!passwd.equals(passwdRetyped))
	{
		System.out.println( FUNCTION_NAME + ": passwords don't match");
		System.exit( 1 );
	}*/
	//set the new entry line,save it in hash table with new key and add the new entry to the file
    String entry = "1:" + Kernel.group.get(groupName, 1) + ":";//set 1 => has password 

    Kernel.gshadow.save(groupName,passwd) ;
    Kernel.group.save(groupName, entry ) ;
    
    Kernel.gshadow.inject() ;
    Kernel.group.inject() ;
    System.out.println("Password added successfully");
    //close files
    Kernel.close( fd1 ) ;
    Kernel.close( fd2 ) ;

    // exit with success
 	//Kernel.exit(0);//disable the comment if you don't use the swing frame
  }

}

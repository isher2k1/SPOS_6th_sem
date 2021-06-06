import java.util.Scanner;
import java.util.Vector;


/*
 * This program set a password to user
 */

public class passwd {
	
	public static final String FUNCTION_NAME = "passwd" ;
	public static final String SHADOW_FILE = "/etc/shadow" ;
	public static final String PASSWD_FILE = "/etc/passwd" ;
  
	public static void main(Vector<String> args) throws Exception {
		// initialize the file system simulator kernel
		Kernel.initialize();
		//check the number of arguments
		if(args.size() != 2){
			System.out.println("[Error] " + FUNCTION_NAME + ": Invalid entry") ;
			Kernel.exit(1) ;
		}

		String userName = (String) args.elementAt(0) ;//get the user name
		String passwd =(String) args.elementAt(1);
		//check if the user name exist
		if(!Kernel.passwd.belongs(userName)){
			System.out.println("[Error] " + FUNCTION_NAME + ": " + userName + " does not exist.") ;
			Kernel.exit(3) ;
		}

		int fd1,fd2;
		//create fd file from the password file(for write)
		fd1 = Kernel.creat(PASSWD_FILE,(short)(Kernel.S_IFREG | 0600));
		//check if the file is opened
		if( fd1 < 0 ){
			Kernel.perror(FUNCTION_NAME) ;
			System.out.println("[Error] " + FUNCTION_NAME + ": could not open the " + PASSWD_FILE +" file.") ;
			Kernel.exit( 2 ) ;
		}
		//create fd file from the shadow file(for write)
		fd2 = Kernel.creat(SHADOW_FILE,(short)(Kernel.S_IFREG | 0600));
		//check if the file is opened
		if( fd2 < 0 ){
			Kernel.perror( FUNCTION_NAME ) ;
			System.out.println("[Error] " + FUNCTION_NAME + ": could not open the " + SHADOW_FILE +" file.") ;
			Kernel.exit( 2 );
		}
		//set the file to the syntax analyzer
		Kernel.passwd.setFd( fd1 );
    	Kernel.shadow.setFd( fd2 );
    	//ask the user to write the user password
    	/*Console console = System.console();
    	String passwd = new String(console.readPassword("%s", "Write your password: "));
    	String passwdRetyped = new String(console.readPassword("%s", "Rewrite your password: "));*/
    	/*System.out.println("Write your password: ");
        Scanner sc = new Scanner(System.in);
        String passwd = sc.nextLine();
        System.out.println("Rewrite your password:");
        String passwdRetyped = sc.nextLine();*/
    	//check if the two password match
    	/*if( ! passwd.equals(passwdRetyped)){
    		System.out.println("[Error] " + FUNCTION_NAME + ": wrong passwords.");
    		System.exit( 1 );
    	}*/
    	//set the new entry line,save it in hash table with new key and add the new entry to the file
    	String entry = "1:" + Kernel.passwd.get(userName,1) + ":" + Kernel.passwd.get(userName,2);//set 1 => has password 

    	Kernel.shadow.save(userName,passwd);
    	Kernel.passwd.save( userName, entry ) ;
    
    	Kernel.shadow.inject() ;
    	Kernel.passwd.inject() ;
    	System.out.println("Password added successfully");
    	//close files
    	Kernel.close( fd1 ) ;
    	Kernel.close( fd2 ) ;

    	// exit with success
    	//Kernel.exit( 0 ) ;
	}
}

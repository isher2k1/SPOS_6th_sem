import java.util.Vector;

/*
 * This program add a group user
 */

public class groupadd {
	
  public static final String FUNCTION_NAME = "groupadd" ;
  public static final String GROUP_FILE = "/etc/group" ;
  
  public static void main( Vector<String> args ) throws Exception{
    
	  // initialize the file system simulator kernel
	  Kernel.initialize() ;
	  //check the number of arguments
	  if(args.size() != 2){
		  System.out.println("[Error] " + FUNCTION_NAME + ": Invalid entry");
		  Kernel.exit( 1 ) ;
	  }

	  String groupname = (String) args.elementAt(0);//get the group name
	  int gid = 0;
	  //get the group id
	  try {
		  gid = Integer.parseInt((String) args.elementAt(1));
	  }
	  catch(NumberFormatException e){
		  System.out.println("[Error] " + FUNCTION_NAME + ": invalid arguments");
		  Kernel.exit( 1 ) ;
	  }
	  //check if the group name exist
	  if(Kernel.group.belongs(groupname)){
		  System.out.println("[Error] " + FUNCTION_NAME + " : " + groupname + " already exists");
		  Kernel.exit(3) ;
	  }
	  //check if the group id exist
	  if(Kernel.group.belongs(gid)){
		  System.out.println("[Error] " + FUNCTION_NAME + ": " + gid + " already used"); 
		  Kernel.exit(3) ;
	  }

	  int fd;
	  //create fd file from the group file(for write)
	  fd = Kernel.creat(GROUP_FILE,(short) (Kernel.S_IFREG | 0644));
	  //check if the file is opened
	  if( fd < 0 ){
		  Kernel.perror(FUNCTION_NAME) ;
		  System.out.println("[Error] " + FUNCTION_NAME + ": could not open the " + GROUP_FILE +" file.");
		  Kernel.exit(2) ;
	  }
	  //set the file to the syntax analyzer
	  Kernel.passwd.setFd( fd );
	  //set the new entry line,save it in hash table with new key and add the new entry to the file
	  Kernel.group.save( groupname,"0:" + gid + ":") ;//set 0 => no password(Default)
	  Kernel.group.inject() ;
	  System.out.println("Group successfully added");

    // exit with success
    //Kernel.exit( 0 ) ;
  }
}

import java.util.Vector;

/*
 * This program change owner of the file
 */

public class chown {
	
    public static final String FUNCTION_NAME = "chown" ;

    public static void main(Vector<String> args) throws Exception{
    	
    	// initialize the file system simulator kernel
    	Kernel.initialize() ;
    	//check the number of arguments
    	if(args.size() < 3){
    		System.out.println("[Error] " + FUNCTION_NAME + ": Invalid entry") ;
    		Kernel.exit(1) ;
    	}
    	String owner = (String)args.elementAt(0);//get the owner name
    	String group = (String)args.elementAt(1);//get the group name
    	//check if the owner name exist
    	if(!Kernel.passwd.belongs(owner)){
    		System.out.println("[Error] " + FUNCTION_NAME + " : " + owner + " does not exist");
    		Kernel.exit(1);
    	}
    	//check if the group name exist
    	if(!Kernel.group.belongs( group )){
    		System.out.println("[Error] " +  FUNCTION_NAME + " : " + group + " does not exist.");
    		Kernel.exit(1);
    	}

    	short userID = (short) Kernel.passwd.getID(owner);//get the owner id
    	short groupID = (short) Kernel.group.getID(group);//get the group id
    	//get the file path and apply the modification
    	for(int i = 2 ; i < args.size();i ++){
    		String path = (String) args.elementAt(i);
    		int status = Kernel.chown(path,userID,groupID);
    		if(status < 0){
    			Kernel.perror(FUNCTION_NAME);
    			Kernel.exit(2);
    		}
    	}
    	// exit with success
    	//Kernel.exit(0);//disable the comment if you don't use the swing frame
    }
}

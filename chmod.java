import java.util.Vector;

/*
 * This program change the right of use of the file
 */

public class chmod{
	public static final String FUNCTION_NAME = "chmod" ;

	public static void main(Vector<String> args) throws Exception{
		
		short mode = 0;
		// initialize the file system simulator kernel
		Kernel.initialize();
		//check the number of arguments
		if(args.size() < 2){
			System.out.println("[Error] " + FUNCTION_NAME + ": Invalid entry.");
			Kernel.exit( 1 ) ;
		}
		//get the mode from the string argument
		try{
			mode = Short.parseShort((String)args.elementAt(0),8) ;
		}
		catch ( NumberFormatException e ){
			System.out.println("[Error] " + FUNCTION_NAME + ": invalid mode format" ) ;
			Kernel.exit( 1 ) ;
		}
		//get the file path and apply the modification
		for(int i = 1;i < args.size();i ++){
			String path = (String)args.elementAt(i);
			try{
				int status = Kernel.chmod(path,mode) ;
				if(status < 0){
					Kernel.perror(FUNCTION_NAME) ;
					Kernel.exit(2);
				}
			}
			catch (Exception e){
				System.out.println( FUNCTION_NAME + ": " + e.getMessage() ) ;
				Kernel.exit(3) ;
			}
		}
		// exit with success
		//Kernel.exit(0);//disable the comment if you don't use the swing frame
	}
}
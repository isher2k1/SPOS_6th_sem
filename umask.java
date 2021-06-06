import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

/*
 * This program define the default right access of file
 */

public class umask {
  
  public static final String FUNCTION_NAME = "umask" ;

  public static void main( Vector<String> args ) throws Exception {
	// initialize the file system simulator kernel
  	Kernel.initialize();
  	//check the number of arguments
  	if(args.size() != 1)
	{
	  System.out.format("%03o\n", Kernel.umask());
	  System.exit(0);
	}
    //get current filesys properties
    String fileProperty = System.getProperty("filesys.conf");
    //check if we get the properties
    if (fileProperty == null)
    	fileProperty = "filesys.conf" ;
    Properties properties = new Properties() ;
    try{
      FileInputStream in = new FileInputStream(fileProperty) ;
      properties.load( in ) ; 
      in.close() ;
	  short umask = Short.parseShort((String) args.elementAt(0),8);
	  if(umask < 0 || umask > 0777)
        throw new NumberFormatException();
	  properties.setProperty("process.umask", (String) args.elementAt(0));
	  properties.store(new FileOutputStream(fileProperty),null );
    }
	catch( NumberFormatException e ) 
	{
      System.out.println("[Error] " + FUNCTION_NAME + ": " +(String)args.elementAt(0) + ": invalid number" );	
	}
    catch( FileNotFoundException e )
    {
      System.out.println("[Error] " + FUNCTION_NAME + ": could not open the file" ) ;
      System.exit( 1 ) ;
    }
    catch( IOException e )
    {
      System.out.println("[Error] " + FUNCTION_NAME + ": could not read the file" ) ;
      System.exit( 1 ) ;
    }

    // exit with success
	//Kernel.exit(0);//disable the comment if you don't use the swing frame
  }

}

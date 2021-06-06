import java.util.Vector;

/*
 * This program show the right access of file or directory
 */

public class lmod{
  
  public static String FUNCTION_NAME = "lmod" ;

  public static void main(Vector<String> args) throws Exception {
	  // initialize the file system simulator kernel
	  Kernel.initialize() ;
	  // for each path-name given
	  for(int i = 0 ; i < args.size() ; i ++){
		  String name = (String) args.elementAt(i); 
		  int status = 0 ;

		  // stat the name to get information about the file or directory
		  Stat stat = new Stat();
		  status = Kernel.stat( name , stat ) ;
		  if( status < 0 ){
			  Kernel.perror( FUNCTION_NAME ) ;
			  Kernel.exit( 1 ) ;
		  }

		  // print the info
		  print( name, stat ) ;
	  }

	// exit with success
	//Kernel.exit(0);//disable the comment if you don't use the swing frame
  }

  private static void print(String name ,Stat stat){
	  // a buffer to fill with a line of output
	  StringBuffer lineToModify = new StringBuffer() ;

	  // mask the file type from the mode
	  short type = (short)(stat.getMode() & Kernel.S_IFMT) ;

	  //The major part of the code above is the same as the "ls"
    
	  /*
	   * Mode:
	   * d = directories
	   * r = read
	   * w = write
	   * x = execute
	   * - = mode not set
	   */
	  //add the corresponding letter for the corresponding mode
	  if(type == Kernel.S_IFDIR)
		  lineToModify.append('d');
	  else
		  lineToModify.append('-');
	  if((stat.getMode() & Kernel.S_IRUSR) == Kernel.S_IRUSR)
		  lineToModify.append('r');
	  else
		  lineToModify.append('-');
	  if((stat.getMode() & Kernel.S_IWUSR) == Kernel.S_IWUSR)
		  lineToModify.append('w');
	  else
		  lineToModify.append('-');
	  if((stat.getMode() & Kernel.S_IXUSR) == Kernel.S_IXUSR)
		 lineToModify.append('x');
	  else
		  lineToModify.append('-');
	  if((stat.getMode() & Kernel.S_IRGRP) == Kernel.S_IRGRP)
		  lineToModify.append('r');
	  else
		  lineToModify.append('-');
	  if((stat.getMode() & Kernel.S_IWGRP) == Kernel.S_IWGRP)
		  lineToModify.append('w');
	  else
		  lineToModify.append('-');
	  if((stat.getMode() & Kernel.S_IXGRP) == Kernel.S_IXGRP)
		  lineToModify.append('x');
	  else
		  lineToModify.append('-');
	  if((stat.getMode() & Kernel.S_IROTH) == Kernel.S_IROTH)
     	lineToModify.append('r');
	  else
		  lineToModify.append('-');
	  if((stat.getMode() & Kernel.S_IWOTH) == Kernel.S_IWOTH)
		  lineToModify.append('w');
	  else
		  lineToModify.append('-');
	  if((stat.getMode() & Kernel.S_IXOTH) == Kernel.S_IXOTH)
		  lineToModify.append('x');
	  else
		  lineToModify.append('-');
	  
	  //add the user,group,name
	  lineToModify.append( ' ' );
	  lineToModify.append(Kernel.passwd.get(stat.getUid()));
	  lineToModify.append( ' ' );
	  lineToModify.append(Kernel.group.get(stat.getGid()));
	  //lineToModify.append( ' ' );
	  lineToModify.append("  \"" + name + "\"") ;
	  //print the resulting line
	  System.out.println(lineToModify.toString());
  	}
}

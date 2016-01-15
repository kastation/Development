package victorModules;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import victorModules.MiscFunctions;

public class victorBluetooth {

	private static final Logger LOGGER = Logger.getLogger(victorBluetooth.class.getName());
	//private static final FileHandler fh = null;
	//private FileHandler fh = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try{
		// TODO Auto-generated method stub
		System.out.println("This is the beginning");
		String osName = System.getProperty("os.name").toLowerCase();
		String userHome = System.getProperty("user.home");
		int osNum = 0;
		//LOGGER.info("Logger Name: "+ LOGGER.getName());
		LOGGER.info("Logger Name: "+ LOGGER.getName() + " " + osName + " " + userHome);
		
		if (osName.contains("linux")){
			osNum = 1;
			//FileHandler fh = new FileHandler("/tmp/myLogFile.log");
			FileHandler fh = new FileHandler(userHome + "/myLogFile2.log");
			LOGGER.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  			
		} else {
			osNum = 2;
			//FileHandler fh = new FileHandler("C:/tmp/myLogFile.log");
			FileHandler fh = new FileHandler(userHome + "/myLogFile2.log");
			LOGGER.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter); 			
		}
		
		// calling the db function
		MiscFunctions xyz = new MiscFunctions();
		xyz.connectDB();
        
		// calling the function to read the xml file
		xyz.readXML();
		
		LOGGER.info("OS Name and OS num: "+ osName + " " + osNum);
		LOGGER.info("I made it to work");
		LOGGER.info("Adding to github");
		
	} catch (SecurityException e) {  
        e.printStackTrace();  
    } catch (IOException e){
    	e.printStackTrace();
    }

     
	}
}

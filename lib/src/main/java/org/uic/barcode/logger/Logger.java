package org.uic.barcode.logger;

import java.util.logging.Level;


public class Logger  {
	
	public void debug(String text){	
		java.util.logging.Logger.getGlobal().log(Level.FINEST,text);
		System.out.println(text);
	}
		
	public void log(Level level,String text){	
		java.util.logging.Logger.getGlobal().log(level,text);
	}	

	public void info(String text, IllegalArgumentException e) {
		java.util.logging.Logger.getGlobal().log(Level.INFO,text);
	}


}
	


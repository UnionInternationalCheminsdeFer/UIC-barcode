package org.uic.barcode.logger;

import java.util.logging.Level;


public class Logger {
	
	private boolean consoleLog = false;
	
	public Logger(boolean consoleLog) {
		this.consoleLog = consoleLog;
	}
	
	public Logger() {
		this.consoleLog = false;
	}
	
	public void debug(String text){	
		java.util.logging.Logger.getGlobal().log(Level.FINEST,text);
		if (consoleLog) {
			System.out.println(text);
		}
	}
		
	public void log(Level level,String text){	
		java.util.logging.Logger.getGlobal().log(level,text);
	}	

	public void info(String text, IllegalArgumentException e) {
		java.util.logging.Logger.getGlobal().log(Level.INFO,text);
	}
	
	public void activateConsoleLog(boolean consoleLogActive) {
		this.consoleLog = consoleLogActive;
	}


}
	


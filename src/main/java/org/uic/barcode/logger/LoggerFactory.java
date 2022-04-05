package org.uic.barcode.logger;

import java.util.HashMap;

/**
 * A factory for creating Logger objects. This allows to replace the default logger by your own loggers.
 */
public class LoggerFactory {
	
	
	/** The registered loggers. */
	private static HashMap<String,Logger> registeredLoggers = new HashMap<String,Logger>(); 
	
	/** The activate console log. */
	public static boolean activateConsoleLog = false;
	
	/**
	 * Gets the logger.
	 *
	 * @param name the name of the logger. UperEncoder uses asnLogger
	 * @return the logger
	 */
	public static Logger getLogger(String name) {
		Logger logger = registeredLoggers.get(name);
		if (logger == null) {
			logger = new Logger(activateConsoleLog);
			registeredLoggers.put(name, logger);
		}
		return logger;
	}

	/**
	 * Checks if console log is on.
	 *
	 * @return true, if console log is on
	 */
	public static boolean isActivateConsoleLog() {
		return activateConsoleLog;
	}

	/**
	 * Sets the console log on
	 *
	 * @param activateConsoleLog the new activate console log
	 */
	public static void setActivateConsoleLog(boolean activateConsoleLog) {
		LoggerFactory.activateConsoleLog = activateConsoleLog;
	}	
	
	/**
	 * Register logger.
	 *
	 * @param name the name of the logger
	 * @param logger the logger to be registered
	 */
	public void registerLogger(String name, Logger logger) {
		registeredLoggers.put(name, logger);
	}
	
}

package de.htwk.leipzig.mib08.computergrafik.fraktal.base.process;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public abstract class ModulProcess {
	
	protected static final Logger _log = Logger.getLogger(ModulProcess.class.getName());
	protected static final String LOG_RESOURCE_PATH = "resource/log.txt";
	
	public ModulProcess() {
		Handler handler = null;
		try {
			handler = new FileHandler( LOG_RESOURCE_PATH );
		} catch (SecurityException | IOException e) {
			throw new IllegalStateException(e.getMessage());
		}
		handler.setFormatter(new SimpleFormatter());
		_log.setLevel(Level.INFO);
		_log.addHandler(handler);
	}

}

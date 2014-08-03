package de.htwk.leipzig.mib08.computergrafik.fraktal.base.process;

import java.awt.Dialog.ModalityType;
import java.awt.Window;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public abstract class GuiModulProcess implements ModulProcessIF {
	
	private static final String LOG_RESOURCE_PATH = "resource/log.txt";
	protected final static Logger _log = Logger.getLogger(GuiModulProcess.class.getName());
	
	public GuiModulProcess() {
		Handler handler = null;
		try {
			handler = new FileHandler( LOG_RESOURCE_PATH );
		} catch (SecurityException | IOException e) {
			startExceptionDialog(e);
		}
		handler.setFormatter(new SimpleFormatter());
		_log.setLevel(Level.INFO);
		_log.addHandler(handler);
	}
	
	@Override
	public void startExceptionDialog(Throwable th) {
		JOptionPane.showMessageDialog(null, th.getMessage(), "Der Vorgang konnte nicht ausgeführt werden.", JOptionPane.ERROR_MESSAGE);
		_log.log(Level.SEVERE, th.getMessage());
	}
	
	protected void showView(JFrame view, boolean modal) {
		view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		showWindow(view, modal);
	}
	
	protected void showDialog(JDialog dialog, boolean modal) {
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setModalityType(modal ? ModalityType.APPLICATION_MODAL: ModalityType.MODELESS);
		showWindow(dialog, modal);
	}

	void showWindow(Window window, boolean modal) {
		window.setAlwaysOnTop(modal);
		window.setAutoRequestFocus(true);
		window.setVisible(true);
	}

}

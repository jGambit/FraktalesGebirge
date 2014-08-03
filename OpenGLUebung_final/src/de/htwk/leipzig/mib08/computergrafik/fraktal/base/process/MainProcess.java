package de.htwk.leipzig.mib08.computergrafik.fraktal.base.process;

import java.awt.Dialog.ModalityType;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JFrame;

public abstract class MainProcess {

	public void showView(JFrame view, boolean modal) {
		view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		showWindow(view, modal);
	}
	
	public void showDialog(JDialog dialog, boolean modal) {
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
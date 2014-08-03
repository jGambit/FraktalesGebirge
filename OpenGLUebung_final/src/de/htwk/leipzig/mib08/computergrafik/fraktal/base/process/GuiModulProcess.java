package de.htwk.leipzig.mib08.computergrafik.fraktal.base.process;

import javax.swing.JOptionPane;

public abstract class GuiModulProcess implements ModulProcessIF {

	public void startExceptionDialog(Throwable th) {
		JOptionPane.showConfirmDialog(null, th.getMessage());
	}

}

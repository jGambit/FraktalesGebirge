package de.htwk.leipzig.mib08.computergrafik.fraktal.base.process;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dialog.ModalityType;
import java.awt.Window;
import java.util.logging.Level;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public abstract class GuiModulProcess extends ModulProcess implements GuiModulProcessIF {
	
	private final Component parentViewComponent;
	
	public GuiModulProcess(Component parentViewComponent) {
		super();
		if (parentViewComponent == null) {
			throw new IllegalArgumentException("The parentViewComponent must not be null!");
		}
		this.parentViewComponent = parentViewComponent;
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
	
	protected Component getParentViewComponent() {
		return parentViewComponent;
	}
	
	@Override
	public synchronized void blockView() {
		getParentViewComponent().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	}
	
	@Override
	public synchronized void unblockView() {
		getParentViewComponent().setCursor(Cursor.getDefaultCursor());
	}

}

package de.htwk.leipzig.mib08.computergrafik.fraktal.base.process;

public interface GuiModulProcessIF {
	
	public void startExceptionDialog(Throwable th);
	public void blockView();
	public void unblockView();
	
}

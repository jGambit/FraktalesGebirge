package de.htwk.leipzig.mib08.computergrafik.fraktal.process;

import javax.swing.JFrame;

import de.htwk.leipzig.mib08.computergrafik.fraktal.base.process.GuiModulProcess;
import de.htwk.leipzig.mib08.computergrafik.fraktal.controller.MainFrame;

public class FraktalesGebirgeMainProcess extends GuiModulProcess {

	private static final class LazyInstanceHolder {
		private static final FraktalesGebirgeMainProcess instance = new FraktalesGebirgeMainProcess();
	}

	public static void main(String[] args) {
		JFrame app = new MainFrame();
		getInstance().showView(app, false);
	}

	private FraktalesGebirgeMainProcess() {
		super();
	}

	public static FraktalesGebirgeMainProcess getInstance() {
		return LazyInstanceHolder.instance;
	}

}

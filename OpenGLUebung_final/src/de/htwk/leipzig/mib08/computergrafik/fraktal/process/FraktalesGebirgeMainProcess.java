package de.htwk.leipzig.mib08.computergrafik.fraktal.process;

import java.awt.Dimension;

import de.htwk.leipzig.mib08.computergrafik.fraktal.controller.MainFrame;

public class FraktalesGebirgeMainProcess extends MainProcess {

	private static final String TITLE = "Fraktales Gebirge";

	private static final class LazyInstanceHolder {
		private static final FraktalesGebirgeMainProcess instance = new FraktalesGebirgeMainProcess();
	}

	public static void main(String[] args) {
		MainFrame app = new MainFrame();
		Dimension size = new Dimension(900, 900);
		getInstance().showView(app, size, TITLE);
	}

	private FraktalesGebirgeMainProcess() {
		super();
	}

	public static FraktalesGebirgeMainProcess getInstance() {
		return LazyInstanceHolder.instance;
	}

}

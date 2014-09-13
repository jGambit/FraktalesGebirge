package de.htwk.leipzig.mib08.computergrafik.fraktal.process;

import java.awt.Component;
import java.awt.Window;

import javax.swing.JFrame;

import de.htwk.leipzig.mib08.computergrafik.fraktal.base.process.GuiModulProcess;
import de.htwk.leipzig.mib08.computergrafik.fraktal.controller.MainFrame;

public class FraktalesGebirgeMainProcess extends GuiModulProcess {

	private static final class LazyInstanceHolder {
		private static final FraktalesGebirgeMainProcess instance = new FraktalesGebirgeMainProcess(new Window(null));
	}

	public static void main(String[] args) {
		JFrame app = new MainFrame();
		getInstance().showView(app, false);
	}

	private FraktalesGebirgeMainProcess(Component parentViewComponent) {
		super(parentViewComponent);
	}

	public static FraktalesGebirgeMainProcess getInstance() {
		return LazyInstanceHolder.instance;
	}

}

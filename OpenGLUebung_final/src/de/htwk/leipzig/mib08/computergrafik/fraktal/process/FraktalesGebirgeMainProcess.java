package de.htwk.leipzig.mib08.computergrafik.fraktal.process;

import java.awt.Component;
import java.awt.Window;

import de.htwk.leipzig.mib08.computergrafik.fraktal.base.process.GuiModulProcess;

public class FraktalesGebirgeMainProcess extends GuiModulProcess {

	private static final class LazyInstanceHolder {
		private static final FraktalesGebirgeMainProcess instance = new FraktalesGebirgeMainProcess(new Window(null));
	}

	public static void main(String[] args) {
		getInstance().getFraktalesGebirgeModulProcess().start();
	}

	private FraktalesGebirgeModulProcess modulProcess;

	private FraktalesGebirgeModulProcess getFraktalesGebirgeModulProcess() {
		if (modulProcess == null) {
			modulProcess = new FraktalesGebirgeModulProcess(getParentViewComponent());
		}
		return modulProcess;
	}

	private FraktalesGebirgeMainProcess(Component parentViewComponent) {
		super(parentViewComponent);
	}

	public static FraktalesGebirgeMainProcess getInstance() {
		return LazyInstanceHolder.instance;
	}

}

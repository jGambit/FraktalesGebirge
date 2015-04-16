package de.htwk.leipzig.mib08.computergrafik.fraktal;

import de.htwk.leipzig.mib08.computergrafik.fraktal.process.FraktalesGebirgeModulProcess;



public class FraktalesGebirgeMain {

	private static final class LazyInstanceHolder {
		private static final FraktalesGebirgeMain instance = new FraktalesGebirgeMain();
	}
	
	private FraktalesGebirgeModulProcess modulProcess;

	public static void main(String[] args) {
		getInstance().getFraktalesGebirgeModulProcess().start();
	}

	private FraktalesGebirgeModulProcess getFraktalesGebirgeModulProcess() {
		if (modulProcess == null) {
			modulProcess = new FraktalesGebirgeModulProcess();
		}
		return modulProcess;
	}

	private FraktalesGebirgeMain() {
	}

	public static FraktalesGebirgeMain getInstance() {
		return LazyInstanceHolder.instance;
	}

}

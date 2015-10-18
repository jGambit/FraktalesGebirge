package de.htwk.leipzig.mib08.computergrafik.fraktal;

import de.htwk.leipzig.mib08.computergrafik.fraktal.model.iface.Triangle3dIF;
import de.htwk.leipzig.mib08.computergrafik.fraktal.process.FraktalesGebirgeGuiProcess;



public class FraktalesGebirgeMain {

	private static final class LazyInstanceHolder {
		private static final FraktalesGebirgeMain instance = new FraktalesGebirgeMain();
	}
	
	private FraktalesGebirgeGuiProcess modulProcess;

	public static void main(String[] args) {
		Triangle3dIF baseTriangle = getInstance().parseTriangle(args);
		getInstance().getFraktalesGebirgeModulProcess().start(baseTriangle);
	}

	Triangle3dIF parseTriangle(String[] args) {
		return null;
	}

	FraktalesGebirgeGuiProcess getFraktalesGebirgeModulProcess() {
		if (modulProcess == null) {
			modulProcess = new FraktalesGebirgeGuiProcess();
		}
		return modulProcess;
	}

	private FraktalesGebirgeMain() {
	}

	public static FraktalesGebirgeMain getInstance() {
		return LazyInstanceHolder.instance;
	}

}

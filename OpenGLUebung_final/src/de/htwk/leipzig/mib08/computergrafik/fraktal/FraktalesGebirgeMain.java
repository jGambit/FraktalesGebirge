package de.htwk.leipzig.mib08.computergrafik.fraktal;

import java.util.Arrays;
import java.util.List;

import de.htwk.leipzig.mib08.computergrafik.fraktal.process.FraktalesGebirgeGuiProcess;

public class FraktalesGebirgeMain {

	private static final class LazyInstanceHolder {
		private static final FraktalesGebirgeMain instance = new FraktalesGebirgeMain();
	}
	
	private FraktalesGebirgeGuiProcess modulProcess;

	public static void main(String[] args) {
		getInstance().startDialog(args);
	}

	private void startDialog(String[] args) {
		List<String> coordinates = args == null ? null : Arrays.asList(args);
		getFraktalesGebirgeModulProcess().start(coordinates);
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

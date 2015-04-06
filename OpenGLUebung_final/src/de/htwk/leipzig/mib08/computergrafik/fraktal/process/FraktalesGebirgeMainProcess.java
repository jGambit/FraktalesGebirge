package de.htwk.leipzig.mib08.computergrafik.fraktal.process;



public class FraktalesGebirgeMainProcess {

	private static final class LazyInstanceHolder {
		private static final FraktalesGebirgeMainProcess instance = new FraktalesGebirgeMainProcess();
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

	private FraktalesGebirgeMainProcess() {
	}

	public static FraktalesGebirgeMainProcess getInstance() {
		return LazyInstanceHolder.instance;
	}

}

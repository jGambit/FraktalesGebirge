package de.htwk.leipzig.mib08.computergrafik.fraktal.controller;

import de.htwk.leipzig.mib08.computergrafik.fraktal.model.Dreieck;
import de.htwk.leipzig.mib08.computergrafik.fraktal.process.FraktalesGebirgeModulProcess;

public class MainFrameController extends ModulViewController<FraktalesGebirgeModulProcess, Dreieck, OpenGlController> {

	public MainFrameController(FraktalesGebirgeModulProcess modulProcess) {
		super(modulProcess);
	}

	@Override
	protected OpenGlController createContentControllerImpl() {
		return new OpenGlController(getModulProcess());
	}

}

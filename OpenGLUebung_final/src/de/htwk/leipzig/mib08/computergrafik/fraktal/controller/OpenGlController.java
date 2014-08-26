package de.htwk.leipzig.mib08.computergrafik.fraktal.controller;

import de.htwk.leipzig.mib08.computergrafik.fraktal.base.controller.ModulController;
import de.htwk.leipzig.mib08.computergrafik.fraktal.base.exception.ToBeHandledByApplicationException;
import de.htwk.leipzig.mib08.computergrafik.fraktal.model.Dreieck;
import de.htwk.leipzig.mib08.computergrafik.fraktal.process.FraktalesGebirgeModulProcess;

public class OpenGlController extends ModulController<FraktalesGebirgeModulProcess, Dreieck> {

	public OpenGlController(FraktalesGebirgeModulProcess modulProcess) {
		super(modulProcess);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void clearFormImpl() throws ToBeHandledByApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void fillFormImpl(Dreieck config)
			throws ToBeHandledByApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void permitFormImpl(boolean permit) {
		// TODO Auto-generated method stub
		
	}

}

package de.htwk.leipzig.mib08.computergrafik.fraktal.controller;

import de.htwk.leipzig.mib08.computergrafik.fraktal.base.controller.ModulController;
import de.htwk.leipzig.mib08.computergrafik.fraktal.base.exception.ToBeHandledByApplicationException;
import de.htwk.leipzig.mib08.computergrafik.fraktal.base.process.GuiModulProcessIF;

public abstract class ModulViewController<P extends GuiModulProcessIF, O, C extends ModulController<P, O>> extends ModulController<P, O> {

	private C contentController;

	public ModulViewController(P modulProcess) {
		super(modulProcess);
	}
	
	@Override
	protected void fillFormImpl(O config)
			throws ToBeHandledByApplicationException {
		getContentController().fillForm(config);
	}
	
	@Override
	protected void clearFormImpl() throws ToBeHandledByApplicationException {
		getContentController().clearForm();
	}
	
	@Override
	protected void permitFormImpl(boolean permit) {
		getContentController().permitForm(permit);
	}

	private C getContentController() {
		if (contentController == null) {
			contentController = createContentControllerImpl();
		}
		return contentController;
	}

	protected abstract C createContentControllerImpl();

}

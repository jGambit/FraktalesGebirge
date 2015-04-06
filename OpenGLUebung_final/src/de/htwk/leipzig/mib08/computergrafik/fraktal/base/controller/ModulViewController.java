package de.htwk.leipzig.mib08.computergrafik.fraktal.base.controller;

import de.htwk.leipzig.mib08.computergrafik.fraktal.base.controller.iface.ModulViewControllerIF;
import de.htwk.leipzig.mib08.computergrafik.fraktal.base.exception.ToBeHandledByApplicationException;
import de.htwk.leipzig.mib08.computergrafik.fraktal.base.process.iface.GuiModulProcessIF;

public abstract class ModulViewController<P extends GuiModulProcessIF, O, C extends ModulController<P, O>> extends ModulController<P, O> implements ModulViewControllerIF<P, O, C> {

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

	@Override
	public C getContentController() {
		if (contentController == null) {
			contentController = createContentControllerImpl();
		}
		return contentController;
	}

	protected abstract C createContentControllerImpl();

}

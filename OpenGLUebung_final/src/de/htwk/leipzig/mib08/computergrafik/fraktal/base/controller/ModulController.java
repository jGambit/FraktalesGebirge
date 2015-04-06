package de.htwk.leipzig.mib08.computergrafik.fraktal.base.controller;

import de.htwk.leipzig.mib08.computergrafik.fraktal.base.controller.iface.ControllerStateIF;
import de.htwk.leipzig.mib08.computergrafik.fraktal.base.controller.iface.ModulControllerIF;
import de.htwk.leipzig.mib08.computergrafik.fraktal.base.exception.ToBeHandledByApplicationException;
import de.htwk.leipzig.mib08.computergrafik.fraktal.base.process.iface.GuiModulProcessIF;


/**
 * @param <P> ModulProcess
 * @param <O> Configuration
 */
public abstract class ModulController<P extends GuiModulProcessIF, O> implements ModulControllerIF<P, O> {
	
	private final P modulProcess;
	private final ControllerStateIF<O> state;

	public ModulController(P modulProcess) {
		this.modulProcess = modulProcess;
		state = new ControllerState<>();
	}
	
	@Override
	public void clearForm() {
		setUpdatingForm();
		blockView();
		try {
			clearFormImpl();
			permitForm(false);
			setCurrentObject(null);
			setCleared(true);
		} catch (ToBeHandledByApplicationException e) {
			handleEventException(e);
		} finally {
			unblockView();
			unSetUpdatingForm();
		}
	}

	@Override
	public void fillForm(O config) {
		setUpdatingForm();
		blockView();
		try {
			if (! isCleared()) {
				clearForm();
			}
			setCurrentObject(config);
			fillFormImpl(config);
			permitForm(true);
			setCleared(false);
		} catch (ToBeHandledByApplicationException e) {
			handleEventException(e);
		} finally {
			unblockView();
			unSetUpdatingForm();
		}
	}
	
	@Override
	public void permitForm(boolean permit) {
		permitFormImpl(permit);
	}

	@Override
	public boolean isUpdatingForm() {
		return getState().isUpdatingForm();
	}

	ControllerStateIF<O> getState() {
		return state;
	}

	public boolean isCleared() {
		return getState().isCleared();
	}

	protected void setCleared(boolean cleared) {
		getState().setCleared(cleared);
	}
	
	@Override
	public P getModulProcess() {
		return modulProcess;
	}
	
	protected void handleEventException(ToBeHandledByApplicationException e) {
		getModulProcess().startExceptionDialog(e);
	}
	
	protected void setUpdatingForm() {
		getState().setUpdatingForm(true);
	}
	
	protected void unSetUpdatingForm() {
		getState().setUpdatingForm(false);
	}
	
	protected O getCurrentObject() {
		return getState().getCurrentObject();
	}

	protected void setCurrentObject(O currentObject) {
		getState().setCurrentObject(currentObject);
	}
	

	protected void blockView() {
		getModulProcess().blockView();
	}

	protected void unblockView() {
		getModulProcess().unblockView();
	}
	
	protected abstract void clearFormImpl() throws ToBeHandledByApplicationException;
	protected abstract void fillFormImpl(O config) throws ToBeHandledByApplicationException;
	protected abstract void permitFormImpl(boolean permit);

}

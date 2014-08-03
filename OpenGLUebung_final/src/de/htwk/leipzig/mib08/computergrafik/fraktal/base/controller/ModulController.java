package de.htwk.leipzig.mib08.computergrafik.fraktal.base.controller;

import de.htwk.leipzig.mib08.computergrafik.fraktal.base.exception.ToBeHandledByApplicationException;
import de.htwk.leipzig.mib08.computergrafik.fraktal.base.process.ModulProcessIF;


/**
 * @param <P> ModulProcess
 * @param <O> Configuration
 */
public abstract class ModulController<P extends ModulProcessIF, O> implements ModulControllerIF<P, O> {
	
	private final P modulProcess;
	private ControllerState<O> state;

	public ModulController(P modulProcess) {
		this.modulProcess = modulProcess;
	}
	
	@Override
	public void clearForm() {
		setUpdatingForm();
		try {
			clearFormImpl();
			permitForm(false);
			setCurrentObject(null);
			setCleared(true);
		} catch (ToBeHandledByApplicationException e) {
			handleEventException(e);
		} finally {
			unSetUpdatingForm();
		}
	}

	@Override
	public void fillForm(O config) {
		setUpdatingForm();
		try {
			if (! isCleared()) {
				clearForm();
			}
			fillFormImpl(config);
			permitForm(true);
		} catch (ToBeHandledByApplicationException e) {
			handleEventException(e);
		} finally {
			unSetUpdatingForm();
		}
	}
	
	@Override
	public void permitForm(boolean permit) {
		
	}

	@Override
	public boolean isUpdatingForm() {
		return getState().isUpdatingForm();
	}

	ControllerState<O> getState() {
		if (state == null) {
			state = new ControllerState<>();
		}
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
	
	protected abstract void clearFormImpl() throws ToBeHandledByApplicationException;
	protected abstract void fillFormImpl(O config) throws ToBeHandledByApplicationException;

}

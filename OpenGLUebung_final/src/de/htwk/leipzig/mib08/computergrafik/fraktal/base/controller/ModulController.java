package de.htwk.leipzig.mib08.computergrafik.fraktal.base.controller;

import de.htwk.leipzig.mib08.computergrafik.fraktal.base.exception.ToBeHandledByApplicationException;
import de.htwk.leipzig.mib08.computergrafik.fraktal.base.process.ModulProcessIF;


/**
 * @param <P> ModulProcess
 * @param <O> Configuration
 */
public abstract class ModulController<P extends ModulProcessIF, O> {
	
	private boolean updatingForm;
	private boolean cleared;
	private final P modulProcess;
	private O currentObject;
	
	public ModulController(P modulProcess) {
		this.modulProcess = modulProcess;
	}
	
	public void clearForm() {
		setUpdatingForm();
		try {
			clearFormImpl();
			setCurrentObject(null);
			setCleared(true);
		} catch (ToBeHandledByApplicationException e) {
			handleEventException(e);
		} finally {
			unSetUpdatingForm();
		}
	}

	public void fillForm(O config) {
		setUpdatingForm();
		try {
			fillFormImpl(config);
		} catch (ToBeHandledByApplicationException e) {
			handleEventException(e);
		} finally {
			unSetUpdatingForm();
		}
	}
	
	public void permitForm(boolean permit) {
		
	}

	public boolean isUpdatingForm() {
		return updatingForm;
	}

	public boolean isCleared() {
		return cleared;
	}

	public void setCleared(boolean cleared) {
		this.cleared = cleared;
	}
	
	public P getModulProcess() {
		return modulProcess;
	}
	
	protected void handleEventException(ToBeHandledByApplicationException e) {
		getModulProcess().startExceptionDialog(e);
	}
	
	protected void setUpdatingForm() {
		updatingForm = true;
	}
	
	protected void unSetUpdatingForm() {
		this.updatingForm = false;
	}
	
	protected O getCurrentObject() {
		return currentObject;
	}

	protected void setCurrentObject(O currentObject) {
		this.currentObject = currentObject;
	}
	
	protected abstract void clearFormImpl() throws ToBeHandledByApplicationException;
	protected abstract void fillFormImpl(O config) throws ToBeHandledByApplicationException;

}

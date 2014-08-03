package de.htwk.leipzig.mib08.computergrafik.fraktal.base.controller;

public class ControllerState<O> {
	boolean updatingForm;
	boolean cleared;
	O currentObject;
	
	boolean isUpdatingForm() {
		return updatingForm;
	}
	
	void setUpdatingForm(boolean updatingForm) {
		this.updatingForm = updatingForm;
	}
	
	boolean isCleared() {
		return cleared;
	}
	
	void setCleared(boolean cleared) {
		this.cleared = cleared;
	}
	
	O getCurrentObject() {
		return currentObject;
	}
	
	void setCurrentObject(O currentObject) {
		this.currentObject = currentObject;
	}

	
	
}
package de.htwk.leipzig.mib08.computergrafik.fraktal.base.controller.iface;

public interface ControllerStateIF<O> {

	public boolean isUpdatingForm();

	public void setUpdatingForm(boolean updatingForm);

	public boolean isCleared();

	public void setCleared(boolean cleared);

	public O getCurrentObject();

	public void setCurrentObject(O currentObject);

}
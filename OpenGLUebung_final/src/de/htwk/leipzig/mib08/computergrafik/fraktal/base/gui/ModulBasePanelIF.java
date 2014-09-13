package de.htwk.leipzig.mib08.computergrafik.fraktal.base.gui;

import de.htwk.leipzig.mib08.computergrafik.fraktal.base.controller.ModulControllerIF;

public interface ModulBasePanelIF<T extends ModulControllerIF<?, ?>> {
	
	public void setController(T controller);

}

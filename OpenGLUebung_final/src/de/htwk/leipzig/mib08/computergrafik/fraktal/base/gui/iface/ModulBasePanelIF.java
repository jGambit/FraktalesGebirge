package de.htwk.leipzig.mib08.computergrafik.fraktal.base.gui.iface;

import de.htwk.leipzig.mib08.computergrafik.fraktal.base.controller.iface.ModulControllerIF;

public interface ModulBasePanelIF<T extends ModulControllerIF<?, ?>> {
	
	public void setController(T controller);

}

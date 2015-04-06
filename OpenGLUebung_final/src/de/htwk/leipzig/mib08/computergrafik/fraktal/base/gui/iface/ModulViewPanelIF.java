package de.htwk.leipzig.mib08.computergrafik.fraktal.base.gui.iface;

import de.htwk.leipzig.mib08.computergrafik.fraktal.base.controller.iface.ModulViewControllerIF;


public interface ModulViewPanelIF<T extends ModulViewControllerIF<?, ?, ?>, P extends ModulBasePanelIF<?>> {

	public void setController(T viewController);
	public P getSubPanel();
	
}

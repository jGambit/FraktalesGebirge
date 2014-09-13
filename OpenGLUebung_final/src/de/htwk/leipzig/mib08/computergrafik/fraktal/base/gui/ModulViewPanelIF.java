package de.htwk.leipzig.mib08.computergrafik.fraktal.base.gui;

import de.htwk.leipzig.mib08.computergrafik.fraktal.base.controller.ModulViewControllerIF;


public interface ModulViewPanelIF<T extends ModulViewControllerIF<?, ?, ?>, P extends ModulBasePanelIF<?>> {

	public void setController(T viewController);
	public P getSubPanel();
	
}

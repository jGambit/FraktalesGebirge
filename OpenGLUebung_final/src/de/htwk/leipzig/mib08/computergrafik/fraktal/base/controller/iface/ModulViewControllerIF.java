package de.htwk.leipzig.mib08.computergrafik.fraktal.base.controller.iface;

import de.htwk.leipzig.mib08.computergrafik.fraktal.base.process.iface.GuiModulProcessIF;

public interface ModulViewControllerIF<P extends GuiModulProcessIF, O, C extends ModulControllerIF<P, O>> {

	public C getContentController();

}
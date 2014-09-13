package de.htwk.leipzig.mib08.computergrafik.fraktal.base.controller;

import de.htwk.leipzig.mib08.computergrafik.fraktal.base.process.GuiModulProcessIF;

public interface ModulViewControllerIF<P extends GuiModulProcessIF, O, C extends ModulControllerIF<P, O>> {

	public C getContentController();

}
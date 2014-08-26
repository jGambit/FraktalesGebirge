package de.htwk.leipzig.mib08.computergrafik.fraktal.base.controller;

import de.htwk.leipzig.mib08.computergrafik.fraktal.base.process.GuiModulProcessIF;

public interface ModulControllerIF<P extends GuiModulProcessIF, O> {

	/**
	 * Leert den Controller und alle Models.
	 */
	public void clearForm();

	/**
	 * Befüllt den Controller mit Daten aus dem gegebenen Konfigurationsobjekt.
	 * Dabei wird der Controller automatisch vorher mittels {@link #clearForm()} geleert. 
	 * @param config Konfigurationsobjekt mit Daten für die Models
	 */
	public void fillForm(O config);

	/**
	 * Aktiviert bzw. deaktiviert die Models. 
	 * @param permit
	 */
	public void permitForm(boolean permit);

	/**
	 * @return Gibt an, ob sich der Controller gerade aktualisiert.
	 */
	public boolean isUpdatingForm();

	/**
	 * @return Liefert den Process mit dem der Controller erstellt wurde
	 */
	public P getModulProcess();

}
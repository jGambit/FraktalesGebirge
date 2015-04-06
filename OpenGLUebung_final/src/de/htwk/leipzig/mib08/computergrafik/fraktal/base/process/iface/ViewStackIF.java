package de.htwk.leipzig.mib08.computergrafik.fraktal.base.process.iface;

import java.awt.Component;

public interface ViewStackIF {

	public void pushView(Component view);

	public Component popView();

	public Component getCurrentView();

	public void blockView();

	public void unlbockView();

}
package de.htwk.leipzig.mib08.computergrafik.fraktal.process;

import java.awt.Dimension;

import javax.swing.JFrame;

public abstract class MainProcess {

	public void showView(JFrame app, Dimension size, String title) {
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(size.width, size.height);
		app.setResizable(false);
		app.setTitle(title);
		app.setLocation(100, 100);
		app.setVisible(true);
	}

}
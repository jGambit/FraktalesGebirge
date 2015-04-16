package de.htwk.leipzig.mib08.computergrafik.fraktal.gui;

import javax.media.opengl.awt.GLJPanel;

import com.github.jgambit.emvc.gui.iface.ModulBasePanelIF;

import de.htwk.leipzig.mib08.computergrafik.fraktal.controller.OpenGlController;


public class OpenGlPanel extends GLJPanel implements ModulBasePanelIF<OpenGlController> {

	private static final long serialVersionUID = -8464667398691555779L;
	float x;
	float y;
	float z;

	public static boolean zoomIn = false;
	public static boolean zoomOut = false;
	
	public OpenGlPanel(){
		super();
	}
	
	@Override
	public void setController(OpenGlController controller) {
		addGLEventListener(controller);
	}
}


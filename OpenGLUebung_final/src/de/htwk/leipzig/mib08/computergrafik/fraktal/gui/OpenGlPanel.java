package de.htwk.leipzig.mib08.computergrafik.fraktal.gui;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.awt.GLJPanel;

import com.github.jgambit.emvc.gui.iface.ModulBasePanelIF;

import de.htwk.leipzig.mib08.computergrafik.fraktal.controller.OpenGlController;


public class OpenGlPanel extends GLJPanel implements ModulBasePanelIF<OpenGlController> {

	private static final long serialVersionUID = -8464667398691555779L;

	public OpenGlPanel(GLCapabilities capabilities) {
		super(capabilities);
//		setContext(new GLContext, destroyPrevCtx)
	}

	@Override
	public void setController(OpenGlController controller) {
		controller.getDrawableModel().setDrawable(this);
	}
}


package de.htwk.leipzig.mib08.computergrafik.fraktal.process;

import java.awt.Color;
import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.github.jgambit.emvc.process.GuiModulProcess;
import com.github.jgambit.emvc.process.ModulProcessViewStack;

import de.htwk.leipzig.mib08.computergrafik.fraktal.controller.MainFrameController;
import de.htwk.leipzig.mib08.computergrafik.fraktal.gui.MainFrame;
import de.htwk.leipzig.mib08.computergrafik.fraktal.model.DrawingMode;
import de.htwk.leipzig.mib08.computergrafik.fraktal.model.Triangle3D;
import de.htwk.leipzig.mib08.computergrafik.fraktal.model.iface.Point3dIF;
import de.htwk.leipzig.mib08.computergrafik.fraktal.model.iface.Triangle3dIF;
import de.htwk.leipzig.mib08.computergrafik.fraktal.valueobject.FraktalesGebirgeConfig;

public class FraktalesGebirgeGuiProcess extends GuiModulProcess {

	private MainFrame view;
	private MainFrameController mainFrameController;
	private FraktalProcess fraktalProcess;

	public FraktalesGebirgeGuiProcess() {
		super(new ModulProcessViewStack());
		pushView(getView());
	}

	public void repaint() {
		getView().repaint();
	}

	public void start(List<String> coordinates) {
		Triangle3dIF baseTriangle = getFraktalProcess().createTriangle(coordinates);
		FraktalesGebirgeConfig config = new FraktalesGebirgeConfig();
		config.setGebirge(baseTriangle);
		getMainFrameController().fillForm(config);
		showView(getView(), false);
	}

	MainFrame getView() {
		if (view == null) {
			view = new MainFrame();
			view.setController(getMainFrameController());
		}
		return view;
	}

	private MainFrameController getMainFrameController() {
		if (mainFrameController == null) {
			mainFrameController = new MainFrameController(this);
		}
		return mainFrameController;
	}
	
	public void rekPaint(Triangle3dIF neu, GL2 gl, DrawingMode mode, int lauf) {
		if(lauf<1) {
			paintTriangle(neu, mode, gl);
			return;	
		}

		// n-tes Dreieck
		List<Triangle3dIF> halvedFractal = createHalvedFractal(neu);
		for (Triangle3dIF fraktal : halvedFractal) {
			rekPaint(fraktal, gl, mode, lauf - 1);
		}
	}
	
	List<Triangle3dIF> createHalvedFractal(Triangle3dIF triangle) {
		return getFraktalProcess().createFraktalMountain(triangle);
	}

	FraktalProcess getFraktalProcess() {
		if (fraktalProcess == null) {
			fraktalProcess = new FraktalProcess();
		}
		return fraktalProcess;
	}

	private void paintTriangle(Triangle3dIF neu, DrawingMode mode, GL2 gl) {
		gl.glBegin(mode == null ? GL.GL_LINE_LOOP : mode.getGlMode());
		
		Color color = neu.getColor();
		float[] rgb = color.getComponents(null);
		gl.glColor3f(rgb[0], rgb[1], rgb[2]);
		gl.glVertex3fv(neu.getA().getVector(), 0);
		gl.glVertex3fv(neu.getB().getVector(), 0);
		gl.glVertex3fv(neu.getC().getVector(), 0);
    	
    	gl.glEnd();
	}
	
	float gibColor(float a, float b, float c) {
		Double rc = new Double((a + b +c) / 3.0f);
		return new Float(Math.abs( (rc / 60.0f ) ));
	}

	public void showInfoDialog() {
		JOptionPane.showMessageDialog(getCurrentViewComponent(), "Version 0.9 Beta",
				"Copyright Dani & Enno", JOptionPane.INFORMATION_MESSAGE);
	}

	public void quit() {
		int a;
		a = JOptionPane.showConfirmDialog(getView(),
				"Beenden, echt jetz oder?");
		if (a == JOptionPane.YES_OPTION) {
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					getView().setVisible(false);
					getView().dispose();
				}
			});
		}
	}

	public void updateHeight(FraktalesGebirgeConfig config, int value) {
		float factor = value * 0.01f;
		Point3dIF a = config.getGebirge().getA();
		Point3dIF b = config.getGebirge().getB();
		Point3dIF c = config.getGebirge().getC();
		config.setGebirge(new Triangle3D(a, b, c, factor));
		getMainFrameController().fillForm(config);
		repaint();
	}

	public void createNewMountain(int height) {
		FraktalesGebirgeConfig config = new FraktalesGebirgeConfig();
		config.setGebirge(getFraktalProcess().createTriangle(null));
		updateHeight(config, height);
	}

	/**
	 * Rotation um die y-Achse
	 * @param drawable
	 */
	public void rotateY(GLAutoDrawable drawable) {
		if (drawable != null) {
			GL gl = drawable.getGL();
			gl.getGL2().glRotatef(20.0f, 0.0f, 1.0f, 0.0f);
		}
	}

	/**
	 * Rotation um die x-Achse
	 * @param drawable
	 */
	public void rotateX(GLAutoDrawable drawable) {
		if (drawable != null) {
			GL gl = drawable.getGL();
			gl.getGL2().glRotatef(20.0f, 1.0f, 0.0f, 0.0f);
		}
	}

	public Triangle3dIF parseTriangle(List<String> coordinates) {
		return getFraktalProcess().createTriangle(coordinates); 
	}

}

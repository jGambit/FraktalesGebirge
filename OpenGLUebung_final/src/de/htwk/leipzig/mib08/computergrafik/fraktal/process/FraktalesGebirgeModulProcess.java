package de.htwk.leipzig.mib08.computergrafik.fraktal.process;

import java.awt.Component;
import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.swing.JOptionPane;

import de.htwk.leipzig.mib08.computergrafik.fraktal.base.process.GuiModulProcess;
import de.htwk.leipzig.mib08.computergrafik.fraktal.controller.MainFrameController;
import de.htwk.leipzig.mib08.computergrafik.fraktal.gui.MainFrame;
import de.htwk.leipzig.mib08.computergrafik.fraktal.model.Point3D;
import de.htwk.leipzig.mib08.computergrafik.fraktal.model.Triangle3D;

public class FraktalesGebirgeModulProcess extends GuiModulProcess {

	private static final Point3D BASE_C = new Point3D(80, -80, 0);
	private static final Point3D BASE_B = new Point3D(0, 80, 0);
	private static final Point3D BASE_A = new Point3D(-80, -80, 0);
	private MainFrame view;
	private MainFrameController mainFrameController;

	public FraktalesGebirgeModulProcess(Component parentViewComponent) {
		super(parentViewComponent);
	}

	public void repaint() {
		getView().repaint();
	}

	public void start() {
		Triangle3D gebirge = createBeseTriangle();
		getMainFrameController().fillForm(gebirge);
		showView(getView(), false);
	}

	private Triangle3D createBeseTriangle() {
		return new Triangle3D(BASE_A, BASE_B, BASE_C);
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
	
	public void rekPaint(Triangle3D neu, GL2 gl, int lauf) {
		if(lauf<1) return;	

		// n-tes Dreieck
		List<Triangle3D> halvedFractal = neu.createHalvedFractal();
		for (Triangle3D fraktal : halvedFractal) {
			rekPaint(fraktal, gl, lauf - 1);
		}
		
		paintTriangle(neu, gl);
	}

	private void paintTriangle(Triangle3D neu, GL2 gl) {
		gl.glBegin(GL.GL_LINE_LOOP);
		
		gl.glColor3f(0, 0, 0);
		gl.glVertex3f (neu.getA().getX(), neu.getA().getY(), neu.getA().getZ());
    	gl.glVertex3f (neu.getB().getX(), neu.getB().getY(), neu.getB().getZ());
    	gl.glVertex3f (neu.getC().getX(), neu.getC().getY(), neu.getC().getZ());
    	
    	gl.glEnd();
	}
	
	float gibColor(float a, float b, float c) {
		Double rc = new Double((a + b +c) / 3.0f);
		return new Float(Math.abs( (rc / 60.0f ) )); 
	}

	public void showInfoDialog() {
		JOptionPane.showMessageDialog(getView(), "Version 0.67 Beta",
				"Copyright Dani & Enno", JOptionPane.INFORMATION_MESSAGE);
	}

	public void quit() {
		int a;
		a = JOptionPane.showConfirmDialog(getView(),
				"Beenden, echt jetz oder?");
		if (a == JOptionPane.YES_OPTION) {
			getView().setVisible(false);
			getView().dispose();
		}
	}

	public void updateHeight(Triangle3D currentObject, int value) {
		float factor = value * 0.005f;
		Point3D a = currentObject.getA();
		Point3D b = currentObject.getB();
		Point3D c = currentObject.getC();
		Triangle3D gebirge = new Triangle3D(a, b, c, factor);
		getMainFrameController().fillForm(gebirge);
	}

}

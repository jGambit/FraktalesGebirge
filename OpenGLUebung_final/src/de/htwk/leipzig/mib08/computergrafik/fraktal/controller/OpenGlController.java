package de.htwk.leipzig.mib08.computergrafik.fraktal.controller;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import com.github.jgambit.emvc.controller.ModulController;
import com.github.jgambit.emvc.exception.ToBeHandledByApplicationException;

import de.htwk.leipzig.mib08.computergrafik.fraktal.model.GLAutoDrawableModel;
import de.htwk.leipzig.mib08.computergrafik.fraktal.model.iface.AutoDrawableModelIF;
import de.htwk.leipzig.mib08.computergrafik.fraktal.process.FraktalesGebirgeGuiProcess;
import de.htwk.leipzig.mib08.computergrafik.fraktal.valueobject.FraktalesGebirgeConfig;

public class OpenGlController extends ModulController<FraktalesGebirgeGuiProcess, FraktalesGebirgeConfig> implements GLEventListener {

	private AutoDrawableModelIF drawableModel;

	public OpenGlController(FraktalesGebirgeGuiProcess modulProcess) {
		super(modulProcess);
	}

	@Override
	public void init(GLAutoDrawable arg0) {
		setUpdatingForm();
		blockView();
		System.out.println("init");
		try {
			getDrawableModel().getGL2().glEnable(GL2.GL_LIGHTING);
			getDrawableModel().getGL2().glEnable(GL2.GL_LIGHT0);
			getDrawableModel().getGL2().glEnable(GL2.GL_COLOR_MATERIAL);
			getDrawableModel().getGL2().glEnable(GL.GL_DEPTH_TEST);
			getDrawableModel().getGL2().glEnable(GL2.GL_NORMALIZE);
			getDrawableModel().getGL2().glEnable(GL2.GL_POLYGON_SMOOTH);
			getDrawableModel().getGL2().glEnable(GL2.GL_POINT_SMOOTH);
			getDrawableModel().getGL2().glEnable(GL.GL_LINE_SMOOTH);

			getDrawableModel().getGL2().glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
			getDrawableModel().getGL2().glMatrixMode(GL2.GL_PROJECTION);
			getDrawableModel().getGL2().glOrtho(-100, 100, -100, 100, -100, 100);
			getDrawableModel().getGL2().glMatrixMode(GL2.GL_MODELVIEW);
		} finally {
			unblockView();
			unSetUpdatingForm();
		}
	}

	@Override
	protected void clearFormImpl() throws ToBeHandledByApplicationException {
//		getGl().getGL2().glLoadIdentity();
	}

	@Override
	protected void fillFormImpl(FraktalesGebirgeConfig config)
			throws ToBeHandledByApplicationException {
		getDrawableModel().update();
	}

	@Override
	protected void permitFormImpl(boolean permit) {
		// TODO Auto-generated method stub
	}

	@Override
	public void display(GLAutoDrawable arg0) {
		if (getCurrentObject() == null) {
			return;
		}
		setUpdatingForm();
		blockView();
		try {
			getModulProcess().display(arg0, getCurrentObject());
		} finally {
			unblockView();
			unSetUpdatingForm();
		}
	}

	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
		System.out.println("Display changed");
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		System.out.println("dispose");
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {
	}

	public AutoDrawableModelIF getDrawableModel() {
		if (drawableModel == null) {
			drawableModel = new GLAutoDrawableModel();
			drawableModel.addEventListener(this);
		}
		return drawableModel;
	}

//	public void reset() {
//		if (getDrawable() != null) {
//			getDrawable().getGL().getGL2().glLoadIdentity();
//		}
//	}

}

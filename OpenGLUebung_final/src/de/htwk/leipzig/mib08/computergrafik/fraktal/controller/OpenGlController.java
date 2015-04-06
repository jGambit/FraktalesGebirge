package de.htwk.leipzig.mib08.computergrafik.fraktal.controller;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import de.htwk.leipzig.mib08.computergrafik.fraktal.base.controller.ModulController;
import de.htwk.leipzig.mib08.computergrafik.fraktal.base.exception.ToBeHandledByApplicationException;
import de.htwk.leipzig.mib08.computergrafik.fraktal.model.Triangle3D;
import de.htwk.leipzig.mib08.computergrafik.fraktal.process.FraktalesGebirgeModulProcess;

public class OpenGlController extends ModulController<FraktalesGebirgeModulProcess, Triangle3D> implements GLEventListener {

	private boolean flagX = false;
	private boolean flagY = false;
	private GLAutoDrawable drawable;
	private int rekTiefe = 5;
	
	public OpenGlController(FraktalesGebirgeModulProcess modulProcess) {
		super(modulProcess);
	}
	
	@Override
	public void init(GLAutoDrawable arg0) {
		setUpdatingForm();
		blockView();
		System.out.println("init");
		try {
			setDrawable(arg0);

			getGl().getGL2().glEnable(GL2.GL_LIGHTING);
			getGl().getGL2().glEnable(GL2.GL_LIGHT0);
			getGl().getGL2().glEnable(GL2.GL_COLOR_MATERIAL);
			getGl().getGL2().glEnable(GL.GL_DEPTH_TEST);
			getGl().getGL2().glEnable(GL2.GL_NORMALIZE);
			getGl().getGL2().glEnable(GL2.GL_POLYGON_SMOOTH);
			getGl().getGL2().glEnable(GL2.GL_POINT_SMOOTH);
			getGl().getGL2().glEnable(GL.GL_LINE_SMOOTH);

			getGl().getGL2().glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
			getGl().getGL2().glMatrixMode(GL2.GL_PROJECTION);
			getGl().getGL2().glOrtho(-100, 100, -100, 100, -100, 100);
			getGl().getGL2().glMatrixMode(GL2.GL_MODELVIEW);
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
	protected void fillFormImpl(Triangle3D config)
			throws ToBeHandledByApplicationException {
		if (getDrawable() != null) {
			display(getDrawable());
		}
	}

	@Override
	protected void permitFormImpl(boolean permit) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void display(GLAutoDrawable arg0) {
		setUpdatingForm();
		blockView();
		try {
			GL gl = arg0.getGL();
		    gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		    
		    if (getCurrentObject() != null) {
		    	getModulProcess().rekPaint(getCurrentObject(), gl.getGL2(), getRekTiefe());
		    }
		    
		  if(flagX) 
			  getModulProcess().rotateX(arg0);
//			  gl.getGL2().glRotatef(20.0f, 1.0f, 0.0f, 0.0f);    // Rotation um die x-Achse
		  
		  flagX = false;

		  if(flagY) 			  
			  getModulProcess().rotateY(arg0);
//			  gl.getGL2().glRotatef(20.0f, 0.0f, 1.0f, 0.0f);    // Rotation um die y-Achse
				 
		  flagY = false;
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

	public void rotateY() {
		flagY = true;
	}

	public void rotateX() {
		flagX = true;
	}

	public GL getGl() {
		return drawable == null ? null : drawable.getGL();
	}

	public void setRekTiefe(int rekTiefe) {
		this.rekTiefe = rekTiefe;
	}

	public GLAutoDrawable getDrawable() {
		return drawable;
	}

	public void setDrawable(GLAutoDrawable drawable) {
		this.drawable = drawable;
	}

	public int getRekTiefe() {
		return rekTiefe;
	}
	
	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {
	}

	public void reset() {
		if (getDrawable() != null) {
			getDrawable().getGL().getGL2().glLoadIdentity();
		}
	}
	
}

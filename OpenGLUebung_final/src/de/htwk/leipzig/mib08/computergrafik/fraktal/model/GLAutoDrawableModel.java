package de.htwk.leipzig.mib08.computergrafik.fraktal.model;

import java.lang.ref.WeakReference;

import javax.media.opengl.DebugGL2;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.swing.event.EventListenerList;

import de.htwk.leipzig.mib08.computergrafik.fraktal.model.iface.AutoDrawableModelIF;

public class GLAutoDrawableModel implements GLEventListener, AutoDrawableModelIF {

	private WeakReference<GLAutoDrawable> drawableRef;
	private final EventListenerList listenerList;

	public GLAutoDrawableModel() {
		listenerList = new EventListenerList();
	}

	public GLAutoDrawable getDrawableRef() {
		return drawableRef.get();
	}

	@Override
	public void setDrawable(GLAutoDrawable drawable) {
		this.drawableRef = new WeakReference<GLAutoDrawable>(drawable);
		if (drawable != null) {
			drawable.addGLEventListener(this);
		}
	}

	@Override
	public void addEventListener(GLEventListener toAdd) {
		removeEventListener(toAdd);
		listenerList.add(GLEventListener.class, toAdd);
	}

	@Override
	public void removeEventListener(GLEventListener toRemove) {
		listenerList.remove(GLEventListener.class, toRemove);
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		drawable.setGL(new DebugGL2(drawable.getGL().getGL2()));
		GLEventListener[] listeners = listenerList.getListeners(GLEventListener.class);
		for (GLEventListener listener : listeners) {
			listener.init(drawable);
		}
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		GLEventListener[] listeners = listenerList.getListeners(GLEventListener.class);
		for (GLEventListener listener : listeners) {
			listener.dispose(drawable);
		}
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		GLEventListener[] listeners = listenerList.getListeners(GLEventListener.class);
		for (GLEventListener listener : listeners) {
			listener.display(drawable);
		}
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GLEventListener[] listeners = listenerList.getListeners(GLEventListener.class);
		for (GLEventListener listener : listeners) {
			listener.reshape(drawable, x, y, width, height);
		}
	}

	@Override
	public void update() {
		GLAutoDrawable drawable = drawableRef.get();
		if (drawable != null) {
			drawable.display();
		}
	}

	@Override
	public GL2 getGL2() {
		GLAutoDrawable glAutoDrawable = drawableRef.get();
		return glAutoDrawable != null ? glAutoDrawable.getGL().getGL2()
				: null;
	}

}

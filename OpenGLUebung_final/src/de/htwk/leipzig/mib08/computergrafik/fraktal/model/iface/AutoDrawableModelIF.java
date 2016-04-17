package de.htwk.leipzig.mib08.computergrafik.fraktal.model.iface;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public interface AutoDrawableModelIF {

	/**
	 * @param drawable the GLPanel or Canvas to draw.
	 */
	void setDrawable(GLAutoDrawable drawable);

	void addEventListener(GLEventListener toAdd);

	void removeEventListener(GLEventListener toRemove);

	/**
	 * Calls display on the AutoDrawable
	 */
	void update();

	GL2 getGL2();

}
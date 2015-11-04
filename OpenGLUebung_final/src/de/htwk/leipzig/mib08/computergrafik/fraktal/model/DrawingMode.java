package de.htwk.leipzig.mib08.computergrafik.fraktal.model;

import javax.media.opengl.GL;

public enum DrawingMode {
	LINE_LOOP(GL.GL_LINE_LOOP),
	TRIANGLE(GL.GL_TRIANGLES)
	;
	
	private final int glMode;
	
	private DrawingMode(int glMode) {
		this.glMode = glMode;
	}
	
	public int getGlMode() {
		return glMode;
	}
}

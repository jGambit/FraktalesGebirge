package de.htwk.leipzig.mib08.computergrafik.fraktal.valueobject;

import de.htwk.leipzig.mib08.computergrafik.fraktal.model.DrawingMode;
import de.htwk.leipzig.mib08.computergrafik.fraktal.model.iface.Triangle3dIF;

public class FraktalesGebirgeConfig {

	private Triangle3dIF gebirge;
	private DrawingMode drawingMode;
	
	public Triangle3dIF getGebirge() {
		return gebirge;
	}
	
	public void setGebirge(Triangle3dIF gebirge) {
		this.gebirge = gebirge;
	}
	
	public DrawingMode getDrawingMode() {
		return drawingMode;
	}
	
	public void setDrawingMode(DrawingMode drawingMode) {
		this.drawingMode = drawingMode;
	}
	
}

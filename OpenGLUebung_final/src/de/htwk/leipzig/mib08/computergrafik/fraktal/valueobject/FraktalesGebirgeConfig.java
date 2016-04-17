package de.htwk.leipzig.mib08.computergrafik.fraktal.valueobject;

import de.htwk.leipzig.mib08.computergrafik.fraktal.model.DrawingMode;
import de.htwk.leipzig.mib08.computergrafik.fraktal.model.iface.Triangle3dIF;

public class FraktalesGebirgeConfig {

	private enum Rotation {
		X,
		Y,
		NONE
	}

	private enum ZoomMode {
		IN,
		OUT,
		NONE
	}

	private Triangle3dIF gebirge;
	private DrawingMode drawingMode;
	private int rekTiefe;
	private Rotation rotation;
	private ZoomMode zoomMode;

	public FraktalesGebirgeConfig() {
		rotation = Rotation.NONE;
		rekTiefe = 5;
	}

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

	public int getRekTiefe() {
		return rekTiefe;
	}

	public void setRekTiefe(int rekTiefe) {
		this.rekTiefe = rekTiefe;
	}

	public boolean isRotateX() {
		return rotation == Rotation.X;
	}

	public void setRotateX() {
		rotation = Rotation.X;
	}

	public boolean isRotateY() {
		return rotation == Rotation.Y;
	}

	public void setRotateY() {
		rotation = Rotation.Y;
	}

	public void unsetRotation() {
		rotation = Rotation.NONE;
	}

	public void setZoomIn() {
		zoomMode = ZoomMode.IN;
	}

	public boolean isZoomIn() {
		return zoomMode == ZoomMode.IN;
	}

	public void setZoomOut() {
		zoomMode = ZoomMode.OUT;
	}

	public boolean isZoomOut() {
		return zoomMode == ZoomMode.OUT;
	}

	public void unsetZoomMode() {
		zoomMode = ZoomMode.NONE;
	}

}

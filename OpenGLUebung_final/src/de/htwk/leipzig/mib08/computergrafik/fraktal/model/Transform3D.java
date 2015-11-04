package de.htwk.leipzig.mib08.computergrafik.fraktal.model;

import de.htwk.leipzig.mib08.computergrafik.fraktal.model.iface.Point3dIF;

public class Transform3D {
	
	public static final float TWO = 2.0f;
	public static final float ZERO = 0.0f;
	public static final float EPSILON = 0.0005f;
	
	public static Point3dIF createMidPoint(Point3dIF start, Point3dIF end) {
		float dx = (start.getX() + end.getX()) / TWO;
		float dy = (start.getY() + end.getY()) / TWO;
		float dz = (start.getZ() + end.getZ()) / TWO;
		return new Point3D(dx, dy, dz);
	}
	
	public static Point3dIF translate(Point3dIF start, float dx, float dy, float dz) {
		return new Point3D(start.getX() + dx, start.getY() + dy, start.getZ() + dz);
	}
	
	public static Point3dIF translateZ(Point3dIF start, float dz) {
		return translate(start, ZERO, ZERO, dz);
	}
	
	public static float square(float x) {
		return x * x;
	}
	
	public static boolean isEqual(float x1, float x2) {
		return Math.abs(x1 - x2) < EPSILON;
	}

}

package de.htwk.leipzig.mib08.computergrafik.fraktal.model;

import java.util.Arrays;

import de.htwk.leipzig.mib08.computergrafik.fraktal.model.iface.Point3dIF;

/**
 * Modell für einen Punkt im R3
 * @author Enrico Timoschenko (tarakos GmbH)
 *
 */
public class Point3D implements Point3dIF {
	
	private final float[] values;
	
	public Point3D() {
		this (Transform3D.ZERO, Transform3D.ZERO, Transform3D.ZERO);
	}
	
	public Point3D(Number x, Number y, Number z) {
		this(x == null ? Transform3D.ZERO : x.floatValue(), 
				y == null ? Transform3D.ZERO : y.floatValue(), 
				z == null ? Transform3D.ZERO : z.floatValue());
	}
	
	public Point3D(float x, float y, float z) {
		values = new float[] {x,y,z};
	}
	
	@Override
	public float getX() {
		return values[0];
	}
	
	@Override
	public float getY() {
		return values[1];
	}
	
	@Override
	public float getZ() {
		return values[2];
	}
	
	@Override
	public String toString() {
		return "(x, y, z) = " + Arrays.toString(values);
	}
	
	public boolean isInRange(Point3D other) {
		return getDistance(other) < Transform3D.EPSILON;
	}
	
	@Override
	public float getDistance(Point3dIF other) {
		float[] q = values;
		float[] p = {other.getX(), other.getY(), other.getZ()};
		return getEuclidean(q, p);
	}

	float getEuclidean(float[] q, float[] p) {
		float result = Transform3D.ZERO;
		
		for (int i = 0; i < q.length; i++) {
			result += Transform3D.square((q[i] - p[i]));
		}
		return (float) Math.sqrt(result);
	}
	
	@Override
	public float getLength() {
		float result = Transform3D.ZERO;
		for (int i = 0; i < values.length; i++) {
			result += Transform3D.square((values[i]));
		}
		return (float) Math.sqrt(result);
	}

	@Override
	public float[] getVector() {
		return values;
	}
	
}

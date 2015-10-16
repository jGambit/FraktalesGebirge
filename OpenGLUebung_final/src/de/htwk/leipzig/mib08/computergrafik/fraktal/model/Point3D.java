package de.htwk.leipzig.mib08.computergrafik.fraktal.model;

import java.util.Arrays;

/**
 * Modell für einen Punkt im R3
 * @author Enrico Timoschenko (tarakos GmbH)
 *
 */
public class Point3D {
	
	private static final float TWO = 2.0f;
	private static final float ZERO = 0.0f;
	private static final float EPSILON = 0.0005f;
	private final float[] values;
	
	public Point3D() {
		this (ZERO, ZERO, ZERO);
	}
	
	public Point3D(Number x, Number y, Number z) {
		this(x == null ? ZERO : x.floatValue(), 
				y == null ? ZERO : y.floatValue(), 
				z == null ? ZERO : z.floatValue());
	}
	
	public Point3D(float x, float y, float z) {
		values = new float[] {x,y,z};
	}
	
	public float getX() {
		return values[0];
	}
	
	public float getY() {
		return values[1];
	}
	
	public float getZ() {
		return values[2];
	}
	
	public Point3D createMidPoint(Point3D other) {
		float dx = (other.getX() + getX()) / TWO;
		float dy = (other.getY() + getY()) / TWO;
		float dz = (other.getZ() + getZ()) / TWO;
		return new Point3D(dx, dy, dz);
	}
	
	public Point3D translate(float dx, float dy, float dz) {
		return new Point3D(getX() + dx, getY() + dy, getZ() + dz);
	}
	
	public Point3D translateZ(float dz) {
		return translate(ZERO, ZERO, dz);
	}

	@Override
	public String toString() {
		return "(x, y, z) = " + Arrays.toString(values);
	}
	
	public boolean isInLinearRange(Point3D other) {
		return inRange(getX(), other.getX())
				&& inRange(getY(), other.getY())
				&& inRange(getZ(), other.getZ());
	}
	
	public boolean isInEuclideanRange(Point3D other) {
		return getEuclideanDistance(other) < EPSILON;
	}
	
	public float getEuclideanDistance(Point3D other) {
		float[] q = values;
		float[] p = other.values;
		if (q.length != p.length) {
			throw new IllegalArgumentException("The given argument is not a 3D-point!");
		}
		return getEuclidean(q, p);
	}

	float getEuclidean(float[] q, float[] p) {
		float result = ZERO;
		
		for (int i = 0; i < q.length; i++) {
			result += square((q[i] - p[i]));
		}
		return (float) Math.sqrt(result);
	}
	
	public float getLength() {
		float result = ZERO;
		for (int i = 0; i < values.length; i++) {
			result += square((values[i]));
		}
		return (float) Math.sqrt(result);
	}
	
	boolean inRange(float x1, float x2) {
		return Math.abs(x1 - x2) < EPSILON;
	}
	
	private float square(float value) {
		return value * value;
	}
	
}

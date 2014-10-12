package de.htwk.leipzig.mib08.computergrafik.fraktal.model;

public class Point3D {
	private static final float TWO = 2.0f;
	private static final float ZERO = 0.0f;
	private static final float EPSILON = 0.0005f;
	private final float x;
	private final float y;
	private final float z;
	
	public Point3D(Number x, Number y, Number z) {
		this(x == null ? ZERO : x.floatValue(), 
				y == null ? ZERO : y.floatValue(), 
				z == null ? ZERO : z.floatValue());
	}
	
	public Point3D(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getZ() {
		return z;
	}
	
	public Point3D createMidPoint(Point3D other) {
		float dx = (other.getX() - getX()) / TWO;
		float dy = (other.getY() - getY()) / TWO;
		float dz = (other.getZ() - getZ()) / TWO;
		return translate(dx, dy, dz);
	}
	
	public Point3D translate(float dx, float dy, float dz) {
		return new Point3D(getX() + dx, getY() + dy, getZ() + dz);
	}
	
	public Point3D translateZ(float dz) {
		return translate(ZERO, ZERO, dz);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		result = prime * result + Float.floatToIntBits(z);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point3D other = (Point3D) obj;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		if (Float.floatToIntBits(z) != Float.floatToIntBits(other.z))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "(x, y, z) = " + toArray();
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
		float[] q = toArray();
		float[] p = other.toArray();
		if (q.length != p.length) {
			throw new IllegalArgumentException("The given argument is not a 3D-point!");
		}
		double result = getEuclidean(q, p);
		return Double.valueOf(result).floatValue();
	}

	double getEuclidean(float[] q, float[] p) {
		double result = 0.0d;
		
		for (int i = 0; i < q.length; i++) {
			result += square((q[i] - p[i]));
		}
		
		return Math.sqrt(result);
	}
	
	boolean inRange(float x1, float x2) {
		return Math.abs(x1 - x2) < EPSILON;
	}
	
	private float[] toArray() {
		return new float[] {getX(), getY(), getZ()};
	}
	
	private double square(float value) {
		return value * value;
	}
	
}

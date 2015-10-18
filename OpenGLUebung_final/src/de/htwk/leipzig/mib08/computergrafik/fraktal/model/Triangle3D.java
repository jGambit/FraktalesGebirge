package de.htwk.leipzig.mib08.computergrafik.fraktal.model;

import java.util.EnumMap;
import java.util.Map;

import de.htwk.leipzig.mib08.computergrafik.fraktal.model.iface.Triangle3dIF;

/**
 * Modell für ein Dreieck im R3
 * @author Enrico Timoschenko (tarakos GmbH)
 *
 */
public class Triangle3D implements Triangle3dIF {
	
	private final Map<TrianglePoints, Point3D> points;
	private float heightFactor;
	
	public Triangle3D(Triangle3dIF original, float height) {
		this(original.getA(), original.getB(), original.getC(), height);
	}
	
	public Triangle3D(Point3D a, Point3D b, Point3D c) {
		this(a, b, c, 0.05f);
	}
	
	public Triangle3D(Point3D a, Point3D b, Point3D c, float heightFactor) {
		points = new EnumMap<>(TrianglePoints.class);
		points.put(TrianglePoints.A, a);
		points.put(TrianglePoints.B, b);
		points.put(TrianglePoints.C, c);
		setHeightFactor(heightFactor);
	}
	
	@Override
	public Point3D getA() {
		return points.get(TrianglePoints.A);
	}
	
	@Override
	public Point3D getB() {
		return points.get(TrianglePoints.B);
	}
	
	@Override
	public Point3D getC() {
		return points.get(TrianglePoints.C);
	}
	
	@Override
	public String toString() {
		return "(A, B, C) = " + points.values();
	}
	
	@Override
	public float getLength() {
		float AB = getA().getEuclideanDistance(getB());
		float BC = getB().getEuclideanDistance(getC());
		float CA = getC().getEuclideanDistance(getA());
		return new Point3D(AB, BC, CA).getLength();
	}

	@Override
	public float getHeightFactor() {
		return heightFactor;
	}

	public void setHeightFactor(float heightFactor) {
		this.heightFactor = heightFactor;
	}

}

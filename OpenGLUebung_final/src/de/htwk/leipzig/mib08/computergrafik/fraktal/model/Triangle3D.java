package de.htwk.leipzig.mib08.computergrafik.fraktal.model;

import java.awt.Color;
import java.util.EnumMap;
import java.util.Map;

import de.htwk.leipzig.mib08.computergrafik.fraktal.model.iface.Point3dIF;
import de.htwk.leipzig.mib08.computergrafik.fraktal.model.iface.Triangle3dIF;

/**
 * Modell für ein Dreieck im R3
 * @author Enrico Timoschenko (tarakos GmbH)
 *
 */
public class Triangle3D implements Triangle3dIF {
	
	private final Map<TrianglePoints, Point3dIF> points;
	private float heightFactor;
	
	public Triangle3D(Triangle3dIF original, float height) {
		this(original.getA(), original.getB(), original.getC(), height);
	}
	
	public Triangle3D(Point3dIF a, Point3dIF b, Point3dIF c) {
		this(a, b, c, 0.05f);
	}
	
	public Triangle3D(Point3dIF a, Point3dIF b, Point3dIF c, float heightFactor) {
		points = new EnumMap<>(TrianglePoints.class);
		points.put(TrianglePoints.A, a);
		points.put(TrianglePoints.B, b);
		points.put(TrianglePoints.C, c);
		setHeightFactor(heightFactor);
	}
	
	@Override
	public Point3dIF getA() {
		return points.get(TrianglePoints.A);
	}
	
	@Override
	public Point3dIF getB() {
		return points.get(TrianglePoints.B);
	}
	
	@Override
	public Point3dIF getC() {
		return points.get(TrianglePoints.C);
	}
	
	@Override
	public String toString() {
		return "(A, B, C) = " + points.values();
	}
	
	@Override
	public float getLength() {
		float[] values = new float[]{getA().getLength(), getB().getLength(), getC().getLength()};
		float result = 0.0f;
		for (float f : values) {
			result += f * f;
		}
		
		return (float) Math.sqrt(result);
	}

	@Override
	public float getHeightFactor() {
		return heightFactor;
	}

	public void setHeightFactor(float heightFactor) {
		this.heightFactor = heightFactor;
	}

	@Override
	public Color getColor() {
		float a = getA().getLength();
		float length = a < 0.0005f ? 0.0005f : a;
		float r = Math.abs(getA().getX()) / length;
		float g = Math.abs(getA().getY()) / length;
		float b = Math.abs(getA().getZ()) / length;
		return new Color(r, g, b);
	}

}

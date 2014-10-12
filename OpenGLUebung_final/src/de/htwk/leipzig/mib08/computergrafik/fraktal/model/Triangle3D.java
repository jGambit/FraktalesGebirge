package de.htwk.leipzig.mib08.computergrafik.fraktal.model;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Triangle3D {
	
	private final Map<TrianglePoints, Point3D> points;
	private Triangle3D halvedMidTriangle;
	
	private enum TrianglePoints {
		A,
		B,
		C
	}
	
	public Triangle3D(Point3D a, Point3D b, Point3D c) {
		points = new EnumMap<>(TrianglePoints.class);
		points.put(TrianglePoints.A, a);
		points.put(TrianglePoints.B, b);
		points.put(TrianglePoints.C, c);
	}
	
	public Point3D getA() {
		return points.get(TrianglePoints.A);
	}
	
	public Point3D getB() {
		return points.get(TrianglePoints.B);
	}
	
	public Point3D getC() {
		return points.get(TrianglePoints.C);
	}
	
	public Triangle3D getHalvedMidTriangle() {
		if (halvedMidTriangle == null) {
			Point3D a = getB().createMidPoint(getC());
			Point3D b = getC().createMidPoint(getA());
			Point3D c = getA().createMidPoint(getB());
			halvedMidTriangle = new Triangle3D(a, b, c);
		}
		return halvedMidTriangle;
	}
	
	public Triangle3D createHalvedATriangle() {
		Point3D a = getA();
		Point3D b = getHalvedMidTriangle().getB();
		Point3D c = getHalvedMidTriangle().getC();
		return new Triangle3D(a, b, c);
	}
	
	public Triangle3D createHalvedBTriangle() {
		Point3D a = getHalvedMidTriangle().getA();
		Point3D b = getB();
		Point3D c = getHalvedMidTriangle().getC();
		return new Triangle3D(a, b, c);
	}
	
	public Triangle3D createHalvedCTriangle() {
		Point3D a = getHalvedMidTriangle().getA();
		Point3D b = getHalvedMidTriangle().getB();
		Point3D c = getC();
		return new Triangle3D(a, b, c);
	}
	
	public List<Triangle3D> createHalvedFractal() {
		return Arrays.asList(toHalvedFractalArray());
	}
	
	private Triangle3D[] toHalvedFractalArray() {
		return new Triangle3D[] {
				createHalvedATriangle(),
				createHalvedBTriangle(),
				getHalvedMidTriangle(),
				createHalvedCTriangle()
		};
	}

	@Override
	public String toString() {
		return "(A, B, C) = " + points.values();
	}
	
}

package de.htwk.leipzig.mib08.computergrafik.fraktal.process;

import java.util.Arrays;
import java.util.List;

import com.github.jgambit.emvc.process.HeadlessModulProcess;

import de.htwk.leipzig.mib08.computergrafik.fraktal.model.Point3D;
import de.htwk.leipzig.mib08.computergrafik.fraktal.model.Triangle3D;
import de.htwk.leipzig.mib08.computergrafik.fraktal.model.TrianglePoints;
import de.htwk.leipzig.mib08.computergrafik.fraktal.model.iface.Triangle3dIF;

public class FraktalProcess extends HeadlessModulProcess {

	public List<Triangle3dIF> createFraktalMountain(Triangle3dIF start) {
		Triangle3dIF center = createHalvedMidTriangle(start);
		return Arrays.asList(
				createHalvedATriangle(start, center),
				createHalvedBTriangle(start, center),
				center,
				createHalvedCTriangle(start, center)
				);
	}
	
	Triangle3dIF createHalvedCTriangle(Triangle3dIF start, Triangle3dIF center) {
		Point3D a = center.getA();
		Point3D b = center.getB();
		Point3D c = start.getC();
		return new Triangle3D(a, b, c);
	}

	Triangle3dIF createHalvedATriangle(Triangle3dIF start, Triangle3dIF center) {
		Point3D a = start.getA();
		Point3D b = center.getB();
		Point3D c = center.getC();
		return new Triangle3D(a, b, c);
	}

	Triangle3dIF createHalvedMidTriangle(Triangle3dIF start) {
		Point3D a = createMidPointWithRandomHeight(TrianglePoints.A, start);
		Point3D b = createMidPointWithRandomHeight(TrianglePoints.B, start);
		Point3D c = createMidPointWithRandomHeight(TrianglePoints.C, start);
		return new Triangle3D(a, b, c);
	}
	
	Triangle3dIF createHalvedBTriangle(Triangle3dIF start, Triangle3dIF center) {
		Point3D a = center.getA();
		Point3D b = start.getB();
		Point3D c = center.getC();
		return new Triangle3D(a, b, c);
	}

	Point3D createMidPointWithRandomHeight(TrianglePoints wanted, Triangle3dIF plane) {
		float height = createRandomHeight(plane);
		switch (wanted) {
		case A:
			return createAndTranslateCenter(plane.getB(), plane.getC(), height);
		case B:
			return createAndTranslateCenter(plane.getC(), plane.getA(), height);
		case C:
			return createAndTranslateCenter(plane.getA(), plane.getB(), height);
		default:
			throw new IllegalArgumentException("The wanted Point is invalid: " + wanted);
		}
	}

	private Point3D createAndTranslateCenter(Point3D start, Point3D end, float height) {
		Point3D midPoint = start.createMidPoint(end);
		return midPoint.translateZ(height);
	}
	
	private float createRandomHeight(Triangle3dIF plane) {
		return (float) (plane.getHeightFactor() * Math.random() * plane.getLength());
	}

}

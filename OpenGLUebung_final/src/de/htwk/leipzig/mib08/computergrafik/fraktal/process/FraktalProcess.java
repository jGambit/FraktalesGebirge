package de.htwk.leipzig.mib08.computergrafik.fraktal.process;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import com.github.jgambit.emvc.process.HeadlessModulProcess;

import de.htwk.leipzig.mib08.computergrafik.fraktal.model.Point3D;
import de.htwk.leipzig.mib08.computergrafik.fraktal.model.Transform3D;
import de.htwk.leipzig.mib08.computergrafik.fraktal.model.Triangle3D;
import de.htwk.leipzig.mib08.computergrafik.fraktal.model.TrianglePoints;
import de.htwk.leipzig.mib08.computergrafik.fraktal.model.iface.Point3dIF;
import de.htwk.leipzig.mib08.computergrafik.fraktal.model.iface.Triangle3dIF;

public class FraktalProcess extends HeadlessModulProcess {

	private final Point3D BASE_C = new Point3D(80, -80, 0);
	private final Point3D BASE_B = new Point3D(0, 80, 0);
	private final Point3D BASE_A = new Point3D(-80, -80, 0);
	
	public List<Triangle3dIF> createFraktalMountain(Triangle3dIF start) {
		Triangle3dIF center = createHalvedMidTriangle(start);
		return Arrays.asList(
				createHalvedATriangle(start, center),
				createHalvedBTriangle(start, center),
				center,
				createHalvedCTriangle(start, center)
				);
	}
	
	public Triangle3dIF createTriangle(List<String> coordinates) {
		NumberListParser parser = new NumberListParser(coordinates);
		List<Number> numbers = parser.getNumberList();
		
		if (numbers.size() != 9) {
			_log.log(Level.WARNING, 
					"Illegal number of arguments, 9 coordinates required!");
			return new Triangle3D(BASE_A, BASE_B, BASE_C);
		}
		Point3dIF[] vector = create3dVector(numbers);
		return new Triangle3D(vector[0], vector[1], vector[2]);
	}

	private Point3dIF[] create3dVector(List<Number> numbers) {
		Point3D[] vector = new Point3D[3];
		
		for (int i = 0; i < numbers.size(); i += 3) {
			Number x = numbers.get(i);
			Number y = numbers.get(i + 1);
			Number z = numbers.get(i + 2);
			vector[i / 3] = new Point3D(x, y, z);
		}
		return vector;
	}
	
	Triangle3dIF createHalvedCTriangle(Triangle3dIF start, Triangle3dIF center) {
		Point3dIF a = center.getA();
		Point3dIF b = center.getB();
		Point3dIF c = start.getC();
		return new Triangle3D(a, b, c);
	}

	Triangle3dIF createHalvedATriangle(Triangle3dIF start, Triangle3dIF center) {
		Point3dIF a = start.getA();
		Point3dIF b = center.getB();
		Point3dIF c = center.getC();
		return new Triangle3D(a, b, c);
	}

	Triangle3dIF createHalvedMidTriangle(Triangle3dIF start) {
		Point3dIF a = createMidPointWithRandomHeight(TrianglePoints.A, start);
		Point3dIF b = createMidPointWithRandomHeight(TrianglePoints.B, start);
		Point3dIF c = createMidPointWithRandomHeight(TrianglePoints.C, start);
		return new Triangle3D(a, b, c);
	}
	
	Triangle3dIF createHalvedBTriangle(Triangle3dIF start, Triangle3dIF center) {
		Point3dIF a = center.getA();
		Point3dIF b = start.getB();
		Point3dIF c = center.getC();
		return new Triangle3D(a, b, c);
	}

	Point3dIF createMidPointWithRandomHeight(TrianglePoints wanted, Triangle3dIF plane) {
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

	private Point3dIF createAndTranslateCenter(Point3dIF start, Point3dIF end, float height) {
		Point3dIF midPoint = Transform3D.createMidPoint(start, end);
		return Transform3D.translateZ(midPoint, height);
	}
	
	private float createRandomHeight(Triangle3dIF plane) {
		return (float) (plane.getHeightFactor() * Math.random() * plane.getLength());
	}
	
}

package de.htwk.leipzig.mib08.computergrafik.fraktal.model.iface;

import de.htwk.leipzig.mib08.computergrafik.fraktal.model.Point3D;

public interface Triangle3dIF {

	Point3D getA();

	Point3D getB();

	Point3D getC();

	float getLength();

	float getHeightFactor();

}
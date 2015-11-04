package de.htwk.leipzig.mib08.computergrafik.fraktal.model.iface;

import java.awt.Color;

public interface Triangle3dIF {

	Point3dIF getA();

	Point3dIF getB();

	Point3dIF getC();

	float getLength();

	float getHeightFactor();

	Color getColor();

}
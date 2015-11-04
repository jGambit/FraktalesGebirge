package de.htwk.leipzig.mib08.computergrafik.fraktal.model.iface;

public interface Point3dIF {

	float getX();

	float getY();

	float getZ();

	float getLength();
	
	float[] getVector();
	
	float getDistance(Point3dIF other);

}
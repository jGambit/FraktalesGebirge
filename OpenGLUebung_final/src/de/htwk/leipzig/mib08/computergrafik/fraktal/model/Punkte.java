package de.htwk.leipzig.mib08.computergrafik.fraktal.model;

public class Punkte {

	private final float x;
	private final float y;
	private float z;
	
	public Punkte(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Punkte(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float getX(){
		return x;
		
	}
	public float getY(){
		return y;
		
	}
	public float getZ(){
		return z;
		
	}
	public void setZ(float Z){
		this.z = Z;
	}
}

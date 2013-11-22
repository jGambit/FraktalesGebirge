
public class Punkte {

	private float x;
	private float y;
	private float z;
	
	public Punkte(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Punkte(int x, int y, int z){
		this.x = new Float(x);
		this.y = new Float(y);
		this.z = new Float(z);
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

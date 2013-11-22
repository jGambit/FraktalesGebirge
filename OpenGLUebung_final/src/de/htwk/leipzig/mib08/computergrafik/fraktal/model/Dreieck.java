package de.htwk.leipzig.mib08.computergrafik.fraktal.model;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

public class Dreieck {

	private final Punkte a;
	private final Punkte b;
	private final Punkte c;
	private float red;
	private float green;
	private float blue;
	public static int mode = 0;
	private static ArrayList<Punkte> Punktliste = new ArrayList<Punkte>();
	public static boolean flag = false;
	public static int hoehe = 60;
	
		public Dreieck(Punkte a, Punkte b, Punkte c){
			this.a = a;
			this.b = b;
			this.c = c;
			this.red = 0.0f;
			this.green = 0.0f;
			this.blue = 0.0f;
			// Punktliste = new ArrayList<Punkte>();
		}
		
		public Punkte getA() {
			return a;
		}
		
		public Punkte getB() {
			return b;
		}
		
		public Punkte getC() {
			return c;
		}
		
		public void paintDreieck(GL2 gl) {
			if(mode==0){
				gl.glBegin(GL.GL_LINE_LOOP);
			}
			if(mode==1){
				gl.glBegin(GL.GL_POLYGON_OFFSET_FILL);
			}
			if(mode==2){
				gl.glBegin(GL.GL_LINE_LOOP);
				 gl.glColor3f(0.0f, 0.0f, 0.0f);
				    
					gl.glVertex3f (a.getX(), a.getY(), a.getZ());
			    	gl.glVertex3f (b.getX(), b.getY(), b.getZ());
			    	gl.glVertex3f (c.getX(), c.getY(), c.getZ());
			    	
			    	gl.glEnd();
			    
			    	gl.glBegin(GL.GL_POLYGON_OFFSET_FILL);
		
	
			}

		    if(mode==2) gl.glColor3f(0.8f, 0.6f, 0.3f);
		    else gl.glColor3f(red, green, blue);
		    
			gl.glVertex3f (a.getX(), a.getY(), a.getZ());
	    	gl.glVertex3f (b.getX(), b.getY(), b.getZ());
	    	gl.glVertex3f (c.getX(), c.getY(), c.getZ());
	    	
	    	gl.glEnd();
		}
		
		public void setColor(float r, float g, float b) {
			red = r;
			green = g;
			blue = b;
		}
		
		public Punkte getMitteWest() {
			
			return null;
		}
		
		
		public static Dreieck gibFraktaleCenter(Dreieck berg){
			
			//A
			float newX1 = (berg.getB().getX()+berg.getA().getX())/2;
			float newY1 = (berg.getB().getY()+berg.getA().getY())/2;
			float newZ1 = (berg.getB().getZ()+berg.getA().getZ())/2;
		
			
			 //B
			float newX2 = (berg.getB().getX()+berg.getC().getX())/2;
			float newY2 = (berg.getB().getY()+berg.getC().getY())/2;
			float newZ2 = (berg.getB().getZ()+berg.getC().getZ())/2;
			
		
			 //C
			float newX3 = (berg.getC().getX()+berg.getA().getX())/2;
			float newY3 = (berg.getC().getY()+berg.getA().getY())/2;
			float newZ3 = (berg.getC().getZ()+berg.getA().getZ())/2;
			 

		
			Punkte newPunkt1 = new Punkte(newX1, newY1, newZ1);
			newPunkt1.setZ(exists(newPunkt1));
			
			Punkte newPunkt2 = new Punkte(newX2, newY2, newZ2);
			newPunkt2.setZ(exists(newPunkt2));
			
			Punkte newPunkt3 = new Punkte(newX3, newY3, newZ3);
			newPunkt3.setZ(exists(newPunkt3));
			
			return new Dreieck(newPunkt1,newPunkt2,newPunkt3);
			
		}

		public static Dreieck gibFraktaleNorth(Dreieck berg){
	
			//A
			float newX1 = (berg.getB().getX()+berg.getA().getX())/2;
			float newY1 = (berg.getB().getY()+berg.getA().getY())/2;
			float newZ1 = (berg.getB().getZ()+berg.getA().getZ())/2;
			
			
			//B
			float newX2 = berg.getB().getX();
			float newY2 = berg.getB().getY();
			float newZ2 = berg.getB().getZ();
		
			//C
			float newX3 = (berg.getB().getX()+berg.getC().getX())/2;
			float newY3 = (berg.getB().getY()+berg.getC().getY())/2;
			float newZ3 = (berg.getB().getZ()+berg.getC().getZ())/2;
		
			
			
			Punkte newPunkt1 = new Punkte(newX1, newY1, newZ1);
			newPunkt1.setZ(exists(newPunkt1));
			
			Punkte newPunkt2 = new Punkte(newX2, newY2, newZ2);
			newPunkt2.setZ(exists(newPunkt2));
			
			Punkte newPunkt3 = new Punkte(newX3, newY3, newZ3);
			newPunkt3.setZ(exists(newPunkt3));
				
			return new Dreieck(newPunkt1,newPunkt2,newPunkt3);
			
		}
		public static Dreieck gibFraktaleEast(Dreieck  berg){

			//A
			float newX1 = (berg.getC().getX()+berg.getA().getX())/2;
			float newY1 = (berg.getC().getY()+berg.getA().getY())/2;
			float newZ1 = (berg.getC().getZ()+berg.getA().getZ())/2;
			//B
			float newX2 = (berg.getB().getX()+berg.getC().getX())/2;
			float newY2 = (berg.getB().getY()+berg.getC().getY())/2;
			float newZ2 = (berg.getB().getZ()+berg.getC().getZ())/2;
			//C
			float newX3 = berg.getC().getX();
			float newY3 = berg.getC().getY();
			float newZ3 = berg.getC().getZ();
		
			
			Punkte newPunkt1 = new Punkte(newX1, newY1, newZ1);
			newPunkt1.setZ(exists(newPunkt1));
			
			Punkte newPunkt2 = new Punkte(newX2, newY2, newZ2);
			newPunkt2.setZ(exists(newPunkt2));
			
			Punkte newPunkt3 = new Punkte(newX3, newY3, newZ3);
			newPunkt3.setZ(exists(newPunkt3));
				
			return new Dreieck(newPunkt1,newPunkt2,newPunkt3);
			
		}
		public static Dreieck gibFraktaleWest(Dreieck  berg){

			//A
			float newX1 = berg.getA().getX();
			float newY1 = berg.getA().getY();
			float newZ1 = berg.getA().getZ();

			//B
			float newX2 = (berg.getB().getX()+berg.getA().getX())/2;
			float newY2 = (berg.getB().getY()+berg.getA().getY())/2;
			float newZ2 = (berg.getB().getZ()+berg.getA().getZ())/2;
		
			//C
			float newX3 = (berg.getC().getX()+berg.getA().getX())/2;
			float newY3 = (berg.getC().getY()+berg.getA().getY())/2;
			float newZ3 = (berg.getC().getZ()+berg.getA().getZ())/2;
			
			
			Punkte newPunkt1 = new Punkte(newX1, newY1, newZ1);
			newPunkt1.setZ(exists(newPunkt1));
			
			Punkte newPunkt2 = new Punkte(newX2, newY2, newZ2);
			newPunkt2.setZ(exists(newPunkt2));
			
			Punkte newPunkt3 = new Punkte(newX3, newY3, newZ3);
			newPunkt3.setZ(exists(newPunkt3));
				
			return new Dreieck(newPunkt1,newPunkt2,newPunkt3);
			
			
		}
		
		private static float exists(Punkte test){
	
			if(flag){
				for (Punkte element : Punktliste) {		
					if(test.getX() == element.getX() 
							&& test.getY() == element.getY()){
								return element.getZ();
					}
				}
			}
			flag=true;
			Punktliste.add(test);
			// System.out.println("hier: "+hoehe);
			return new Float((Math.random() * hoehe) );
			
			
		}
}

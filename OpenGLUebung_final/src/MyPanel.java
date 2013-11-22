import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.util.List;
import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.GLUT;


public class MyPanel extends GLJPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8464667398691555779L;
	float x;
	float y;
	float z;

	GL gl;
	GLU glu;
	Dreieck berg;
	List<Dreieck> gebirge;
	GLAutoDrawable arg0;
	public static boolean flagX = false;
	public static boolean flagY = false;
	public static boolean zoomIn = false;
	public static boolean zoomOut = false;
	
	public static int rekTiefe = 3;
	
	public MyPanel(){
		
	glu = new GLU();
		//backBuffer = new BufferedImage(200, 200, BufferedImage.TYPE_3BYTE_BGR);
	//graphics = backBuffer.createGraphics();
	//graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	// setSize(backBuffer.getWidth(null), backBuffer.getHeight(null));	
		
	this.addGLEventListener(new GLEventListener() {
		
		public void display(GLAutoDrawable arg0) {
			gl = arg0.getGL();
		    gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		    
			
		    berg = new Dreieck(new Punkte(-80, -80, 0),
		    				new Punkte(0, 80, 0),
		    				new Punkte(80, -80, 0));
		    //  berg.paintDreieck(gl);
		    // gl.glLoadIdentity(); // zurücksetzen
		    rekPaint(berg, rekTiefe);
	
		    
		  if(flagX)   
			  gl.glRotatef(20.0f, 1.0f, 0.0f, 0.0f);    // Rotation um die x-Achse
		  
		  flagX = false;

		  if(flagY) 			  
			  gl.glRotatef(20.0f, 0.0f, 1.0f, 0.0f);    // Rotation um die y-Achse
				 
		  flagY = false;
		  
		  if(zoomIn) {
//			  gl.glMatrixMode(GL.GL_PROJECTION); 
//			  gl.glLoadIdentity(); 
//			  glu.gluPerspective(10.0, 1.0, 3.0, 7.0); 
//			  gl.glMatrixMode(GL.GL_MODELVIEW); 
//			  gl.glLoadIdentity(); 
//			  glu.gluLookAt(0.0, 0.0, 5.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
//			  System.out.println("zoom in");
//			  zoomIn = false;
			  float h = (float)HEIGHT / (float)WIDTH;
	           
			    gl.glMatrixMode(GL.GL_PROJECTION);
			 
			    //System.err.println("GL_VENDOR: " + gl.glGetString(GL.GL_VENDOR));
			    //System.err.println("GL_RENDERER: " + gl.glGetString(GL.GL_RENDERER));
			    //System.err.println("GL_VERSION: " + gl.glGetString(GL.GL_VERSION));
			    gl.glLoadIdentity();
			    gl.glFrustum(-1.0f, 1.0f, -h, h, 5.0f, 600.0f);
			    gl.glMatrixMode(GL.GL_MODELVIEW);
			    gl.glLoadIdentity();
			    gl.glTranslatef(0.0f, 0.0f, -100.0f);
		  }
		  
		  if(zoomOut) {
			  System.out.println("zoom out");
			  zoomOut = false;
		  }
		  
			  
			
		}

		
		public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
			// TODO Auto-generated method stub
			
		}

		
		public void init(GLAutoDrawable arg0) {


			  GL gl = arg0.getGL();
		     // float l_position[] = {100.0f, 100.0f, 200.0f, 1.0f};
			 // gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, l_position, 0);  
			  
		      gl.glEnable(GL.GL_LIGHTING);
		      gl.glEnable(GL.GL_LIGHT0);
		      gl.glEnable(GL.GL_COLOR_MATERIAL);
		      gl.glEnable(GL.GL_DEPTH_TEST);
		      gl.glEnable(GL.GL_NORMALIZE);
		      gl.glEnable(GL.GL_POLYGON_SMOOTH);
		      gl.glEnable( GL.GL_POINT_SMOOTH );
		      gl.glEnable( GL.GL_LINE_SMOOTH );


		  	  gl.glClearColor(1.0f,1.0f,1.0f,1.0f);                   
		      gl.glMatrixMode(GL.GL_PROJECTION);
		      gl.glOrtho(-100, 100, -100, 100, -100, 100);	  
		      gl.glMatrixMode(GL.GL_MODELVIEW);

		}

		
		public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
				int arg4) {
			// TODO Auto-generated method stub
			
		}
		});

	}
	public void rekPaint(Dreieck neu, int lauf) {

		if(lauf<1) return;	

		// n-tes Dreieck
		berg = Dreieck.gibFraktaleCenter(neu);		
		berg.setColor(0.0f, 
					gibColor(berg.getA().getZ(), 
							berg.getB().getZ(), 
							berg.getC().getZ()), 0.0f);
		berg.paintDreieck(gl);
		    	
		// Komplexitaet der Rekursion reduziert
		rekPaint(berg, lauf-2);
		    	
		berg = Dreieck.gibFraktaleNorth(neu);
		berg.setColor(0.0f, 
				gibColor(berg.getA().getZ(), 
						berg.getB().getZ(), 
						berg.getC().getZ()), 
						0.0f);
		berg.paintDreieck(gl);
		    
		// Komplexitaet reduziert
		rekPaint(berg, lauf-2);
		    	
		berg = Dreieck.gibFraktaleWest(neu);
		berg.setColor(0.0f, 
				gibColor(berg.getA().getZ(), 
						berg.getB().getZ(), 
						berg.getC().getZ()), 
						0.0f);
		berg.paintDreieck(gl);
		    
		// Komplexitaet reduziert
		rekPaint(berg, lauf-2);
		    	
		berg = Dreieck.gibFraktaleEast(neu);
		berg.setColor(0.0f, 
				gibColor(berg.getA().getZ(), 
						berg.getB().getZ(), 
						berg.getC().getZ()), 
						0.0f);
		berg.paintDreieck(gl);
		    	
		// Komplexiateat reduziert
		rekPaint(berg, lauf-2);
    	
	
	}
	
	float gibColor(float a, float b, float c) {
		Double rc = new Double((a + b +c) / 3.0f);
		return new Float(Math.abs( (rc / 60.0f ) )); 
	}
}

	
	
	
	


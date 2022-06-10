package Objects;
import java.awt.Graphics;
import java.awt.Polygon;
//import java.awt.Rectangle;
import java.util.LinkedList;

import Principal.Principal;

public abstract class GameObject 
{
	//protected double x, y;
	
	protected ObjectId id;
	protected float velx = 0, vely = 0;
	
	protected boolean falling = true;
	protected boolean jumping = false;
	
	protected float massa;
	protected float angle;
	
	protected double xpoints[];
	protected double ypoints[];
	
	
	
	public GameObject(Polygon p, ObjectId id)
	{
		//System.out.println("GameObject");
		xpoints = new double[p.npoints];
		ypoints = new double[p.npoints];
		//Convertendo points int para points double
		for (int i=0; i<p.npoints; ++i) {
			xpoints[i] = p.xpoints[i];
		    ypoints[i] = p.ypoints[i];
		}
		this.id = id;
		
	}
	public GameObject(ObjectId id)
	{
		//System.out.println("GameObject");
//		xpoints = new double[p.npoints];
//		ypoints = new double[p.npoints];
//		//Convertendo points int para points double
//		for (int i=0; i<p.npoints; ++i) {
//			xpoints[i] = p.xpoints[i];
//		    ypoints[i] = p.ypoints[i];
//		}
		this.id = id;
		
	}
	
	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render(Graphics g);
	public Polygon getPolygon() {
		Polygon p = new Polygon();
		for (int i=0; i<xpoints.length; ++i) {
		     p.addPoint((int)xpoints[i], (int)ypoints[i]);
		}
		return p;
	}
	////
	public void novaPosicao(double x, double y) {
		x = x - Principal.getCenterX(xpoints);
		y = y - Principal.getCenterY(ypoints);
		for (int i=0; i<xpoints.length; ++i) {
			xpoints[i] += x;
			ypoints[i] += y;
		}
	}
	public void novaPosicaoX(double x) {
		x = x - Principal.getCenterX(xpoints);
		for (int i=0; i<xpoints.length; ++i) {
			xpoints[i] += x;
		}
	}
	public void novaPosicaoY(double y) {
		y = y - Principal.getCenterX(xpoints);
		for (int i=0; i<xpoints.length; ++i) {
			xpoints[i] += y;
		}
	}
	////
	
	public void addPosicao(double x, double y) {
		for (int i=0; i<xpoints.length; ++i) {
			xpoints[i] += x;
			ypoints[i] += y;
		}
	}
	
	
	public double getPrimeiroX() {
		return xpoints[0];
	}
	public double getPrimeiroY() {
		return ypoints[0];
	}
	public float getVelx() {
		return velx;
	}
	public float getVely() {
		return vely;
	}
	
	public void setVelx(float velx) {
		this.velx = velx;
	}
	
	public void setVely(float vely) {
		this.vely = vely;
	}
	
	
	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public  ObjectId getId() {
		return id;
	}
	public double getCenterX() {
		double max = xpoints[0];
		double min = xpoints[0];
		for(int i = 0; i<xpoints.length; i++) {
			if(max < xpoints[i])
				max = xpoints[i];
			else if(min > xpoints[i])
				min = xpoints[i];
		}
		
		double center = (max+min)/2;
	    return center; 
	}
	public double getCenterY() {
		double max = ypoints[0];
		double min = ypoints[0];
		for(int i = 0; i<ypoints.length; i++) {
			if(max < ypoints[i])
				max = ypoints[i];
			if(min > ypoints[i])
				min = ypoints[i];
		}
		
		double center = (max+min)/2;
	    return center; 
	}
	
	public void rotatePolygon(double angle) {
		angle = Math.toRadians(angle);
		//Armazenar angulo
		this.angle += angle;
		//Corrigir angulo armazenado se passar o limite
		if(this.angle > 2/1*Math.PI) this.angle -= 2/1*Math.PI;
		if(this.angle < -2/1*Math.PI) this.angle += 2/1*Math.PI;
		
		double centerX = getCenterX();
		double centerY = getCenterY();
		
		for(int i = 0; i<xpoints.length; i++) {
			
			xpoints[i] -= centerX;
			ypoints[i] -= centerY;
			
			double newx = ((xpoints[i] * Math.cos(angle) - ypoints[i] * Math.sin(angle)));
			double newy = ((xpoints[i] * Math.sin(angle) + ypoints[i] * Math.cos(angle)));
			
//			newx = p.x * cos(angle) - p.y * sin(angle)
//			newy = p.x * sin(angle) + p.y * cos(angle)
			
			xpoints[i] = (newx + centerX);
			ypoints[i] = (newy + centerY);
			
	    	

		}
		
		
	}
	public double[] scalePoints(double vezes) {
//		double cx = xpoints[0];
//		double cy = ypoints[0];
		double cx = getCenterX();
		double cy = getCenterY();
		
		for(int i = 0; i<xpoints.length; i++) {

			xpoints[i] -= cx;
			ypoints[i] -= cy;
//			diferencaX += p.xpoints[i];
//			diferencaY += p.ypoints[i];
			
			xpoints[i] *= vezes;
			ypoints[i] *= vezes;
			
			xpoints[i] += cx;
			ypoints[i] += cy;
			
		}
//		System.out.println(diferencaX);
//		p.translate((int)diferencaX/4, (int)diferencaY/4);
		
		return xpoints;
	}

	
	
}
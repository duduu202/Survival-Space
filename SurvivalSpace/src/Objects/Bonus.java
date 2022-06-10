package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.LinkedList;

import Principal.Principal;



public class Bonus extends GameObject{
	Color color = new Color(255, 255, 255);
	private int width = 10;
	private int height = 10;
	
	private int corR = 255;
	private int corG = 255;
	private int corB = 255;
	private double corRI = 1;
	private double corGI = 1;
	private double corBI = 1;
	private int corRV = 2;
	private int corGV = 4;
	private int corBV = 6;
	
	private int tipo;
	
	
	public Bonus(Polygon p, ObjectId id, int tipo) {
		super(p, id);
		// TODO Auto-generated constructor stub
		novaPosicao(Principal.getRandomNumber(0, Principal.WIDTH), Principal.getRandomNumber(0, Principal.WIDTH));
		
		switch(tipo) {
		case 1:

			break;
		case 2:
			corRI = 1.0;
			corGI = 1.0;
			corBI = 0.0;
			width = 20;
			corRV = 6;
			corGV = 6;
			corBV = 0;
			break;
		}
		this.tipo = tipo;

		
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		corR = corR - corRV;
		corG = corG - corGV;
		corB = corB - corBV;
		
		if(corR < 1)
			corR = 255;
		if(corB < 1)
			corB = 255;
		if(corG < 1)
			corG = 255;
		
		
		// TODO Auto-generated method stub
		color = new Color((int)(corR * corRI), (int)(corG * corGI), (int)(corB * corBI));
		
		// new Color(corR, (int)(255*((shield)/defShield)*cor) ,(int)(255*((shield)/defShield)*cor));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillPolygon(getPolygon());
		
	}

	
	
//	public Rectangle getBounds() {
//		// TODO Auto-generated method stub
//		return new Rectangle ((int)x, (int)y, (int)width, (int)height);
//	}
	

	
	public int getTipo() {
		return tipo;
	}

}

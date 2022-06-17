package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.LinkedList;

import Principal.Principal;

public class Flag extends GameObject{
	Color flag = new Color(255, 255, 255);
	private int width = 32;
	private int height = 32;
	
	private int corR = 255;
	private int corG = 255;
	private int corB = 255;
	private double corRI = 1;
	private double corGI = 1;
	private double corBI = 1;
	private int corRV = 2;
	private int corGV = 4;
	private int corBV = 6;
	
	public Flag(Polygon p, ObjectId id) {
		super(p, id);
		novaPosicao(Principal.getRandomNumber(0, Principal.WIDTH), Principal.getRandomNumber(0, Principal.HEIGHT));
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		corR = corR - corRV;
		corG = corG - corGV;
		
		if(corR < 1)
			corR = 255;
		if(corB < 1)
			corB = 255;
		if(corG < 1)
			corG = 255;
		
		flag = new Color((int)(corR * corRI), (int)(corG * corGI), (int)(corB * corBI));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(flag);
		g.fillPolygon(getPolygon());
		
	}

}

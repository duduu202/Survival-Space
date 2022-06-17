package Objects;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.LinkedList;

import Principal.Principal;


public class Block extends GameObject {
	
	double width = 32;
	double height = 32;
	public Block(Polygon p, ObjectId id) {
		super(p, id);
	}
	public Block(double x, double y, Polygon p, ObjectId id) {
		super(p, id);
		novaPosicao(x, y);
	}
	
	public void tick(LinkedList<GameObject> object) 
	{
		// TODO Auto-generated method stub
		
	}

	
	public void render(Graphics g) 
	{
		g.setColor(Color.white);
		g.drawPolygon(getPolygon());
		
	}

	//Substituido por metodos da super classe
//	@Override
//	public Polygon getPolygon() {
//		return Principal.RectangleToPolygon(new Rectangle((int)x, (int)y, (int)width, (int)height));
//	}
	
//	public Rectangle getBounds() {
//		return new Rectangle((int)x, (int)y, 32, 32);
//	}





	


	


}
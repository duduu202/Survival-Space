package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.LinkedList;
import Principal.Principal;

public class Bullet extends GameObject{
	float velMax = 20f;
	float divForX = 0;
	float divForY = 0;
	public int dano = 5;
	Color c = new Color(255,100,255);
	public Bullet(double x, double y, Polygon p, ObjectId id, float radians) {
		super(p, id);
		novaPosicao(x, y);
		rotatePolygon(Math.toDegrees(radians));
		angle = (float)(radians - Math.toRadians(90));
		
		velx = 10;
        divForX = (float) Math.cos(angle); //Força dividida de acordo com o coseno
        divForY = (float) Math.sin(angle); //Força dividida de acordo com o seno
	}
	
	
	
	@Override
	public void tick(LinkedList<GameObject> object) {
		addPosicao(velMax*divForX, velMax*divForY);
		if(getCenterX() > Principal.WIDTH) {
			Principal.removeObjHandler(this);
		}
		if(getCenterY() > Principal.HEIGHT) {
			Principal.removeObjHandler(this);
		}
		if(getCenterX() < 0) {
			Principal.removeObjHandler(this);
		}
		if(getCenterY() < 0) {
			Principal.removeObjHandler(this);
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(c);
		g.fillPolygon(getPolygon());
		
	}

}

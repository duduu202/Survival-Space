package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.LinkedList;
import PhysicsEngine.CollisionDetection;
import Principal.Principal;



public class AI extends GameObject{
	private float width = 25, height = 25;
	private int dificuldade = 0;
	private double velocidade;
	private float velMax = 10;
	private float freiar = 0.9f;
	CollisionDetection cd;
	
	public AI(Polygon p, ObjectId id, int dificuldade) {
		super(p, id);
		this.dificuldade = dificuldade;
		
		switch(dificuldade) {
		case 1:
			massa = 5;
			velocidade = 0.01;
			break;
		case 2:
			width = 50;
			height = 50;
			massa = 0.5f;
			velocidade = 0.03;
			break;
		case 3:
			massa = 0.1f;
			velocidade = 0.05;
			break;
		case 4:
			width = 12;
			height = 12;
			massa = 0.1f;
			velocidade = 0.10;
			break;
		case 11:
			massa = 0.1f;
			velocidade = 0.50;
			velMax = 7f; //7
			freiar = 0.4f; //3
			break;
		default:
			massa = 0;
			velocidade = 0.1;
		}
		
	}
	public AI(double x, double y, Polygon p, ObjectId id, int dificuldade) {
		super(p, id);
		novaPosicao(x, y);
		this.dificuldade = dificuldade;
		switch(dificuldade) {
		case 1:
			massa = 5;
			velocidade = 0.01;
			break;
		case 2:
			width = 50;
			height = 50;
			massa = 0.5f;
			velocidade = 0.03;
			break;
		case 3:
			massa = 0.1f;
			velocidade = 0.05;
			break;
		case 4:
			width = 12;
			height = 12;
			massa = 0.1f;
			velocidade = 0.10;
			break;
		case 11:
			massa = 0.1f;
			velocidade = 0.50;
			velMax = 7f; //7
			freiar = 0.4f; //3
			break;
		default:
			massa = 0;
			velocidade = 0.1;
		}
		
	}
	
	@Override
	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		
		if(getVelocity() > velMax) {
			velx = velx * freiar;
			vely = vely * freiar;
		}
		
//		x += velx;
//		y += vely;
		addPosicao(velx, vely);

		hunt(object);
	}
	
	private void hunt(LinkedList<GameObject> object) {
		GameObject tempOb;
		
		for(int i=0; i < object.size(); i++) { //Seleciona o objeto armazenado no handler
			tempOb = object.get(i); 
			Colisao(object.get(i));
			
			
			if(tempOb.getId() == ObjectId.Player) { //se o objeto for o player
				Polygon tempPol = tempOb.getPolygon();
				//Rectangle IA = this.getBounds();
				
				float diferencaX = (float) (tempOb.getCenterX() - getCenterX());
				float diferencaY = (float) (tempOb.getCenterY() - getCenterY());
				
                //float distancia = (diferencaX * diferencaX) + (diferencaY * diferencaY);
                //distancia = (float) Math.sqrt(distancia);
				
                float anguloRadians = (float) Math.atan2(diferencaY, diferencaX); //Algulo em radianos
                float divForX = (float) Math.cos(anguloRadians); //Força dividida de acordo com o coseno
                float divForY = (float) Math.sin(anguloRadians); //Força dividida de acordo com o seno
                
                velx += velocidade*divForX / massa;
                vely += velocidade*divForY / massa;
                
                
			}
		}
	}
	private void Colisao(GameObject tempOb) {

		if(tempOb.getId() == ObjectId.Bullet) {
			if(cd.decColAdvanced(tempOb.getPolygon(), getPolygon())) {
				Principal.removeObjHandler(this);
				Principal.removeObjHandler(tempOb);
			}
			
		}
		
	}
	public float getVelocity() {
		float vx = velx;
		float vy = vely;
		
		if(velx < 0) {
			vx = -velx;
		}
		if(vely < 0) {
			vy = -vely;
		}
		return vx + vy;
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		
		g.fillPolygon(getPolygon()); //Desenhar player
		
	}

//	@Override
//	public Polygon getPolygon() {
//		// TODO Auto-generated method stub
//		return Principal.RectangleToPolygon(new Rectangle ((int)x, (int)y, (int)width, (int)height));
//	}
//	
//	public Rectangle getBounds() {
//		// TODO Auto-generated method stub
//		return new Rectangle ((int)x, (int)y, (int)width, (int)height);
//	}

}

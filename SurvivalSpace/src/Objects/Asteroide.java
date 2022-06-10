package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.LinkedList;

import PhysicsEngine.CollisionDetection;
import Principal.Principal;

public class Asteroide extends GameObject {
	//Polygon p;
	public boolean col;
	CollisionDetection cd;
	GameObject tempOb;
	Asteroide tAst;
	Player player;
	int scaleVida = 5;
	double x,y;
//	public static int numAsteroides;
	
	public Asteroide(double x, double y, Polygon p, ObjectId id) {
		super(p, id);
		//numAsteroides++;
		
//		this.x = x;
//		this.y = y;
//		this.p = p;
//		this.p.translate((int)x, (int)y);
		novaPosicao(x,y);
	}
	public Asteroide(ObjectId id, int scaleVida) {
		super(id);
		//numAsteroides++;
		this.scaleVida = scaleVida;
		int local = Principal.getRandomNumber(-1, 4);
		switch(local) {
		case 0: x = -100; y = Principal.getRandomNumber(0, Principal.HEIGHT);
		break;
		case 1: x = Principal.WIDTH+100; y = Principal.getRandomNumber(0, Principal.HEIGHT);
		break;
		case 2: y = -100; x = Principal.getRandomNumber(0, Principal.WIDTH);
		break;
		case 3: y = Principal.HEIGHT+100; x = Principal.getRandomNumber(0, Principal.WIDTH);
		break;
		}
		gerarAsteroide();
		

		
		//novaPosicao(x,y);
	}
	public void gerarAsteroide() {
		xpoints = new double[36];
		ypoints = new double[36];
		
		int minRange = 5;
		int range = 7;

	    for (int v = 0; v < 36; v++) {
	    	xpoints[v] = ((Math.cos(v * 10 * Math.PI / 180))) * Principal.getRandomNumber(minRange, range);
	    	ypoints[v] = ((Math.sin(v * 10 * Math.PI / 180))) * Principal.getRandomNumber(minRange, range);
	    }
	    

	    scalePoints(scaleVida);
	    novaPosicao(x, y);
	    
	    while(velx == 0 && vely == 0) {
	    	addVel(Principal.getRandomNumber(-3, 3), Principal.getRandomNumber(-3, 3));
	    }
	}
	
	public void addVel(int vx, int vy) {
		velx += vx;
		vely += vy;
	}
	
	int contador = 0;
	@Override
	public void tick(LinkedList<GameObject> object) {
		detectarColisao(object);
		rotatePolygon((velx+vely)/4);
		// TODO Auto-generated method stub
		addPosicao((int)velx, (int)vely);
		x += velx;
		y += vely;
		if(Principal.foraTela((GameObject)this, 200)) {
			Principal.removeObjHandler(this);
			//numAsteroides--;
		}
		//System.out.println(Principal.getCenterX(p));
		
		contador++;
	}
	
	private void detectarColisao(LinkedList<GameObject> object) {
		col = false;
		for(int i=0; i < object.size(); i++) { //Seleciona o objeto armazenado no handler
			tempOb = object.get(i); 
			
			
			if(Principal.distancia(tempOb, this) < 300) {//Otimização
				if(tempOb.getId() == ObjectId.Asteroid && tempOb != this) {
					tAst = (Asteroide) tempOb;
					if(cd.decColAdvanced(tAst.getPolygon(), getPolygon())) {
						if(tAst.scaleVida < this.scaleVida) {
							CollisionDetection.corrigirColisao(tempOb, this);
						}
						else {
							CollisionDetection.corrigirColisao(this, tempOb);
						}
						
						x = getCenterX();
						y = getCenterY();
						col = true;
					}
					
					//tAst.col = cd.decColAdvanced(tAst.getPoly(), p);
				}
				if(tempOb.getId() == ObjectId.Player) {
					player = (Player) tempOb;
					Polygon pp = player.getPolygon();
					//System.out.println(pp.ypoints[3]);
					if(cd.decColAdvanced(pp, getPolygon())) {
						col = true;
					}
					//tAst.col = cd.decColAdvanced(tAst.getPoly(), p);
				}
				if(tempOb.getId() == ObjectId.Bullet) {
					if(cd.decColAdvanced(tempOb.getPolygon(), getPolygon())) {
						if(scaleVida <= 1) {
							//numAsteroides--;
							Principal.removeObjHandler(this);
						}
						else {
							Bullet b = (Bullet) tempOb;
							scaleVida -= b.dano;
							gerarAsteroide();
							Principal.removeObjHandler(tempOb);
						}

						col = true;
					}
					
				}
			}
		}
	}
	

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
//		if(col)
//			g.setColor(Color.RED);
//		else
			g.setColor(Color.GRAY);
		
		//g.drawPolygon(getPolygon());
		g.fillPolygon(getPolygon());
		
		//g.drawLine((int)Principal.getCenterX(xpoints), (int)Principal.getCenterY(ypoints), 0, 0);
		//g.drawPolygon(p);
	}

}

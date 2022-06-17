package PhysicsEngine;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.LinkedList;

import Objects.Bonus;
import Objects.GameObject;
import Objects.ObjectId;
import Principal.Principal;

public class CollisionDetection {
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	//Ultima colusão x e y
	static float ucX; //////////
	static float ucY;
	
	public static boolean detectarColisaoRec(Rectangle p,Rectangle r) {
		//p = retangulo 1
		//r = retangulo 2 (bloco)
		
		boolean colDetec = false;
																
		if((p.getCenterY()-p.height/2) < (r.getCenterY()+r.height/2) && //y //Cima
				(p.getCenterY()+p.height/2) > (r.getCenterY()-r.height/2) && //y //Baixo
				(p.getCenterX()-p.width/2) < (r.getCenterX()+r.width/2) && //x //Esquerda
				(p.getCenterX()+p.width/2) > (r.getCenterX()-r.width/2)) { //x //Direita
			colDetec = true;
			
		}
		return colDetec;
	}
	

	public static void corrigirColisao(GameObject a, GameObject b) {
		
			//Rectangle IA = this.getBounds();
				
			float diferencaX = (float) (a.getCenterX() - b.getCenterX());
			float diferencaY = (float) (a.getCenterY() - b.getCenterY());
				
            //float distancia = (diferencaX * diferencaX) + (diferencaY * diferencaY);
            //distancia = (float) Math.sqrt(distancia);
			
            float anguloRadians = (float) Math.atan2(diferencaY, diferencaX); //Algulo em radianos
            float divForX = (float) Math.cos(anguloRadians); //Força dividida de acordo com o coseno
            float divForY = (float) Math.sin(anguloRadians); //Força dividida de acordo com o seno
            
            
            a.addPosicao(divForX*10, divForY*10);
	}
	
	public static boolean decColAdvanced(Polygon p,Polygon r) {
		boolean colDetec = false;
		
		float centerPX = Principal.getCenterX(p);
		float centerPY = Principal.getCenterY(p);
		
		if(r.contains(centerPX, centerPY)) {
			return true;
		}
		centerPX = Principal.getCenterX(r);
		centerPY = Principal.getCenterY(r);
		
		if(p.contains(centerPX, centerPY)) {
			return true;
		}
		
		float x1, y1, x2, y2, x3, y3, x4, y4;
		
		//Dessa maneira é impossível definir a linha final
//		int[] px = p.xpoints;
//		int[] py = p.ypoints;
//		int[] rx = r.xpoints;
//		int[] ry = r.ypoints;
		
		int[] px = new int[p.npoints+1];
		int[] py = new int[p.npoints+1];
		int[] rx = new int[r.npoints+1];
		int[] ry = new int[r.npoints+1];	
		
		
		System.arraycopy(p.xpoints, 0, px, 0, p.npoints);
		System.arraycopy(p.ypoints, 0, py, 0, p.npoints);
		System.arraycopy(r.xpoints, 0, rx, 0, r.npoints);
		System.arraycopy(r.ypoints, 0, ry, 0, r.npoints);
		
		//Definindo linha final:
		px[px.length-1] = px[0];
		py[py.length-1] = py[0];
		rx[rx.length-1] = rx[0];
		ry[ry.length-1] = ry[0];
		
		for(int i = 0; i<px.length-1; i++) {
			x1 = px[i];
			y1 = py[i];
			x2 = px[i+1];
			y2 = py[i+1];
			for(int j = 0; j<rx.length-1; j++) {
				x3 = rx[j];
				y3 = ry[j];
				x4 = rx[j+1];
				y4 = ry[j+1];
				
				
		        float den = ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));
		        float t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / den;
		        float u = -((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / den;
		        
		        if (t > 0 && t < 1 && u > 0 && u < 1) {
		        	//System.out.println("den: "+den);//System.out.println("u: "+u);System.out.println("t: "+t);System.out.println("x1: "+x1+ " y1: "+y1+ " x2: "+x2+ " y2: "+y2+ "| x3: "+x3+ " y3: "+y3+ " x4: "+x4+ " y4: "+y4);
		        	//Coordenadas da interseção (Local da colisão):
		        	//float intersecaoX, intersecaoY; //////////
		        	ucX = x1 + t * (x2 - x1);
		        	ucY = y1 + t * (y2 - y1);
		        	colDetec = true;
		        }
			}
		}
		return colDetec;
	}







}

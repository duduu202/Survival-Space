package GamePolygons;

import java.awt.Polygon;

import Principal.Principal;

public class Polygons {
	public static Polygon getPlayer() {
		int width = 10;
		int height = 15;
		int scale = 1;
		int contraste = 10;
		Polygon p = new Polygon();
		
		p.addPoint(0, 0);//começo
		p.addPoint(width/2, -contraste); //bico
		p.addPoint(width, 0); //superior direito
		p.addPoint(width, height-contraste);
		
		//ponta direita
		p.addPoint(width+contraste, height+contraste);
		//p.addPoint(width-contraste, height-contraste);//voltar
		
		p.addPoint(width/2, height-contraste); //abaixo, meio
		//Ponta esquerda
		p.addPoint(0-contraste, height+contraste);
		p.addPoint(0, height-contraste);//voltar
		
		
		//p.addPoint(0, 0);//final (não necessário, ele cria automaticamente)
		
		Principal.scalePoly(p,scale);
		//p = makeRect(width, height);
		return p;
	}
	
	public static Polygon getBullet() {
		int width = 10;
		int height = 15;
		int scale = 1;
		int contraste = 10;
		Polygon p = new Polygon();
		
		p.addPoint(0, 0);//começo
		p.addPoint(width/2, -contraste); //bico
		p.addPoint(width, 0); //superior direito
		p.addPoint(width, height-contraste);
		
//		//ponta direita
//		p.addPoint(width+contraste, height+contraste);
//		//p.addPoint(width-contraste, height-contraste);//voltar
//		
//		p.addPoint(width/2, height-contraste); //abaixo, meio
//		//Ponta esquerda
//		p.addPoint(0-contraste, height+contraste);
		p.addPoint(0, height-contraste);//voltar
		
		
		//p.addPoint(0, 0);//final (não necessário, ele cria automaticamente)
		
		Principal.scalePoly(p,scale);
		//p = makeRect(width, height);
		return p;
	}
	
	public static Polygon getBonus(int tipo) {
		int width = 0;
		int height = 0;
		
		switch(tipo) {
		case 1:
			width = 10;
			height = 10;
			break;
		case 2:
			width = 10;
			height = 20;
			break;
		}
		Polygon p = new Polygon();
		p = makeRect(width, height);
		return p;
	}
	public static Polygon getBonus2() {
		int width = 10;
		int height = 10;
		Polygon p = new Polygon();
		p = makeRect(width, height);
		return p;
	}
	public static Polygon getFlag() {
		int width = 32;
		int height = 32;
		Polygon p = new Polygon();
		p = makeRect(width, height);
		return p;
	}
	public static Polygon getBlock() {
		Polygon p = new Polygon();
		int width = 32;
		int height = 32;
		p = makeRect(width, height);
		return p;
	}
	public static Polygon getIA(int dificuldade) {
		Polygon p = new Polygon();
		int width = 32;
		int height = 32;
		
		switch(dificuldade) {
		case 1:
			break;
		case 2:
			width = 50;
			height = 50;
			break;
		case 3:
			break;
		case 4:
			width = 12;
			height = 12;
			break;
		default:
			width = 32;
			height = 32;
		}
		p = makeRect(width, height);
		
		return p;
	}
	private static Polygon makeRect(int w, int h) {
		Polygon p = new Polygon();
		p.addPoint(0, 0);p.addPoint(w, 0);
		p.addPoint(w, h);p.addPoint(0, h);
		return p;
	}
}

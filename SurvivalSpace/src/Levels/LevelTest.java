package Levels;

import java.awt.Polygon;

import GamePolygons.Polygons;
import Objects.Asteroide;
import Objects.Block;
import Objects.Bonus;
import Objects.ObjectId;
import Objects.Player;
import Objects.AI;
import Principal.Handler;
import Principal.Principal;

public class LevelTest {
	Handler h;
	public LevelTest(Handler h) {
		this.h = h;
	}
	public void level1() {
		h.addObject(new Player(Polygons.getPlayer(), ObjectId.Player));
		
		for(int xx = 0; xx < Principal.WIDTH+32; xx += 32)
				h.addObject(new Block (xx, Principal.HEIGHT-32, Polygons.getBlock(), ObjectId.Block));
		
		h.addObject(new Block (100, 200, Polygons.getBlock(), ObjectId.Block));
		h.addObject(new Block (100, 250, Polygons.getBlock(), ObjectId.Block));
		h.addObject(new Block (230, 190, Polygons.getBlock(), ObjectId.Block));
		h.addObject(new Block (180, 90, Polygons.getBlock(), ObjectId.Block));
		
		h.addObject(new AI(Principal.WIDTH-200, Principal.HEIGHT-100, Polygons.getIA(1), ObjectId.AI, 1));
		
		h.addObject(new AI(Principal.WIDTH-100, Principal.HEIGHT-100, Polygons.getIA(2), ObjectId.AI, 2));
		h.addObject(new AI(Principal.WIDTH-100, Principal.HEIGHT-200, Polygons.getIA(2), ObjectId.AI, 2));
		h.addObject(new AI(Principal.WIDTH-100, Principal.HEIGHT-400, Polygons.getIA(2), ObjectId.AI, 2));
		h.addObject(new AI(Principal.WIDTH-100, Principal.HEIGHT-500, Polygons.getIA(2), ObjectId.AI, 2));
		h.addObject(new AI(Principal.WIDTH-100, Principal.HEIGHT-600, Polygons.getIA(2), ObjectId.AI, 2));
		h.addObject(new AI(Principal.WIDTH-100, Principal.HEIGHT-700, Polygons.getIA(2), ObjectId.AI, 2));
		h.addObject(new AI(Principal.WIDTH-100, Principal.HEIGHT-800, Polygons.getIA(2), ObjectId.AI, 2));
		

		
		h.addObject(new AI(Principal.WIDTH-100, Principal.HEIGHT-400, Polygons.getIA(3), ObjectId.AI, 3));
		h.addObject(new AI(Principal.WIDTH-300, Principal.HEIGHT-500, Polygons.getIA(3), ObjectId.AI, 3));
		
		h.addObject(new AI(Principal.WIDTH-200, Principal.HEIGHT-100, Polygons.getIA(3), ObjectId.AI, 4));
		
		h.addObject(new AI(Principal.WIDTH-100, Principal.HEIGHT-700, Polygons.getIA(11), ObjectId.AI, 11));
		h.addObject(new AI(Principal.WIDTH-150, Principal.HEIGHT-700, Polygons.getIA(11), ObjectId.AI, 11));
		
		h.addObject(new Bonus(Polygons.getBonus(1), ObjectId.Bonus, 1));
		h.addObject(new Bonus(Polygons.getBonus(1), ObjectId.Bonus, 1));
		h.addObject(new Bonus(Polygons.getBonus(1), ObjectId.Bonus, 1));
		h.addObject(new Bonus(Polygons.getBonus(1), ObjectId.Bonus, 1));
		h.addObject(new Bonus(Polygons.getBonus(1), ObjectId.Bonus, 1));
		
		h.addObject(new Bonus(Polygons.getBonus(2), ObjectId.Bonus, 2));
		h.addObject(new Bonus(Polygons.getBonus(2), ObjectId.Bonus, 2));
		h.addObject(new Bonus(Polygons.getBonus(2), ObjectId.Bonus, 2));
		//for(int i = 0; i < 3000; i++){addObject(new Bonus(500,500, ObjectId.Bonus, 2));}
			

		
		//////////////////////////////
		Polygon p = new Polygon();
		p.addPoint(300, 300);p.addPoint(350, 300);p.addPoint(350, 350);p.addPoint(310, 350);
		Asteroide a = new Asteroide (200, 400, p, ObjectId.Asteroid);
		a.addVel(1, 0);
		h.addObject(a);
		
		Polygon p2 = new Polygon();
		p2.addPoint(302, 312);p2.addPoint(452, 302);p2.addPoint(352, 355);p2.addPoint(312, 342);p2.addPoint(252, 242);
		Asteroide b = new Asteroide (300, 300, p2, ObjectId.Asteroid);
		b.addVel(-1, 0);
		h.addObject(b);
		
		p2 = new Polygon();
		p2.addPoint(302, 312);p2.addPoint(452, 302);p2.addPoint(352, 355);p2.addPoint(312, 342);p2.addPoint(352, 242);p2.addPoint(252, 292);
		Asteroide c = new Asteroide (300, 400, p2, ObjectId.Asteroid);
		h.addObject(c);
		
	}
	public void playerOnly() {
		for(int xx = 0; xx < Principal.WIDTH+32; xx += 32)
			h.addObject(new Block (xx, Principal.HEIGHT-20, Polygons.getBlock(), ObjectId.Block));
		
		for(int xx = 0; xx < Principal.WIDTH+32; xx += 32)
			h.addObject(new Block (xx, 20, Polygons.getBlock(), ObjectId.Block));
		
		for(int xx = 0; xx < Principal.WIDTH+32; xx += 32)
			h.addObject(new Block (20, xx, Polygons.getBlock(), ObjectId.Block));
		
		for(int xx = 0; xx < Principal.WIDTH+32; xx += 32)
			h.addObject(new Block (Principal.WIDTH-20, xx, Polygons.getBlock(), ObjectId.Block));
		
		h.addObject(new Player(Principal.WIDTH/2,Principal.HEIGHT/2,Polygons.getPlayer(), ObjectId.Player));
		
		h.addObject(new Block (Principal.WIDTH/2-300,Principal.HEIGHT/2, Polygons.getPlayer(), ObjectId.Block));
		
		Polygon p2 = new Polygon();
		p2.addPoint(302, 312);p2.addPoint(452, 302);p2.addPoint(352, 355);p2.addPoint(312, 342);p2.addPoint(252, 242);
		Asteroide b = new Asteroide (300, 300, p2, ObjectId.Asteroid);
		b.addVel(-1, 0);
		h.addObject(b);
		
		for(int i = 0; i<10; i++)
			h.addObject(new Asteroide(ObjectId.Asteroid, Principal.getRandomNumber(1, 10)));
		
		
		
	}
	public void playerOnlyForReal() {
		h.addObject(new Player(Principal.WIDTH/2,Principal.HEIGHT/2,Polygons.getPlayer(), ObjectId.Player));
	}
}

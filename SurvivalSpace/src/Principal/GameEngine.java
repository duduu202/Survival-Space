package Principal;

import Objects.ObjectId;
import GamePolygons.Polygons;
import Objects.AI;
import Objects.Asteroide;
import Objects.Bonus;
import Objects.Flag;

public class GameEngine {
	Handler h;
	Asteroide a;
	AI ai;
	
	int cada;
	public GameEngine(Handler handler) {
		this.h = handler;
		cada = 1;
	}
	

	
	public void gameEngineTick() {
		
		if(cada(5)) {
			spawnAsteroide();
		}
//		if(cada(60)) {
//			spawnAI();
//		}
//		if(cada(60)) {
//			spawnBonus();
//		}
//		
//		if(cada(360*4)) {
//			spawnFlag();
//		}

		cada++;
	}
	
	boolean flag = false;
	private void spawnFlag() {
		if(!flag) {
			h.addObject(new Flag(Polygons.getFlag(), ObjectId.Flag));
			flag = true;
		}
	}
	//Ex: A cada 20 ticks, boolean = true;
	private boolean cada(float ticks){
		boolean eDivisivel = cada % ticks == 0;
		return eDivisivel;
	}
	
	private void spawnBonus() {
		if(h.contObjets(ObjectId.Bonus) <= 10) {
			h.addObject(new Bonus(Polygons.getBonus(2), ObjectId.Bonus, 2));
			//h.addObject(new Bonus(Polygons.getBonus(1), ObjectId.Bonus, 1));
		}
		
	}

	private void spawnAI() {
		if(h.contObjets(ObjectId.AI) < 100) {
			h.addObject(new AI(Principal.getRandomNumber(0, Principal.WIDTH), Principal.getRandomNumber(0, Principal.HEIGHT), Polygons.getIA(2), ObjectId.AI, 2));
		}
		
	}


	private void spawnAsteroide() {
		if(h.contObjets(ObjectId.Asteroid) < 75) {
			h.addObject(new Asteroide(ObjectId.Asteroid, Principal.getRandomNumber(1, 20)));
		}
	}

}

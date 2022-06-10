package Principal;

import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Iterator;
import java.util.LinkedList;

import GamePolygons.Polygons;
import Levels.LevelTest;
import Objects.Asteroide;
import Objects.Block;
import Objects.Bonus;
import Objects.Flag;
import Objects.GameObject;
import Objects.ObjectId;
import Objects.Player;
import Objects.AI;


public class Handler 
{
	
	static public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	
	public void tick() 
	{
		for(int i=0; i < object.size(); i++) {
			tempObject = object.get(i);
//			if(tempObject instanceof Block) {
//				tempObject.rotatePolygon(1);
//				
//			}
			tempObject.tick(object);
			//tempObject.
			
		}

	}
	
	public void render (Graphics g) {
		//System.out.println("hendler render");
		

		
		for(int i=0; i < object.size(); i++) {
			tempObject = object.get(i);
			
			tempObject.render(g);
		}

	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void clearAll() {
		this.object.clear();
	}
	
	public int contObjets(ObjectId id) {
		int cont = 0;
		for(int i = 0; i<this.object.size(); i++) {
			GameObject t = this.object.get(i);
			if(t.getId() 
					== id) {
				cont++;
			}
		}
		return cont;
	}
	
	/*
	 * Criar level aqui :)
	 */
	public void creatLevel() {
		LevelTest t = new LevelTest(this);
		//t.level1();
		t.playerOnly();
		//t.playerOnlyForReal();
	}
	
}

/*
	Por algum motivo, mesmo com otimizações usando o iterator, e metodos do linkedlist usando LAST e FIST, o codigo não teve muita melhora
package Principal;

import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Iterator;
import java.util.LinkedList;

import GamePolygons.Polygons;
import Levels.LevelTest;
import Objects.Asteroide;
import Objects.Block;
import Objects.Bonus;
import Objects.Flag;
import Objects.GameObject;
import Objects.ObjectId;
import Objects.Player;
import Objects.AI;


public class Handler 
{
	
	static public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	static private Iterator it;
	
	static public LinkedList<GameObject> tObject = new LinkedList<GameObject>();
	static public LinkedList<GameObject> tRemover = new LinkedList<GameObject>();
	private static void atualizarLista() {
		while(!tObject.isEmpty()) {
			object.addLast(tObject.getFirst());
			tObject.removeFirst();
		}
		
		while(!tRemover.isEmpty()) {
			object.remove(tRemover.getFirst());
			tRemover.removeFirst();
		}
	}
	public void tick() 
	{
		it = object.iterator();
//		for(int i=0; i < object.size(); i++) {
//			tempObject = object.get(i);
////			if(tempObject instanceof Block) {
////				tempObject.rotatePolygon(1);
////				
////			}
//			tempObject.tick(object);
//			//tempObject.
//			
//		}
		
		while(it.hasNext()) {
			tempObject = (GameObject) it.next();
			tempObject.tick(object);
		}
		atualizarLista();
	}
	
	public void render (Graphics g) {
		//System.out.println("hendler render");
		
		it = object.iterator();
		
//		for(int i=0; i < object.size(); i++) {
//			tempObject = object.get(i);
//			
//			tempObject.render(g);
//		}
		while(it.hasNext()) {
			tempObject = (GameObject) it.next();
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		//this.object.add(object);
		tObject.add(object);
	}
	
	public void removeObject(GameObject object) {
		//this.object.remove(object);
		tRemover.add(object);
	}
	
	public void clearAll() {
		this.object.clear();
	}
	
	public int contObjets(ObjectId id) {
		Iterator it = object.iterator();
		int cont = 0;
		while(it.hasNext()) {
			GameObject t = (GameObject) it.next();
			if(t.getId() 
					== id) {
				cont++;
			}
		}
		return cont;
	}
	
	public void creatLevel() {
		LevelTest t = new LevelTest(this);
		//t.level1();
		t.playerOnly();
		//t.playerOnlyForReal();
	}
	
}


*/
package Principal;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.sun.source.doctree.SinceTree;

import Objects.GameObject;
import Objects.ObjectId;
import Objects.Player;
import Objects.AI;
import GameInput.Input;
//import GameInput.Input;
import GameInput.InputHandler;
import GameInput.InputSet;
import GameInput.Keyboard;

/*
 * Com o canvas é impossível rotacionar a "tela" (canvas) sem atrapalahr na física, pois o
 * x e o y permanecem o mesmo. Por isso, para rotacionar objetos eu vou usar a classe Polygon,
 * Com ela eu posso criar as formas geometricas e rotacionar o x e o y dos vertices!
 * Isso não interfere na física do jogo e cumpre o objetivo de rotacionar objetos
 */

public class Principal extends Canvas implements Runnable{
	
	//JLabel label;
	
	private static final long serialVersionUID = 988601718953775631L;
	
	private static boolean running = false;
	private Thread thread;
	
	public static int WIDTH, HEIGHT;
	
	private static Window window;
	private static Keyboard keyboard;
	private static InputSet inputSet;
	private static boolean win = false;
	private static Component canvas;
	//Object
	//O handler feito em sua propria classe serve para "segurar" todos os objetos de uma vez
	static Handler handler;
	static GameEngine ge;
	
	Principal(){

		
	}
	private void init() {
		System.out.println("init");
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		canvas = window.components[0];
		

		
		inputSet = new InputSet();
		keyboard = new Keyboard(inputSet);
		addKeyListener(keyboard); //Adicionando KeyListener de outra classe
		handler = new Handler();
		
		ge = new GameEngine(handler);
		
		handler.creatLevel();
	}
	
	public void restart() {
		thread.interrupt();
		running = false;
		handler.clearAll();
		handler = new Handler();
		start();
	}
	public synchronized void start() {
		System.out.println("start");
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	

	
	public void run() 
	{
		System.out.println("run");

		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		
		float adaptacao = 1;
		
		
		

		System.out.println("variaveis criadas (run())");
		
		
		//Gameloop
		//tick() e render() são independentes
		//tick sempre atualizará 60 vezes por segunto.
		//Render pode atualizar quantas vezes quiser sem interferir na
		//frequência de atualização do tick
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if(delta > 60*adaptacao) {
				System.out.println("Lag multo alto! " + delta + "\nDiminundo ticks!");
				delta = 1;
				ns *= 2;
				adaptacao /= 2;
			}

			while(delta >= 1) {
				tick();
				updates++;
				delta--;
				
			}
			render();
			frames++;
			
			if(frames > 400 && ns > (1000000000 / amountOfTicks)) {
				System.out.println("Lag normalizando, aumentando ticks");
				ns /= 2;
				adaptacao *= 2;
			}
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames + " TICKS: " + updates);
				//atualização de tick e render por segundo será informado no título da janela:
				window.setTitulo("Survival Space | Frames: " + new StringBuilder().append("").append(frames).toString()
						+ " Ticks: " + new StringBuilder().append("").append(updates).toString());
				frames = 0;
				updates = 0;
			}
		}
		
		if(!win) {
			restart();
		}
		else {
			render(); //Ultima renderização
		}
	}
	static public void setTituloP(String s) {
		window.setTitulo("The Game | " + s);
	}
	
	private void tick() 
	{
		//System.out.println("tick");
		ge.gameEngineTick();
		handler.tick();
//		int mouse_x=MouseInfo.getPointerInfo().getLocation().x-canvas.getLocationOnScreen().x;
//		int mouse_y=MouseInfo.getPointerInfo().getLocation().y-canvas.getLocationOnScreen().y;
//		System.out.println("mx: "+mouse_x);
//		System.out.println("my: "+mouse_y);
	}
	

	
	private void render() 
	{
		//System.out.println("render");
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		///////////////////////////////////
		//Desenhar aqui
		

		
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, 2000, 1000);
		

		
		handler.render(g);
		
		
		if(win) {
			g.setColor(Color.yellow); g.drawString("GANHOU! :D", WIDTH/2, HEIGHT/2);
		}
		/////////////////////////////////
		g.dispose();
		bs.show();
		
		
	}
	static public void setRunning(boolean set) {
		running = set;
	}
	
	public static void main(String args[]) {
		System.out.println("main");
		window = new Window(1280, 720, "Survival Space", new Principal());
		window.setTitulo("Survival Space");
		
	}
	public static void win() {
		win = true;
	}
	

	
//	//KEYBOARD
	public static boolean isPressedKey(Integer input) {
		return inputSet.isPressed(input);
	}
	
	// OUTROS ///////////////////////////////////////////////////////////////////////////////////////////////////////
	public static double distancia(GameObject a, GameObject b) {
		double diferencaX = a.getCenterX() - b.getCenterX();
		double diferencaY = a.getCenterY() - b.getCenterY();
		
        double distancia = (diferencaX * diferencaX) + (diferencaY * diferencaY);
        distancia = Math.sqrt(distancia);
        
        return distancia;
	}
	public static boolean foraTela(GameObject a, int tolerancia) {
		boolean b = false;
		if(a.getCenterX() > Principal.WIDTH + tolerancia) {
			b = true;
		}
		if(a.getCenterY() > Principal.HEIGHT + tolerancia) {
			b = true;
		}
		if(a.getCenterX() < 0 - tolerancia) {
			b = true;
		}
		if(a.getCenterY() < 0 - tolerancia) {
			b = true;
		}
		return b;
	}
	public static void addObjHandler(GameObject object) {
		handler.addObject(object);
	}
	public static void removeObjHandler(GameObject object) {
		handler.removeObject(object);
	}
	public static Polygon RectangleToPolygon(Rectangle rect) {
	    int[] xpoints = {rect.x, rect.x + rect.width, rect.x + rect.width, rect.x};
	    int[] ypoints = {rect.y, rect.y, rect.y + rect.height, rect.y + rect.height};
	    return new Polygon(xpoints, ypoints, 4); 
	}
	public static float getCenterX(Polygon p) {
		float max = p.xpoints[0];
		float min = p.xpoints[0];
		for(int i = 0; i<p.npoints; i++) {
			if(max < p.xpoints[i])
				max = p.xpoints[i];
			else if(min > p.xpoints[i])
				min = p.xpoints[i];
		}
		
		float center = (max+min)/2;
	    return center; 
	}
	public static float getCenterY(Polygon p) {
		float max = p.ypoints[0];
		float min = p.ypoints[0];
		for(int i = 0; i<p.npoints; i++) {
			if(max < p.ypoints[i])
				max = p.ypoints[i];
			else if(min > p.ypoints[i])
				min = p.ypoints[i];
		}
		
		float center = (max+min)/2;
	    return center; 
	}
	
	public static double getCenterX(double xpoints[]) {
		double max = xpoints[0];
		double min = xpoints[0];
		for(int i = 0; i<xpoints.length; i++) {
			if(max < xpoints[i])
				max = xpoints[i];
			else if(min > xpoints[i])
				min = xpoints[i];
		}
		
		double center = (max+min)/2;
	    return center; 
	}
	
	public static double getCenterY(double ypoints[]) {
		double max = ypoints[0];
		double min = ypoints[0];
		for(int i = 0; i<ypoints.length; i++) {
			if(max < ypoints[i])
				max = ypoints[i];
			else if(min > ypoints[i])
				min = ypoints[i];
		}
		
		double center = (max+min)/2;
	    return center; 
	}
	
	/*
	 * Essa função é o problema, ELA NÃO GIRA OS VERTICES CORRETAMENTE!
	 * Se corrigir isso, todos os problemas são resolvidos!
	 * 
	 * Mas acho que será necessário descobri uma outra maneira de girar os polygonos
	 * 
	 * TALVEZ, uma solução seria:
	 * -Armazenar a posição original do polygono, e toda vez que você deseja girar, você gira usando as posições
	 * originais, mas não atualizando elas, ainda vai existir a distorção, mas vai ser pouco (mas perceptível)
	 * =IDEIA RUIM
	 * 
	 * -Criar um arrayX e arrayY dentro das classes que usam o GameObject (ou melhor, criar isso no GameObject)
	 * Esses arrays armazenariam os pontos x e y de todos os vertices do polygono, e esses valores poderiam ser floats!
	 * E na hora de coletar os bounds()(Pegar o Polygon) dessas classes, você cria o Polygono na hora usando esses 
	 * arrays!, assim, a distorção ao girar será minima ou irrelevante dependendo da precisão da variável do array!
	 * =IDEIA BOA (IDEIA ESCOLHIDA E FUNCIONANDO!)
	 * 
	 */
	@Deprecated
	public static void rotatePolygon(double xpoints[], double ypoints[], double angle) {
		
		double centerX = getCenterX(xpoints);
		double centerY = getCenterY(ypoints);
		
		angle = Math.toRadians(angle);
		     
		for(int i = 0; i<xpoints.length; i++) {
			
			xpoints[i] -= centerX;
			ypoints[i] -= centerY;
			
			double newx = ((xpoints[i] * Math.cos(angle) - ypoints[i] * Math.sin(angle)));
			double newy = ((xpoints[i] * Math.sin(angle) + ypoints[i] * Math.cos(angle)));
			
//			newx = p.x * cos(angle) - p.y * sin(angle)
//			newy = p.x * sin(angle) + p.y * cos(angle)
			
			xpoints[i] = (newx + centerX);
			ypoints[i] = (newy + centerY);
			
	    	

		}
		
	}
	
	
	//Funciona, MAS ele faz o scale a partir do primeiro ponto, NÃO DO CENTRO!
	public static Polygon scalePoly(Polygon p, double vezes) {
//		int cx = p.xpoints[0];
//		int cy = p.ypoints[0];
		double cx = getCenterX(p);
		double cy = getCenterY(p);
//		float diferencaX = 0;
//		float diferencaY = 0;
		
		for(int i = 0; i<p.npoints; i++) {

			p.xpoints[i] -= cx;
			p.ypoints[i] -= cy;
			
//			diferencaX += p.xpoints[i];
//			diferencaY += p.ypoints[i];
			
			p.xpoints[i] *= vezes;
			p.ypoints[i] *= vezes;
			
			
			p.xpoints[i] += cx;
			p.ypoints[i] += cy;
			
		}
//		System.out.println(diferencaX);
//		p.translate((int)diferencaX/4, (int)diferencaY/4);
		
		return p;
	}
	
	public static double[] scalePoints(double xpoints[],double ypoints[], double vezes) {
//		double cx = xpoints[0];
//		double cy = ypoints[0];
		double cx = getCenterX(xpoints);
		double cy = getCenterY(ypoints);
		
		for(int i = 0; i<xpoints.length; i++) {

			xpoints[i] -= cx;
			ypoints[i] -= cy;
//			diferencaX += p.xpoints[i];
//			diferencaY += p.ypoints[i];
			
			xpoints[i] *= vezes;
			ypoints[i] *= vezes;
			
			xpoints[i] += cx;
			ypoints[i] += cy;
			
		}
//		System.out.println(diferencaX);
//		p.translate((int)diferencaX/4, (int)diferencaY/4);
		
		return xpoints;
	}

	public static int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
	
}

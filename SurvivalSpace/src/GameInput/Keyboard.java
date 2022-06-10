package GameInput;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//import handler;

import Objects.Player;

/*
 * A classe Keyboard é focada em coletar os inputs dado pelo usuário e 
 * armazenálos no InputSet através da classe Principal.
 * 
 * Assim, qualquer outra classe que queira verificar se uma tecla foi pressionada,
 * basta perguntar para a classe Principal, desde que, esse input configurado no
 * enum Input e um else if nessa classe para verificar e adicionar esse input. Ou
 * usar o "KeyCode" da tecla se você souber o código :v
 * 
 * Atualização: Com o uso do código, o enum "Input" se torna inutil, e eu não precisarei das dezenas de if e else...
 * então me livrei do enum Input
 */
public class Keyboard extends KeyAdapter {
	private static InputSet IS;
	
	
    public Keyboard(InputSet IS) {
    	System.out.println("Keyboard");
        this.IS = IS;
    }

	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
//        if (key == KeyEvent.VK_LEFT) {
//        	inputHandler.add(Input.LEFT);
//        } else if (key == KeyEvent.VK_RIGHT) {
//        	inputHandler.add(Input.RIGHT);
//        } else if (key == KeyEvent.VK_UP) {
//        	inputHandler.add(Input.UP);
//        } else if (key == KeyEvent.VK_DOWN) {
//        	inputHandler.add(Input.DOWN);
//        } else if (key == KeyEvent.VK_SHIFT) {
//        	inputHandler.add(Input.SHIFT);
//        }
        
        IS.add(key);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
//        if (key == KeyEvent.VK_LEFT) {
//        	inputHandler.remove(Input.LEFT);
//        } else if (key == KeyEvent.VK_RIGHT) {
//        	inputHandler.remove(Input.RIGHT);
//        } else if (key == KeyEvent.VK_UP) {
//        	inputHandler.remove(Input.UP);
//        } else if (key == KeyEvent.VK_DOWN) {
//        	inputHandler.remove(Input.DOWN);
//        } else if (key == KeyEvent.VK_SHIFT) {
//        	inputHandler.remove(Input.SHIFT);
//        }
        
		IS.remove(key);
	}
}

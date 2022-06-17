package Principal;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JLabel;

import GameInput.Keyboard;

public class Window 
{
	JFrame frame = new JFrame("0");
	public Component[] components;
	
	public Window(int w, int h, String title, Principal game) 
	{
		//Informações básicas para a criação da janela
		System.out.println("window");
		game.setPreferredSize(new Dimension(w, h));
		game.setMaximumSize(new Dimension(w, h));
		game.setMinimumSize(new Dimension(w, h));
		
		//label();
		
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		
		frame.setVisible(true);
		
		
		components = frame.getComponents();
		game.start();
		
	}
	public void label() {
		JLabel label = new JLabel("teste", JLabel.CENTER);
		label.setBounds(120, 130, 360, 400);
		label.setBackground(Color.BLACK);
		label.setText("TESTEEEE LABELLL");

		frame.add(label);
	}
	
	public void setTitulo(String title) {
		frame.setTitle(title);
	}
}

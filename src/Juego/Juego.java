package Juego;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;


public class Juego extends Canvas {

	private static final long serialVersionUID = 1L;

//Dimensiones
	private static final int ANCHO = 800;
	private	static final int ALTO = 600;

	private static final String NOMBRE = "Juego";

	private static JFrame ventana;

	private Juego() {
		setPreferredSize(new Dimension(ANCHO, ALTO)); //asignacion dimensiones

		ventana = new JFrame(NOMBRE);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //termina ejecucion de la ventana al cerrar con la X
		ventana.setResizable(false); //tamaño no modificable
		ventana.setLayout(new BorderLayout()); 
		ventana.add(this, BorderLayout.CENTER);
		ventana.pack();
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

	public static void main(String[] args) {

		Juego juego  = new Juego(); //Inicia el JFrame
	}

}
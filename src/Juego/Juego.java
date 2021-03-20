package Juego;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;


public class Juego extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;

//Dimensiones
	private static final int ANCHO = 800;
	private	static final int ALTO = 600;

	private static volatile boolean corriendo = false; // boolean ver si el juego se esta ejecutando

	private static final String NOMBRE = "Juego";
	private static int aps = 0;
	private static int fps = 0;

	private static JFrame ventana;
	private static Thread thread;

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
		juego.iniciar();

	}

	private synchronized void iniciar(){
		corriendo=true;

		thread = new Thread(this, "Graficos"); //Creacion del segundo thread para graficos
		thread.start(); // Inicio del thread

	}

	private synchronized void detener(){
		corriendo = false;

		try {
			thread.join(); //Espera a que el thread termine para terminar su ejecución
		} catch (InterruptedException e) { //si falla el try, da un mensaje para consola
			e.printStackTrace();
		}

	}

	private void actualizar(){
		aps++;
	}

	private void mostrar(){
		fps++;
	}

	public void run() {
		final int NS_X_SEGUNDO = 1000000000; //NANOSEGUNDOS POR SEGUNDO
		final byte APS = 30; //ACTUALIZACIONES POR SEGUNDO
		final double NS_X_ACTUALIZACION = NS_X_SEGUNDO / APS;

		long referenciaActualizacion = System.nanoTime();
		long counter = System.nanoTime();

		double tiempoTranscurrido;
		double delta = 0;

		while(corriendo){
			final long INICIOBUCLE = System.nanoTime();


			tiempoTranscurrido = INICIOBUCLE - referenciaActualizacion;
			referenciaActualizacion = INICIOBUCLE;

			delta += tiempoTranscurrido / NS_X_ACTUALIZACION;

			while(delta>=1){
				actualizar();
				delta --;
			}
			mostrar();

			if (System.nanoTime()- counter > NS_X_SEGUNDO){
				ventana.setTitle(NOMBRE +" || APS: " + aps + " || FPS: "+ fps);
				System.out.println(NOMBRE +" || APS: " + aps + " || FPS: "+ fps);
				aps =0;
				fps =0;
				counter = System.nanoTime();
			}
		}
	}
}
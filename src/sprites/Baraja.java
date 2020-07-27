package sprites;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import util.Assets;

public class Baraja {

	public ArrayList<Carta> biblioteca, cementerio, mano;
	public int xbiblioteca, ybiblioteca;
	private Image bibliotecaimg, cementerioimg;
	private int ANCHO = 100 , ALTO = 100, imgAncho = 128, imgAlto = 128;
	
	
	public Baraja() {
		biblioteca = new ArrayList<Carta>();
		cementerio = new ArrayList<Carta>();
		mano = new ArrayList<Carta>();
		bibliotecaimg = Assets.biblioteca;
		cementerioimg = Assets.cementerio;
	}
	public void addCarta(Carta aux) {
		biblioteca.add(aux);
	}
	public void addCartas(ArrayList<Carta> aux) {
		for (Carta carta : aux) {
			biblioteca.add(carta);
		}
		
	}
	
	public void barajar() {
		Collections.shuffle(biblioteca);
	}
	
	public void robar() {
		mano.add(biblioteca.get(0));
		biblioteca.remove(0);
	}
	
	public void addCartaCementerio(Carta aux) {
		cementerio.add(aux);
	}
	public void addCartasCementerio(ArrayList<Carta> aux) {
		cementerio.addAll(aux);
	}
	
	public void Vuelta() {
		addCartas(cementerio);
		cementerio = new ArrayList<Carta>();
	}
	
	public void draw(Graphics g, int ancho, int alto) {
		g.drawImage(bibliotecaimg, 50, alto-150, 50 + ANCHO , alto-150 + ALTO, 0, 0, imgAncho, imgAlto, null);
		g.setColor(Color.BLACK);
		g.setFont(new Font ("SansSerif",Font.PLAIN,40));
		g.drawString(String.valueOf(biblioteca.size()), 125, alto-60);
		
		g.drawImage(cementerioimg, ancho-150, alto-150, ancho-150 + ANCHO , alto-150 + ALTO, 0, 0, imgAncho, imgAlto, null);
		g.setColor(Color.BLACK);
		g.setFont(new Font ("SansSerif",Font.PLAIN,40));
		g.drawString(String.valueOf(cementerio.size()), ancho-70, alto-60);
	
		
}
}

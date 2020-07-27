
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


import javax.swing.JPanel;

import sprites.Enemigo;
import sprites.Espada;
import sprites.Espadadown;
import sprites.PuntoMapa;
import util.Assets;


public class Mapa extends JPanel implements MouseListener {

	/**
	 * Alberto Galán López
	 */
	private static final long serialVersionUID = 1L;
	private int ANCHO;
	private int ALTO;

	boolean isRunning = false;

	private BufferedImage bufferedImage;
	//private InputHandler input;
	
	
	Point punto;
	private int x, y, arrastre, nanimacion, altura;
	private int maporiancho=1920, maporialto=3255;
	private boolean arriba, abajo;
	
	
	
	
	private Image mapa;
	private Espada espadaUP;
	private Espadadown espadaDOWN;
	private PuntoMapa puntomapa;
	
	

	
	

	public Mapa(int ancho, int alto) {
		setVisible(true);
		ANCHO = ancho;
		ALTO = alto-30;
		setSize(ANCHO, ALTO);
		bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		//input = new InputHandler(this);
		mapa = Assets.mapa;
		altura = 2218;
		arriba = false;
		abajo = false;
		
		espadaUP = new Espada();
		espadaDOWN = new Espadadown();
		puntomapa = new PuntoMapa();
	}


	void update() {
		// handle inputs
		
		//donde esta el puntero del raton
		punto=MouseInfo.getPointerInfo().getLocation();
		x=punto.x;
		y=punto.y-25;
		
		if(arriba) {
			if(!espadaUP.running) {
				espadaUP.running = true;
			}
			if(altura>=0) {
				altura-=2;
			}else {
				altura=0;
			}
		}
		if(abajo) {
			if(!espadaDOWN.running) {
				espadaDOWN.running = true;
			}
			if(altura<=2218) {
				altura+=2;
			}else {
				altura=2218;
			}
		}
		
		
	}



	void draw() {
		Graphics g = getGraphics();
		Graphics bbg = bufferedImage.getGraphics();
		// Fondo
		bbg.drawImage(mapa, 0, 0, ANCHO, ALTO, 0, altura, maporiancho, 1037+altura, null);
//		System.out.println(ANCHO + " " + ALTO);
	
		
		nanimacion++;
		if(nanimacion%6==0) {
			if(espadaUP.running) {
				espadaUP.frame++;
			}
			if(espadaDOWN.running) {
				espadaDOWN.frame++;
			}
			if(nanimacion >= 60) {
				nanimacion=0;
			}
		}
		espadaUP.draw(bbg);
        espadaDOWN.draw(bbg);
        
        puntomapa.draw(bbg, altura, ANCHO, ALTO);
		
		
        g.drawImage(bufferedImage, 0, 0, this);
	}



	





	@Override
	public void mouseClicked(MouseEvent e) {
		if(puntomapa.colisionClick(x, y)) {
			System.out.println("Comienza la pelea");
		}
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(espadaUP.colisionClick(e.getX(), e.getY())){
			espadaUP.running = true;
			arriba = true;
		}
		if(espadaDOWN.colisionClick(e.getX(), e.getY())){
			espadaDOWN.running = true;
			abajo = true;	
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		arriba = false;
		abajo = false;
		
	}
	
}

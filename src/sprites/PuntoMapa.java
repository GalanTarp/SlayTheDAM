package sprites;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import util.Assets;

public class PuntoMapa {
	public int xPunto, yPunto, ximage, yimage;

	public boolean color, running;
	private int ALTO = 150 , ANCHO = 150, imgAncho = 300, imgAlto = 300;
	private Image imgespada;
	
	
	
	
	
	public PuntoMapa() {
		xPunto = 410;
		yPunto = 465;
		ximage = 570; 
		yimage = 2870;
		imgespada = Assets.espada;
	}
	public void draw(Graphics g, int altura, int anchoaux, int altoaux) {
		if (altura<yimage && yimage< altura+1037) {
			yPunto = (yimage-altura)*altoaux/1037;
			g.fillOval(xPunto, yPunto, 20, 20);
		}
		
		
	}
	
	public boolean colisionClick(int xMouse, int yMouse) {
		Rectangle recBarra = new Rectangle(xPunto, yPunto, ANCHO,ALTO);
		Rectangle recMouse = new Rectangle(xMouse, yMouse, 1, 1);
		return recBarra.intersects(recMouse);
	}
}

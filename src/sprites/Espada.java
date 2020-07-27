package sprites;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import util.Assets;

public class Espada {
	public int xEspada, yEspada, frame=0;

	public boolean color, running;
	private int ALTO = 150 , ANCHO = 150, imgAncho = 300, imgAlto = 300;
	private Image imgespada;
	
	
	
	
	
	public Espada() {
		xEspada = 75;
		yEspada = 200;
		imgespada = Assets.espada;
	}
	public void draw(Graphics g) {
		if(frame>=8 ) {
			frame=0;
			running = false;
		}
		
		g.drawImage(imgespada, xEspada, yEspada, xEspada+ANCHO , yEspada+ALTO,
				frame*imgAncho, 0, (frame+1)*imgAncho, imgAlto, null);
		
	}
	
	public boolean colisionClick(int xMouse, int yMouse) {
		Rectangle recBarra = new Rectangle(xEspada, yEspada, ANCHO,ALTO);
		Rectangle recMouse = new Rectangle(xMouse, yMouse, 1, 1);
		return recBarra.intersects(recMouse);
	}
}

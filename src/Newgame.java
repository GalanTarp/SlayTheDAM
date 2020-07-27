
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import util.Assets;

public class Newgame {
	public int xBarra=150, yBarra = 400;

	public boolean color;
	private int ALTO = 50 , ANCHO = 300;
	private Image newgame = Assets.start;
	
	
	public void draw(Graphics g) {
		g.drawImage(newgame, xBarra, yBarra, xBarra+ANCHO , yBarra+ALTO, 0, 0, 860, 220, null);
		
	}
	
	public boolean colisionClick(int xMouse, int yMouse) {
		Rectangle recBarra = new Rectangle(xBarra, yBarra, ANCHO,ALTO);
		Rectangle recMouse = new Rectangle(xMouse, yMouse, 1, 1);
		return recBarra.intersects(recMouse);
	}

}

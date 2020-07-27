package sprites;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import util.Assets;

public class Carta {
	public int xCarta, yCarta, mana;
	protected Image cartaimg;
	public boolean mostrar, nueva;
	public int tipo, id;
	
	
	
	
	public Carta(int id) {
		xCarta = 600;
		yCarta = 550;
		this.id=id;
		switch (id) {
		case 0:
			cartaimg = Assets.strike;
			break;
		case 1:
			cartaimg = Assets.defend;
			break;
		case 2:
			cartaimg = Assets.bash;
			break;
		case 3:
			cartaimg = Assets.anger;
			break;
		default:
			break;
		}
	}

	

	public void draw(Graphics g, int min) {
		if (mostrar) {
			yCarta = 450;
			if(mana <= min) {
				g.setColor( new Color(0, 0, 255, 127));
				g.fillRect( xCarta+5, yCarta+5, 678/3-10, 874/3+5);
			}
			g.drawImage(cartaimg, xCarta, yCarta, xCarta + 678/3, yCarta+ 874/3, 0, 0, 678, 874, null);
			
		}else {
			yCarta = 550;
			if(mana <= min) {
				g.setColor( new Color(0, 0, 255, 127));
				g.fillRect( xCarta+5, yCarta+5, 678/3-10, 874/3+5);
			}
			
	        //things you draw after here will not be rotated
			g.drawImage(cartaimg, xCarta, yCarta, xCarta + 678/3, yCarta+ 874/3, 0, 0, 678, 874, null);
		}
		
	}
	
	public void drawseleccionada(Graphics g, int x, int y, Color color) {
		
		if(color == Color.GREEN) {
			g.setColor( new Color(0, 255, 0, 127));
			g.fillRect( x-678/8+5, y-874/8+5,  678/4-13, 874/4);
		}
		if(color == Color.RED) {
			g.setColor( new Color(255, 0, 0, 127));
			g.fillRect( x-678/8+5, y-874/8+5,  678/4-13, 874/4);
		}
		g.drawImage(cartaimg, x-678/8, y-874/8, x + 678/8, y+ 874/8,
				0, 0, 678, 874, null);
		
			
	}
	public void drawnew(Graphics g) {
		
		g.setColor( new Color(234, 190, 63, 127));
		g.fillRect( xCarta+5, yCarta+5, 678/3-10, 874/3+5);
		g.drawImage(cartaimg, xCarta, yCarta, xCarta + 678/3, yCarta+ 874/3,
				0, 0, 678, 874, null);
		
			
	}
	
	public boolean colisionClick(int xMouse, int yMouse) {
		Rectangle recCarta = new Rectangle(xCarta, yCarta, 678/3,874/3);
		Rectangle recMouse = new Rectangle(xMouse, yMouse, 1, 1);
		return recCarta.intersects(recMouse);
	}
	
	public void doAction(Personaje pj, ArrayList<Enemigo> enemigos, int i) {
		
	}
}

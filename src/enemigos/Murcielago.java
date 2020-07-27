package enemigos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import sprites.Daño;
import sprites.Enemigo;
import util.Assets;
import util.Parameters;

public class Murcielago extends Enemigo{

	private Image murcielagoimg;
	public int imgAncho = 121, imgAlto = 89;
	
	
	
	
	public  Murcielago() {
		super();
		murcielagoimg = Assets.murcielago;
		xEnemy = 750;
		yEnemy = Parameters.lineabase - 50 -89;
		vida = 40;
		vidamax = 40;
		ataque = 8;
		ANCHO = imgAncho;
		ALTO = imgAlto;
		framey=0;
		vulnerable = 0;
		daños = new ArrayList<Daño>();
	}

	public void draw(Graphics g) {
		
		if (atacando) {
			 if(frame>=5) {
				framey=0;
				frame=0;
				atacando = false;
				}
			 if(movimiento>=-30 && !vuelta) {
				 movimiento-=2;
			 }if(movimiento<-30) {
				 vuelta = true;
			 }if (vuelta) {
				 if (movimiento<=0) {
					movimiento++; 
				 }
			 }
			g.drawImage(murcielagoimg, xEnemy+movimiento, yEnemy, xEnemy+ movimiento + ANCHO, yEnemy+ ALTO, 
					(frame)*imgAncho, framey*imgAlto, (frame+1)*imgAncho,(framey+1)*imgAlto, null);
		}else if(hit) {
		 if(frame>=3) {
			hit = false;
			framey = 0;
			frame=0;
			}
			g.drawImage(murcielagoimg, xEnemy, yEnemy, xEnemy + ANCHO, yEnemy+ ALTO, 
				(frame)*imgAncho, framey*imgAlto, (frame+1)*imgAncho,(framey+1)*imgAlto, null);
	
		}
		else {
			if(frame>=5) {
				frame = 0;
				framey = 0;
			}
			g.drawImage(murcielagoimg, xEnemy, yEnemy, xEnemy + ANCHO, yEnemy+ ALTO,
					(frame)*imgAncho, 0, (frame+1)*imgAncho, imgAlto, null);	
		}
		g.setColor(Color.darkGray);
		g.fillRect(xEnemy, yEnemy-40, ANCHO, 30);
//		g.setColor(Color.LIGHT_GRAY);
//		g.fillRect(xPj+4, yPj-36, (ANCHO)/2-8, 22);
		g.setColor(Color.RED);
		g.fillRect(xEnemy+4, yEnemy-36, (ANCHO*vida)/vidamax-8, 22);
		g.setColor(Color.white);
		g.setFont(new Font ("SansSerif",Font.PLAIN,20));
		g.drawString(Integer.toString(vida)+" / "+ Integer.toString(vidamax), xEnemy+ANCHO/4, yEnemy-18);
		
		//pintar daños
		for (int i = 0; i < daños.size(); i++) {
			daños.get(i).draw(g);
			if(daños.get(i).y>400) {
				daños.remove(i);
			} 
		}
	
	}
	
	public int doAttack() {
		return ataque;
	}
}



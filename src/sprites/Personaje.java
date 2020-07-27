package sprites;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import util.Assets;
import util.Parameters;

public class Personaje {
	public int xpj, ypj, vida, frame, framey, bloq, framecorazon, direccion;
	private Image pjimg, bloqimg;
	private int vidamax, movimiento;
	public int ANCHO = 182*2 , ALTO = 123*2,  imgAncho = 182, imgAlto = 123;
	public boolean atacando, defend, hit, vuelta;
	public Baraja baraja;
	private Font ttfReal;
	public ArrayList<Daño> daños;
	public Mana mana;
	
	
	
	public Personaje() throws FontFormatException, IOException {
		pjimg = Assets.pj;
		bloqimg = Assets.escudo;
		xpj = Parameters.lineabase - ALTO;
		ypj = 250;
		vida = 80;
		vidamax = 80;
		frame=0;
		framey=0;
		framecorazon=10;
		direccion=1;
		atacando = false;
		defend = false;
		hit = false;
		vuelta = false;
		baraja = new Baraja();
		daños = new ArrayList<Daño>();
		mana = new Mana();
		loadFont();
		
//		 String fontname = "/fonts/A.ttf";
//		 InputStream is = this.getResourceAsStream(fontname);
//		    Font font = Font.createFont(Font.TRUETYPE_FONT, is);
	}

	public void draw(Graphics g) {
		if (defend) {
			if(frame>=9) {
				defend = false;
				framey = 0;
				frame = 0;
			}
			g.drawImage(pjimg, xpj, ypj, xpj + ANCHO, ypj+ ALTO, 
					(frame)*imgAncho, framey*imgAlto, (frame+1)*imgAncho,(framey+1)*imgAlto, null);
		}else if(atacando) {
			 if(frame>=6) {
				atacando = false;
				framey = 0;
				frame=0;
				movimiento = 0;
				vuelta= false;
				}
			 if(movimiento<=30 && !vuelta) {
				 movimiento+=2;
			 }if(movimiento>30) {
				 vuelta = true;
			 }if (vuelta) {
				 movimiento--;
			 }
			 
			 g.drawImage(pjimg, xpj+movimiento, ypj, xpj+ movimiento + ANCHO, ypj+ ALTO,
						frame*imgAncho, framey*imgAlto, (frame+1)*imgAncho,(framey+1)* imgAlto, null);	
		
		}else if(hit) {
			 if(frame>=4) {
				hit = false;
				framey = 0;
				frame=0;
			 }
			 g.drawImage(pjimg, xpj, ypj, xpj + ANCHO, ypj+ ALTO,
						frame*imgAncho, framey*imgAlto, (frame+1)*imgAncho,(framey+1)* imgAlto, null);	
		
		}
		else {
			if(frame>=6) {
				frame = 0;
				framey = 0;
			}
			g.drawImage(pjimg, xpj, ypj, xpj + ANCHO, ypj+ ALTO,
					frame*imgAncho, 0, (frame+1)*imgAncho, imgAlto, null);	
		}
		
		//rectangulos vida
		if(bloq>0) {
			g.setColor(Color.BLUE);
			g.fillRect(xpj-5, ypj-25, ANCHO+10, 30);
		}
		g.setColor(Color.darkGray);
		g.fillRect(xpj, ypj-20, ANCHO, 20);
		g.setColor(Color.GREEN);
		g.fillRect(xpj+4, ypj-16, (ANCHO*vida)/vidamax-8, 12);
		
		//letras
		g.setColor(Color.BLACK);
		g.setFont(ttfReal);
		g.drawString(Integer.toString(vida)+" / "+ Integer.toString(vidamax),
				xpj+ANCHO/2, ypj);
		
		
		// bloq
		if(bloq==0) {

			
		}else if(bloq>0) {
	
			g.drawImage(bloqimg, xpj-50, ypj-45, xpj+50 , ypj+40,
					0, 0, 600, 800, null);
			g.drawString(Integer.toString(bloq),
					xpj-5, ypj);
		}
				
		//pintar daños
		if(daños.size()>0) {
			for (int i = 0; i < daños.size(); i++) {
				daños.get(i).draw(g);
				if(daños.get(i).y>400) {
					daños.remove(i);
				} 
			}
		}
	}
	public void update() {
		
	}
	
	public void restavida(int i) {
		int aux = 0;
		aux = bloq-i;
		if(aux<0) {
			bloq=0;
			vida+=aux;
		}else {
			bloq-=i;
		}
	}
	public void loadFont() throws FontFormatException, IOException{
	    String fontFileName = "../fuentes/Monofett-Regular.ttf";
	    InputStream is = this.getClass().getResourceAsStream(fontFileName);

	    Font ttfBase = Font.createFont(Font.TRUETYPE_FONT, is);

	    ttfReal = ttfBase.deriveFont(Font.PLAIN, 30);
	    
	  }
}

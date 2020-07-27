package sprites;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import util.Assets;

public class Mana {
	public int xmana, ymana;
	private Image manaimg;
	public int mana,frame;
	private int ANCHO = 100 , ALTO = 100, imgAncho = 164, imgAlto = 164;
	private Font ttfReal;
	
	
	
	
	public Mana() throws FontFormatException, IOException {
		manaimg = Assets.mana;
		xmana = 100;
		ymana = 400;
		mana = 3;
		loadFont();
	}

	public void draw(Graphics g) {
			g.drawImage(manaimg, xmana, ymana, xmana + ANCHO, ymana+ ALTO, 0, 0, imgAncho, imgAlto, null);
			g.setColor(Color.BLACK);
			g.setFont(ttfReal);
			g.drawString(String.valueOf(mana), xmana+40, ymana+65);
		
			
	}
	
	public void loadFont() throws FontFormatException, IOException{
	    String fontFileName = "../fuentes/Monofett-Regular.ttf";
	    InputStream is = this.getClass().getResourceAsStream(fontFileName);

	    Font ttfBase = Font.createFont(Font.TRUETYPE_FONT, is);

	    ttfReal = ttfBase.deriveFont(Font.PLAIN, 50);
	    
	  }
}

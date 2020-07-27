package sprites;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Daño {
	public int x,y, daño, orientacion;
	private int[] eje;
	private int color;
	
	
	public Daño(int x, int y, int daño, int orientacion) {
		super();
		eje = new int[] {x+ orientacion * 50,y};
		this.x = -orientacion*50;
		this.y = 0;
		this.orientacion = orientacion;
		this.daño = daño;
		color=0;
	}

	public void draw(Graphics g) {
		
		g.setColor(new Color(255,color,color));
		//cambiar el color  a cada vez mas blanco cuando esta bajando
		if(orientacion == -1) {
			if(x<=0) {
				if(color<=255) {
					color+=4;
					if (color>255) {
						color=255;
					}
				}
			}
		}else if(orientacion == 1) {
			if(x>=0) {
				if(color<=255) {
					color+=4;
					if (color>255) {
						color=255;
					}
				}
			}
		}
		
	
		
		g.setFont(new Font ("SansSerif",Font.PLAIN,40));
		g.drawString(String.valueOf(daño), eje[0]+x, eje[1]+y);
				
		x+= orientacion*3;
		y = (x*x)/50-50;
	}
	
	

}
	
	


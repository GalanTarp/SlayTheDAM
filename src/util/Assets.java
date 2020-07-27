package util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Assets {

	//IMAGENES
	public static Image fondo;
	public static Image fondo2;
	public static Image start;
	public static Image pj;
	public static Image ataque;
	public static Image enemy;
	public static Image mana;
	public static Image biblioteca;
	public static Image cementerio;
	public static Image escudo;
	public static Image choose;
	public static Image skip;
	public static Image endturn;

	//cartas
	public static Image strike;
	public static Image defend;
	public static Image bash;
	public static Image anger;
	
	//enemigos
	public static Image murcielago;
	

	public static Image mapa;
	public static Image espada;
	public static Image espadad;
	
	public static AudioClip reboteb;
	
	
	
	
	public static void loadAssets() {
		
		fondo = (new ImageIcon("assets/fondo.jpg")).getImage();
		fondo2 = (new ImageIcon("assets/Ice-backgorund.png")).getImage();
		start = (new ImageIcon("assets/newgame.png")).getImage();
		pj = (new ImageIcon("assets/caballero.png")).getImage();
		ataque = (new ImageIcon("assets/punto2.png")).getImage();
		enemy = (new ImageIcon("assets/jabali.png")).getImage();
		mana = (new ImageIcon("assets/RedEnergy.png")).getImage();
		biblioteca = (new ImageIcon("assets/Base.png")).getImage();
		cementerio = (new ImageIcon("assets/Cementerio.png")).getImage();
		escudo = (new ImageIcon("assets/escudo.png")).getImage();
		choose = (new ImageIcon("assets/Choose.png")).getImage();
		skip = (new ImageIcon("assets/skip.png")).getImage();
		endturn = (new ImageIcon("assets/Endturn.png")).getImage();

		strike = (new ImageIcon("assets/Strike_R.png")).getImage();
		defend = (new ImageIcon("assets/Defend_R.png")).getImage();
		bash = (new ImageIcon("assets/Bash.png")).getImage();
		anger = (new ImageIcon("assets/Anger.png")).getImage();
		

		murcielago = (new ImageIcon("assets/murcielago.png")).getImage();
		
		

		mapa = (new ImageIcon("assets/mapa.png")).getImage();
		espada = (new ImageIcon("assets/espada.png")).getImage();
		espadad = (new ImageIcon("assets/espadad.png")).getImage();

//		reboteb = Applet.newAudioClip(ClassLoader.getSystemResource("assets/reboteb.wav"));

		
	}
}

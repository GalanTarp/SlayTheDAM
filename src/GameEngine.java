           
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import enemigos.Murcielago;
import libroDeCartas.Anger;
import libroDeCartas.Bash;
import libroDeCartas.Defend;
import libroDeCartas.Strike;
import sprites.Carta;
import sprites.Daño;
import sprites.Enemigo;
import sprites.Mana;
import sprites.Personaje;
import util.Assets;

public class GameEngine extends JPanel implements MouseListener {

	/**
	 * Alberto Galán López
	 */
	private static final long serialVersionUID = 1L;
	private int ANCHO;
	private int ALTO;

	boolean isRunning = false;

	private BufferedImage bufferedImage;
	//private InputHandler input;
	
	private Personaje pj;
	private ArrayList<Enemigo> enemigos;
	
	
	private ArrayList<Carta> cartasnuevas;
	Point punto;
	private int x, y, arrastre, nanimacion, nrobo, turno, currentenemy, ntuturno, nturnoenemigo,
		enemigosel;
	
	private boolean tuturno, turnoenemigo;
	
	
	
	
	private Image fondo, choose, skip, endturn;
	
	
	

	
	

	//Inicio del combate declaro todo
	public GameEngine(int ancho, int alto, Personaje auxpj, ArrayList<Enemigo> auxenemigos) throws FontFormatException, IOException {
		setVisible(true);
		ANCHO = ancho;
		ALTO = alto-30;
		this.pj = auxpj;
		this.enemigos = auxenemigos;
		setSize(ANCHO, ALTO);
		bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		//input = new InputHandler(this);
		fondo = Assets.fondo2;
		choose = Assets.choose;
		skip = Assets.skip;
		endturn = Assets.endturn;
		
		cartasnuevas = new ArrayList<Carta>();
		turno = 0;
		arrastre = -1;
		nanimacion=0;
		tuturno=true;
		ntuturno = 0;
		turnoenemigo = false;
		nturnoenemigo =0;
		enemigosel = -1;
		addbaraja();
		addmano();
		currentenemy=0;
		
		int auxenemigo = 750;
		for (Enemigo enemigo : enemigos) {
			enemigo.xEnemy = auxenemigo;
			auxenemigo += enemigo.ANCHO+20;
		}
	}

	//añado cartas a la baraja ( trasladar)
	private void addbaraja() {
		pj.baraja.addCarta(new Strike(0));
		pj.baraja.addCarta(new Strike(0));
		pj.baraja.addCarta(new Strike(0));
		pj.baraja.addCarta(new Strike(0));
		pj.baraja.addCarta(new Strike(0));
		pj.baraja.addCarta(new Defend(1));
		pj.baraja.addCarta(new Defend(1));
		pj.baraja.addCarta(new Defend(1));
		pj.baraja.addCarta(new Anger(3));
		pj.baraja.addCarta(new Bash(2));
		pj.baraja.barajar();
	}
	
	
	//añade cartas a la mano
	private void addmano() {
		if(turno==0 && !tuturno) {
			
			if (pj.baraja.mano.size()<4) {
				if(pj.baraja.biblioteca.size()==0) {
					pj.baraja.Vuelta();
					pj.baraja.barajar();
				}
				pj.baraja.robar();
			}
			else {
				
				turno = 1;
			}
			
		}
	}







	void update() {
		
		//donde esta el puntero del raton
		punto=MouseInfo.getPointerInfo().getLocation();
		x=punto.x;
		y=punto.y-25;
		

		if(!comprobarenemigos()) {
		// handle inputs
		AnalizarTurno();
		

		//cambiar mano si estan en mostrar o no
			for(Carta carta : pj.baraja.mano) {
				if(carta.colisionClick(x,y)){
		        	carta.mostrar = true;
		        }else {
		        	carta.mostrar = false;
		        }
			}
		}
		
	}
	
	//Sucede cada frame y comprueba si estas combatiendo
	
	private boolean comprobarenemigos() {
		for (int i = 0; i < enemigos.size(); i++) {
			if(enemigos.get(i).vida<=0) {
				enemigos.remove(i);
			}
		}
		if(enemigos.size()<=0 && cartasnuevas.size()==0) {
			cartasnuevas.add(new Strike(0));
			cartasnuevas.add(new Strike(0));
			cartasnuevas.add(new Anger(3));
		}
		return (enemigos.size()<=0);
	}



	private void AnalizarTurno() {
		switch (turno) {
		case 0: //Robar cartas y resetear mana y bloq
			
			pj.bloq = 0;
			pj.mana.mana = 3;
			
			break;
			
		case 1: // jugar cartas y anallizar cuando te quedas sin cartas o cuando no puedes jugar ninguna carta
			if(!pj.atacando && !pj.defend && !turnoenemigo) {
				int numcartasjugables=0;
					for (Carta carta : pj.baraja.mano) {
						if(carta.mana<=pj.mana.mana) {
							numcartasjugables++;
						}
					}
				if(numcartasjugables==0) {
					turnoenemigo = true;
				}
			}
			break;
			
		case 2: //turnos enemigos
			 if(!enemigos.get(currentenemy).atacando && currentenemy<enemigos.size()-1) {
				currentenemy++;
				atacaenemigo();
				
			}if(!enemigos.get(currentenemy).atacando && currentenemy==enemigos.size()-1) {
				tuturno=true;
				currentenemy = 0;
				turno = 0;
			}
			
			break;
		default:
			break;
		}
		
		
	}

	//accion de atacar un enemigo
	private void atacaenemigo() {
		if(!turnoenemigo) {
			enemigos.get(currentenemy).atacando = true;
			pj.restavida(enemigos.get(currentenemy).doAttack());
			pj.daños.add(new Daño(pj.xpj+182, pj.ypj + 123, enemigos.get(currentenemy).doAttack(), -1));
			enemigos.get(currentenemy).atacando=true;
			enemigos.get(currentenemy).frame = 0;
			enemigos.get(currentenemy).framey = 1;
			pj.hit=true;
			pj.frame = 0;
			pj.framey = 3;
		}
	}

	void draw() {
		Graphics g = getGraphics();
		Graphics bbg = bufferedImage.getGraphics();
		// Fondo
		bbg.drawImage(fondo, 0, 0, ANCHO, ALTO, 0, 0, 640, 480, null);
		
	
		// draws sprites
		nanimacion++;
		if(nanimacion%6==0) {
			pj.frame ++;
			pj.framecorazon+=pj.direccion;

			for (Enemigo enemigo : enemigos) {
				enemigo.frame++;
				
			}
			if(nanimacion >= 60) {
				nanimacion=0;
			}
		}
		pj.draw(bbg);
		for (Enemigo enemigo : enemigos) {
			enemigo.draw(bbg);
		}
		
		nrobo++;
		if(nrobo%12==0) {
			addmano();
			if(nrobo>=60) {
				nrobo=0;
			}
		}
		
		
		//pintar biblioteca y cementerio
		
		pj.baraja.draw(bbg, ANCHO, ALTO);
		
		//pintar boton end turn
		
		bbg.drawImage(endturn, 1100, 475, 1272, 550, 0, 0, 230, 100, null);
		
		//pintar las mano centradas
		int aux = ANCHO/2-(678/6)*pj.baraja.mano.size()+10*(pj.baraja.mano.size()-1);
		for (int i = 0; i < pj.baraja.mano.size(); i++) {
			if(arrastre!= i) {
				pj.baraja.mano.get(i).xCarta=aux;
				pj.baraja.mano.get(i).draw(bbg, pj.mana.mana);
				
			}else {
				pj.baraja.mano.get(i).drawseleccionada(bbg, x, y, colisionCartaValido(pj.baraja.mano.get(i)));
			}
			aux += 678/3-10*(pj.baraja.mano.size());
		}
		
		//pintar mana
		pj.mana.draw(bbg);
		
		//pintar TU TURNO
		if(tuturno) {
			ntuturno++;
			bbg.setColor( new Color(0,0,0, 127));
			bbg.fillRect( 0, ALTO/2-50, ANCHO, 100);
			bbg.setColor(Color.WHITE);
			bbg.setFont(new Font ("SansSerif",Font.PLAIN,70));
			bbg.drawString("TU TURNO", ANCHO/2-200, ALTO/2+25);
			if(ntuturno>=60) {
				tuturno=false;
				ntuturno=0;
			}
		}
		//pintar TURNO ENEMIGO
		if(turnoenemigo) {
			nturnoenemigo++;
			bbg.setColor( new Color(0,0,0, 127));
			bbg.fillRect( 0, ALTO/2-50, ANCHO, 100);
			bbg.setColor(Color.WHITE);
			bbg.setFont(new Font ("SansSerif",Font.PLAIN,70));
			bbg.drawString("TURNO ENEMIGO", ANCHO/2-300, ALTO/2+25);
			if(nturnoenemigo>=60) {
				turnoenemigo=false;
				atacaenemigo();
				nturnoenemigo=0;
				turno=2;
			}
		}
		
		//pintar pantalla fin de batalla
		
		if(comprobarenemigos()) {
			bbg.setColor( new Color(0,0,0, 200));
			bbg.fillRect( 0, 0, ANCHO, ALTO);
			
			bbg.drawImage(choose, 250, 50, 1100, 210, 0, 0, 800, 160, null);
			
			int aux2=250;
			for (Carta carta : cartasnuevas) {
				carta.xCarta = aux2;
				carta.yCarta = 210;
				carta.drawnew(bbg);
				aux2 += 678/3 + 100;
			}
			
			bbg.drawImage(skip, 550, 600, 800, 660, 0, 0, 250, 60, null);
		}
		
		
        g.drawImage(bufferedImage, 0, 0, this);
	}



	private Color colisionCartaValido(Carta carta) {
		Color aux = null;
		if(carta.tipo==2) {
			Rectangle recCarta = new Rectangle(x-30, y-30,  60, 60);
			for (int i = 0; i < enemigos.size(); i++) {
				Rectangle recEnemy = new Rectangle(enemigos.get(i).xEnemy, enemigos.get(i).yEnemy, enemigos.get(i).ANCHO, enemigos.get(i).ALTO);
				if(recCarta.intersects(recEnemy)) {
					enemigosel = i;
					return aux=Color.GREEN;
				}else {
					enemigosel = -1;
				}
			}
			Rectangle recPj = new Rectangle(pj.xpj, pj.ypj, pj.ANCHO, pj.ALTO);
			if(recCarta.intersects(recPj)) {
				return aux=Color.RED;
			}
		}
		if(carta.tipo==0 || carta.tipo==1) {
			Rectangle recCarta = new Rectangle(x-30, y-30,  60, 60);
			for (Enemigo enemigo : enemigos) {
				Rectangle recEnemy = new Rectangle(enemigo.xEnemy+enemigo.ANCHO/4, enemigo.yEnemy+enemigo.ALTO/4, enemigo.ANCHO/4, enemigo.ALTO/4);
				if(recCarta.intersects(recEnemy)) {
					return aux=Color.RED;
				}
			}
			Rectangle recPj = new Rectangle(pj.xpj, pj.ypj, pj.ANCHO, pj.ALTO);
			if(recCarta.intersects(recPj)) {
				return aux=Color.GREEN;
			}
		}
		
		return aux;
	}

	private void atacar(int i) {
		if(pj.mana.mana >= pj.baraja.mano.get(i).mana) {
			pj.baraja.mano.get(i).doAction(pj,enemigos, enemigosel);
	    	pj.mana.mana -= pj.baraja.mano.get(i).mana;
	    	pj.baraja.addCartaCementerio(pj.baraja.mano.get(i));
	    	
	    	pj.baraja.mano.remove(i);
		}
	
	}


	private int colisioncartanew() {
		for (int i = 0; i < cartasnuevas.size(); i++) {
			Rectangle recCarta = new Rectangle(cartasnuevas.get(i).xCarta, cartasnuevas.get(i).yCarta, 678/3,874/3);
			Rectangle recMouse = new Rectangle(x, y, 1, 1);
			if(recCarta.intersects(recMouse)) {
				return i;
			}
		}
		
		return -1;
	}

	private boolean colisionendturn() {
		Rectangle recButton = new Rectangle(1100, 475, 172,75);
		Rectangle recMouse = new Rectangle(x, y, 1, 1);
		return recButton.intersects(recMouse);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(comprobarenemigos() && colisioncartanew()>=0) {
			pj.baraja.biblioteca.add(cartasnuevas.get(colisioncartanew()));
		}
		if(colisionendturn() && turno == 1 && !turnoenemigo) {
			turnoenemigo = true;
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
		if(turno==1 && !comprobarenemigos() && !turnoenemigo) {
			if(!pj.atacando && !pj.defend && !pj.hit) {
				// jugar carta
				for (int i = 0; i < pj.baraja.mano.size(); i++) {
					if(pj.baraja.mano.get(i).colisionClick(e.getX(), e.getY())){
						arrastre = i;
					}
				}
			
			}
			
			
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (arrastre >=0) {
			if(colisionCartaValido(pj.baraja.mano.get(arrastre))==Color.GREEN) {
				atacar(arrastre);
			}
			arrastre = -1;
		}
		
	}
	
}

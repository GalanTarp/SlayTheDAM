package libroDeCartas;

import java.util.ArrayList;

import sprites.Carta;
import sprites.Enemigo;
import sprites.Personaje;
import util.Assets;

public class Defend extends Carta {

	public int bloq = 5 ;
	public Defend(int id) {
		super(id);
		this.mana = 1;
		this.tipo = 1;
	}
	public void doAction(Personaje pj, ArrayList<Enemigo> enemigos, int i) {
		pj.defend = true;
		pj.frame = 0;
		pj.framey = 2;
		pj.framecorazon=0;
    	pj.bloq += bloq;
	}

}

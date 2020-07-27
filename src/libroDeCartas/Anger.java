package libroDeCartas;

import java.util.ArrayList;

import sprites.Carta;
import sprites.Daño;
import sprites.Enemigo;
import sprites.Personaje;

public class Anger extends Carta {
	
	public int deal=6;

	public Anger(int id) {
		super(id);
		this.mana = 0;
		this.tipo = 2;
		// TODO Auto-generated constructor stub
	}
	public void doAction(Personaje pj, ArrayList<Enemigo> enemigos, int i) {
		pj.atacando = true;
		pj.frame = 0;
		enemigos.get(i).hit = true;
		enemigos.get(i).framey =3;
		enemigos.get(i).frame =0;
		pj.atacando=true;
		pj.frame = 0;
		pj.framey = 1;
		if(enemigos.get(i).vulnerable>0) {
			enemigos.get(i).vida -= (int)(deal*1.5);
			enemigos.get(i).daños.add(new Daño(enemigos.get(i).xEnemy + enemigos.get(i).ANCHO/2, enemigos.get(i).yEnemy + enemigos.get(i).ALTO/2, (int)(deal*1.5),1));
		}else {
			enemigos.get(i).vida -= deal;
			enemigos.get(i).daños.add(new Daño(enemigos.get(i).xEnemy + enemigos.get(i).ANCHO/2, enemigos.get(i).yEnemy + enemigos.get(i).ALTO/2, deal,1));
		}
		pj.baraja.addCartaCementerio(new Anger(3));
	}

}

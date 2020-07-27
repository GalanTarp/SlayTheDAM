import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import enemigos.Murcielago;
import sprites.Enemigo;
import sprites.Personaje;
import util.Assets;
import util.Parameters;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class GameJFrame extends JFrame implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private BufferedImage bufferedImage;
	private Image fondo;
	boolean isRunning = true, isRunningDraw = true;
//	private InputHandler input;
	
	private Newgame newgame, creadorcartas;
	private GameEngine game;
	private Mapa mapa;
	
	

	private Personaje pj;
	private ArrayList<Enemigo> enemigos;
	

	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	public static void main(String[] args) throws FontFormatException, IOException {
		GameJFrame frame = new GameJFrame();
		new IndexLoop(frame).start();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	public GameJFrame() throws FontFormatException, IOException {
		setTitle("Game ");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(screenSize.width, screenSize.height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		addMouseListener(this);
		Assets.loadAssets();
		Parameters.loadParameters();
		
		bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		fondo =Assets.fondo;
		
		
		newgame = new Newgame();
		creadorcartas = new Newgame();
		pj = new Personaje();
		enemigos = new ArrayList<Enemigo>();
		
		creadorcartas.yBarra = 500;
		
		
		//Creador enemigos

		enemigos.add(new Murcielago());
		enemigos.add(new Murcielago());
		
		game = new GameEngine(screenSize.width, screenSize.height, pj, enemigos);
		mapa = new Mapa(screenSize.width, screenSize.height);

		setVisible(true);
			
	}
	void draw() {
		Graphics g = getGraphics();
		Graphics bbg = bufferedImage.getGraphics();
		bbg.drawImage(fondo, 0, 0, screenSize.width, screenSize.height, 0, 0, 1600, 740, null);
		newgame.draw(bbg);
		creadorcartas.draw(bbg);
		g.drawImage(bufferedImage, 0, 0, contentPane);
		
		
		
	}
	void update() {
		// handle inputs
		if(!game.isRunning && !isRunningDraw) {
//			isRunningDraw  = true;
		}
		
	}
	
	

	public void empezar() {
		game.isRunning = true;
		new GameLoop(game).start();
		game.setBounds(0, 0, game.getWidth(), game.getHeight());
		contentPane.add(game);
		game.requestFocus();	
	}
	
	private void empezarMapa() {
		mapa.isRunning = true;
		new GameLoop(mapa).start();
		mapa.setBounds(0, 0, mapa.getWidth(), mapa.getHeight());
		contentPane.add(mapa);
		mapa.requestFocus();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
        if(newgame.colisionClick(e.getX(), e.getY()) && isRunningDraw){
        	empezar();
        	isRunningDraw = false;
        }
        if(creadorcartas.colisionClick(e.getX(), e.getY()) && isRunningDraw){
        	empezarMapa();
        	isRunningDraw = false;
        }
        if(game.isRunning) {
        	game.mouseClicked(e);
        }
        if(mapa.isRunning) {
        	mapa.mouseClicked(e);
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
		// TODO Auto-generated method stub
		game.mousePressed(e);
		mapa.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		game.mouseReleased(e);
		mapa.mouseReleased(e);
	}
	
}

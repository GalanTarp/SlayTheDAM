
public class GameLoop extends Thread {
	private static final int FPS = 60;
	private GameEngine game;
	private Mapa creador;
	public GameLoop(GameEngine game) {
		super();
		this.game = game;
		// TODO Auto-generated constructor stub
	}
	public GameLoop(Mapa creador) {
		super();
		this.creador = creador;
		// TODO Auto-generated constructor stub
	}
	


	@Override
	public void run() {
		try {
			if(game!=null) {
				while (game.isRunning) {
					long time = System.currentTimeMillis();
	
					game.update();
					game.draw();
	
					// delay for each frame - time it took for one frame
					time = (1000 / FPS) - (System.currentTimeMillis() - time);
	
					if (time > 0) {
						try {
							Thread.sleep(time);
						} catch (Exception e) {
						}
					}
				}
			}else if(creador!=null) {
				while (creador.isRunning) {
					long time = System.currentTimeMillis();
	
					creador.update();
					creador.draw();
	
					// delay for each frame - time it took for one frame
					time = (1000 / FPS) - (System.currentTimeMillis() - time);
	
					if (time > 0) {
						try {
							Thread.sleep(time);
						} catch (Exception e) {
						}
					}
				}
			}
			
			game.setVisible(false);
			//creador.setVisible(false);
		

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}

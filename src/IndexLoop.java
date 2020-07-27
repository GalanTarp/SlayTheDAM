
public class IndexLoop extends Thread {
	private static final int FPS = 60;
	private GameJFrame visor;

	public IndexLoop(GameJFrame visor) {
		super();
		this.visor = visor;
		// TODO Auto-generated constructor stub
	}
	
	


	@Override
	public void run() {
		try {
			while (visor.isRunning) {
				long time = System.currentTimeMillis();

				visor.update();
				if(visor.isRunningDraw) {
					visor.draw();
				}
				
				// delay for each frame - time it took for one frame
				time = (1000 / FPS) - (System.currentTimeMillis() - time);

				if (time > 0) {
					try {
						Thread.sleep(time);
					} catch (Exception e) {
					}
				}
			}
									
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}

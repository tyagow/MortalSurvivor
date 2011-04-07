import java.awt.Color;
import java.awt.Graphics2D;


public class GerenciadorRespawn extends Objeto {

	int timerRespawn=0;
	int tempoDeRespawn=2000;
	private boolean respawn=false;
	
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		if (CanvasGame.heroi.vivo==false) {
			respawn=true;
			timerRespawn+=DiffTime;
			
			if (timerRespawn>=tempoDeRespawn) {
				CanvasGame.heroi.respaw(GamePanel.rnd.nextInt(GamePanel.PWIDTH/2)+100, GamePanel.rnd.nextInt(GamePanel.PHEIGHT/2)+100);
				timerRespawn=0;
				respawn=false;
			}
			
		}
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		if (respawn) {
	
			dbg.setColor(Color.black);
			dbg.fillRect(300, 300, timerRespawn*50/tempoDeRespawn, 20); // life*30/maximoVida
		}
			
			
	}

}

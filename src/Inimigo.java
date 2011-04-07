import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Inimigo extends Objeto {
	
	int dano;
	private double vel;
	private int tempoEntreAtaque;
	private int maximoVida=100;
	private int maxVel=100;
	
	public Inimigo() {
		X = GamePanel.rnd.nextInt(GamePanel.PWIDTH);
		Y = GamePanel.rnd.nextInt(GamePanel.PHEIGHT);
		sizeX = 30;
		sizeY = 30;
		dano = 10;
		vel=100;
		life=100;
		// TODO Auto-generated constructor stub
	}

	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub	
			
			calculaIA(DiffTime);
			
			double difX = CanvasGame.heroi.X - X;
			double difY = CanvasGame.heroi.Y - Y;
			double ang =  Math.atan2(difY, difX);

			double velx=(int) (Math.cos(ang)*vel);
			double vely=(int) (Math.sin(ang)*vel);
			
			X+=velx*DiffTime/1000.0f;
			Y+=vely*DiffTime/1000.0f;

	}


	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		dbg.drawOval((int)X,(int)Y,(int)sizeX,(int)sizeY);
		
		dbg.drawRect((int)X-5, (int)Y-17, 30, 10);
		dbg.setColor(Color.green);
		dbg.fillRect((int)X-5+1, (int)Y-16, (int)(life*30/maximoVida)-1, 9);
		
	}
	
	private void calculaIA(int DiffTime) {
		// TODO Auto-generated method stub
		
		tempoEntreAtaque+=DiffTime;
		
		if(Constantes.colidecircular(X+sizeX/2, Y+sizeY/2,sizeX/2,CanvasGame.heroi.X+CanvasGame.heroi.sizeX/2,CanvasGame.heroi.Y+CanvasGame.heroi.sizeY/2,CanvasGame.heroi.sizeX/2)){
			if (tempoEntreAtaque>500) {
				CanvasGame.heroi.life-=dano;
				tempoEntreAtaque=0;
			}
			
			vel=0;
			
			}
		else vel=maxVel;
		
		
	}

}

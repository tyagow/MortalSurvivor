package Efeitos;

import java.awt.Color;
import java.awt.Graphics2D;

import Constantes.Constantes;
import GameState.GamePanel;


public class Sangue extends Particula {
	
	public Color cor =  null;
	
	int r,g,b;
	

	
	public Sangue(double X,double Y,double pvx,double pvy,int tvida,Color cor) {
		// TODO Auto-generated constructor stu
		this.setX(X);
		this.setY(Y);
		this.velx = (int) pvx;
		this.vely = (int) pvy;
		
		this.tempodevida = tvida;
		
		this.setVivo(true);
		this.tempototal = 0;
		
		this.cor = cor;
		setSizeX(GamePanel.rnd.nextInt(6)+3);
		setSizeY(GamePanel.rnd.nextInt(6)+3);
		
		r = cor.getRed();
		g = cor.getGreen();
		b = cor.getBlue();
	}
	
	@Override
	public void SimulaSe(int DiffTime) {
		tempototal+=DiffTime;
		
		setX(getX() + (velx*DiffTime/1000.0f));
		setY(getY() + (vely*DiffTime/1000.0f));
		

		if ((int)((1.0f - (tempototal/(float)tempodevida))*255) < 0) {
			alpha=0;
		}else {
			alpha = (int)((1.0f - (tempototal/(float)tempodevida))*255);
		}
		
		if(tempototal>=tempodevida){
			setVivo(false);
			alpha = 255;//GamePanel.rnd.nextInt(100)+155;
			setSizeX(GamePanel.rnd.nextInt(Constantes.SANGUE_SIZE_X)+1);
			setSizeY(GamePanel.rnd.nextInt(Constantes.SANGUE_SIZE_Y)+1);
		}
		
	}
	public int getAlpha() {
		
		return alpha;
	}
	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		dbg.setColor(new Color(r,g,b,alpha));
		
		dbg.fillOval((int)getX()-XMundo,(int)getY()-YMundo, getSizeX(), getSizeY());
	}
}

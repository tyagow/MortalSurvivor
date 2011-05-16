package Armas;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import GameState.GamePanel;



public abstract class ArmaTorre extends Arma {

	int tempoRecarrega;

	public int estado=0;


	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		dbg.setColor(Color.black);
		AffineTransform trans = dbg.getTransform();
		dbg.translate(X-XMundo, Y-YMundo);
		dbg.rotate(angulo+Math.PI/2);
		//dbg.drawLine(0, 0, sizeX, 0);
		dbg.drawImage(imagem,-sizeX/2,-sizeY/2,sizeX/2,sizeY/2,0,0,sizeX,sizeY,null);

		dbg.setTransform(trans);
		
		if (estado==1) {
			dbg.setColor(Color.LIGHT_GRAY);
			dbg.fillRect(GamePanel.PWIDTH/2-50, GamePanel.PHEIGHT/2-205,(int)(tempoRecarrega*100/tempoRecarrega) , 20);

			dbg.setColor(Color.black);
			dbg.drawRect(GamePanel.PWIDTH/2-51, GamePanel.PHEIGHT/2-206, 103, 21);
		}
////		
//		dbg.drawString("Dano: "+getDano(),5 , 60);
//		dbg.drawString("mag: "+getMag(),5 , 70);
		
	}
	


	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		
		calculaIA(DiffTime);
	

		
		
	}
	
	
	private void calculaIA(int DiffTime) {
		// TODO Auto-generated method stub
		timerTempoEntreTiros+=(+ DiffTime);
	
		
		if (round>0) 
			estado=0;
		else estado=1;
			
		if (estado==0) {//atirando
	

			if (timerTempoEntreTiros>=tempoEntreTirosMax) {
				if (atirou) {	
					atira();
					timerTempoEntreTiros=(0);
					
				}
			}
		}
		
		if (estado==1) {
			
			
			tempoRecarrega+=( DiffTime);
			
			
			if (mag<1)
				estado=2;
				
			if (timerTempoEntreTiros>=tempoRecarrega) {
				
				if (mag >=1) {
					timerTempoEntreTiros=0;
					round=(maxRound);
					mag-=(1);
					estado=0;
				}
			}
			
		}
		if (estado==2) {
			
			if (round>0) 
				estado=0;
			
			
		}
	}



	@Override
	public void definePosicaoArma(double ang,double startX,double startY) {
		// TODO Auto-generated method stub
		angulo=(ang);
		X=(startX);
		Y=(startY);
		

	}
	

	
	public abstract void atira();



	@Override
	public void atirou() {
		// TODO Auto-generated method stub

			atirou=true;
			
		
	}



	@Override
	public void naoAtirou() {
		// TODO Auto-generated method stub
		atirou=false;

	}




}

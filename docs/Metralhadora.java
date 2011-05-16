package Armas;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import Constantes.Constantes;
import GameState.GamePanel;
import Gerenciadores.GerenciadorDeSom;


public class Metralhadora extends Arma {

	
	private boolean soltouTiro=true;
	private boolean semMunicao=false;
	
		
	public int estado=0;
	public Metralhadora() {
		
		maxMag=(Constantes.METRALHADORA_mag);

		tipo=(2);
		atirou=false;
		dano=(Constantes.METRALHADORA_dano);
		mag=(Constantes.METRALHADORA_mag);
		peso=(Constantes.METRALHADORA_peso);
		round=(Constantes.METRALHADORA_round);
		maxRound=(Constantes.METRALHADORA_round);
		timerTempoEntreTiros=(0);
		tempoRecarrega=(0);
		valor=(Constantes.METRALHADORA_valor);
	
	
		
	}
	


	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		dbg.setColor(Color.black);
		AffineTransform trans = dbg.getTransform();
		dbg.translate(X-XMundo, X-YMundo);
		dbg.rotate(angulo-Math.PI/2);
		//dbg.drawLine(0, 0, sizeX, 0);
		System.out.println("X"  + X);
		System.out.println("Y"  + Y);
		
		dbg.drawImage(imagem, -sizeX/2-10, -sizeY/2-5, sizeX-2,sizeY,0,0,sizeX,sizeY,null);
		dbg.setTransform(trans);
		
		if (estado==1) {
			dbg.setColor(Color.LIGHT_GRAY);
			dbg.fillRect(GamePanel.PWIDTH/2-50, GamePanel.PHEIGHT/2-205,(int)(tempoRecarrega*100/Constantes.METRALHADORA_tempoRecarrega) , 20);

			dbg.setColor(Color.black);
			dbg.drawRect(GamePanel.PWIDTH/2-51, GamePanel.PHEIGHT/2-206, 103, 21);
		}
		
		dbg.drawString("Round: "+round,5 , 20);
		dbg.drawString("mag: "+mag,5 , 30);
		
	}
	


	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		
		calculaIA(DiffTime);
		
		
		
	}
	
	private void calculaIA(int DiffTime) {
		// TODO Auto-generated method stub
	timerTempoEntreTiros+=DiffTime;
	
		
		if (round>0) 
			estado=0;
		else estado=1;
			
		if (estado==0) {
			recarregando=(false);
			


			
			if (timerTempoEntreTiros>=Constantes.METRALHADORA_tempoEntreTiros) {
				
					if (atirou) {	
							atira();
							timerTempoEntreTiros=(0);
					}
				}
				
//				if (!atirou)
//					soltouTiro=true;
			}
		
		
		if (estado==1) {
			
			if (mag>0) {

				tempoRecarrega+= DiffTime;
				
				recarregando=(true);
			
					
				if (tempoRecarrega>=Constantes.METRALHADORA_tempoRecarrega) {
					
					if (mag >=1) {
						tempoRecarrega=(0);
						round=(Constantes.METRALHADORA_round);
						mag=(mag - 1);
						estado=0;
					}
				}
			}else {
				estado =2;
			}
			
		}
		if (estado==2) {
			
			if (round>0) 
				estado=0;
			recarregando=(false);

			
		}
	}



	@Override
	public void definePosicaoArma(double ang,double startX,double startY) {
		// TODO Auto-generated method stub
		angulo=(ang);
		X=(startX);
		Y=(startY);

	}
	
	
	private void atira() {
		
		// TODO Auto-generated method stub
		
		if (temMunicao()) {
			round=(round - 1);
			Constantes.projeteis.add( new Projetil (this,angulo,Constantes.TIPO_ASSASINO_PLAYER ));
			GerenciadorDeSom.tiroAK();
		}
		
	}


	private boolean temMunicao() {
		// TODO Auto-generated method stub
		
		if (round<1) {
			semMunicao=true;
			return false;
			
		}else 
			semMunicao=false;
		
		
		return true;
	}

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

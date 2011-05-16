package Armas;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import Constantes.Constantes;
import GameState.GamePanel;
import Gerenciadores.GerenciadorDeSom;


public class Pistola extends Arma {

	
	private boolean soltouTiro=true;
	private boolean semMunicao=false;
	
		
	public int estado=0;
	public Pistola() {
		mag=(Constantes.PISTOLA_mag);

		tipo=(1);
		atirou=false;
		dano=(Constantes.PISTOLA_dano);
		peso=(Constantes.PISTOLA_peso);
		round=(Constantes.PISTOLA_round);
		maxRound=(Constantes.PISTOLA_round);
		timerTempoEntreTiros=(0);
		tempoRecarrega=(0);
		valor=(Constantes.PISTOLA_valor);
	

		
		
	}
	


	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		dbg.setColor(Color.black);
		AffineTransform trans = dbg.getTransform();
		dbg.translate(X-XMundo, Y-YMundo);
		dbg.rotate(angulo-Math.PI/2);
		//dbg.drawLine(0, 0, getSizeX(), 0);

		dbg.drawImage(imagem, -sizeX/2-10, -sizeY/2-5, sizeX-2,sizeY,0,0,sizeX,sizeY,null);
		dbg.setTransform(trans);
		
		if (estado==1) {
			dbg.setColor(Color.LIGHT_GRAY);
			dbg.fillRect(GamePanel.PWIDTH/2-50, GamePanel.PHEIGHT/2-205,(int)(tempoRecarrega*100/Constantes.PISTOLA_tempoRecarrega) , 20);

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

			if (timerTempoEntreTiros>=Constantes.PISTOLA_tempoEntreTiros) {
				
				if (atirou&&soltouTiro) {	
					soltouTiro=false;
					atira();
					timerTempoEntreTiros=0;
					
				}
				
				if (!atirou)
					soltouTiro=true;
			}
		}
		
		if (estado==1) {
			
			if (mag>0) {
					
				tempoRecarrega+=DiffTime;
				
				recarregando=(true);
				
					
				if (timerTempoEntreTiros>=Constantes.PISTOLA_tempoRecarrega) {
					
					if (mag >=1) {
						timerTempoEntreTiros=0;
						round=(Constantes.PISTOLA_round);
						mag-=1;
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
	
//	@Override
//	public void recarrega() {
//		setMag(Constantes.PISTOLA_mag+1);
//		//setRound(Constantes.PISTOLA_round);
//	}

	
	private void atira() {
		
		// TODO Auto-generated method stub
		
		if (temMunicao()) {
			round-=1;
			Constantes.projeteis.add( new Projetil (this,angulo,Constantes.TIPO_ASSASINO_PLAYER ));
			GerenciadorDeSom.de.run();
//			Sound.music("sound/de.wav",false);
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
		soltouTiro=true;

	}


}

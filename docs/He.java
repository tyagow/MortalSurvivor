package Armas;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import Constantes.Constantes;
import Data.Imagem;
import GameState.GamePanel;


public class He extends Arma {

	
	private boolean soltouTiro=true;
	private boolean semMunicao=false;
	
		
	public int estado=0;
	public He() {
		maxMag=(Constantes.HE_mag);
		maxRound=(Constantes.HE_round);

		tipo=(1);
		atirou=false;
		dano=(Constantes.HE_dano);
		mag=(Constantes.HE_mag);
		peso=(Constantes.HE_peso);
		round=(Constantes.HE_round);
		timerTempoEntreTiros=(0);
		tempoRecarrega=(0);
		valor=(Constantes.HE_valor);
	
		imagem=(Imagem.he);
		imagem_hud=(Imagem.he_hud);
		sizeX=(imagem.getWidth());
		sizeY=(imagem.getHeight());
		

		
	}
	


	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		dbg.setColor(Color.black);
		AffineTransform trans = dbg.getTransform();
		dbg.translate(X-XMundo, Y-YMundo);
		dbg.rotate(angulo-Math.PI/2);
		//dbg.drawLine(0, 0, getSizeX(), 0);
		
		if (recarregando&&round>0) {
			dbg.drawImage(imagem, 0, +6, sizeX,6+sizeY,sizeX,sizeY,0,0,null);
		}
		
		
		dbg.setTransform(trans);
		
		if (estado==1) {
			dbg.setColor(Color.LIGHT_GRAY);
			dbg.fillRect(GamePanel.PWIDTH/2-50, GamePanel.PHEIGHT/2-205,(int)(tempoRecarrega*100/Constantes.HE_tempoRecarrega) , 20);

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

			if (timerTempoEntreTiros>=Constantes.HE_tempoEntreTiros) {
				
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
			
			
			tempoRecarrega += DiffTime;
			
			recarregando=(true);
			if (mag<1)
				estado=2;
				
			if (tempoRecarrega>=Constantes.HE_tempoRecarrega) {
				
				if (mag >=1) {
					tempoRecarrega=(0);
					round=(Constantes.HE_round);
					mag-=1;
					estado=0;
				}
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
//		setMag(Constantes.HE_mag+1);
//		//setRound(Constantes.HE_round);
//	}

	
	private void atira() {
		
		// TODO Auto-generated method stub
		
		if (temMunicao()) {
			round -=1;
			Constantes.projeteis.add( new ProjetilGranada(this, angulo, 1, imagem));
			//Constantes.de.run();
		}
		
	}


	private boolean temMunicao() {
		// TODO Auto-generated method stub
		
		if (round<1) {
			setSemMunicao(true);
			return false;
			
		}else 
			setSemMunicao(false);
		
		
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



	public void setSemMunicao(boolean semMunicao) {
		this.semMunicao = semMunicao;
	}



	public boolean isSemMunicao() {
		return semMunicao;
	}


}

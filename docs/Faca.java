package Armas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import Constantes.Constantes;
import Data.Imagem;
import Personagem.Inimigo;


public class Faca extends Arma {
	
	
	private int estado;
	private boolean soltouTiro;
	private double oldAng;
	private double alcanceAtaque=50;
	private boolean atirando;

	public Faca() {
		tipo=(0);
		atirou=false;
		dano=(Constantes.FACA_dano);
		peso=(Constantes.FACA_peso);
		round=(Constantes.FACA_round);
		timerTempoEntreTiros=(0);
		tempoRecarrega=(0);
		valor=(Constantes.FACA_valor);
	
		imagem=(Imagem.faca);
		imagem_hud=(Imagem.faca);
		sizeX=(imagem.getWidth());
		sizeY=(imagem.getHeight());
		
		
	}
 
	@Override
	public void definePosicaoArma(double ang,double startX,double startY) {
		// TODO Auto-generated method stub
		
		angulo=(ang);
		oldAng=ang;
		X=(startX);
		Y=(startY);

	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
			dbg.setColor(Color.black);
			AffineTransform trans = dbg.getTransform();
			dbg.translate(X-XMundo, Y-YMundo);
			if(estado==1)
				dbg.rotate(oldAng);
			else 
				dbg.rotate(angulo);
			
			dbg.drawImage(imagem, -sizeX/2+8, -sizeY/2, sizeX-2,sizeY,sizeX,sizeY,0,0,null);

			//dbg.drawLine(0, 0, (int)alcanceAtaque/2, 0);
			//dbg.drawOval(0/*(int)Math.cos(getAngulo())*50*/,-25/*(int) Math.sin(getAngulo())*50*/,(int) alcanceAtaque,(int)alcanceAtaque);
//			
			dbg.setTransform(trans);
			
			if (estado==1) {
//				dbg.setColor(Color.LIGHT_GRAY);
//				dbg.fillRect(GamePanel.PWIDTH/2-50, GamePanel.PHEIGHT/2-205,(int)(tempoRecarrega*100/Constantes.PISTOLA_tempoRecarrega) , 20);

				dbg.setColor(Color.black);
				dbg.drawOval((int)(X-alcanceAtaque/2), (int)(Y-alcanceAtaque/2), (int)alcanceAtaque,(int)alcanceAtaque);
				
			}
			
//			dbg.drawString("Round: "+round,5 , 20);
//			dbg.drawString("mag: "+mag,5 , 30);
			
		
		
	}

	@Override
	public void SimulaSe(int Difftime) {
		// TODO Auto-generated method stub
		
		
		timerTempoEntreTiros+=( Difftime);
		tempoRecarrega+=( Difftime);
		

			
		if (estado==0) {
			oldAng=angulo;
		
			if (timerTempoEntreTiros>=Constantes.FACA_tempoEntreTiros) {
				
				if (atirou&&soltouTiro) {	
					soltouTiro=false;
					atira();
					timerTempoEntreTiros=(0);
					
				}
				
				if (!atirou)
					soltouTiro=true;
			}else {
				
//				if (atirou&&soltouTiro) {	
//					if (!atirando) {
//						atirando=true;
//						
//					}
//				}
				
			}
			
		}
		
		if (estado==1) {
			
			
			oldAng+=Math.PI*Difftime/Constantes.FACA_tempoAtaque;
//				
//				for (int i = 0; i < CanvasGame.inimigos.size(); i++) {
//					if (Constantes.colidecircular(getX(), getY(), alcanceAtaque, CanvasGame.inimigos.get(i).getX(), CanvasGame.inimigos.get(i).getY(), CanvasGame.inimigos.get(i).getSizeX()/2)) {
//						
//						CanvasGame.inimigos.get(i).recebeuDano(getDano(),1);
//						CanvasGame.gerenciadorEfeitos.ativaSangue( CanvasGame.inimigos.get(i).getX(), CanvasGame.inimigos.get(i).getY(),getAngulo() ,(int)getDano());
//							
//						break;
//					}
//		
//				}
				
			procuraInimigos();
			
			if (tempoRecarrega>=Constantes.FACA_tempoAtaque) {
				tempoRecarrega=(0);
				estado=0;	
			}
				
			
			
		}
//		if (estado==2) {
//			
//			if (round>0) 
//				estado=0;
//			
//			
//			}
	
		
	}
	private void procuraInimigos() {
		// TODO Auto-generated method stub
//		for (int i = 0;i<GerenciadorDeRaids.getRaids().size();i++) {
//			Raid ra = GerenciadorDeRaids.getRaids().get(i);
		
			for (int j = 0;j<Constantes.inimigos.size();j++) {
				Inimigo in = Constantes.inimigos.get(j);
				

				if (Constantes.colidecircular(X, Y,alcanceAtaque,in.X,in.Y,in.sizeX/2)) {
				
					penetration--;
					Constantes.inimigos.get(j).recebeuDano(dano,1);
					//CanvasGame.gerenciadorEfeitos.ativaSangue(getX(),getY(),getAngulo(),(int)getDano());
				
				
						break;
					}
			}
			
//		}
	}

	private void atira() {
		// TODO Auto-generated method stub
		angulo-= (Math.PI/2);
		estado=1;
	}

	@Override
	public void recarrega() {
		// TODO Auto-generated method stub

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

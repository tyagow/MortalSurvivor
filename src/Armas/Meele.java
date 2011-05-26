package Armas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Constantes.Constantes;
import Data.Imagem;
import Gerenciadores.GerenciadorEfeitos;
import Personagem.Inimigo;
import Som.ThreadSom;


public class Meele extends Arma {
	
	
	private int estado;
	private boolean soltouTiro;
	private double oldAng;
	private int alcanceAtaque=20;
	private boolean atirando;
	private ThreadSom tiro;
	private ThreadSom tiroHit;
	
	public Meele(BufferedImage img1,BufferedImage img2,ThreadSom _tiro,ThreadSom _tiroHit) {
		super(img1,img2,_tiro,_tiroHit);		
		imagem =img1;
		imagem_hud = img2;
		tiro=_tiro;
		tiroHit=_tiroHit;
		sizeX=imagem.getWidth();
		sizeY=imagem.getHeight();
		
		
		setTipo(0);
		atirou=false;
		setDano(Constantes.FACA_dano);
		setPeso(Constantes.FACA_peso);
		setRound(Constantes.FACA_round);
		setTempoEntreTiros(0);
		setTempoRecarrega(0);
		setValor(Constantes.FACA_valor);
	
//		imagem=(Imagem.faca);
	//	imagem_hud=(Imagem.faca);
		
		
		
		setSizeX(getImagem().getWidth());
		setSizeY(getImagem().getHeight());
		
		
	}
 
	@Override
	public void definePosicaoArma(double ang,double startX,double startY) {
		// TODO Auto-generated method stub
		
		setAngulo(ang);
		oldAng=ang;
		setX(startX);
		setY(startY);

	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
			
	
			dbg.setColor(Color.black);
			
			AffineTransform trans = dbg.getTransform();
			dbg.translate(getX()-XMundo, getY()-YMundo);
			if(estado==1)
				dbg.rotate(oldAng);
			else 
				dbg.rotate(angulo);
			
			dbg.drawImage(getImagem(), -getSizeX()/2+8, -getSizeY()/2, getSizeX()-2,getSizeY(),getSizeX(),getSizeY(),0,0,null);

			//dbg.drawLine(0, 0, (int)alcanceAtaque/2, 0);
			//dbg.drawOval(0/*(int)Math.cos(getAngulo())*50*/,-25/*(int) Math.sin(getAngulo())*50*/,(int) alcanceAtaque,(int)alcanceAtaque);(int)(+Math.cos(angulo)*20),(int)(Math.sin(angulo)*20)
//			
			dbg.setTransform(trans);
			dbg.setColor(Color.red);
				//getX(), getY(),alcanceAtaque,in.getX(),in.getY(),in.getSizeX()/2

//			dbg.fillOval((int)(X-XMundo+Math.cos(angulo)*20)-13,(int)( Y-YMundo+Math.sin(angulo)*20)-13, alcanceAtaque,alcanceAtaque);	//getX(), getY(),alcanceAtaque,in.getX(),in.getY(),in.getSizeX()/2
			if (estado==1) {
//				dbg.setColor(Color.LIGHT_GRAY);
//				dbg.fillRect(GamePanel.PWIDTH/2-50, GamePanel.PHEIGHT/2-205,(int)(tempoRecarrega*100/Constantes.PISTOLA_tempoRecarrega) , 20);

				dbg.setColor(Color.black);
				//dbg.drawOval((int)(getX()-alcanceAtaque/2), (int)(getY()-alcanceAtaque/2), (int)alcanceAtaque,(int)alcanceAtaque);
				
			}
			
//			dbg.drawString("Round: "+round,5 , 20);
//			dbg.drawString("mag: "+mag,5 , 30);
			
		
		
	}

	@Override
	public void SimulaSe(int Difftime) {
		// TODO Auto-generated method stub
		
		
		setTempoEntreTiros(getTempoEntreTiros() + Difftime);
		setTempoRecarrega(getTempoRecarrega() + Difftime);
		

			
		if (estado==0) {
			oldAng=getAngulo();
		
			if (getTempoEntreTiros()>=Constantes.FACA_tempoEntreTiros) {
				
				if (atirou&&soltouTiro) {	
					soltouTiro=false;
					GerenciadorEfeitos.criaEfeitoFaca((X+Math.cos(angulo)*20)-13, Y+Math.sin(angulo)*20-13, angulo);

					atira();
					setTempoEntreTiros(0);
					
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
			
			if (getTempoRecarrega()>=Constantes.FACA_tempoAtaque) {
				setTempoRecarrega(0);
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
		boolean atigiuInimigo = false;
			for (int j = 0;j<Constantes.inimigos.size();j++) {
				Inimigo in = Constantes.inimigos.get(j);
				
//(int)(X-XMundo+Math.cos(angulo)*20),(int)( Y-YMundo+Math.sin(angulo)*20)
				
				if (Constantes.colidecircular((int)(X+Math.cos(angulo+Math.PI/2)*20), (int)( Y+Math.sin(angulo+Math.PI/2)*20),alcanceAtaque,in.getX(),in.getY(),in.getSizeX()/2)) {
				
					penetration--;
					Constantes.inimigos.get(j).recebeuDano(getDano(),1);
					//CanvasGame.gerenciadorEfeitos.ativaSangue(getX(),getY(),getAngulo(),(int)getDano());
				//X-XMundo+Math.cos(angulo)*20)-13,(int)( Y-YMundo+Math.sin(angulo)*20)-13
						//break;
					atigiuInimigo=true;
				}
			}
			if (atigiuInimigo) {
				if (tiroHit!=null) {
					
					tiroHit.run();
				}
				}
	

//		}
	}

	private void atira() {
		// TODO Auto-generated method stub
		setAngulo(getAngulo() - (Math.PI/2));
		estado=1;	
		if (tiro!=null) {
			
			tiro.run();
		}
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

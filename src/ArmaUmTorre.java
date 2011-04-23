import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;


public class ArmaUmTorre extends Arma {

	

	private boolean semMunicao=false;

	BufferedImage AnimeSet;
	public int estado=0;
	public ArmaUmTorre(BufferedImage img) {
		
		
		setMaxMag(Constantes.TORRE_ARMA_UM_mag);
		atirou=false;
		AnimeSet=img;
		setDano(Constantes.TORRE_ARMA_UM_dano);
		setMag(Constantes.TORRE_ARMA_UM_mag);
		setRound(Constantes.TORRE_ARMA_UM_round);
		setTempoEntreTiros(0);
		setTempoRecarrega(0);
		setValor(Constantes.TORRE_ARMA_UM_valor);
		setSizeX(AnimeSet.getWidth());
		setSizeY(AnimeSet.getHeight());
		estado=0;
		
	}
	


		// TODO Auto-generated constructor stub
	



	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		dbg.setColor(Color.black);
		AffineTransform trans = dbg.getTransform();
		dbg.translate(getX()-XMundo, getY()-YMundo);
		dbg.rotate(getAngulo()+Math.PI/2);
		//dbg.drawLine(0, 0, getSizeX(), 0);
		dbg.drawImage(AnimeSet,-getSizeX()/2,-getSizeY()/2,getSizeX()/2,getSizeY()/2,0,0,getSizeX(),getSizeY(),null);

		dbg.setTransform(trans);
		
		if (estado==1) {
			dbg.setColor(Color.LIGHT_GRAY);
			dbg.fillRect(GamePanel.PWIDTH/2-50, GamePanel.PHEIGHT/2-205,(int)(getTempoRecarrega()*100/Constantes.TORRE_ARMA_UM_tempoRecarrega) , 20);

			dbg.setColor(Color.black);
			dbg.drawRect(GamePanel.PWIDTH/2-51, GamePanel.PHEIGHT/2-206, 103, 21);
		}
		
		dbg.drawString("Round: "+getRound(),5 , 60);
		dbg.drawString("mag: "+getMag(),5 , 70);
		
	}
	


	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		
		calculaIA(DiffTime);
	

		
		
	}
	
	private void calculaIA(int DiffTime) {
		// TODO Auto-generated method stub
		setTempoEntreTiros(getTempoEntreTiros() + DiffTime);
	
		
		if (getRound()>0) 
			estado=0;
		else estado=1;
			
		if (estado==0) {
	

			if (getTempoEntreTiros()>=Constantes.TORRE_ARMA_UM_tempoEntreTiros) {
				if (atirou) {	
					atira();
					setTempoEntreTiros(0);
					
				}
			}
		}
		
		if (estado==1) {
			
			
			setTempoRecarrega(getTempoRecarrega() + DiffTime);
			
			
			if (getMag()<1)
				estado=2;
				
			if (getTempoRecarrega()>=Constantes.TORRE_ARMA_UM_tempoRecarrega) {
				
				if (getMag() >=1) {
					setTempoRecarrega(0);
					setRound(Constantes.TORRE_ARMA_UM_round);
					setMag(getMag() - 1);
					estado=0;
				}
			}
			
		}
		if (estado==2) {
			
			if (getRound()>0) 
				estado=0;
			
			
		}
	}



	@Override
	public void definePosicaoArma(double ang,double startX,double startY) {
		// TODO Auto-generated method stub
		setAngulo(ang);
		setX(startX);
		setY(startY);
		

	}
	

	
	private void atira() {
//		System.out.println("atira");
		// TODO Auto-generated method stub
		
//		if (temMunicao()) {
			setRound(getRound() - 1);
			
			CanvasGame.projeteis.add( new Projetil (this,getAngulo(),2 ));
			Constantes.ak.run();
			
//		}
		
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

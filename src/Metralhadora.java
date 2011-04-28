import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;


public class Metralhadora extends Arma {

	
	private boolean soltouTiro=true;
	private boolean semMunicao=false;
	
		
	public int estado=0;
	public Metralhadora() {
		
		setMaxMag(Constantes.METRALHADORA_mag);

		setTipo(2);
		atirou=false;
		setDano(Constantes.METRALHADORA_dano);
		setMag(Constantes.METRALHADORA_mag);
		setPeso(Constantes.METRALHADORA_peso);
		setRound(Constantes.METRALHADORA_round);
		setTempoEntreTiros(0);
		setTempoRecarrega(0);
		setValor(Constantes.METRALHADORA_valor);
	
	
		
	}
	


	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		dbg.setColor(Color.black);
		AffineTransform trans = dbg.getTransform();
		dbg.translate(getX()-XMundo, getY()-YMundo);
		dbg.rotate(getAngulo()-Math.PI/2);
		//dbg.drawLine(0, 0, getSizeX(), 0);

		dbg.drawImage(imagem, -getSizeX()/2-10, -getSizeY()/2-5, getSizeX()-2,getSizeY(),0,0,getSizeX(),getSizeY(),null);
		dbg.setTransform(trans);
		
		if (estado==1) {
			dbg.setColor(Color.LIGHT_GRAY);
			dbg.fillRect(GamePanel.PWIDTH/2-50, GamePanel.PHEIGHT/2-205,(int)(getTempoRecarrega()*100/Constantes.METRALHADORA_tempoRecarrega) , 20);

			dbg.setColor(Color.black);
			dbg.drawRect(GamePanel.PWIDTH/2-51, GamePanel.PHEIGHT/2-206, 103, 21);
		}
		
		dbg.drawString("Round: "+getRound(),5 , 20);
		dbg.drawString("mag: "+getMag(),5 , 30);
		
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
			setRecarregando(false);
			


			
			if (getTempoEntreTiros()>=Constantes.METRALHADORA_tempoEntreTiros) {
				
					if (atirou) {	
							atira();
							setTempoEntreTiros(0);
					}
				}
				
//				if (!atirou)
//					soltouTiro=true;
			}
		
		
		if (estado==1) {
			
			
			setTempoRecarrega(getTempoRecarrega() + DiffTime);
			
			setRecarregando(true);
			if (getMag()<1)
				estado=2;
				
			if (getTempoRecarrega()>=Constantes.METRALHADORA_tempoRecarrega) {
				
				if (getMag() >=1) {
					setTempoRecarrega(0);
					setRound(Constantes.METRALHADORA_round);
					setMag(getMag() - 1);
					estado=0;
				}
			}
			
		}
		if (estado==2) {
			
			if (getRound()>0) 
				estado=0;
			setRecarregando(false);

			
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
		
		// TODO Auto-generated method stub
		
		if (temMunicao()) {
			setRound(getRound() - 1);
			CanvasGame.projeteis.add( new Projetil (this,getAngulo(),Constantes.TIPO_ASSASINO_PLAYER ));
			GerenciadorDeSom.m4a.run();
		}
		
	}


	private boolean temMunicao() {
		// TODO Auto-generated method stub
		
		if (getRound()<1) {
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

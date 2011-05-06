import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;


public class He extends Arma {

	
	private boolean soltouTiro=true;
	private boolean semMunicao=false;
	
		
	public int estado=0;
	public He() {
		setMaxMag(Constantes.HE_mag);
		setMaxRound(Constantes.HE_round);

		setTipo(1);
		atirou=false;
		setDano(Constantes.HE_dano);
		setMag(Constantes.HE_mag);
		setPeso(Constantes.HE_peso);
		setRound(Constantes.HE_round);
		setTempoEntreTiros(0);
		setTempoRecarrega(0);
		setValor(Constantes.HE_valor);
	
		setImagem(Imagem.he);
		setImagem_hud(Imagem.he_hud);
		setSizeX(getImagem().getWidth());
		setSizeY(getImagem().getHeight());
		

		
	}
	


	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		dbg.setColor(Color.black);
		AffineTransform trans = dbg.getTransform();
		dbg.translate(getX()-XMundo, getY()-YMundo);
		dbg.rotate(getAngulo()-Math.PI/2);
		//dbg.drawLine(0, 0, getSizeX(), 0);
		
		if (!isRecarregando()&&getRound()>0) {
			dbg.drawImage(getImagem(), 0, +6, getSizeX(),6+getSizeY(),getSizeX(),getSizeY(),0,0,null);
		}
		
		
		dbg.setTransform(trans);
		
		if (estado==1) {
			dbg.setColor(Color.LIGHT_GRAY);
			dbg.fillRect(GamePanel.PWIDTH/2-50, GamePanel.PHEIGHT/2-205,(int)(getTempoRecarrega()*100/Constantes.HE_tempoRecarrega) , 20);

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

			if (getTempoEntreTiros()>=Constantes.HE_tempoEntreTiros) {
				
				if (atirou&&soltouTiro) {	
					soltouTiro=false;
					atira();
					setTempoEntreTiros(0);
					
				}
				
				if (!atirou)
					soltouTiro=true;
			}
		}
		
		if (estado==1) {
			
			
			setTempoRecarrega(getTempoRecarrega() + DiffTime);
			
			setRecarregando(true);
			if (getMag()<1)
				estado=2;
				
			if (getTempoRecarrega()>=Constantes.HE_tempoRecarrega) {
				
				if (getMag() >=1) {
					setTempoRecarrega(0);
					setRound(Constantes.HE_round);
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
	
//	@Override
//	public void recarrega() {
//		setMag(Constantes.HE_mag+1);
//		//setRound(Constantes.HE_round);
//	}

	
	private void atira() {
		
		// TODO Auto-generated method stub
		
		if (temMunicao()) {
			setRound(getRound() - 1);
			Constantes.projeteis.add( new ProjetilGranada(this, getAngulo(), 1, getImagem()));
			//Constantes.de.run();
		}
		
	}


	private boolean temMunicao() {
		// TODO Auto-generated method stub
		
		if (getRound()<1) {
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

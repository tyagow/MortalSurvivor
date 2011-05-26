package Armas;

import java.awt.image.BufferedImage;

import Constantes.Constantes;
import Gerenciadores.GerenciadorDeSom;
import Som.ThreadSom;


public class ArmaTresTorre extends ArmaTorre {

	
	public int estado=0;
	public ArmaTresTorre(BufferedImage img1,BufferedImage img2,ThreadSom _tiro,ThreadSom _tiroHit) {
		super(img1,img2,_tiro,_tiroHit);
		

		setMaxRound(Constantes.TORRE_ARMA_TRES_round);
		tempoRecarrega=Constantes.TORRE_ARMA_TRES_tempoRecarrega;
		setTempoEntreTirosMax(Constantes.TORRE_ARMA_UM_tempoEntreTiros);
		setMaxMag(Constantes.TORRE_ARMA_TRES_mag);
		atirou=false;
		setDano(Constantes.TORRE_ARMA_TRES_dano);
		setMag(Constantes.TORRE_ARMA_TRES_mag);
		setRound(Constantes.TORRE_ARMA_TRES_round);
		setTempoEntreTiros(0);
		setTempoRecarrega(0);
		setValor(Constantes.TORRE_ARMA_TRES_valor);
		setSizeX(getImagem().getWidth());
		setSizeY(getImagem().getHeight());
		estado=0;
		
	}
	
	@Override
	public void atira() {

			setRound(getRound() - 1);
			Constantes.projeteis.add( new Projetil (this,getAngulo(),	Constantes.ID_ARMA_TRES_TORRE,(int)(getX()-Math.cos(getAngulo()-Math.PI/2)*10), (int)(getY()-Math.sin(getAngulo()-Math.PI/2)*10)));

			Constantes.projeteis.add( new Projetil (this,getAngulo(),	Constantes.ID_ARMA_TRES_TORRE ));
			Constantes.projeteis.add( new Projetil (this,getAngulo(),	Constantes.ID_ARMA_TRES_TORRE,(int)(getX()+Math.cos(getAngulo()-Math.PI/2)*10), (int)(getY()+Math.sin(getAngulo()-Math.PI/2)*10)));
			
			GerenciadorDeSom.ak.run();
			
		
	}

}

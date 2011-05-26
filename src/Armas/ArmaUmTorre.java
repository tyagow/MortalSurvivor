package Armas;

import java.awt.image.BufferedImage;

import Constantes.Constantes;
import Gerenciadores.GerenciadorDeSom;
import Som.ThreadSom;


public class ArmaUmTorre extends ArmaTorre {


	
	public ArmaUmTorre(BufferedImage img1,BufferedImage img2,ThreadSom _tiro,ThreadSom _tiroHit) {
		super(img1,img2,_tiro,_tiroHit);		
		
		
		setMaxRound(Constantes.TORRE_ARMA_UM_round);
		tempoRecarrega=Constantes.TORRE_ARMA_UM_tempoRecarrega;
		setTempoEntreTirosMax(Constantes.TORRE_ARMA_UM_tempoEntreTiros);
		setMaxMag(Constantes.TORRE_ARMA_UM_mag);
		atirou=false;
		setDano(Constantes.TORRE_ARMA_UM_dano);
		setMag(Constantes.TORRE_ARMA_UM_mag);
		setRound(Constantes.TORRE_ARMA_UM_round);
		setTempoEntreTiros(0);
		setTempoRecarrega(0);
		setValor(Constantes.TORRE_ARMA_UM_valor);
		estado=0;
		
	}
	@Override
	public void atira() {
//		System.out.println("atira");
		// TODO Auto-generated method stub
		
//		if (temMunicao()) {
			setRound(getRound() - 1);
			
			Constantes.projeteis.add( new Projetil (this,getAngulo(),2 ));
			
			
			
			GerenciadorDeSom.ak.run();
			
//		}
		
	}
	

}

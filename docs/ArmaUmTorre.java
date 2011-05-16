package Armas;

import java.awt.image.BufferedImage;

import Constantes.Constantes;
import Gerenciadores.GerenciadorDeSom;


public class ArmaUmTorre extends ArmaTorre {


	
	public ArmaUmTorre(BufferedImage img) {
		
		maxRound=(Constantes.TORRE_ARMA_UM_round);
		tempoRecarrega=Constantes.TORRE_ARMA_UM_tempoRecarrega;
		timerTempoEntreTiros=(Constantes.TORRE_ARMA_UM_tempoEntreTiros);
		imagem_hud=(img);
		maxMag=(Constantes.TORRE_ARMA_UM_mag);
		atirou=false;
		imagem=(img);
		dano=(Constantes.TORRE_ARMA_UM_dano);
		mag=(Constantes.TORRE_ARMA_UM_mag);
		round=(Constantes.TORRE_ARMA_UM_round);
		timerTempoEntreTiros=(0);
		tempoRecarrega=(0);
		valor=(Constantes.TORRE_ARMA_UM_valor);
		sizeX=(imagem.getWidth());
		sizeY=(imagem.getHeight());
		estado=0;
		
	}
	@Override
	public void atira() {
//		System.out.println("atira");
		// TODO Auto-generated method stub
		
//		if (temMunicao()) {
		round -= 1;
			
			Constantes.projeteis.add( new Projetil (this,angulo,2 ));
			
			
			
			GerenciadorDeSom.ak.run();
			
//		}
		
	}
	

}

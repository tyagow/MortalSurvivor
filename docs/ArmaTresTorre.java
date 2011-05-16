package Armas;

import java.awt.image.BufferedImage;

import Constantes.Constantes;
import Gerenciadores.GerenciadorDeSom;


public class ArmaTresTorre extends ArmaTorre {

	
	public int estado=0;
	public ArmaTresTorre(BufferedImage img) {
		
	
		
		imagem_hud=(img);

		maxRound=(Constantes.TORRE_ARMA_TRES_round);
		tempoRecarrega=Constantes.TORRE_ARMA_TRES_tempoRecarrega;
		tempoEntreTirosMax=(Constantes.TORRE_ARMA_UM_tempoEntreTiros);
		maxMag=(Constantes.TORRE_ARMA_TRES_mag);
		atirou=false;
		imagem=(img);
		dano=(Constantes.TORRE_ARMA_TRES_dano);
		mag=(Constantes.TORRE_ARMA_TRES_mag);
		round=(Constantes.TORRE_ARMA_TRES_round);
		timerTempoEntreTiros=(0);
		tempoRecarrega=(0);
		valor=(Constantes.TORRE_ARMA_TRES_valor);
		sizeX=(imagem.getWidth());
		sizeY=(imagem.getHeight());
		estado=0;
		
	}
	
	@Override
	public void atira() {

			round-=1;
			Constantes.projeteis.add( new Projetil (this,angulo,	Constantes.ID_ARMA_TRES_TORRE,(int)(X-Math.cos(angulo-Math.PI/2)*10), (int)(Y-Math.sin(angulo-Math.PI/2)*10)));

			Constantes.projeteis.add( new Projetil (this,angulo,	Constantes.ID_ARMA_TRES_TORRE ));
			Constantes.projeteis.add( new Projetil (this,angulo,	Constantes.ID_ARMA_TRES_TORRE,(int)(X+Math.cos(angulo-Math.PI/2)*10), (int)(Y+Math.sin(angulo-Math.PI/2)*10)));
			
			GerenciadorDeSom.ak.run();
			
		
	}

}

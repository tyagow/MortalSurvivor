package Armas;

import java.awt.image.BufferedImage;

import Constantes.Constantes;
import Gerenciadores.GerenciadorDeSom;



public class ArmaDoisTorre extends ArmaTorre {



	public int estado=0;
	public ArmaDoisTorre(BufferedImage _img) {
		
		maxRound=(Constantes.TORRE_ARMA_DOIS_round);
		tempoRecarrega=Constantes.TORRE_ARMA_DOIS_tempoRecarrega;
		tempoEntreTirosMax=(Constantes.TORRE_ARMA_UM_tempoEntreTiros);
		maxMag=(Constantes.TORRE_ARMA_DOIS_mag);
	
		atirou=false;
		imagem_hud=(_img);

		imagem=(_img);
		dano=(Constantes.TORRE_ARMA_DOIS_dano);
		mag=(Constantes.TORRE_ARMA_DOIS_mag);
		round=(Constantes.TORRE_ARMA_DOIS_round);
		timerTempoEntreTiros=(0);
		tempoRecarrega=(0);
		valor=(Constantes.TORRE_ARMA_DOIS_valor);
		sizeX=(imagem.getWidth());
		sizeY=(imagem.getHeight());
		estado=0;
		
	}
	@Override
	public void atira() {
//		System.out.println("atira");
		// TODO Auto-generated method stub
		
//		if (temMunicao()) {
			round-= 1;
		
			Constantes.projeteis.add( new Projetil (this,angulo,	Constantes.ID_ARMA_TRES_TORRE,(int)(X-Math.cos(angulo-Math.PI/2)*6), (int)(Y-Math.sin(angulo-Math.PI/2)*5)));


			Constantes.projeteis.add( new Projetil (this,angulo,	Constantes.ID_ARMA_TRES_TORRE,(int)(X+Math.cos(angulo-Math.PI/2)*5), (int)(Y+Math.sin(angulo-Math.PI/2)*5)));
	
			
			
			GerenciadorDeSom.ak.run();
			
//		}
		
	}
	

}

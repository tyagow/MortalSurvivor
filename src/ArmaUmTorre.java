import java.awt.image.BufferedImage;


public class ArmaUmTorre extends ArmaTorre {


	
	public ArmaUmTorre(BufferedImage img) {
		
		setMaxRound(Constantes.TORRE_ARMA_UM_round);
		tempoRecarrega=Constantes.TORRE_ARMA_UM_tempoRecarrega;
		setTempoEntreTirosMax(Constantes.TORRE_ARMA_UM_tempoEntreTiros);
		
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
	

}

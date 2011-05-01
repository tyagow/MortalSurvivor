import java.awt.image.BufferedImage;


public class ArmaTresTorre extends ArmaTorre {

	BufferedImage AnimeSet;
	public int estado=0;
	public ArmaTresTorre(BufferedImage img) {
		
		
		
		setMaxRound(Constantes.TORRE_ARMA_TRES_round);
		tempoRecarrega=Constantes.TORRE_ARMA_TRES_tempoRecarrega;
		setTempoEntreTirosMax(Constantes.TORRE_ARMA_TRES_tempoEntreTiros);
		setMaxMag(Constantes.TORRE_ARMA_TRES_mag);
		atirou=false;
		AnimeSet=img;
		setDano(Constantes.TORRE_ARMA_TRES_dano);
		setMag(Constantes.TORRE_ARMA_TRES_mag);
		setRound(Constantes.TORRE_ARMA_TRES_round);
		setTempoEntreTiros(0);
		setTempoRecarrega(0);
		setValor(Constantes.TORRE_ARMA_TRES_valor);
		setSizeX(AnimeSet.getWidth());
		setSizeY(AnimeSet.getHeight());
		estado=0;
		
	}

}

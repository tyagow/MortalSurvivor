import java.awt.image.BufferedImage;



public class ArmaDoisTorre extends ArmaTorre {


	BufferedImage AnimeSet;
	public int estado=0;
	public ArmaDoisTorre(BufferedImage img) {
		
		setMaxRound(Constantes.TORRE_ARMA_DOIS_round);
		tempoRecarrega=Constantes.TORRE_ARMA_DOIS_tempoRecarrega;
		setTempoEntreTirosMax(Constantes.TORRE_ARMA_DOIS_tempoEntreTiros);
		setMaxMag(Constantes.TORRE_ARMA_DOIS_mag);
		atirou=false;
		AnimeSet=img;
		setDano(Constantes.TORRE_ARMA_DOIS_dano);
		setMag(Constantes.TORRE_ARMA_DOIS_mag);
		setRound(Constantes.TORRE_ARMA_DOIS_round);
		setTempoEntreTiros(0);
		setTempoRecarrega(0);
		setValor(Constantes.TORRE_ARMA_DOIS_valor);
		setSizeX(AnimeSet.getWidth());
		setSizeY(AnimeSet.getHeight());
		estado=0;
		
	}
	

}

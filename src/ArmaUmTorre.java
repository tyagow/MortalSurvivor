import java.awt.image.BufferedImage;


public class ArmaUmTorre extends ArmaTorre {


	
	public ArmaUmTorre(BufferedImage img) {
		
		setMaxRound(Constantes.TORRE_ARMA_UM_round);
		tempoRecarrega=Constantes.TORRE_ARMA_UM_tempoRecarrega;
		setTempoEntreTirosMax(Constantes.TORRE_ARMA_UM_tempoEntreTiros);
		setImagem_hud(img);
		setMaxMag(Constantes.TORRE_ARMA_UM_mag);
		atirou=false;
		setImagem(img);
		setDano(Constantes.TORRE_ARMA_UM_dano);
		setMag(Constantes.TORRE_ARMA_UM_mag);
		setRound(Constantes.TORRE_ARMA_UM_round);
		setTempoEntreTiros(0);
		setTempoRecarrega(0);
		setValor(Constantes.TORRE_ARMA_UM_valor);
		setSizeX(getImagem().getWidth());
		setSizeY(getImagem().getHeight());
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

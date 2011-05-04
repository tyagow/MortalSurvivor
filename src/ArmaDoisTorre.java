import java.awt.image.BufferedImage;



public class ArmaDoisTorre extends ArmaTorre {



	public int estado=0;
	public ArmaDoisTorre(BufferedImage img) {
		
		setMaxRound(Constantes.TORRE_ARMA_DOIS_round);
		tempoRecarrega=Constantes.TORRE_ARMA_DOIS_tempoRecarrega;
		setTempoEntreTirosMax(Constantes.TORRE_ARMA_UM_tempoEntreTiros);
		setMaxMag(Constantes.TORRE_ARMA_DOIS_mag);
	
		atirou=false;
		setImagem_hud(img);

		setImagem(img);
		setDano(Constantes.TORRE_ARMA_DOIS_dano);
		setMag(Constantes.TORRE_ARMA_DOIS_mag);
		setRound(Constantes.TORRE_ARMA_DOIS_round);
		setTempoEntreTiros(0);
		setTempoRecarrega(0);
		setValor(Constantes.TORRE_ARMA_DOIS_valor);
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
		
			CanvasGame.projeteis.add( new Projetil (this,getAngulo(),	Constantes.ID_ARMA_TRES_TORRE,(int)(getX()-Math.cos(getAngulo()-Math.PI/2)*6), (int)(getY()-Math.sin(getAngulo()-Math.PI/2)*5)));


			CanvasGame.projeteis.add( new Projetil (this,getAngulo(),	Constantes.ID_ARMA_TRES_TORRE,(int)(getX()+Math.cos(getAngulo()-Math.PI/2)*5), (int)(getY()+Math.sin(getAngulo()-Math.PI/2)*5)));
	
			
			
			GerenciadorDeSom.ak.run();
			
//		}
		
	}
	

}

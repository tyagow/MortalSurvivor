package Armas;
import Data.Imagem;


public class Deagle extends Pistola {

	public Deagle() {
		// TODO Auto-generated constructor stub
		super();
		setImagem(Imagem.deagle);
		setImagem_hud(Imagem.deagle_hud);
		setSizeX(getImagem().getWidth());
		setSizeY(getImagem().getHeight());
		
	}

	
	
}

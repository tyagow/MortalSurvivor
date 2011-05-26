package Torre;

import java.awt.Graphics2D;

import AbstractClasses.ObjetoImage;
import Armas.Arma;
import Constantes.Constantes;
public class SlotTorre extends ObjetoImage{

	public Arma armaAtiva;
	public Torre torre;

	
	public SlotTorre() {
		sizeX=(Constantes.SLOT_SIZEX+4);
		sizeY=(Constantes.SLOT_SIZEY+2);
		vivo=(true);
		ativo=(false);
		selecionado=(false);
	
	}

	public void setSlot(Arma _armaAtiva){
		
		if (_armaAtiva.imagem_hud!=null)
			AnimeSet=(_armaAtiva.imagem_hud);
		else {
			AnimeSet=(_armaAtiva.imagem);

		}
		
		armaAtiva=(_armaAtiva);
		torre = new Torre ( AnimeSet,armaAtiva,0,0);
	}
	
	@Override
	public void DesenhaSeLayerDois(Graphics2D dbg, int xMundo, int yMundo) {
		dbg.drawRect((int)getX(), (int)getY(), getSizeX(), getSizeY());

	}
}

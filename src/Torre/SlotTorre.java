package Torre;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import AbstractClasses.ObjetoImage;
import Armas.Arma;
import Constantes.Constantes;
import Gerenciadores.GerenciadorReward;
public class SlotTorre extends ObjetoImage{

	public Arma armaAtiva;
	public Torre torre;
	public int valorTorre;

	
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
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		if(selecionado&&GerenciadorReward.cash>=valorTorre){
			
			dbg.setColor(Color.white);
		}else if (selecionado)
				dbg.setColor(Color.lightGray);
		else {
			dbg.setColor(Color.darkGray);
		}
		
		if(ativo){
			if (GerenciadorReward.cash>=valorTorre) {
				dbg.setColor(Color.green);
				
			}
			else 
				dbg.setColor(Color.white);
		}
	
		
		dbg.fillRect((int)X,(int) Y, sizeX, sizeY);
		
		
		AffineTransform trans = dbg.getTransform();
		
//		dbg.scale(1.4, 1.4);
		dbg.drawImage(AnimeSet, null, (int)getX() +getSizeX()/2-AnimeSet.getWidth()/2, (int)getY()+getSizeY()/2-AnimeSet.getHeight()/2+3 );		
		dbg.setTransform(trans);
		
		DesenhaSeLayerDois(dbg,XMundo,YMundo);

	}
	@Override
	public void DesenhaSeLayerDois(Graphics2D dbg, int xMundo, int yMundo) {
		dbg.drawRect((int)getX(), (int)getY(), getSizeX(), getSizeY());

	}
}

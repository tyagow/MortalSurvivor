package Torre;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import AbstractClasses.Objeto;
import Armas.Arma;
import Constantes.Constantes;
public class SlotTorre extends Objeto{

	private BufferedImage AnimeSet;
	private Arma armaAtiva;
	public boolean ativo;
	private boolean selecionado;
	
	public SlotTorre() {
		setSizeX(Constantes.SLOT_SIZEX+4);
		setSizeY(Constantes.SLOT_SIZEY+2);
		setVivo(true);
		setAtivo(false);
		setSelecionado(false);
	
	}
	
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		if(isSelecionado()){
			dbg.setColor(Color.red);
		}else{
			dbg.setColor(Color.black);
		}
		
		if(isAtivo()){
			dbg.setColor(Color.green);
		}
		
		
		dbg.drawImage(getAnimeSet(), null, (int)getX() +getSizeX()/2-getAnimeSet().getWidth()/2, (int)getY()+getSizeY()/2-getAnimeSet().getHeight()/2+3 );
		dbg.drawRect((int)getX(), (int)getY(), getSizeX(), getSizeY());

	}
	
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setSlot(Arma _armaAtiva){
		setAnimeSet(_armaAtiva.getImagem_hud());
		setArmaAtiva(_armaAtiva);
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setAnimeSet(BufferedImage animeSet) {
		AnimeSet = animeSet;
	}

	public BufferedImage getAnimeSet() {
		return AnimeSet;
	}

	public void setArmaAtiva(Arma armaAtiva) {
		this.armaAtiva = armaAtiva;
	}

	public Arma getArmaAtiva() {
		return armaAtiva;
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		
	}
}

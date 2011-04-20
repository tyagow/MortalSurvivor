import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class SlotTorre extends Objeto{

	private BufferedImage AnimeSet;
	private Arma armaAtiva;
	private boolean ativo;
	private boolean selecionado;
	private boolean oldIsMousePressed;
	
	public SlotTorre() {
		setSizeX(Constantes.SLOT_SIZEX);
		setSizeY(Constantes.SLOT_SIZEY);
		setVivo(true);
		setAtivo(false);
	}
	
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		if (Constantes.colideQuadrado((int)getX(), (int)getY(), getSizeX(), getSizeY(), (int)CanvasGame.getMiraAtiva().getX(), (int)CanvasGame.getMiraAtiva().getY(), 1, 1 )) {
			System.out.println("entrou");
			if (!isAtivo()) {
				if (CanvasGame.getMiraAtiva().isPressed()) {
					if(!oldIsMousePressed)
						setAtivo(true);
				}
			}
			setSelecionado(true);
		}else {
			setSelecionado(false);
		}
		
		oldIsMousePressed = CanvasGame.getMiraAtiva().isPressed();
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
		
		dbg.drawRect((int)getX(), (int)getY(), getSizeX(), getSizeY());
		
		dbg.drawImage(AnimeSet, null, (int)getX() + getSizeX()/3, (int)getY() + getSizeY()/5);
	}
	
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setTorre(BufferedImage _AnimeSet, Arma _armaAtiva){
		AnimeSet = _AnimeSet;
		armaAtiva = _armaAtiva;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	public boolean isSelecionado() {
		return selecionado;
	}
}

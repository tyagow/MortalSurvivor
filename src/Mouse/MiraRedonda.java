package Mouse;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Canvas.CanvasGame;
import Constantes.Constantes;
import Data.Imagem;


public class  MiraRedonda extends Mira {

	BufferedImage mira;
	private boolean pressed;
	private boolean released;
	private int button;
	public MiraRedonda() {
		
		setSizeX(Constantes.MOUSE_SIZEX);
		setSizeY(Constantes.MOUSE_SIZEY);
		setVivo(true);
		setPressed(false);
		setReleased(true);
		mira = Imagem.mira1;
		
	}
	
	@Override
	public void SimulaSe(int DiffTime) {

		
		setX(CanvasGame.getMousex());
		setY(CanvasGame.getMousey());
			
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		
		dbg.setColor(Color.black);
		dbg.drawOval((int)getX()-getSizeX()/2, (int)getY()-getSizeY()/2, getSizeX(), getSizeY());
		dbg.drawLine((int)getX(), (int)getY()-getSizeY()/2-4, (int)getX(),(int)getY()-getSizeY()/4); 
		dbg.drawLine((int)getX(), (int)getY()+getSizeY()/2+4, (int)getX(),(int)getY()+getSizeY()/4);
		dbg.drawLine((int)getX()-getSizeX()/2-4, (int)getY(), (int)getX()-getSizeX()/4,(int)getY());
		dbg.drawLine((int)getX()+getSizeX()/2+4, (int)getY(), (int)getX()+getSizeX()/4,(int)getY());
		
	    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		dbg.drawLine((int)getX()-getSizeX()/4, (int)getY(), (int)getX()+getSizeX()/4,(int)getY());
		dbg.drawLine((int)getX(), (int)getY()-getSizeY()/4, (int)getX(),(int)getY()+getSizeY()/4);


	    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

	}
	
	public void pressed(int button) {
	
		this.button=button;
		if (!isPressed()) {
			setReleased(false);
			setPressed(true);
			
		}
		
	}
	public void released(int button) {
		this.button=button;
		setReleased(true);
		setPressed(false);

	}

	public void trataClickMouse1() {
		
		CanvasGame.heroi.trataClick();
		

	}

	public void trataClickMouse2() {
		CanvasGame.gerenciadorTorre.click((int)getXMundo(),(int)getYMundo());

		
	}

	public double getXMundo() {
		
		return getX()+CanvasGame.tela.XTela;
	}
	public double getYMundo() {
		
		return getY()+CanvasGame.tela.YTela;
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}

	public boolean isPressed() {
		return pressed;
	}

	public void setReleased(boolean released) {
		this.released = released;
	}

	public boolean isReleased() {
		return released;
	}

	public void setButton(int button) {
		this.button = button;
	}

	public int getButton() {
		return button;
	}

}

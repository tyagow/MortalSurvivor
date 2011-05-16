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
		
		sizeX=(Constantes.MOUSE_SIZEX);
		sizeY=(Constantes.MOUSE_SIZEY);
		vivo=(true);
		pressed=(false);
		released=(true);
		mira = Imagem.mira1;
		
	}
	
	@Override
	public void SimulaSe(int DiffTime) {

		
		X=(CanvasGame.getMousex());
		Y=(CanvasGame.getMousey());
			
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		
		dbg.setColor(Color.black);
		dbg.drawOval((int)X-sizeX/2, (int)Y-sizeY/2, sizeX, sizeY);
		dbg.drawLine((int)X, (int)Y-sizeY/2-4, (int)X,(int)Y-sizeY/4); 
		dbg.drawLine((int)X, (int)Y+sizeY/2+4, (int)X,(int)Y+sizeY/4);
		dbg.drawLine((int)X-sizeX/2-4, (int)Y, (int)X-sizeX/4,(int)Y);
		dbg.drawLine((int)X+sizeX/2+4, (int)Y, (int)X+sizeX/4,(int)Y);
		
	    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		dbg.drawLine((int)X-sizeX/4, (int)Y, (int)X+sizeX/4,(int)Y);
		dbg.drawLine((int)X, (int)Y-sizeY/4, (int)X,(int)Y+sizeY/4);


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
		
		return X+CanvasGame.tela.XTela;
	}
	public double getYMundo() {
		
		return Y+CanvasGame.tela.YTela;
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

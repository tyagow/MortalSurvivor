package Mouse;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Canvas.CanvasGame;
import Constantes.Constantes;
import Data.Imagem;


public class  MiraJogo extends Mira {

	BufferedImage mira;
	public boolean pressed;
	public boolean released;
	public int button;
	public boolean isOval;
	public Color cor;
	public int timerIA=0;
	private int auxSize;

	public MiraJogo() {
		
		sizeX=20;//(Constantes.MOUSE_SIZEX);
		sizeY=20;//(Constantes.MOUSE_SIZEY);
		vivo=(true);
		pressed=(false);
		released=(true);
		mira = Imagem.mira1;
		
	}
	
	@Override
	public void SimulaSe(int DiffTime) {


		timerIA +=DiffTime;
		
		if (timerIA>1000)
			calculaIA();
		
		X=(CanvasGame.getMousex());
		Y=(CanvasGame.getMousey());
			
	}
	public void calculaIA() {
		if (Constantes.EVENT_atualizaMira)	
			atualizaMira();
		
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		
		dbg.setColor(cor);
		if (isOval)
		dbg.drawOval((int)X-sizeX/2, (int)Y-sizeY/2, sizeX, sizeY);
		
		if (sizeX <15)
			auxSize = 3;
		else
			auxSize =4;
		dbg.drawLine((int)X, (int)Y-sizeY/2-auxSize, (int)X,(int)Y-sizeY/4); 
		dbg.drawLine((int)X, (int)Y+sizeY/2+auxSize, (int)X,(int)Y+sizeY/4);
		dbg.drawLine((int)X-sizeX/2-auxSize, (int)Y, (int)X-sizeX/4,(int)Y);
		dbg.drawLine((int)X+sizeX/2+auxSize, (int)Y, (int)X+sizeX/4,(int)Y);
		
	    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		dbg.drawLine((int)X-sizeX/4, (int)Y, (int)X+sizeX/4,(int)Y);
		dbg.drawLine((int)X, (int)Y-sizeY/4, (int)X,(int)Y+sizeY/4);


	    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

	}
	public void atualizaMira() {
		isOval = Constantes.DADOS_miraDoJogoOval;
		sizeX=Constantes.DADOS_miraSize;
		sizeY=Constantes.DADOS_miraSize;
		cor = Constantes.DADOS_miraCor;
		
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

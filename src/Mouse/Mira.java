package Mouse;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import AbstractClasses.Objeto;
import Canvas.CanvasGame;


public abstract class  Mira extends Objeto {

	BufferedImage mira;
	public boolean pressed;
	public boolean released;
	public int button;
	public int XTela;
	public int YTela;
	//private boolean clicked;
	public Mira() {
		

		setVivo(true);
		setPressed(false);
		setReleased(true);

		
		 //SimulaSe(0);
	}
	
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		
		setX(CanvasGame.getMousex());
		setY(CanvasGame.getMousey());
	
		
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		


//		System.out.println("pressed"+isPressed());
	//	System.out.println(isReleased());

		//dbg.drawImage(mira,(int)X,(int)Y,(int)X+sizeX,(int)Y+sizeY,0,0,(int)mira.getWidth(),(int)mira.getHeight(),null);

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

	public  void trataClickMouse1(){};

	public  void trataClickMouse2() {};
	
	
	public double getXMundo() {
		// TODO Auto-generated method stub
		
		return X+CanvasGame.tela.XTela;
	}
	public double getYMundo() {
		// TODO Auto-generated method stub
		
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

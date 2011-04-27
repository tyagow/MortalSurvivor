import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public abstract class  Mira extends Objeto {

	BufferedImage mira;
	private boolean pressed;
	private boolean released;
	private int button;
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
		
		setX(CanvasGame.mousex);
		setY(CanvasGame.mousey);
	
		
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

	public abstract void trataClickMouse1();

	public abstract void trataClickMouse2() ;
	
	
	public double getXMundo() {
		// TODO Auto-generated method stub
		
		return getX()+CanvasGame.MAPA.MapX;
	}
	public double getYMundo() {
		// TODO Auto-generated method stub
		
		return getY()+CanvasGame.MAPA.MapY;
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

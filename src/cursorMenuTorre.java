import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class  cursorMenuTorre extends Mira {

	BufferedImage mira;
	private boolean pressed;
	private boolean released;
	private int button;
	//private boolean clicked;
	public cursorMenuTorre() {
		
	
		setVivo(true);
		setPressed(false);
		setReleased(true);
		mira = Imagem.miraMenu;
		setSizeX(mira.getWidth());
		setSizeY(mira.getHeight());
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
//		
//		dbg.setColor(Color.black);
//		dbg.drawOval((int)getX()-getSizeX()/2, (int)getY()-getSizeY()/2, getSizeX(), getSizeY());
//		dbg.drawLine((int)getX(), (int)getY()-getSizeY()/2-4, (int)getX(),(int)getY()-getSizeY()/4); 
//		dbg.drawLine((int)getX(), (int)getY()+getSizeY()/2+4, (int)getX(),(int)getY()+getSizeY()/4);
//		dbg.drawLine((int)getX()-getSizeX()/2-4, (int)getY(), (int)getX()-getSizeX()/4,(int)getY());
//		dbg.drawLine((int)getX()+getSizeX()/2+4, (int)getY(), (int)getX()+getSizeX()/4,(int)getY());
//		
//	    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
//		dbg.drawLine((int)getX()-getSizeX()/4, (int)getY(), (int)getX()+getSizeX()/4,(int)getY());
//		dbg.drawLine((int)getX(), (int)getY()-getSizeY()/4, (int)getX(),(int)getY()+getSizeY()/4);
//
//
//	    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

//		System.out.println("pressed"+isPressed());
	//	System.out.println(isReleased());

		dbg.drawImage(mira,(int)getX(),(int)getY(),(int)getX()+getSizeX(),(int)getY()+getSizeY(),0,0,(int)mira.getWidth(),(int)mira.getHeight(),null);

	}
	
	public void pressed(int button) {
	
		this.button=button;
		if (!isPressed()) {
			setReleased(false);
			setPressed(true);
			
		}
		
	}
	public void released() {

		setReleased(true);
		setPressed(false);

	}

	public void trataClickMouse1() {
		// TODO Auto-generated method stub
		
		

	}

	public void trataClickMouse2() {
		// TODO Auto-generated method stub
		CanvasGame.gerenciadorTorre.click((int)getXMundo(),(int)getYMundo());

		
	}

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

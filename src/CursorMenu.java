import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class  CursorMenu extends Mira {

	BufferedImage mira;
	private boolean pressed;
	private boolean released;
	private int button;
	//private boolean clicked;
	public CursorMenu() {
		
	
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
		// TODO 
		GamePanel.getCanvasAtivo();
//		setX(CanvasStart.getMousex());
//		setY(CanvasStart.getMousey());
		

		
		
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		dbg.drawImage(mira,(int)X,(int)Y,(int)X+getSizeX(),(int)Y+getSizeY(),0,0,(int)mira.getWidth(),(int)mira.getHeight(),null);

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
		
		

	}

	public void trataClickMouse2() {

		
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

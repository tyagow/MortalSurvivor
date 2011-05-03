import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;



public abstract class GCanvas {
	private static Mira miraAtiva;

	
	private static double mousex;
	private static double mousey;

	abstract void DesenhaSe(Graphics2D dbg);
	abstract void SimulaSe(long diftime);
	
	abstract void keyPressed(KeyEvent e);
	abstract void keyReleased(KeyEvent e );

	abstract void mouseMoved(MouseEvent e);
	abstract void mouseDragged(MouseEvent e);
		
	abstract void mouseReleased(MouseEvent e);
		

	abstract void mousePressed(MouseEvent e);
		

	abstract void mouseExited(MouseEvent e);
		

	abstract void mouseEntered(MouseEvent e);	


	abstract void mouseClicked(MouseEvent e);
	public static void setMiraAtiva(Mira miraAtiva) {
		GCanvas.miraAtiva = miraAtiva;
	}
	public static Mira getMiraAtiva() {
		return miraAtiva;
	}
	public static void setMousey(double mousey) {
		GCanvas.mousey = mousey;
	}
	public static double getMousey() {
		return mousey;
	}
	public static void setMousex(double mousex) {
		GCanvas.mousex = mousex;
	}
	public static double getMousex() {
		return mousex;
	}

	
}

package Canvas;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;



public abstract class GCanvas {


	public abstract void DesenhaSe(Graphics2D dbg);
	public abstract void SimulaSe(long diftime);
	
	public abstract void keyPressed(KeyEvent e);
	public abstract void keyReleased(KeyEvent e );

	public abstract void mouseMoved(MouseEvent e);
	public abstract void mouseDragged(MouseEvent e);
		
	public abstract void mouseReleased(MouseEvent e);
		

	public abstract void mousePressed(MouseEvent e);
		

	public abstract void mouseExited(MouseEvent e);
		

	public abstract void mouseEntered(MouseEvent e);	


	public abstract void mouseClicked(MouseEvent e);

	
}

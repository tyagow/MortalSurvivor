import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public abstract class GCanvas {
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
	
}

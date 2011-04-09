import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Mira extends Objeto {

	BufferedImage mira;
	public Mira() {
		
		sizeX=40;
		sizeY=40;
		vivo=true;
		mira = Constantes.mira1;
	}
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		
		X=CanvasGame.mousex;
		Y=CanvasGame.mousey;
	
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		
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

		

		//dbg.drawImage(mira,(int)X,(int)Y,(int)X+sizeX,(int)Y+sizeY,0,0,(int)mira.getWidth(),(int)mira.getHeight(),null);

	}

}

package AbstractClasses;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Armas.Arma;
import Constantes.Constantes;
public class ObjetoImage extends Objeto {

	public BufferedImage AnimeSet;
	public boolean ativo;
	public boolean selecionado;
	
	public ObjetoImage() {

	
	}
	public ObjetoImage(int _x,int _y,int _sizeX,int _sizeY, BufferedImage _AnimeSet) {
		sizeX=(_sizeX);
		sizeY=(_sizeY);
		X=_x;
		Y=_y;
		vivo=(true);
		ativo=(false);
		selecionado=(false);
		AnimeSet=_AnimeSet;
	
	}
	
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		if(selecionado){
			dbg.setColor(Color.white);
		}else{
			dbg.setColor(Color.lightGray);
		}
		
		if(ativo){
			dbg.setColor(Color.green);
		}
	
		
		dbg.fillRect((int)X,(int) Y, sizeX, sizeY);
		
		
		AffineTransform trans = dbg.getTransform();
		
//		dbg.scale(1.4, 1.4);
		dbg.drawImage(AnimeSet, null, (int)getX() +getSizeX()/2-AnimeSet.getWidth()/2, (int)getY()+getSizeY()/2-AnimeSet.getHeight()/2+3 );		
		dbg.setTransform(trans);
		
		DesenhaSeLayerDois(dbg,XMundo,YMundo);

	}

	public  void DesenhaSeLayerDois(Graphics2D dbg, int xMundo, int yMundo) {
		
	}
	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		
	}
	public void mouseEntered(MouseEvent e) {
		
	}
	public void mouseExited(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		
	}
	
	public void mouseDragged(MouseEvent arg0) {
		
	}
}

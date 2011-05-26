package shop;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import AbstractClasses.ObjetoImage;
import Constantes.Constantes;
public class SlotInterativo extends ObjetoImage{

Color corAtivo;
	public SlotInterativo(int _x, int _y, int _sizeX, int _sizeY, BufferedImage _AnimeSet) {
			super(_x, _y, _sizeX, _sizeY, _AnimeSet);
			corAtivo=new Color(255,255,255,200);
	}
	@Override
	public  void DesenhaSeLayerDois(Graphics2D dbg, int xMundo, int yMundo) {
		

		
		
	}
	public void mouseReleased(MouseEvent e) {
		if (selecionado) {
			
			if (!ativo)
				ativo=true;
			else 
				ativo=false;
		
		}
			
	}

	public void mouseMoved(MouseEvent e) {
//	
//		if ( (Math.abs(X-e.getX()+Constantes.XTela)) +  Math.abs((Y-e.getY()+Constantes.YTela)) <= 2 ) {
		if (Constantes.colideQuadrado((int)X,(int)Y,getSizeX(),getSizeY(),e.getX()+Constantes.XTela,e.getY()+Constantes.YTela,2,2 )) {

			selecionado=true;
		}else {
			selecionado=false;

		}
	}

	public void mousePressed(MouseEvent e) {
		
	}
	
	public void mouseDragged(MouseEvent arg0) {
		
	}
}

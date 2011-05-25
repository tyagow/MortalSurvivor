package shop;

import java.awt.event.MouseEvent;

import AbstractClasses.ObjetoImage;
import Constantes.Constantes;
public class SlotInterativo extends ObjetoImage{


	
	
	public void mouseReleased(MouseEvent e) {
		if (selecionado) {
			
			if (!ativo)
				ativo=true;
			else 
				ativo=false;
		
		}
			
	}

	public void mouseMoved(MouseEvent e) {
	
		if ( (X-e.getX()+Constantes.XTela) +  (Y-e.getY()+Constantes.YTela) <= 2 ) {
			
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

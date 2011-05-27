package Interface;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Constantes.Constantes;

public class BotaoTela extends Botao {

	public BotaoTela(BufferedImage img, String _name, int _x, int _y,
			int _sizeX, int _fontSize, boolean _isOval) {
		super(img, _name, _x, _y, _sizeX, _fontSize, _isOval);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {

		if (Constantes.colideQuadrado((int)X,(int)Y,sizeX,sizeY,e.getX(),e.getY(),2,2 )) {
			selecionado=true;
			
		}
		else {
			selecionado=false;
		}
	}
	
	
	
}

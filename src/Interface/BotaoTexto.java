package Interface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;

public class BotaoTexto extends BotaoMenuPause {

	public BotaoTexto(BufferedImage img, String _name, int _x, int _y,
			int _sizeX, int _fontSize, boolean _isOval) {
		super(img, _name, _x, _y, _sizeX, _fontSize, _isOval);

		
	}

	
	@Override
	public void caluculaIA(int DiffTime) {
     
		_xDois=_xUm=0;
    
	}   
      
}

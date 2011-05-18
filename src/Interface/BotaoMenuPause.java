package Interface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;

public class BotaoMenuPause extends BotaoTela {
	int tempo = 0;
	public int _yUm;
	public int _xUm;
	public int _yDois;
	public int _xDois;
	public BotaoMenuPause(BufferedImage img, String _name, int _x, int _y,
			int _sizeX, int _fontSize, boolean _isOval) {
		super(img, _name, _x, _y, _sizeX, _fontSize, _isOval);
		
		_xUm =(int) X+sizeX/2;
		_xDois =(int) X+sizeX/2;
		_yUm =(int)Y;
		_yDois=(int)Y;
		
	}
	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		Font temp = dbg.getFont();
		dbg.setFont(font);
		
		 // desenha botao com imagem
				
				dbg.setColor(Color.WHITE);
//				if (selecionado)
//					dbg.fillRect((int)X-XMundo-getSizeX(),(int)Y-YMundo, GamePanel.PWIDTH, getSizeY());
//				dbg.drawRect((int)X-XMundo,(int)Y-YMundo, sizeX,sizeY);
				 Stroke stk = dbg.getStroke();
					dbg.setColor(new Color(250,250,250,100));	
//					dbg.setColor(Color.white);
//				else
//					dbg.setColor(new Color(50,50,50,200));
				if (_xUm!=_xDois) {
			
					    
					    dbg.setStroke(new BasicStroke(1.5f));
					dbg.drawLine((int)_xUm,(int)_yUm, _xDois, _yDois);
					dbg.drawLine((int)_xUm,(int)_yUm+sizeY, _xDois, _yDois+sizeY);
				}
		
				if (selecionado) {
					dbg.setColor(Color.gray);

				dbg.setColor(Color.red );
				}else {
					
					dbg.setColor(Color.white);
				}
				
		
				dbg.drawString(name,(int) X+getSizeX()/2-XMundo-name.length()/2*((dbg.getFont().getSize()*2)/3),(int) Y+getSizeY()/2+(dbg.getFont().getSize()/2)-YMundo);
				
				dbg.setStroke(stk);
;			
		
		dbg.setFont(temp);
	}
	
	@Override
	public void caluculaIA(int DiffTime) {
     
       
         
         
		if (selecionado) {
//			System.out.println("XUM" + DiffTime);
			if (_xUm > X && _xDois <X+sizeX) {
				_xUm -= 2;
	
				_xDois+=2;
			}
			
		}
		else {
			if (_xUm < X+sizeX/2 && _xDois > X+sizeX/2) {	
				_xUm +=1;
	
				_xDois-=1;
			}

		}
			
		
	}
}

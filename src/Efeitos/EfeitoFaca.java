package Efeitos;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class EfeitoFaca extends Particula {
	
	double ang;
	BufferedImage img;
	public EfeitoFaca(double _x,double _y,double _ang,int _tempoTotal,BufferedImage _img ) {
		
		X=_x;
		Y=_y;
		ang = _ang;
		img = _img;
		tempototal=_tempoTotal;
		tempodevida=0;
	}
	
	@Override
	public void SimulaSe(int DiffTime) {
		
		tempodevida+=DiffTime;
		
		X+= Math.cos(ang)*5*DiffTime/1000.0f;
		Y+= Math.sin(ang)*5*DiffTime/1000.0f;
		
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		
	}
	
	
	
	
}

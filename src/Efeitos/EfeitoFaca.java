package Efeitos;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class EfeitoFaca extends Particula {
	
	double ang;
	BufferedImage img;
	public EfeitoFaca(double _x,double _y,double _ang,int _tempoTotal,BufferedImage _img ) {
		
		X= _x + 14;
		Y= _y + 14;
	
		img = _img;
		vivo=true;
		sizeX=img.getWidth();
		sizeY=img.getHeight();
		ang = _ang;
		tempototal=_tempoTotal;
		tempodevida=0;
	}
	
	@Override
	public void SimulaSe(int DiffTime) {
//		System.out.println(tempodevida);
		tempodevida+=DiffTime;
		
		X+= Math.cos(ang)*5*DiffTime/1000.0f;
		Y+= Math.sin(ang)*5*DiffTime/1000.0f;
		if (tempodevida > tempototal)
			vivo=false;
		
//		System.out.println(ang);
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		
		
		AffineTransform trans = dbg.getTransform();
		dbg.translate(X-XMundo, Y-YMundo);
		dbg.rotate(ang+Math.PI/2);
		
//		dbg.drawOval(0/*(int)Math.cos(getAngulo())*50*/,-25/*(int) Math.sin(getAngulo())*50*/,(int) alcanceAtaque,(int)alcanceAtaque); //(int)(+Math.cos(angulo)*20),(int)(Math.sin(angulo)*20)
//		dbg.fillOval((int)(+Math.cos(ang)*20)-13,(int)( Math.sin(ang)*20)-13, 10,10);	//getX(), getY(),alcanceAtaque,in.getX(),in.getY(),in.getSizeX()/2
		
		
		
		dbg.drawImage(img,(int) -15,(int) -15,(int) 15,(int) 15, 0 , 0, sizeX, sizeY, null);
		dbg.setTransform(trans);
		
	}
	
	
	
	
}

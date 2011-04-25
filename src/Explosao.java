import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


public class Explosao extends Particula {
	
	BufferedImage img,img1;
	
	float alfa = 0;
	float diametro = 0;
	double ang ;
	
	
	public Explosao(double X,double Y,int velx,int vely,int tvida,BufferedImage img,BufferedImage img1) {
		// TODO Auto-generated constructor stu
		setX(X);
		setY(Y);
		
		this.velx = velx;
		this.vely = vely;
		
		this.tempodevida = tvida;
		
		setVivo(true) ;
		this.tempototal = 0;
		
		this.img = img;
		//ang=GamePanel.rnd.nextInt(360)+1;
	
	}
	
	@Override
	public void SimulaSe(int DiffTime) {
		tempototal+=DiffTime;
		
		setX(getX() + velx*DiffTime/1000.0f);
		setY(getY() + vely*DiffTime/1000.0f);
		
		if(tempototal>=tempodevida){
			setVivo(false);
		}
		
		alfa = (1.0f - (tempototal/(float)tempodevida));
		diametro =  1.0f-alfa;
		
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		AffineTransform trans = dbg.getTransform();
		
		Composite comp = dbg.getComposite();
		
		
			//dbg.rotate(ang);
			dbg.translate((int)getX()-XMundo, (int)getY()-YMundo);
			
			//dbg.rotate(ang);
			dbg.scale(diametro*1.0f, diametro*1.0f);
			
			dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alfa));
			
			if(diametro<0.2){
				dbg.drawImage(img1, null, -8, -8);
			}else{
				dbg.drawImage(img, null, -8, -8);
			}
		
		dbg.setComposite(comp);	
		
		dbg.setTransform(trans);
	}
}

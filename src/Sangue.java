import java.awt.Color;
import java.awt.Graphics2D;


public class Sangue extends Particula {
	
	Color cor =  null;
	
	int r,g,b;
	
	int alfa = 0;
	
	public Sangue(double X,double Y,double pvx,double pvy,int tvida,Color cor) {
		// TODO Auto-generated constructor stu
		this.X = X;
		this.Y = Y;
		this.velx = (int) pvx;
		this.vely = (int) pvy;
		
		this.tempodevida = tvida;
		
		this.vivo = true;
		this.tempototal = 0;
		
		this.cor = cor;
	
		
		r = cor.getRed();
		g = cor.getGreen();
		b = cor.getBlue();
	}
	
	@Override
	public void SimulaSe(int DiffTime) {
		tempototal+=DiffTime;
		
		X+=velx*DiffTime/1000.0f;
		Y+=vely*DiffTime/1000.0f;
		
		if(tempototal>=tempodevida){
			vivo = false;
		}
		
		alfa = (int)((1.0f - (tempototal/(float)tempodevida))*255);
		
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		dbg.setColor(new Color(r,g,b,alfa));

		dbg.fillRect((int)(X-XMundo),(int)Y-YMundo, 3, 1);
	}
}

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class Texto extends Objeto {
	public int xp;
	double size=8;
	private int time;
	private int tipo;
	private int VelocidadeAumenta;
	private int tempoVida;
	public String text;
	public float alpha = 1.0f;
	
	public Texto(int xp,double x,double y){
		
		this.X=x;
		this.Y=y;
		this.xp=xp;
		vivo=true;
		tipo=1;
		tempoVida=200;
		VelocidadeAumenta=20;
		
	}
	public Texto(){
		
		X=GamePanel.PWIDTH/2;
		Y=GamePanel.PHEIGHT/2;
		vivo=true;
		tipo=2;
		tempoVida=300;
		VelocidadeAumenta=70;
	}

	public Texto(double x, double y, double life) {
		// TODO Auto-generated constructor stub
		this.X=x;
		this.Y=y;
		this.xp=xp;
		vivo=true;
		tipo=3;
		tempoVida=200;
		VelocidadeAumenta=20;
		this.life=life;
		
	}
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		

			alpha-=0.4f*DiffTime/1000.0f;
			if (alpha<0) alpha =0;
//		else alpha-=1.0f*DiffTime/1000.0f;
		
		
		size+=VelocidadeAumenta*DiffTime/1000.0f;
		time+=DiffTime;
		X-=VelocidadeAumenta*DiffTime/1000.0f;
		Y-=VelocidadeAumenta*DiffTime/1000.0f;
		
		if (time/tempoVida>3){
			vivo=false;
			
		}
		
			
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		
			Font oldfont =dbg.getFont();
			Font big = new Font("SansSerif", Font.BOLD, (int) size);
	
			
			dbg.setFont(big);
			//Color.
			if (tipo==1) {
				dbg.setFont(big);
				dbg.setColor(Color.black);
				
			    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
				dbg.drawString("+"+xp+" XP ", (int)X, (int)Y);
							
				dbg.setColor(Color.yellow);
		
				dbg.drawString("+"+xp+" XP ", (int)X-2, (int)Y-2);
			    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
				
			}
			
//			if (tipo==2) {
//				
//
//				dbg.setColor(Color.red);
//			    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
//
//				dbg.drawString("+Level UP "+GamePanel.minhaNave.nivel, (int)X, (int)Y);
//							
//				dbg.setColor(Color.yellow);
//		
//				dbg.drawString("+Level UP "+GamePanel.minhaNave.nivel, (int)X-5, (int)Y-5);
//			    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
//
//			
//		}		
			
			if (tipo==3) {
			
			dbg.setColor(Color.DARK_GRAY);
			
		    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			dbg.drawString("+"+(int)life+" life ", (int)X, (int)Y);
						
			dbg.setColor(Color.green);
	
			dbg.drawString("+"+(int)life+" life ", (int)X-3, (int)Y-3);
		    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		    System.out.println(alpha);
		}
			
			
			
				dbg.setFont(oldfont);

		
	
	}
}

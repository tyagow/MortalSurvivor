import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class TextoExtraXp extends Objeto {
	private int xp;
	double size=10;
	private int time;
	private int tipo;
	private int VelocidadeAumenta;
	private int tempoVida;
	public String text;
	public float alpha = 1.0f;
	Font big;
	public TextoExtraXp(int xp,double x,double y){
		
		setX(x);
		setY(y);
		this.xp=xp*3;
		setVivo(true);
		tipo=1;
		tempoVida=150;
		VelocidadeAumenta=30;
		big= new Font("SansSerif", Font.BOLD, (int) size);
		
	}
	
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		

			alpha-=0.4f*DiffTime/1000.0f;
			if (alpha<0) alpha =0;
//		else alpha-=1.0f*DiffTime/1000.0f;
		
		
		size+=VelocidadeAumenta*DiffTime/1000.0f;
		time+=DiffTime;
		setX(getX() - (VelocidadeAumenta*DiffTime/1000.0f));
		setY(getY() - (VelocidadeAumenta*DiffTime/1000.0f));
		
		if (time/tempoVida>3){
			setVivo(false);
			
		}
		big= new Font("SansSerif", Font.BOLD, (int) size);
		
			
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		
			Font oldfont =dbg.getFont();
	
	
			
			dbg.setFont(big);

				dbg.setFont(big);
				dbg.setColor(Color.blue);
				
			    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
				dbg.drawString("+"+xp+" XP Fast Kill ", (int)getX()-XMundo, (int)getY()-YMundo);
							
				dbg.setColor(Color.CYAN);
		
				dbg.drawString("+"+xp+" XP Fast Kill ", (int)getX()-2-XMundo, (int)getY()-2-YMundo);
			    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
				
	

			
			
			
				dbg.setFont(oldfont);

		
	
	}
}

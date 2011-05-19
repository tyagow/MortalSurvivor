package Efeitos;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import AbstractClasses.Objeto;
import GameState.GamePanel;


public class Texto extends Objeto {
	private int xp;
	public double size=8;
	private int time;
	public int tipo;
	private int VelocidadeAumenta;
	private int tempoVida;
	public String text;
	public float alpha = 1.0f;
	public Font big;
	public Color cor;
	public Texto(int xp,double x,double y){
		
		setX(x);
		setY(y);
		this.xp=xp;
		setVivo(true);
		tipo=1;
		tempoVida=200;
		VelocidadeAumenta=20;
		big= new Font("SansSerif", Font.BOLD, (int) size);
		
	}
	public Texto(){
		
		setX(GamePanel.PWIDTH/2);
		setY(GamePanel.PHEIGHT/2);
		setVivo(true);
		tipo=2;
		tempoVida=-1;
		//VelocidadeAumenta=70;
		big= new Font("SansSerif", Font.BOLD, (int) size);

	}

	public Texto(double x, double y, double _life) {
		// TODO Auto-generated constructor stub
		X=(x);
		Y=(y);
		vivo=(true);
		tipo=3;
		tempoVida=200;
		VelocidadeAumenta=10;
		life=((int) _life);
		
	}
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub

		if (tipo !=-1 ) {
			big= new Font("SansSerif", Font.BOLD, (int) size);

			alpha-=0.4f*DiffTime/1000.0f;
			if (alpha<0) alpha =0;
//		else alpha-=1.0f*DiffTime/1000.0f;

			size+=VelocidadeAumenta*DiffTime/1000.0f;
			time+=DiffTime;
			X-=((VelocidadeAumenta*DiffTime/1000.0f));
			Y-=((VelocidadeAumenta*DiffTime/1000.0f));
		
			if (time/tempoVida>3){
				vivo=(false);
			}
		}
		
			
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		
			Font oldfont =dbg.getFont();
	
	
			
			dbg.setFont(big);
			if (tipo==1) {
				dbg.setFont(big);
				dbg.setColor(Color.black);
				
//			    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
				dbg.drawString("+"+xp+" XP ", (int)getX()-XMundo, (int)getY()-YMundo);
							
				dbg.setColor(Color.yellow);
		
				dbg.drawString("+"+xp+" XP ", (int)getX()-2-XMundo, (int)getY()-2-YMundo);
//			    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
				
			}
			
			if (tipo==-1) {
				

				dbg.setColor(cor);
			    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

				dbg.drawString(text, (int)X, (int)Y);
							
		
			    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

			
		}		
			
			if (tipo==3) {
			
			dbg.setColor(Color.DARK_GRAY);
			
		    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			dbg.drawString("+"+(int)getLife()+" life ", (int)getX(), (int)getY());
						
			dbg.setColor(Color.green);
	
			dbg.drawString("+"+(int)getLife()+" life ", (int)getX()-3, (int)getY()-3);
		    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		    //System.out.println(alpha);

		}
			
			
			
				dbg.setFont(oldfont);

		
	
	}
}

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;


public class Projetil extends Objeto {
	
	int vel=800;
	double ang;
	int tipo;
	Objeto pai;
	public Projetil(Objeto pai,double ang,int tipo){
		// TODO Auto-generated constructor stub
		this.pai = pai;

		this.X = pai.X+pai.sizeX/2-2;
		this.Y = pai.Y+pai.sizeY/2-2;
		this.ang = ang;
		
		vivo = true;
		sizeX=8;
		sizeY=4;
	}
	
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub

		oldx=X;
		oldy=Y;
		X+=Math.cos(ang)*vel*DiffTime/1000.0f;
		Y+=Math.sin(ang)*vel*DiffTime/1000.0f;
		

		if(X<0||X>=(GamePanel.PWIDTH)|| Y<0||Y>=(GamePanel.PHEIGHT)) {
			X = oldx;
			Y = oldy;
			vivo = false;
			ativaParticulas();

		}
//		if(Y<0){
//			Y = oldy;
//			vivo = false;
//			ativaParticulas();
//
//		}
//
//		if(X>=(GamePanel.PWIDTH)){
//			X = oldx;
//			vivo = false;
//			ativaParticulas();
//		}
//		if(Y>=(GamePanel.PHEIGHT)){
//			Y = oldy;
//			vivo = false;
//			ativaParticulas();
//
//		}		
		
		

	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		
		dbg.setColor(Color.black);
		
		AffineTransform trans = dbg.getTransform();
		dbg.translate(X+sizeX/2-1, Y+sizeY/2-1);
		dbg.rotate(ang);
		//dbg.drawLine(0, 0, 5, 0);
		dbg.fillOval(-sizeX/2,-sizeY/2, sizeX,sizeY);


		dbg.setTransform(trans);		
		
		
	}
	
	public void ativaParticulas() {
		Color cor;
		double velx = Math.cos(ang)*1000;
		double vely= Math.sin(ang)*1000;
		for(int B = 0; B < 30;B++){
			int modv = GamePanel.rnd.nextInt(600)+50;
			
			double pvx = 0;
			double pvy = 0;
		
				pvx = velx + modv;
				pvy = vely - modv;

			
			pvx = (int)(pvx*(0.21+0.25*GamePanel.rnd.nextFloat()));
			pvy = (int)(pvy*(0.21+0.25*GamePanel.rnd.nextFloat()));
			
			if(GamePanel.rnd.nextBoolean()){
				cor = Color.red;
			}else{
				cor = Color.red;
			}
		
			Particula part = (Particula)new Sangue(X,Y,-pvx/6,-pvy/6,GamePanel.rnd.nextInt(400)+100,cor);
			
			CanvasGame.particulas.add(part);
			
		}

	}





}

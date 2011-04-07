import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Iterator;


public class Projetil extends Objeto {
	
	int vel=800;
	double ang;
	int tipo;
	Arma pai;
	private double dano;
	public Projetil(Arma pai,double ang,int tipo){
		// TODO Auto-generated constructor stub
		this.pai = pai;

		this.X = pai.X;
		this.Y = pai.Y;
		this.ang = ang;
		this.dano=pai.dano;
		vivo = true;
		sizeX=4;
		sizeY=2;
	}
	
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		oldx=X;
		oldy=Y;
		X+=Math.cos(ang)*vel*DiffTime/1000.0f;
		Y+=Math.sin(ang)*vel*DiffTime/1000.0f;
		
		if((int)X<0||(int)X>=(GamePanel.PWIDTH)|| (int)Y<0||(int)Y>=(GamePanel.PHEIGHT)) {
			
			X = oldx;
			Y = oldy;
			vivo = false;
			
		}
		
		for(int i = 0; i < CanvasGame.inimigos.size();i++){
			Inimigo inim = CanvasGame.inimigos.get(i);
			if (Constantes.colidecircular(X+sizeX/2, Y+sizeY/2,sizeX/2,inim.X+inim.sizeX/2,inim.Y+inim.sizeY/2,inim.sizeX/2)) {
				
				CanvasGame.inimigos.get(i).life-=dano;
				CanvasGame.gerenciadorEfeitos.ativaSangue(X,Y,ang,(int)dano);
				vivo=false;
				
				break;
				}
			}
			
		}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		
		dbg.setColor(Color.black);
	
		AffineTransform trans = dbg.getTransform();
		dbg.translate(X+sizeX/2-1, Y+sizeY/2-1);
		dbg.rotate(ang);
		dbg.fillOval(-sizeX/2,-sizeY/2, sizeX,sizeY);

		dbg.setTransform(trans);		
		
		
	}
//	
//	public void ativaParticulas() {
//		Color cor;
//		double velx = Math.cos(ang)*1000;
//		double vely= Math.sin(ang)*1000;
//		for(int B = 0; B < 100;B++){
//			int modv = GamePanel.rnd.nextInt(600)+50;
//			
//			double pvx = 0;
//			double pvy = 0;
//		
//				pvx = velx + modv;
//				pvy = vely - modv;
//
//			
//			pvx = (int)(pvx*(0.21+0.25*GamePanel.rnd.nextFloat()));
//			pvy = (int)(pvy*(0.21+0.25*GamePanel.rnd.nextFloat()));
//			
//			if(GamePanel.rnd.nextBoolean()){
//				cor = Color.red;
//			}else{
//				cor = Color.red;
//			}
//		
//			Particula part = (Particula)new Sangue(X,Y,-pvx/6,-pvy/6,GamePanel.rnd.nextInt(400)+100,cor);
//			
//			//CanvasGame.particulas.add(part);
//			
//		}
//
//	}





}

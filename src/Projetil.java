import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Iterator;


public class Projetil extends Objeto {
	
	int vel=800;
	double ang;
	int tipo;
	Arma pai;
	private int dano;
	public Projetil(Arma pai,double ang,int tipo){
		// TODO Auto-generated constructor stub
		this.pai = pai;
		this.ang = ang;

		this.setX(pai.getX());
		this.setY(pai.getY());

		this.tipo=tipo;
		this.dano=pai.getDano();
		setVivo(true);
		setSizeX(4);
		setSizeY(2);
	}
	
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		setOldx((int)getX());
		setOldy((int)getY());
		
		setX(getX() + (Math.cos(ang)*vel*DiffTime/1000.0f));
		setY(getY() + (Math.sin(ang)*vel*DiffTime/1000.0f));
		
		if((int)getX()<0||(int)getX()>=(CanvasGame.largura)|| (int)getY()<0||(int)getY()>=(CanvasGame.altura)) {
			
			setX(getOldx());
			setY(getOldy());
			setVivo(false);
			
		}
		
		for(int i = 0; i < CanvasGame.inimigos.size();i++){
			Inimigo inim = CanvasGame.inimigos.get(i);
			if (Constantes.colidecircular(getX(), getY(),getSizeX()/2,inim.getX(),inim.getY(),inim.getSizeX()/2)) {
				
		
			CanvasGame.inimigos.get(i).recebeuDano(dano,tipo);
			CanvasGame.gerenciadorEfeitos.ativaSangue(getX(),getY(),ang,(int)dano);
				
				
				setVivo(false);
				
				break;
				}
			}
			
		}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		
		dbg.setColor(Color.black);
	
		AffineTransform trans = dbg.getTransform();
		dbg.translate(getX()-XMundo, getY()-YMundo);
		dbg.rotate(ang);
		dbg.fillOval(-getSizeX()/2,-getSizeY()/2, getSizeX(),getSizeY());

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

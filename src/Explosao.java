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
	int dano;

	private boolean first=true;
	
	public Explosao(double X,double Y,int velx,int vely,int tvida,BufferedImage img,BufferedImage img1) {
		// TODO Auto-generated constructor stu
		setX(X);
		setY(Y);
		
		this.velx = velx;
		this.vely = vely;
		
		this.tempodevida = tvida;
		
		setVivo(true) ;
		this.tempototal = 0;
		dano = Constantes.HE_dano;
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
		
		
		if (first) {
			verificaColisaInimigos();
			
		}
		
	}

	private void verificaColisaInimigos() {
		// TODO Auto-generated method stub
		
		for (int i = 0;i<GerenciadorDeRaids.getRaids().size();i++) {
			Raid ra = GerenciadorDeRaids.getRaids().get(i);
		
			for (int j = 0;j<ra.inimigos.size();j++) {
				Inimigo in = ra.inimigos.get(j);
				
				
				if (Constantes.colidecircular(getX(), getY(),Constantes.HE_RANGE,in.getX(),in.getY(),in.getSizeX()/2)) {

					
					GerenciadorDeRaids.getRaids().get(i).inimigos.get(j).recebeuDano(dano,Constantes.TIPO_ASSASINO_PLAYER);
					//CanvasGame.gerenciadorEfeitos.ativaSangue(getX(),getY(), GamePanel.rnd.nextInt(360)*Math.PI/360,1);

					
				}
			}

		}
		
		first=false;
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

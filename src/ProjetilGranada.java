import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


public class ProjetilGranada extends Projetil {

		int objX,objY;
		int distancia;
		BufferedImage imagem;
		
	public ProjetilGranada(Arma pai, double ang, int tipo,BufferedImage img) {
		super(pai, ang, tipo);
		
		imagem=img;
       
		int objX =(int)(CanvasGame.getMiraAtiva().getXMundo());
		int objY=(int)( CanvasGame.getMiraAtiva().getYMundo());
		

		
		
		setSizeX(img.getWidth());
		setSizeY(img.getHeight());
		// TODO Auto-generated constructor stub
	}

		
		@Override
		public void SimulaSe(int DiffTime) {
			// TODO Auto-generated method stub
			setOldx((int)getX());
			setOldy((int)getY());
			
			setX(getX() + (Math.cos(ang)*vel*DiffTime/1000.0f));
			setY(getY() + (Math.sin(ang)*vel*DiffTime/1000.0f));
			
			
			
			boolean chegouObjetivo = ((int)getX()==objX&&(int)getY()==objY);
			System.out.println(chegouObjetivo);
			if((int)getX()<0||(int)getX()>=(CanvasGame.largura)|| (int)getY()<0||(int)getY()>=(CanvasGame.altura) ||chegouObjetivo) {
				
				setX(getOldx());
				setY(getOldy());
				setVivo(false);
				
			}
	
		}
	//
//		private List<Inimigo> extracted() {
//			return ((List<Inimigo>) CanvasGame.gerenciadorDeRaids);
//		}

		@Override
		public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
			// TODO Auto-generated method stub
			System.out.println((int)getX());
			System.out.println(objX);
			System.out.println("obj de verdade:"+(int) CanvasGame.getMiraAtiva().getX());
			dbg.setColor(Color.black);
		
			AffineTransform trans = dbg.getTransform();
			dbg.translate(getX()-XMundo, getY()-YMundo);
			dbg.rotate(ang);
			//dbg.fillOval(-getSizeX()/2,-getSizeY()/2, getSizeX(),getSizeY());
			
						dbg.drawImage(imagem, -getSizeX()/2,-getSizeX()/2,getSizeX()/2,getSizeY()/2,0,0,getSizeX(),getSizeY(),null);

			dbg.setTransform(trans);		
			
			
		}
	//	
//		public void ativaParticulas() {
//			Color cor;
//			double velx = Math.cos(ang)*1000;
//			double vely= Math.sin(ang)*1000;
//			for(int B = 0; B < 100;B++){
//				int modv = GamePanel.rnd.nextInt(600)+50;
//				
//				double pvx = 0;
//				double pvy = 0;
//			
//					pvx = velx + modv;
//					pvy = vely - modv;
	//
//				
//				pvx = (int)(pvx*(0.21+0.25*GamePanel.rnd.nextFloat()));
//				pvy = (int)(pvy*(0.21+0.25*GamePanel.rnd.nextFloat()));
//				
//				if(GamePanel.rnd.nextBoolean()){
//					cor = Color.red;
//				}else{
//					cor = Color.red;
//				}
//			
//				Particula part = (Particula)new Sangue(X,Y,-pvx/6,-pvy/6,GamePanel.rnd.nextInt(400)+100,cor);
//				
//				//CanvasGame.particulas.add(part);
//				
//			}
	//
//		}

		public void setDano(int dano) {
			this.dano = dano;
		}

		public int getDano() {
			return dano;
		}





	}

	
	


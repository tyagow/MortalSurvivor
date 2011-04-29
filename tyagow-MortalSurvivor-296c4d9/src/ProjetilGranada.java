import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


public class ProjetilGranada extends Projetil {

		double objX,objY;
		int distancia;
		BufferedImage imagem;
		private double angDesenho;
		
	public ProjetilGranada(Arma pai, double ang, int tipo,BufferedImage img) {
		super(pai, ang, tipo);
		
		imagem=img;
       
		 objX =(int)(CanvasGame.getMiraAtiva().getXMundo());
		 objY=(int)( CanvasGame.getMiraAtiva().getYMundo());
		 
		 
		 
		 
		vel=500;
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
	
			boolean chegouObjetivo;
			
			boolean oX;
			boolean oY;

			if (objX >= getX()) {
				   oX =(objX - getX() <= 1) ;
			   }else {
				   oX =( getX()-objX <= 1) ;
			   }	
			   
			   if (objY >= getY()) {
				   oY =(objY - getY() <= 1);
 
			   }else {
				   oY =(getY() -objY <= 1);
			   }
			//chegouObjetivo=oX&&oY;
			
			   
			   chegouObjetivo=Constantes.colidecircular(getX(), getY(), 5, objX, objY, 5);
			//Constantes.colidecircular(getX(), getY(), 5, objX, objY, 5);
		
			
			vel-=DiffTime/1000.0f;
			angDesenho += Math.PI*DiffTime/1000.0f;
			
			
			for (int i = 0;i<GerenciadorDeRaids.getRaids().size();i++) {
				Raid ra = GerenciadorDeRaids.getRaids().get(i);
			
				for (int j = 0;j<ra.inimigos.size();j++) {
					Inimigo in = ra.inimigos.get(j);
					

					if (Constantes.colidecircular(getX(), getY(),getSizeX()/2,in.getX(),in.getY(),in.getSizeX()/2)) {
					
			
				
						setVivo(false);
						
						
						
					}
				}
				
			}

		
			
			if((int)getX()<0||(int)getX()>=(CanvasGame.largura)|| (int)getY()<0||(int)getY()>=(CanvasGame.altura) ||chegouObjetivo) {
				
				setVivo(false);
				
			}
			if (!isVivo()) {
				CanvasGame.gerenciadorEfeitos.explosao(getX(),getY(),100,100);

				Sound.music("sound/explode6.wav",false);
			}
			
		
	}
	//
//		private List<Inimigo> extracted() {
//			return ((List<Inimigo>) CanvasGame.gerenciadorDeRaids);
//		}

		@Override
		public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
			// TODO Auto-generated method stub

			dbg.setColor(Color.black);
		
			AffineTransform trans = dbg.getTransform();
			dbg.translate(getX()-XMundo, getY()-YMundo);
			dbg.rotate(angDesenho);
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

	
	


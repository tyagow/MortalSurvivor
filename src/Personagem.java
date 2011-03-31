import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;


public class Personagem extends Objeto {
	Color cor;
	boolean LEFT,RIGHT,UP,DOWN;
	int velx,vely;
	double ang;
	private int VelMaxFrente=200;
	private int VelMaxX=200;
	private double vel=40;
	private double angm;
	private double VelMaxYTraz=80;
	private double VelMaxLado=130;
	private int VelMaxY=200;
	public boolean ATIRA;
	private int timertiro=0;
	
	public Personagem(int x,int y) {
		cor=Color.black;
		this.X=x;
		this.Y=y;
		sizeX=20;
		sizeY=20;
		
	}
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		calculaIA(DiffTime);
		oldx=X;
		oldy=Y;
		
		X+=Math.cos(angm)*vel*DiffTime/1000.0f; 
		Y+=Math.sin(angm)*vel*DiffTime/1000.0f; 	
//		X+=velx*DiffTime/1000.0f; 
//		Y+=vely	*DiffTime/1000.0f; 
		
		if (X+sizeX+1 >=GamePanel.PWIDTH || Y+sizeY+1>=GamePanel.PHEIGHT  || Y-1 <=0 || X-1<=0) {
			
			X=oldx;
			Y=oldy;
		}
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		dbg.setColor(cor);
		dbg.fillOval((int)X,(int) Y, sizeX,sizeY);
		
		AffineTransform trans = dbg.getTransform();
		dbg.translate(X+sizeX/2-XMundo, Y+sizeY/2-YMundo);
		dbg.rotate(ang);
		dbg.drawLine(0, 0, 30, 0);
		dbg.setColor(cor);

		dbg.setTransform(trans);		
		
	}
	private void calculaIA(int DiffTime) {
		// TODO Auto-generated method stub

		
		//Timers
		timertiro+=DiffTime;
		
		
		// Movimentação
//			if(UP){
//				 if (vely >-VelMaxY)
//					 vely -= 10;
//				 else vely=-VelMaxY;
//			}else if(DOWN){
//				if (vely <VelMaxY)
//					vely += 10;
//				else vely=VelMaxY;
//			}else{
//				vely = 0;
//			}
//		
//			if(LEFT){
//				 if (velx >-VelMaxX)
//					 velx -= 10;
//				 else velx=-VelMaxX;
//				 
//			}else if(RIGHT){
//				if (velx <VelMaxX)	
//					velx += 10;
//				else velx=VelMaxX;
//			}else{
//				velx = 0;
//			}
//		
		
		
		boolean c=false;
		// Movimentação seguindo o mouse
			if(UP){
				 if (vel <VelMaxFrente) {
					 vel += 15;
					 angm=ang;
				 }
				 else {
					 angm=ang;
					 vel=VelMaxFrente;
				 }
				 
				 c=true;
			}
			if(DOWN){
				if (vel <VelMaxYTraz) {
					angm =ang+ Math.PI;
					vel += 8;
				}else {
					vel=VelMaxYTraz;
					angm =ang+ Math.PI;
					
				}
				c=true;
			}
			
//			
			if(LEFT){
				 if (vel <VelMaxLado) {
					angm=ang-Math.PI/2; 
					 vel += 10;
				 }
				 else {
					 angm=ang-Math.PI/2;
					 vel=VelMaxLado;
				 }
				c=true;

			}
			if(RIGHT){
				if (vel <VelMaxLado) {
					angm =ang+Math.PI/2 ;
					vel += 10;
				}
				else {
					angm=ang+Math.PI/2;
					vel=VelMaxLado;
				}
				c=true;

			}
			if (!c) vel=0;

//			
		
		// Angulo mouse

			int px = CanvasGame.mousex-(int)X;
			int py = CanvasGame.mousey-(int)Y;
			ang = Math.atan2(py, px);
			
			
			if (ATIRA) {
				
				atira();
			}
			
			
		
		
		
	}
	private void atira() {
		// TODO Auto-generated method stub
		if (timertiro>=Constantes.PERSONAGEM_TEMPO_TIRO) {
			CanvasGame.projeteis.add( new Projetil (this,ang,1 ));
			timertiro=0;
		}
		
	}
}

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;


public class Heroi extends Objeto {
	Color cor;
	boolean LEFT,RIGHT,UP,DOWN;
	double ang;
	private int VelMaxFrente=200;
	private double VelMaxTras=80;
	private double VelMaxLado=130;
	
	
	private double vel;
	private double angMovimentacao;
	public boolean ATIRA=false;
	private int timertiro=0;
	private double maximoVida=100;
	
	private Arma armaMelee=new Faca();
	private Arma armaPrimaria;
	private Arma armaSecundaria=new Pistola();
	private Arma ultimaArma;
	private Arma armaAtiva=armaSecundaria;
	
	
	private boolean PRIMARIA=false;
	boolean SECUNDARIA=true;
	boolean MELEE=false;
	private boolean ARMA_ANTERIOR=false;

	
	
	public Heroi(int x,int y) {
		cor=Color.black;
		this.X=x;
		this.Y=y;
		sizeX=20;
		sizeY=20;
		life=100;
		vivo=true;
	
		
	}
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub

		if (vivo) {
	
			calculaIA(DiffTime);
			oldx=X;
			oldy=Y;
			
			X+=Math.cos(angMovimentacao)*vel*DiffTime/1000.0f; 
			Y-=Math.sin(angMovimentacao)*vel*DiffTime/1000.0f; 	
	 
			
			if (X+sizeX+1 >=GamePanel.PWIDTH || Y+sizeY+1>=GamePanel.PHEIGHT  || Y-1 <=0 || X-1<=0) {
				
				X=oldx;
				Y=oldy;
			}
			armaAtiva.definePosicaoArma(ang, X+sizeX/2, Y+sizeY/2);
			armaAtiva.SimulaSe(DiffTime);
		}
		
		
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		if (vivo) {

		armaAtiva.DesenhaSe(dbg, XMundo, YMundo);
			
		dbg.setColor(cor);
		dbg.fillOval((int)X,(int) Y, sizeX,sizeY);
		
		
		
		
		///// VIDA TEMPORARIO ## FAZER HUD
		dbg.drawRect((int)X-5, (int)Y-17, 30, 10);
		dbg.setColor(Color.green);
		dbg.fillRect((int)X-5+1, (int)Y-16, (int)(life*30/maximoVida)-1, 9);
		}
		
		
	}
	private void calculaIA(int DiffTime) {
		// TODO Auto-generated method stub

		if (life<0)
			vivo =false;
//		
		//Timers
		timertiro+=DiffTime;
		
		
		System.out.println("ang "+(int)Math.toDegrees(ang));
		int angAux = (int)Math.toDegrees(ang);
		
		if(angAux<0){
			angAux+=360;
		}
		

		int angMovtmp = (int)Math.toDegrees(angMovimentacao);
		
		if(angMovtmp<0){
			angMovtmp+=360;
		}
		
		System.out.println(" angAux "+angAux+" angMovtmp "+angMovtmp);
		
		int anguloDif = Math.abs(angAux - angMovtmp);

		System.out.println(" anguloDif "+anguloDif);
		
		if(anguloDif>180){
			anguloDif=360-anguloDif;
		}
		
		System.out.println(" anguloDifFinal "+anguloDif);
		
		if(anguloDif<45){
			vel = VelMaxFrente;
			//System.out.println("VelMaxFrente");
		}else if(anguloDif<90){
			vel = VelMaxLado;
			//System.out.println("VelMaxLado");
		}else{
			vel = VelMaxTras;
			//System.out.println("VelMaxTras");
		}
		
//		if (angMovimentacao >= angAux)
//			anguloDif=angMovimentacao-angAux;
//		else
//			anguloDif=angAux-angMovimentacao;
//
//		
//		if (anguloDif<0)
//			anguloDif*=-1;
//
//		if (anguloDif<=Math.PI/4) {
//			vel = VelMaxFrente;
//		}
//		if (anguloDif>Math.PI/4 && anguloDif<=3*Math.PI/4) {
//			vel = VelMaxLado;
//		}
//		if(anguloDif>3*Math.PI/4 ){
//			vel = VelMaxTras;
//		}
		
		if(UP){ 
			
			angMovimentacao=Math.PI/2;
			
		}else if(DOWN){
			angMovimentacao=Math.PI*3/2;
		}else if(LEFT){
			angMovimentacao=Math.PI;

		}else if(RIGHT){
			angMovimentacao=0;
		
		}else vel = 0;
		
//		System.out.println("angaux "+angAux);
//		System.out.println("angmov "+angMovimentacao);
//		System.out.println("angdif "+anguloDif);

//		System.out.println("vel "+vel);
		//boolean c=false;
		
		// Angulo mouse

			int px = (int)X-CanvasGame.mousex;
			int py = (int)Y-CanvasGame.mousey;
			ang = Math.atan2(py, px);
			

			
			
			if (ATIRA) {
				
				armaAtiva.atirou = true;
			}
			else 
				armaAtiva.atirou = false;
			
			if (PRIMARIA) {
				ultimaArma=armaAtiva;
				armaAtiva=armaPrimaria;
				PRIMARIA=false;
			}
			else if (SECUNDARIA)  {
				ultimaArma=armaAtiva;
				armaAtiva=armaSecundaria;
				SECUNDARIA=false;
			}
			else if (MELEE) {
				ultimaArma=armaAtiva;
				armaAtiva=armaMelee;
				MELEE=false;
			}
			else if (ARMA_ANTERIOR) 
				armaAtiva=ultimaArma;
				ARMA_ANTERIOR=false;
		
		
	}

	public void respaw(int X,int Y) {
		
		this.X=X;
		this.Y=Y;
		vivo=true;	
		life=maximoVida;
		armaAtiva.recarrega();
		
	}

}

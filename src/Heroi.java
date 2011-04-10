import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;


public class Heroi extends Objeto {
	Color cor;
	boolean LEFT,RIGHT,UP,DOWN;
	double ang;
	private int VelMaxFrente=350;
	private double VelMaxTras=200;
	private double VelMaxLado=270;
	
	
	private double vel;
	private double angMovimentacao;
	private boolean Atira=false;
	private int maximoVida=100;
	
	private Arma armaMelee=new Faca();
	private Arma armaPrimaria;
	private Arma armaSecundaria=new Pistola();
	private Arma ultimaArma;
	private Arma armaAtiva=armaSecundaria;
	
	
	boolean PRIMARIA=false;
	boolean SECUNDARIA=true;
	boolean MELEE=false;
	boolean ARMA_ANTERIOR=false;
	private int larguraMapa,alturaMapa;

	
	
	public Heroi(int x,int y) {
		cor=Color.black;
		this.setX(x);
		this.setY(y);
		setSizeX(20);
		setSizeY(20);
		setLife(100);
		setVivo(true);
		larguraMapa=CanvasGame.MAPA.Largura*16;
		alturaMapa=CanvasGame.MAPA.Altura*16;

		
	}
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub

		if (isVivo()) {
	
			calculaIA(DiffTime);
			setOldx((int) getX());
			setOldy((int) getY());
			
			setX(getX() + (Math.cos(angMovimentacao)*vel*DiffTime/1000.0f)); 
			setY(getY() - (Math.sin(angMovimentacao)*vel*DiffTime/1000.0f)); 	
	 
			if (getX()+getSizeX()/2+1 >=larguraMapa || getY()+getSizeY()/2+1>=alturaMapa || getY()-getSizeY()/2-1 <=0 || getX()-getSizeX()/2-1<=0) {
				
				setX(getOldx());
				setY(getOldy());
			}
			armaAtiva.definePosicaoArma(ang, getX(), getY());
			armaAtiva.SimulaSe(DiffTime);
		}
//		System.out.println(X);
//		System.out.println(Y);
		
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		if (isVivo()) {

			armaAtiva.DesenhaSe(dbg, XMundo, YMundo);
			dbg.setColor(cor);
			int px =(int) (getX()-XMundo);
			int py = (int)(getY()-YMundo);
			dbg.fillOval((int)px-getSizeX()/2,(int)py-getSizeY()/2, getSizeX(),getSizeY());

			///// VIDA TEMPORARIO ## FAZER HUD
			dbg.drawRect((int)px-getSizeX()/2-5, (int)py-getSizeY()/2-17, 30, 10);
			dbg.setColor(Color.green);
			dbg.fillRect((int)px-getSizeX()/2-5+1, (int)py-16-getSizeY()/2, (int)(getLife()*30/maximoVida)-1, 9);
		}
		
//		System.out.println(CanvasGame.MAPA.MapX + "   <- MAPX ");
//		System.out.println(CanvasGame.MAPA.MapY + "   <- MAPY ");
//
//		System.out.println("1"+CanvasGame.MAPA.Altura*16);
//		System.out.println("2"+CanvasGame.MAPA.Largura*16);
	}
	private void calculaIA(int DiffTime) {
		// TODO Auto-generated method stub

		if (getLife()<0)
			setVivo(false);
		
		calculaAnguloVelocidade();
		trataDirecaoMovimentacao();
		
		double difX =CanvasGame.mousex+CanvasGame.MAPA.MapX-getX();
		
		double difY =CanvasGame.mousey+CanvasGame.MAPA.MapY-getY();
		
		ang = Math.atan2(difY, difX);
			
		trataTiroArma();	
			
		
		
	}

	private void trataTiroArma() {
		// TODO Auto-generated method stub
		if (Atira) {
			
			armaAtiva.atirou();
		}
		else 
			armaAtiva.naoAtirou();
		
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
		else if (ARMA_ANTERIOR)  {
			Arma temp = armaAtiva;
			armaAtiva=ultimaArma;
			ultimaArma=temp;
			
			ARMA_ANTERIOR=false;
		}
		
	}
	private void calculaAnguloVelocidade() {
		// TODO Auto-generated method stub
		
		int angAux = (int)Math.toDegrees(ang);
		
//		System.out.println("ang: "+ang);
		if(angAux<0){
			angAux=Math.abs(180-angAux)-180;
		}
		else  {
			angAux=Math.abs(180-angAux)+180;
		}
		
//		System.out.println("angAux :"+angAux);
		int angMovtmp = (int)Math.toDegrees(angMovimentacao);
		
		if(angMovtmp<0){
			angMovtmp+=360;
		}
//		System.out.println("angMove:"+angMovtmp);
	
		int anguloDif = Math.abs(angAux - angMovtmp) ;
//				System.out.println(" angulo dif 1"+anguloDif);
		if(anguloDif>180){
			anguloDif=Math.abs(anguloDif-360);
		}
//		System.out.println(" angulo dif final"+anguloDif);

		if(anguloDif<80){
			vel = VelMaxFrente-armaAtiva.getPeso();
		}else if(anguloDif<140){
			vel = VelMaxLado-armaAtiva.getPeso();
		}else{
			vel = VelMaxTras-armaAtiva.getPeso();
		}
	}
	
	private void trataDirecaoMovimentacao() {
		// TODO Auto-generated method stub
		if (UP&&RIGHT) {
			angMovimentacao=Math.toRadians(45);
			
		}
		else if (UP&&LEFT) {
			angMovimentacao=Math.toRadians(135);
			
		}
		else if (DOWN&&LEFT) {
			angMovimentacao=Math.toRadians(225);
			
		}else if (DOWN&&RIGHT) {
			angMovimentacao=Math.toRadians(315);
			
		}
			
		else if(UP){ 
			
			angMovimentacao=Math.PI/2;
			
		}else if(DOWN){
			angMovimentacao=Math.PI*3/2;
		}else if(LEFT){
			angMovimentacao=Math.PI;

		}else if(RIGHT){
			angMovimentacao=0;
		
		}else vel = 0;
	}
	
	public void respaw(int X,int Y) {
		
		this.setX(X);
		this.setY(Y);
		setVivo(true);	
		setLife(maximoVida);
		armaAtiva.recarrega();
		
	}
	public void trataClick() {
		// TODO Auto-generated method stub
		if (Atira)
			Atira=false;
		else Atira=true;
		
	}

}

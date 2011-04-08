import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/* A FAZER
 * 
 * torre trocar de direção de uma forma mais suave estilo a nave do jogo de IA
 * 
 */
public class Torre extends Objeto{

	BufferedImage AnimeSet;
	
//	int frame;
//	int timeranimacao;
//	int animacao;
//	int tempoentreframes;

	public boolean select=false;
	int range;
	double ang = 0;

	int sizeX = 20;
	int sizeY = 20;

	int pmy;
	int pmx;
	
	Arma armaAtiva;
	
	double oldx;
	double oldy; 


//	public boolean click = false;

	Color cor;

	public Torre(/*BufferedImage _AnimeSet,*/ Arma arma){ 
		// TODO Auto-generated constructor stub
		
		armaAtiva=arma;
		cor = Color.black;
		range=200;
		ang=0;
		//AnimeSet = _AnimeSet;
		//frame = 0;
	//	animacao = 0;
		//timeranimacao = 0;

		int _x=((CanvasGame.mousex+4)/16)*16; 
		X=_x+CanvasGame.MAPA.MapX;
		int _y=((CanvasGame.mousey-5)/16)*16; 
		Y=_y+CanvasGame.MAPA.MapY;		

		vivo = true;

		
		
	}
	
	
	public void DesenhaSe(Graphics2D dbg,int XMundo,int YMundo) {
		// TODO Auto-generated method stub
		armaAtiva.DesenhaSe(dbg, XMundo, YMundo);

		AffineTransform trans = dbg.getTransform();
		dbg.translate(X-XMundo, Y-YMundo);
		dbg.rotate(ang);

		dbg.setColor(cor);

		
		dbg.drawOval(-sizeX/2,-sizeY/2, (int)sizeX,(int)sizeY);
	//	dbg.drawImage(AnimeSet,-14,-18,sizeX-10,sizeY-14,sizeX*frame+start,startY,(sizeX*frame)+sizeX+start,(startY)+sizeY,null);

//		if(select&&CanvasGame.Torre==0) {
//			dbg.setColor(Color.blue);
			dbg.drawOval((int)-range/2, (int)-range/2, range, range);

//		}

		dbg.setTransform(trans);
		

}
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub	
		
		boolean at=false;
		for (int i = 0;i<CanvasGame.inimigos.size();i++) {
			
		
			Inimigo in = CanvasGame.inimigos.get(i);
				if (Constantes.colidecircular(in.X, in.Y, in.sizeX/2, X, Y, range/2)) {
					
					int difX=(int) (X-in.X);
					int difY=(int) (Y-in.Y);
					ang=Math.atan2(difY,difX)+Math.PI;
					armaAtiva.definePosicaoArma(ang,X, Y);

					armaAtiva.atirou();
					at=true;
					break;
					
				}			
			}

		
		 if (at==false) 
			 armaAtiva.naoAtirou();
		 
		armaAtiva.definePosicaoArma(ang,X, Y);
		armaAtiva.SimulaSe((int)DiffTime);	

	}
}

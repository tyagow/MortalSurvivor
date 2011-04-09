import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

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
	private int timerContruindo;
	public boolean select=false;
	private int range;
	private double ang = 0;

	int sizeX = 20;
	int sizeY = 20;

	int pmy;
	int pmx;
	
	private	Arma armaAtiva;
	
	private double oldx;
	private double oldy; 


//	public boolean click = false;

	Color cor;
	boolean contruindo;

	private int timerSelect;
	public Torre(/*BufferedImage _AnimeSet,*/ Arma arma,int x,int y){ 
		// TODO Auto-generated constructor stub
	
		contruindo=true;
		armaAtiva=arma;
		cor = Color.cyan;
		setRange(200);
		ang=0;
		//AnimeSet = _AnimeSet;
		//frame = 0;
		//animacao = 0;
		//timeranimacao = 0;

		X=x;
		Y=y;		

		vivo = true;

		
		
	}
	
	
	public void DesenhaSe(Graphics2D dbg,int XMundo,int YMundo) {
		// TODO Auto-generated method stub
		armaAtiva.DesenhaSe(dbg, XMundo, YMundo);

		AffineTransform trans = dbg.getTransform();
		dbg.translate(X-XMundo, Y-YMundo);
		dbg.rotate(ang);

		dbg.setColor(cor);

		
	//	dbg.drawImage(AnimeSet,-14,-18,sizeX-10,sizeY-14,sizeX*frame+start,startY,(sizeX*frame)+sizeX+start,(startY)+sizeY,null);

		
		if (contruindo) {
			
//			dbg.drawRect(-sizeX/2, sizeY/2, (int)(timerContruindo*30/Constantes.TEMPO_TORRE_CONSTRUINDO), (int)(timerContruindo*30/Constantes.TEMPO_TORRE_CONSTRUINDO)); //(int)(life*30/maximoVida)
			dbg.drawRect((int)-sizeX/2-5, (int)-sizeY/2-17, 30, 10);
			dbg.setColor(Color.lightGray);
			dbg.fillRect((int)-sizeX/2-5+1, (int)-16-sizeY/2, (int)(timerContruindo*30/Constantes.TEMPO_TORRE_CONSTRUCAO), 9);		
		}//else 
			dbg.fillOval(-sizeX/2,-sizeY/2, (int)sizeX,(int)sizeY);



		dbg.setTransform(trans);
		

}
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub	
		calculaIA(DiffTime);

		if (!contruindo)	{		
			procuraInimigos();
			 
			armaAtiva.definePosicaoArma(ang,X, Y);
			armaAtiva.SimulaSe((int)DiffTime);	
			
	
		}	
	}


	private void calculaIA(int DiffTime) {
		// TODO Auto-generated method stub
		timerSelect+=DiffTime;
		timerContruindo+=(int)DiffTime;
		
		if (timerContruindo>=Constantes.TEMPO_TORRE_CONSTRUCAO) {
			timerContruindo=0;
			contruindo=false;
		}
		
		if (select&&timerSelect>=Constantes.TEMPO_TORRE_SELECIONADA)
			select=false;
	}
	
	public void seleciona() {

		if (select){
			select =false;
			CanvasGame.gerenciadorHud.desativaHudTorre();
		}
		else {
			
			CanvasGame.gerenciadorHud.ativaHudTorre(this);
			select=true;
			timerSelect=0;
		}
	}

	private void procuraInimigos() {
		// TODO Auto-generated method stub
		boolean at=false;
		
		Iterator<Inimigo> it = CanvasGame.inimigos.iterator();
		while (it.hasNext()){
			
			Inimigo in = it.next();
				if (Constantes.colidecircular(in.X, in.Y, in.sizeX/2, X, Y, getRange()/2)) {
					
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
	}


	public void setRange(int range) {
		this.range = range;
	}


	public int getRange() {
		return range;
	}


	public void resetTimerSelect() {
		// TODO Auto-generated method stub
		timerSelect=0;
		
	}

}

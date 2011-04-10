import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Inimigo extends Objeto {
	
	int dano;
	private double vel;
	private int tempoEntreAtaque;
	private int maximoVida=100;
	private int maxVel=100;
	private int tipoAssasino;
	private int larguraMapa;
	private int alturaMapa;
	
	
	public Inimigo() {
		larguraMapa=CanvasGame.MAPA.Largura*16;
		alturaMapa=CanvasGame.MAPA.Altura*16;
		setX(GamePanel.rnd.nextInt(alturaMapa));
		setY(GamePanel.rnd.nextInt(larguraMapa));
		setSizeX(30);
		setSizeY(30);
		dano = 10;
		vel=100;
		setLife(100);
		setVivo(true);

		// TODO Auto-generated constructor stub
	}

	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub	
			
			calculaIA(DiffTime);
			
			double difX = CanvasGame.heroi.getX() - getX();
			double difY = CanvasGame.heroi.getY() - getY();
			double ang =  Math.atan2(difY, difX);

			double velx=(int) (Math.cos(ang)*vel);
			double vely=(int) (Math.sin(ang)*vel);
			
			setX(getX() + (velx*DiffTime/1000.0f));
			setY(getY() + (vely*DiffTime/1000.0f));
			
			if (getLife()<0) {
				setVivo(false);
				
			}				

	}


	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		dbg.drawOval((int)getX()-getSizeX()/2-XMundo,(int)getY()-getSizeY()/2-YMundo,(int)getSizeX(),(int)getSizeY());
//		System.out.println("INIMIGO"+X);
//		System.out.println("INIMIGO"+Y);
		dbg.drawRect((int)getX()-5-getSizeX()/2-XMundo, (int)getY()-17-getSizeY()/2-YMundo, 30, 10);
		dbg.setColor(Color.green);
		dbg.fillRect((int)getX()-5+1-getSizeX()/2-XMundo, (int)getY()-16-getSizeX()/2-YMundo, (int)(getLife()*30/maximoVida)-1, 9);
		
	}
	
	private void calculaIA(int DiffTime) {
		// TODO Auto-generated method stub
		
		tempoEntreAtaque+=DiffTime;
		
		if(Constantes.colidecircular(getX(), getY(),getSizeX()/2,CanvasGame.heroi.getX(),CanvasGame.heroi.getY(),CanvasGame.heroi.getSizeX()/2)){
			if (tempoEntreAtaque>500) {
				CanvasGame.heroi.setLife(CanvasGame.heroi.getLife() - dano);
				tempoEntreAtaque=0;
			}
			
			vel=0; /// variavel para o inimigo nao atravessar o player....
			
			}
		else vel=maxVel;
		
		
	}

	public void recebeuDano(int dano,int tipo) {
		// TODO Auto-generated method stub
		if (getLife()-dano<=0) {
			morreu(tipo);
		}else  {
			setLife(getLife() - dano);
		}
				
	}

	public void morreu(int tipo) {
		// TODO Auto-generated method stub
		
		setLife(0);	
		setTipoAssasino(tipo);
		setVivo(false);
			
	}

	public void setTipoAssasino(int tipoAssasino) {
		this.tipoAssasino = tipoAssasino;
	}

	public int getTipoAssasino() {
		return tipoAssasino;
	}
		
	
}


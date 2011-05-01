import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import com.sun.media.sound.MidiUtils.TempoCache;

/* A FAZER
 * 
 * torre trocar de direção de uma forma mais suave estilo a nave do jogo de IA
 * 
 */
public class Torre extends Objeto{

	BufferedImage AnimeSet;

	private int timerContruindo;
	public boolean select=false;
	private int range;
	private double ang = 0;



	int pmy;
	int pmx;
	
	private	Arma armaAtiva;
	private MenuTorre menuAtivo; //mudei para menu torre mas o certo eh menu somente ... resolver depois

	private MenuTorre menuStatusTorre;
	Color cor;
	private boolean contruindo;


	private int timerSelect;

	private boolean controleCursor;


	
	
	public Torre(BufferedImage _AnimeSet, Arma arma,int x,int y){ 
		// TODO Auto-generated constructor stub
		
		AnimeSet=_AnimeSet;
		if (AnimeSet.getWidth() <16)
			setSizeX(16);
		else
			setSizeX(AnimeSet.getWidth());

		if (AnimeSet.getHeight() <16) {
			setSizeY(16);
		}
		else
			setSizeY(AnimeSet.getHeight());

		setX(x/16*16+getSizeX()/2);
		setY(y/16*16+getSizeY()/2);	
		contruindo=true;
		setArmaAtiva(arma);
		cor = Color.cyan;
		setRange(200);
		ang=0;
		menuAtivo=null;
		
		GerenciadorObstaculos.addObstaculos((int)getX(),(int)getY(),getSizeX(),getSizeY());
		
		menuStatusTorre=new MenuTorre(x, y, Constantes.HUD_TORRE_SIZEX, Constantes.HUD_TORRE_SIZEY, (Color.LIGHT_GRAY), 2000, this);

		setVivo(true);
	
	}
	

	public void DesenhaSe(Graphics2D dbg,int XMundo,int YMundo) {
		// TODO Auto-generated method stub
		getArmaAtiva().DesenhaSe(dbg, XMundo, YMundo);
		
		if (menuAtivo!=null) {
			dbg.setColor(Color.red);
			dbg.drawOval((int)getX()-getRange()/2-XMundo, (int)getY()-getRange()/2-YMundo, getRange(), getRange());
			menuAtivo.DesenhaSe(dbg, XMundo, YMundo);

		}
		AffineTransform trans = dbg.getTransform();
		dbg.translate(getX()-XMundo, getY()-YMundo);
		dbg.rotate(ang);

		dbg.setColor(cor);


		
		if (contruindo) {
			
			dbg.drawRect((int)-getSizeX()/2-5, (int)-getSizeY()/2-17, 30, 10);
			dbg.setColor(Color.lightGray);
			dbg.fillRect((int)-getSizeX()/2-5+1, (int)-16-getSizeY()/2, (int)(timerContruindo*30/Constantes.TEMPO_TORRE_CONSTRUCAO), 9);		
		}
		dbg.setTransform(trans);
		

}
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub	
		calculaIA(DiffTime);

		if (!contruindo)	{		
			procuraInimigos();
			 
			getArmaAtiva().definePosicaoArma(ang,getX(), getY());
			getArmaAtiva().SimulaSe((int)DiffTime);	
			
			if (menuAtivo!=null) {
				menuAtivo.SimulaSe((int)DiffTime);	
				trataMenuAtivo();
					
			}
	
		}	
	}


	private void trataMenuAtivo() {
		
		// TODO Auto-generated method stub
		if (menuAtivo.getEvoluiRange()) {

			range+=10;
			menuAtivo.tratouBotaoRange();
		}
		if (menuAtivo.getEvoluiFire()) {

			getArmaAtiva().setTempoEntreTirosMax(getArmaAtiva().getTempoEntreTirosMax() -3);
			
			menuAtivo.tratouBotaoFire();
		}
		if (menuAtivo.isEvoluiDano()) {

			getArmaAtiva().setDano(getArmaAtiva().getDano()+1);
			
			menuAtivo.tratouBotaoDano();
		}
		
		if (!menuAtivo.isVivo()) {
			controleCursor=false;
			menuAtivo= null;
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

	}
	

	
	
	public void seleciona() {
		
		if (menuAtivo!=null&&menuAtivo.isVivo()){
			select =false;
			menuAtivo.setVivo(false);
		}
		
	 if (menuAtivo==null) {
		
		menuAtivo=menuStatusTorre;	
		menuAtivo.setVivo(true);
		select=true;
		timerSelect=0;
	}
		

	}

	private void procuraInimigos() {
		// TODO Auto-generated method stub
		boolean at=false;
		
		for (int i = 0;i<GerenciadorDeRaids.getRaids().size();i++) {
			Raid ra = GerenciadorDeRaids.getRaids().get(i);
		
			for (int j = 0;j<ra.inimigos.size();j++) {
				Inimigo in = ra.inimigos.get(j);
				

				if (Constantes.colidecircular(in.getX(), in.getY(), in.getSizeX()/2, getX(), getY(), getRange()/2)) {
					
					int difX=(int) (getX()-in.getX());
					int difY=(int) (getY()-in.getY());
					ang=Math.atan2(difY,difX)+Math.PI;
					getArmaAtiva().definePosicaoArma(ang,getX(), getY());

					getArmaAtiva().atirou();
					at=true;
					break;
					
				}			
			}

		}
		 if (at==false) 
			 getArmaAtiva().naoAtirou();
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


public MenuTorre getMenuAtivo() {
	return menuAtivo;
}


public void setMenuAtivo(MenuTorre menuAtivo) {
	this.menuAtivo = menuAtivo;
}


public void setArmaAtiva(Arma armaAtiva) {
	this.armaAtiva = armaAtiva;
}


public Arma getArmaAtiva() {
	return armaAtiva;
}
}

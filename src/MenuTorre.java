import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.LinkedList;


public class MenuTorre extends Menu {

	private int timerSelecionado;
	private Torre torrePai;
	private boolean rangeAtivo;
	private boolean evoluiRange;
	
	public MenuTorre(int x, int y, int sizeX, int sizeY, Color cor,int _tempoVida, Torre pai) {
		super(x-Constantes.HUD_TORRE_STARTX, y-Constantes.HUD_TORRE_STARTY, sizeX, sizeY, cor, _tempoVida);
		// TODO Auto-generated constructor stu
		setEvoluiRange(false);
		setTorrePai(pai);
		setRangeAtivo(false);
		timerSelecionado=0;
		setTempoVida(_tempoVida);
		setBotoes(criaBotoesStatusTorre());
		
	}
	
	
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		timerSelecionado+=DiffTime;
		
		if (getTempoVida()!=-1&&timerSelecionado>= getTempoVida()) {
			
			setVivo(false);
			timerSelecionado=0;
		}
		Iterator<Botao> it = getBotoes().iterator();
		while(it.hasNext()){
			Botao bt = it.next();
			bt.SimulaSe((int)DiffTime);
			if (bt.isAtivo()) {
				if (bt.getName()=="Range"){
					trataBotaoRange();
					bt.setAtivo(false);
				}
			}
				

		}
		
		

		if (Constantes.colideQuadrado((int)getX(),(int) getY(),(int) getSizeX(),getSizeY(), (int)CanvasGame.getMiraAtiva().getXMundo(),(int) CanvasGame.getMiraAtiva().getYMundo(),(int) CanvasGame.getMiraAtiva().getSizeX(),(int)CanvasGame.getMiraAtiva().getSizeY())) {
			timerSelecionado=0;	
			CanvasGame.setMiraMenu();
		}else 
			CanvasGame.setMiraJogo();
		
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int xMundo, int yMundo) {
		// TODO Auto-generated method stub
		
		//desenha menu
		dbg.setColor(Color.black);
		dbg.drawRect((int)getX()-xMundo,(int) (getY()-yMundo), getSizeX(),getSizeY());
		
		dbg.setColor(Color.lightGray);
		dbg.fillRect((int)getX()-xMundo+1,(int) (getY()-yMundo)+1, getSizeX()-1,getSizeY()-1);

		// range
			dbg.setColor(Color.red);
			//dbg.drawOval((int)getX()-torrePai.getRange()/2-xMundo, (int)getY()-torrePai.getRange()/2-yMundo, torrePai.getRange(), torrePai.getRange());

		//desenha botoes
		Iterator<Botao> it = getBotoes().iterator();
		while(it.hasNext()){
			Botao bt = it.next();
			bt.DesenhaSe(dbg, xMundo, yMundo);
			

		}
		
	}
	
	private LinkedList<Botao> criaBotoesStatusTorre() {
		// TODO Auto-generated method stub
		LinkedList<Botao>aux = new LinkedList<Botao>();
		
		Botao botaoRange = new Botao("Range",(int)getX()+10,(int)getY()+10);
		aux.add(botaoRange);
		
		return aux;
	}

	private void trataBotaoRange() {
		// TODO Auto-generated method stub
			setEvoluiRange(true);
			
		
	}
	
	public void tratouBotaoRange() {
		setEvoluiRange(false);		
	}


	private void setEvoluiRange(boolean evoluiRange) {
		this.evoluiRange = evoluiRange;
	}


	public boolean getEvoluiRange() {
		return evoluiRange;
	}


	public void setRangeAtivo(boolean rangeAtivo) {
		this.rangeAtivo = rangeAtivo;
	}


	public boolean isRangeAtivo() {
		return rangeAtivo;
	}


	public void setTorrePai(Torre torrePai) {
		this.torrePai = torrePai;
	}


	public Torre getTorrePai() {
		return torrePai;
	}


}

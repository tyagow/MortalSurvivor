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
		super(x-sizeX/2-5, y-sizeY-5, sizeX, sizeY, cor, _tempoVida);
		// TODO Auto-generated constructor stu
		setEvoluiRange(false);
		setTorrePai(pai);
		setRangeAtivo(false);
		setTimerSelecionado(0);
		setTempoVida(_tempoVida);
		setBotoes(criaBotoesStatusTorre());
		
	}
	
	
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		setTimerSelecionado(getTimerSelecionado() + DiffTime);
		
		if (Constantes.colideQuadrado((int)getX(),(int) getY(), (int) getSizeX(), (int) getSizeY(), (int) CanvasGame.getMiraAtiva().getXMundo(),(int) CanvasGame.getMiraAtiva().getYMundo(), 1, 1)) {
			setTimerSelecionado(0);
		}
		
		if (getTempoVida() != -1 && getTimerSelecionado() >= getTempoVida()) {
			
			setVivo(false);
			setTimerSelecionado(0);
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
		
	
		aux.add(new Botao(null,"range",(int)getX()+10,(int)getY()+10,40,12,false));
		aux.add(new Botao(null,"fireR",(int)getX()+10,(int)getY()+30,40,12,false));
		aux.add(new Botao(null,"Dano",(int)getX()+10,(int)getY()+50,40,12,false));
		
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
	


	public void setTimerSelecionado(int timerSelecionado) {
		this.timerSelecionado = timerSelecionado;
	}


	public int getTimerSelecionado() {
		return timerSelecionado;
	}


}

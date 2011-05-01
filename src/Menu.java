import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.LinkedList;


public abstract class Menu extends Objeto {

	private boolean ativo;
	private int tempoVida;
	private Color cor;
	
	private LinkedList<Botao> botoes = new LinkedList<Botao>();
	
	public Menu (int _x,int _y,int sizeX,int sizeY, Color cor, int _tempoVida) {
		
		setX(_x);
		setY(_y);
		setSizeX(sizeX);
		setSizeY(sizeY);
		setCor(cor);
		setVivo(true);
		setTempoVida(_tempoVida);
		
	}


	//private abstract  void trataBotao(Botao part);
	
	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		
		dbg.setColor(Color.white);
		dbg.drawRect((int)getX()-XMundo,(int)getY()-YMundo, getSizeX(), getSizeY());
		
		dbg.setColor(new Color(50,50,50,200));
		
		dbg.fillRect((int)getX()+1-XMundo,(int)getY()-YMundo+1, getSizeX()-2, getSizeY()-2);
		
		Iterator<Botao> it = botoes.iterator();
		while(it.hasNext()){
			Botao bot= it.next();
			
			bot.DesenhaSe(dbg, XMundo, YMundo);
			
			//trataBotao(bot);
		
		}
		

	}
	public void setCor(Color cor) {
		this.cor = cor;
	}
	public Color getCor() {
		return cor;
	}
	public void setTempoVida(int tempoVida) {
		this.tempoVida = tempoVida;
	}
	public int getTempoVida() {
		return tempoVida;
	}
	public void setBotoes(LinkedList<Botao> botoes) {
		this.botoes = botoes;
	}
	public LinkedList<Botao> getBotoes() {
		return botoes;
	}


	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}


	public boolean isAtivo() {
		return ativo;
	}

}

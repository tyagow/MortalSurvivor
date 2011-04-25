import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;


public class Menu extends Objeto {

	
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
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub

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

}

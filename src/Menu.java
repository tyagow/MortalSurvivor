import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


public abstract class Menu extends Objeto {

	public boolean ativo;
	public int tempoVida;
	public Color cor;
	
	public int r,g,b,alpha;
	
	public ArrayList<Botao> botoes = new ArrayList<Botao>();
	public boolean selecionado;
	
	public Menu (int _x,int _y,int sizeX,int sizeY, Color cor, int _tempoVida) {
		
		setX(_x);
		setY(_y);
		setSizeX(sizeX);
		setSizeY(sizeY);
		setCor(cor);
		setVivo(true);
		setTempoVida(_tempoVida);
		setR(cor.getRed());
		setG(cor.getGreen());
		setB(cor.getBlue());
		setAlpha(100);
	}


	//private abstract  void trataBotao(Botao part);
	
	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		
		dbg.setColor(Color.white);
		dbg.drawRect((int)getX()-XMundo,(int)getY()-YMundo, getSizeX(), getSizeY());
		
		dbg.setColor(new Color(50,50,50,200));
		
		dbg.fillRect((int)getX()+1-XMundo,(int)getY()-YMundo+1, getSizeX()-2, getSizeY()-2);
		
		Iterator<Botao> it = getBotoes().iterator();
		while(it.hasNext()){
			Botao bot= it.next();
			
			bot.DesenhaSe(dbg, XMundo, YMundo);
			
			//trataBotao(bot);
		
		}
		

	}
public void mouseClicked(MouseEvent e) {
	for (int i=0;i<botoes.size();i++) {
		
		botoes.get(i).mouseClicked(e);
	}

	}
	public void mousePressed(MouseEvent e) {
		for (int i=0;i<botoes.size();i++) {
			
			botoes.get(i).mousePressed(e);
		}
	}
	public void mouseReleased(MouseEvent e) {
		for (int i=0;i<botoes.size();i++) {
			
			botoes.get(i).mouseReleased(e);
		}
	}


public void mouseMoved(MouseEvent e) {
		
		if (Constantes.colideQuadrado((int)getX(),(int)getY(),getSizeX(),getSizeY(), (int)e.getX(),(int)e.getY() ,2,2 )) {
			selecionado=true;
			
		}
		else {
			selecionado=false;
		}
		
		for (int i=0;i<botoes.size();i++) {
			
			botoes.get(i).mouseMoved(e);
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
//	public void setBotoes(ArrayList<Botao> botoes) {
//		this.botoes = botoes;
//	}
	public ArrayList<Botao> getBotoes() {
		return botoes;
	}


	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}


	public boolean isAtivo() {
		return ativo;
	}


	public void setBotoes(ArrayList<Botao> botoes) {
		this.botoes = botoes;
	}


	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}


	public int getAlpha() {
		return alpha;
	}


	public void setR(int r) {
		this.r = r;
	}


	public int getR() {
		return r;
	}


	public void setB(int b) {
		this.b = b;
	}


	public int getB() {
		return b;
	}


	public void setG(int g) {
		this.g = g;
	}


	public int getG() {
		return g;
	}

}

package Interface;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import Torre.Torre;

import Canvas.CanvasGame;
import Constantes.Constantes;


public class MenuTorre extends FrameBase {

	private int timerSelecionado;
	private Torre torrePai;
	
	private boolean rangeAtivo;

	
	
	public boolean evoluiRange;
	public boolean evoluiFire;
	public boolean evoluiDano;
	
	public boolean ativaRange=false;

	
	public MenuTorre(int x, int y, int sizeX, int sizeY, Color cor,int _tempoVida, Torre pai) {
		super(x-sizeX/2-5, y-sizeY-5, sizeX, sizeY, cor, _tempoVida);
		// TODO Auto-generated constructor 
		evoluiRange=(false);
		torrePai=(pai);
		rangeAtivo=(false);
		timerSelecionado=(0);
		tempoVida=(_tempoVida);
		botoes=(criaBotoesStatusTorre());
		
	}
	
	
	@Override
	public void SimulaSe(int DiffTime) {
	
		
		timerSelecionado=(timerSelecionado + DiffTime);
		
		if (selecionado) {
			timerSelecionado=0;
		}
		
		if (tempoVida != -1 && timerSelecionado >= tempoVida) {
			
			setVivo(false);
			timerSelecionado=(0);
		}
		Iterator<Botao> it = botoes.iterator();
		while(it.hasNext()){
			Botao bt = it.next();
			bt.SimulaSe((int)DiffTime);
			bt.mousex=Constantes.mouseXTela;
			bt.mousex=Constantes.mouseYTela;
			trataBotaoSelecionado(bt);

			if (bt.ativo==true) {
				trataBotao(bt);
			}
			
			
		}
		
	
		
	}
	private void trataBotaoSelecionado(Botao _bt) {

			
			if (_bt.name == "range")
				ativaRange = _bt.selecionado;
		
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		if (Constantes.colideQuadrado((int)X,(int)Y,sizeX,sizeY,(int) e.getX() + Constantes.XTela,(int) e.getY()+Constantes.YTela,2,2 )) {
			selecionado=true;
		}
		else {
			selecionado=false;
		}

		for (int i=0;i<botoes.size();i++) {
			botoes.get(i).mouseMoved(e);
//			
//			botoes.get(i).mousex = e.getX()+Constantes.XTela;
//
//			botoes.get(i).mousey = e.getY()+Constantes.YTela;
			
		
}

	}
	
	@Override
	protected void trataBotao(Botao bt) {
		// TODO Auto-generated method stub
		if (bt.name=="range"){
			trataBotaoRange();
			bt.ativo = false;
		}else if (bt.name=="fire"){
			trataBotaoFire();
			bt.ativo = false;
		}else if (bt.name=="dano"){
			trataBotaoDano();
			bt.ativo = false;
		}
		
		
	}




	@Override
	public void DesenhaSe(Graphics2D dbg, int xMundo, int yMundo) {
		// TODO Auto-generated method stub
		
		//desenha menu
		dbg.setColor(Color.black);
//		dbg.drawRect((int)getX()-xMundo,(int) (getY()-yMundo), getSizeX(),getSizeY());
		
		dbg.setColor(new Color(r,g,b,alpha));
		dbg.fillRect((int)getX()-xMundo+1,(int) (getY()-yMundo)+1, getSizeX()-1,getSizeY()-1);

		// range
			dbg.setColor(Color.red);
			//dbg.drawOval((int)getX()-torrePai.getRange()/2-xMundo, (int)getY()-torrePai.getRange()/2-yMundo, torrePai.getRange(), torrePai.getRange());
//		//desenha botoes
	Iterator<Botao> it = botoes.iterator();
	while(it.hasNext()){
			Botao bt = it.next();
			bt.DesenhaSe(dbg, xMundo, yMundo);
			trataDesenhoBotao(dbg,bt,xMundo,yMundo);

		}
		dbg.setColor(Color.black);
		
	
		
		//dbg.drawString(torrePai., x, y)
		
	}
	
	private void trataDesenhoBotao(Graphics2D dbg,Botao bt,int XTela,int YTela) {
		// TODO Auto-generated method stub
		dbg.setFont(Constantes.FonteNormal);
		if (bt.name=="range") {
			dbg.setColor(Color.white);
			dbg.drawString("$"+torrePai.armaAtiva.custoRange, (int)bt.getX()+bt.getSizeX()+10-XTela,(int) bt.getY()+bt.getSizeY()-2-YTela);
			
		}
		if (bt.name=="fire") {
			dbg.setColor(Color.white);
			dbg.drawString("$"+torrePai.armaAtiva.custoFire, (int)bt.getX()+bt.getSizeX()+10-XTela,(int) bt.getY()+bt.getSizeY()-2-YTela);
			
		}
		if (bt.name=="dano") {
			dbg.setColor(Color.white);
			dbg.drawString("$"+torrePai.armaAtiva.custoDano, (int)bt.getX()+bt.getSizeX()+10-XTela,(int) bt.getY()+bt.getSizeY()-2-YTela);
			
		}
	}


	private ArrayList<Botao> criaBotoesStatusTorre() {
		// TODO Auto-generated method stub
		ArrayList<Botao>aux = new ArrayList<Botao>();
		
	
		aux.add(new Botao(null,"range",(int)getX()+10,(int)getY()+10,40,12,false));
		aux.add(new Botao(null,"fire",(int)getX()+10,(int)getY()+35,40,12,false));
		aux.add(new Botao(null,"dano",(int)getX()+10,(int)getY()+60,40,12,false));
		
		return aux;
	}

	private void trataBotaoRange() {
		// TODO Auto-generated method stub
			evoluiRange=(true);
			
		
	}


	public void tratouBotaoRange() {
		evoluiRange=(false);		
	}

//
//	private void setEvoluiRange(boolean evoluiRange) {
//		this.evoluiRange = evoluiRange;
//	}
//
//
//	public boolean getEvoluiRange() {
//		return evoluiRange;
//	}
//
//
//	public void setRangeAtivo(boolean rangeAtivo) {
//		this.rangeAtivo = rangeAtivo;
//	}
//
//
//	public boolean isRangeAtivo() {
//		return rangeAtivo;
//	}

	
	private void trataBotaoDano() {
		// TODO Auto-generated method stub
		evoluiDano=(true);
	}	
	public void tratouBotaoDano() {
		// TODO Auto-generated method stub
		evoluiDano=(false);
	}


	private void trataBotaoFire() {
		// TODO Auto-generated method stub
		evoluiFire=(true);
	}
	public void tratouBotaoFire() {
		// TODO Auto-generated method stub
		evoluiFire=(false);
	}

//	public void setTorrePai(Torre torrePai) {
//		this.torrePai = torrePai;
//	}
//
//
//	public Torre getTorrePai() {
//		return torrePai;
//	}
//	


//	public void setTimerSelecionado(int timerSelecionado) {
//		this.timerSelecionado = timerSelecionado;
//	}
//
//
//	public int getTimerSelecionado() {
//		return timerSelecionado;
//	}
//
//
//	public void setEvoluiFire(boolean evoluiFire) {
//		this.evoluiFire = evoluiFire;
//	}
//
//
//	public boolean getEvoluiFire() {
//		return evoluiFire;
//	}
//
//
//	public void setEvoluiDano(boolean evoluiDano) {
//		this.evoluiDano = evoluiDano;
//	}
//
//
//	public boolean isEvoluiDano() {
//		return evoluiDano;
//	}


}

package Canvas;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import Constantes.Constantes;
import Data.Imagem;
import GameState.GamePanel;
import Gerenciadores.GerenciadorDeSom;
import Interface.Botao;
import Interface.Menu;
import Interface.MenuOptions;
import Mouse.CursorMenu;
import Mouse.Mira;

public class CanvasStart extends GCanvas{
	
	static BufferedImage imagemlogo;

	private Font fonteLogo, fonteMenu, fonteAutores;
	GerenciadorDeSom loader;
	boolean instrucao = false;
	BufferedImage img;
	Imagem imgloader;
	
	Menu menuAtivo;
	private static Mira miraAtiva;

	

	private Menu menuOptions;
	
	private static ArrayList<Botao> botoes= new ArrayList<Botao>();
	public CanvasStart() {
		// TODO Auto-generated constructor stub
		Constantes.XTela=0;
		Constantes.YTela=0;
		
		loader = new GerenciadorDeSom();
		
		imgloader = new Imagem();
		setFonteLogo(new Font("Courier", Font.BOLD, 60));
		
		fonteMenu = new Font("Courier", Font.BOLD, 30);
		
		setFonteAutores(new Font("Courier", Font.BOLD, 14));
	 
		
		botoes.add(new Botao(null,"Play",100,100,120,25,false));
		botoes.add(new Botao(null,"Score",100,150,120,25,false));
		botoes.add(new Botao(null,"Help",100,200,120,25,false));
		botoes.add(new Botao(null,"Options",100,250,120,25,false));
		botoes.add(new Botao(null,"Exit",100,300,120,25,false));
		menuOptions= new MenuOptions(250, 100, 200, 200, Color.darkGray, 9999);
		
		setMiraAtiva(new CursorMenu());
		


	}
	@Override
	public void DesenhaSe(Graphics2D dbg) {
		// TODO Auto-generated method stub
		
			dbg.setFont(fonteMenu);
			
			dbg.setColor(Color.black);
			dbg.fillRect(0, 0, GamePanel.PWIDTH, GamePanel.PHEIGHT);
			dbg.drawImage(Imagem.fundoCidade, -50, 0,GamePanel.PWIDTH+50,GamePanel.PHEIGHT,null);

			dbg.drawImage(Imagem.logo, GamePanel.PWIDTH-100, GamePanel.PHEIGHT-110,100,110,null);
			
			if (menuAtivo!=null) 
				menuAtivo.DesenhaSe(dbg, 0, 0);
			
			
			dbg.setColor(Color.yellow);
			
			//dbg.setFont(fonteAutores);
			Iterator<Botao> it = botoes.iterator();

			while(it.hasNext()){
				Botao obj= it.next();
			obj.DesenhaSe(dbg, 0, 0);
			}
			
			getMiraAtiva().DesenhaSe(dbg, 0, 0);

		
	

	}

	@Override
	public void SimulaSe(long diftime) {
		// TODO Auto-generated method stub
		Constantes.XTela=0;
		Constantes.YTela=0;
		if (menuAtivo!=null)
			menuAtivo.SimulaSe((int)diftime);

//		Iterator<Botao> it = botoes.iterator();
//		while(it.hasNext()){
			for (int x=0;x<botoes.size();x++) {
	//				Botao b= (Botao) it.next();
				Botao b= botoes.get(x);
				
				b.SimulaSe((int)diftime);			
				if (b.ativo==true) {
					trataBotao(b);
					botoes.get(x).ativo=false;
				}		
			}
		
		
	//	getMiraAtiva().SimulaSe((int)diftime);	

		

	}
	private void trataBotao(Botao b) {
		// TODO Auto-generated method stub
		if (b.name.contains("Play") ) {
			
			
			GamePanel.setCanvasAtivo(CanvasGame.instance);
		
			//setMiraAtiva(new MiraRedonda());
			
		}else if (b.name.contains("Options") ) {
			if (menuAtivo!=menuOptions){
				menuAtivo=menuOptions;
			}
			else {
				menuAtivo=null;
			}
		}else if (b.name.contains("Exit") ) {
			
			System.exit(0);
			
		}else if (b.name.contains("Play") ) {
			
			GamePanel.setCanvasAtivo(CanvasGame.instance);
			
		}
		
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_ENTER){
			
			GamePanel.setCanvasAtivo(CanvasGame.instance);


		}else if(keyCode == KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
		
		if(keyCode == KeyEvent.VK_F1){
		}
		
		if(keyCode == KeyEvent.VK_ESCAPE){
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		for (int i=0;i<botoes.size();i++) {
			
			botoes.get(i).mouseMoved(e);
		}
		if (menuAtivo!=null)  {
			for (int i=0;i<menuAtivo.botoes.size();i++) {
				
				menuAtivo.botoes.get(i).mouseMoved(e);
			}
		}
		
		Constantes.mouseXTela=e.getX()+Constantes.XTela;
		Constantes.mouseYTela=e.getY()+Constantes.YTela;

		miraAtiva.X=e.getX();
		miraAtiva.Y=e.getY();
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	
		int button = e.getButton();
		getMiraAtiva().released(button);
		if (button == MouseEvent.BUTTON1) {
			
		
			getMiraAtiva().trataClickMouse1();

			
		}
	
		for (int i=0;i<botoes.size();i++) {
			
			botoes.get(i).mouseReleased(e);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int button = e.getButton();
		
		getMiraAtiva().pressed(button);
		
		if (button == MouseEvent.BUTTON1) {
			getMiraAtiva().trataClickMouse1();
			
			
			}
		else if (button == MouseEvent.BUTTON3) {
			getMiraAtiva().trataClickMouse2();
		
		}
		for (int i=0;i<botoes.size();i++) {
			
			botoes.get(i).mousePressed(e);
		}
		if (menuAtivo!=null)  {
			for (int i=0;i<menuAtivo.botoes.size();i++) {
				
				menuAtivo.botoes.get(i).mousePressed(e);
			}
		}
		
}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (int i=0;i<botoes.size();i++) {
			
			botoes.get(i).mouseClicked(e);
		}
		
		if (menuAtivo!=null)  {
			for (int i=0;i<menuAtivo.botoes.size();i++) {
				
				menuAtivo.botoes.get(i).mouseClicked(e);
			}
		}
	}
	public void setFonteAutores(Font fonteAutores) {
		this.fonteAutores = fonteAutores;
	}
	public Font getFonteAutores() {
		return fonteAutores;
	}
	public void setFonteLogo(Font fonteLogo) {
		this.fonteLogo = fonteLogo;
	}
	public Font getFonteLogo() {
		return fonteLogo;
	}
	public static void setMiraAtiva(Mira _miraAtiva) {
		miraAtiva = _miraAtiva;
	}
	public static Mira getMiraAtiva() {
		return miraAtiva;
	}

}

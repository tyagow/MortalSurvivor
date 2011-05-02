import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import sun.audio.AudioPlayer;

public class CanvasStart extends GCanvas{
	
	static BufferedImage imagemlogo;

	private Font fonteLogo, fonteMenu, fonteAutores;
	GerenciadorDeSom loader;
	boolean instrucao = false;
	BufferedImage img;
	Imagem imgloader;
	
	Menu menuAtivo;

	private Menu menuOptions;
	
	private static ArrayList<Botao> botoes= new ArrayList<Botao>();
	public CanvasStart() {
		// TODO Auto-generated constructor stub
		
		loader = new GerenciadorDeSom();
		
		
		imgloader = new Imagem();
		fonteLogo = new Font("Courier", Font.BOLD, 60);
		
		fonteMenu = new Font("Courier", Font.BOLD, 30);
		
		fonteAutores = new Font("Courier", Font.BOLD, 14);
	 
		
		botoes.add(new Botao(null,"Play",100,100,120,25,false));
		botoes.add(new Botao(null,"Score",100,150,120,25,false));
		botoes.add(new Botao(null,"Help",100,200,120,25,false));
		botoes.add(new Botao(null,"Options",100,250,120,25,false));
		botoes.add(new Botao(null,"Exit",100,300,120,25,false));
		menuOptions= new MenuOptions(250, 100, 200, 200, Color.darkGray, 9999);
		
		GamePanel.getCanvasAtivo();

		GCanvas.setMiraAtiva(new CursorMenuTorre());
		


	}
	@Override
	void DesenhaSe(Graphics2D dbg) {
		// TODO Auto-generated method stub
		
			dbg.setFont(fonteMenu);
			
			dbg.setColor(Color.black);
			dbg.fillRect(0, 0, GamePanel.PWIDTH, GamePanel.PHEIGHT);

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
	void SimulaSe(long diftime) {
		// TODO Auto-generated method stub
		
		if (menuAtivo!=null)
			menuAtivo.SimulaSe((int)diftime);
		
//		Iterator<Botao> it = botoes.iterator();
//		while(it.hasNext()){
			for (int x=0;x<botoes.size();x++) {
	//				Botao b= (Botao) it.next();
				Botao b= botoes.get(x);
	
				b.SimulaSe((int)diftime);			
				if (b.isAtivo()) {
					trataBotao(b);
					botoes.get(x).setAtivo(false);
				}		
			}
		
		
		getMiraAtiva().SimulaSe((int)diftime);	

		

	}
	private void trataBotao(Botao b) {
		// TODO Auto-generated method stub
		if (b.getName().contains("Play") ) {
			
			GamePanel.setCanvasAtivo(CanvasGame.instance);
			
		}else if (b.getName().contains("Options") ) {
			if (menuAtivo!=menuOptions){
				menuAtivo=menuOptions;
			}
			else {
				menuAtivo=null;
			}
		}else if (b.getName().contains("Play") ) {
			
			GamePanel.setCanvasAtivo(CanvasGame.instance);
			
		}else if (b.getName().contains("Play") ) {
			
			GamePanel.setCanvasAtivo(CanvasGame.instance);
			
		}
		
		
	}
	@Override
	void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_ENTER){
			
			GamePanel.setCanvasAtivo(CanvasGame.instance);


		}else if(keyCode == KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
		
		if(keyCode == KeyEvent.VK_F1){
			instrucao = true;
		}
		
		if(keyCode == KeyEvent.VK_ESCAPE){
			instrucao = false;
		}
		
	}

	@Override
	void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		setMousex(e.getX());
		setMousey(e.getY());
	}

	@Override
	void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		setMousex(e.getX());
		setMousey(e.getY());
	}

	@Override
	void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		int button = e.getButton();
		getMiraAtiva().released(button);
		if (button == MouseEvent.BUTTON1) {
			
		
			getMiraAtiva().trataClickMouse1();

			
		}
	


	}

	@Override
	void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int button = e.getButton();
		getMiraAtiva().pressed(button);
		
		if (button == MouseEvent.BUTTON1) {
			getMiraAtiva().trataClickMouse1();
			
			
			}
		else if (button == MouseEvent.BUTTON3) {
			getMiraAtiva().trataClickMouse2();
		
		}
		
}

	@Override
	void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		setMousex(e.getX());
		setMousey(e.getY());
	}

	@Override
	void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

package Canvas;


import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import Constantes.Constantes;
import Data.Imagem;
import GameState.GamePanel;
import Gerenciadores.GerenciadorDeSom;
import Gerenciadores.GerenciadorObstaculos;
import Interface.Botao;
import Interface.FrameBase;
import Interface.FrameStart;
import Interface.FrameOptions;
import Map.Obstaculo;
import Mouse.CursorMenu;
import Mouse.Mira;

public class CanvasStart extends GCanvas{
	
	static BufferedImage imagemlogo;

	private Font fonteLogo, fonteMenu, fonteAutores;
	GerenciadorDeSom loader;
	boolean instrucao = false;
	BufferedImage img;
	Imagem imgloader;
	
	FrameBase frameAtivo;
	private static Mira miraAtiva;

	

	private FrameBase menuOptions;
	private FrameStart frameStart;
	
	
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
	 
		frameStart=new FrameStart(0, 0, GamePanel.PWIDTH,GamePanel.PHEIGHT, Color.darkGray, -1);
		
//		menuOptions= new FrameOptions(250, 0, GamePanel.PWIDTH-250,GamePanel.PHEIGHT, Color.darkGray, 9999);
		
		frameAtivo=frameStart;
		setMiraAtiva(new CursorMenu());
		
		
		

//		
//		FileOutputStream in;
//		try {
//			in = new FileOutputStream( ( "obstaculos.txt") );
//			OutputStreamWriter out= new OutputStreamWriter(in);
//			
//			out.write("UHUHUHUHUUHHU",0,"UHUHUHUHUUHHU".length());
//		
//			out.close();
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			System.out.println("arquivo nao achado");
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			System.out.println("write pau");
//
//			e.printStackTrace();
//		}
//		
//		
//		
//		InputStream in2 = Data.Imagem.class.getResourceAsStream("obstaculos.txt");
//		
//		
//		BufferedReader bf = new BufferedReader(new InputStreamReader(in2));
//		
//		try {
//			System.out.println(bf.readLine());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}



	}
	@Override
	public void DesenhaSe(Graphics2D dbg) {
		// TODO Auto-generated method stub
		
			dbg.setFont(fonteMenu);
			
			dbg.setColor(Color.black);
			dbg.fillRect(0, 0, GamePanel.PWIDTH, GamePanel.PHEIGHT);
			dbg.drawImage(Imagem.fundoCidade, -50, 0,GamePanel.PWIDTH+50,GamePanel.PHEIGHT,null);

			dbg.drawImage(Imagem.logo, GamePanel.PWIDTH-100, GamePanel.PHEIGHT-110,100,110,null);
			
			if (frameAtivo!=null) 
				frameAtivo.DesenhaSe(dbg, 0, 0);

			dbg.setColor(Color.yellow);
			
			//dbg.setFont(fonteAutores);
			Iterator<Botao> it = botoes.iterator();

			while(it.hasNext()){
				Botao obj= it.next();
			obj.DesenhaSe(dbg, 0, 0);
			}
			
			getMiraAtiva().DesenhaSe(dbg, 0, 0);

			dbg.setColor(Color.yellow);
			
//			//predio 1
//			dbg.fillRoundRect(24, GamePanel.PHEIGHT-100,8, 8, 1, 1);
//			dbg.fillRoundRect(40, GamePanel.PHEIGHT-100,8, 8, 1, 1);
//			dbg.fillRoundRect(56, GamePanel.PHEIGHT-100,8, 8, 1, 1);
//			dbg.fillRoundRect(72, GamePanel.PHEIGHT-100,8, 8, 1, 1);
//	
//			dbg.fillRoundRect(24, GamePanel.PHEIGHT-84,8, 8, 1, 1);
//			dbg.fillRoundRect(40, GamePanel.PHEIGHT-84,8, 8, 1, 1);
//			dbg.fillRoundRect(56, GamePanel.PHEIGHT-84,8, 8, 1, 1);
//			dbg.fillRoundRect(72, GamePanel.PHEIGHT-84,8, 8, 1, 1);
//			
//			
//			//predio 2
//			dbg.fillRoundRect(110, GamePanel.PHEIGHT-84,4, 4, 1, 1);
//			dbg.fillRoundRect(115, GamePanel.PHEIGHT-84,4, 4, 1, 1);
//			dbg.fillRoundRect(120, GamePanel.PHEIGHT-84,4, 4, 1, 1);
//			
//			dbg.fillRoundRect(110, GamePanel.PHEIGHT-92,4, 4, 1, 1);
//			dbg.fillRoundRect(115, GamePanel.PHEIGHT-92,4, 4, 1, 1);
//			dbg.fillRoundRect(120, GamePanel.PHEIGHT-92,4, 4, 1, 1);
	
			
	}

	@Override
	public void SimulaSe(long diftime) {
		// TODO Auto-generated method stub
		Constantes.XTela=0;
		Constantes.YTela=0;
		if (frameAtivo!=null)
			frameAtivo.SimulaSe((int)diftime);

//		Iterator<Botao> it = botoes.iterator();
//		while(it.hasNext()){
//			for (int x=0;x<botoes.size();x++) {
//	//				Botao b= (Botao) it.next();
//				Botao b= botoes.get(x);
//				
//				b.SimulaSe((int)diftime);			
//				if (b.ativo==true) {
//					trataBotao(b);
//					botoes.get(x).ativo=false;
//				}		
//			}
//		
		
	//	getMiraAtiva().SimulaSe((int)diftime);	

		

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
		if (frameAtivo!=null)  {
//			for (int i=0;i<menuAtivo.botoes.size();i++) {
				
				frameAtivo.mouseMoved(e);
//			}
		}
		
		Constantes.mouseXTela=e.getX()+Constantes.XTela;
		Constantes.mouseYTela=e.getY()+Constantes.YTela;

		miraAtiva.X=e.getX();
		miraAtiva.Y=e.getY();
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		miraAtiva.X=e.getX();
		miraAtiva.Y=e.getY();
	
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
		if (frameAtivo!=null)  {
		
				
				frameAtivo.mousePressed(e);
			
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
		
		if (frameAtivo!=null)  {
	
				
				frameAtivo.mouseClicked(e);
			
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

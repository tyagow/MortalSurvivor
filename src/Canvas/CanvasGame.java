package Canvas;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import sun.applet.Main;

import AbstractClasses.Objeto;
import Armas.Projetil;
import Base.Base;
import Constantes.Constantes;
import Data.Imagem;
import GameState.GamePanel;
import Gerenciadores.GerenciadorJogo;
import Gerenciadores.GerenciadorDeRaids;
import Gerenciadores.GerenciadorEfeitos;
import Gerenciadores.GerenciadorHud;
import Gerenciadores.GerenciadorObstaculos;
import Gerenciadores.GerenciadorRespawn;
import Gerenciadores.GerenciadorTorre;
import Gerenciadores.GerenciadorXP;
import Map.Minimap;
import Map.Obstaculo;
import Map.TileMap;
import Map.WayPoint;
import Mouse.CursorMenuTorre;
import Mouse.Mira;
import Mouse.MiraJogo;
import Personagem.Heroi;




public class CanvasGame extends GCanvas {

	public static CanvasGame instance = null;
	
	static BufferedImage imagemcharsets;
	static boolean click;
	public static int Health = 20;
	
	public static boolean endGame=false;
	public static Mira miraAtiva;

	
	private static double mousex;
	private static double mousey;
	BufferedImage fundo;

	public static Base base;
	
	public static ArrayList<Objeto> objetos = new ArrayList<Objeto>();
	
	public static GerenciadorTorre gerenciadorTorre;
	public static GerenciadorEfeitos gerenciadorEfeitos;
	public static GerenciadorRespawn gerenciadorRespawn;
	public static GerenciadorHud gerenciadorHud;
	public static GerenciadorDeRaids gerenciadorDeRaids;
	private static  GerenciadorXP gerenciadorXP;
	public static GerenciadorObstaculos gerenciadorObstaculos;
	private static GerenciadorJogo gameManager;
	
	
	public static Minimap minimap;
	public static double timer = 0;
	
	public static Heroi heroi;


	public static float velocidadeJogo;




	
	public static TileMap tela;
	public static int altura;
	public static int largura;

	private static Mira miraJogo;


	protected static boolean ContiuaJogo;

	private static CursorMenuTorre miraMenu;
	
	static Imagem loadImagem;

	public static boolean miraDoJogoSelecionada;

	public static boolean separacaoInimigos=false;
	

	
	public CanvasGame() {
		// TODO Auto-generated constructor stub
		instance = this;
		tela = new TileMap(Imagem.tileset, GamePanel.PWIDTH/16, GamePanel.PHEIGHT/16);
		tela.AbreMapa("60x601.map");
		largura = tela.Largura*16;
		altura = tela.Altura*16;
		inicializaFase();
		recarregaFase();
		
	}





	private void inicializaFase() {
		gerenciadorObstaculos=new GerenciadorObstaculos();
		base = new Base(largura/2, altura/2, Imagem.base);
		gerenciadorTorre = new GerenciadorTorre();
		gerenciadorEfeitos = new GerenciadorEfeitos();
		gerenciadorRespawn= new GerenciadorRespawn();
		gerenciadorHud=new GerenciadorHud();
		gerenciadorDeRaids= new GerenciadorDeRaids();
		gameManager=new GerenciadorJogo();
		gerenciadorXP=new GerenciadorXP();
		miraJogo= new MiraJogo();
		miraMenu=new CursorMenuTorre();
		minimap=new Minimap();
		miraAtiva=miraJogo;
	}





	private void recarregaFase() {
		
		velocidadeJogo=1.0f;
		gerenciadorTorre.reset();

		gerenciadorObstaculos.reset();
		Constantes.inimigos.clear();
		gerenciadorDeRaids.reset();
		gerenciadorHud.reset();
		gerenciadorEfeitos.reset();
		gerenciadorXP.reset();
		
		base.life=Constantes.BASE_LIFE_1;
		heroi=new Heroi(GamePanel.PWIDTH/2, GamePanel.PHEIGHT/2,Imagem.heroiUm);
		

		
			ContiuaJogo=true;
//	Constantes.saveObstaculosInFile();	
	}





	@Override
	public
	void DesenhaSe(Graphics2D dbg) {
		// TODO Auto-generated method stub
		dbg.setFont(Constantes.FonteNormal);
		dbg.setColor(Color.white);
		dbg.fillRect(0,0,GamePanel.PWIDTH, GamePanel.PHEIGHT);
		tela.DesenhaSe(dbg);
		dbg.setColor(Color.black);
		dbg.drawString(""+GamePanel.FPS, 10, 10);
		

		
		

		for(int i = 0; i < objetos.size();i++){
			
			Objeto proj = (Objeto)objetos.get(i);
			proj.DesenhaSe(dbg, Constantes.XTela, Constantes.YTela);
			
		}
			
		
		base.DesenhaSe(dbg, Constantes.XTela, Constantes.YTela);
		gerenciadorEfeitos.DesenhaSe(dbg, Constantes.XTela, Constantes.YTela);


		gerenciadorObstaculos.DesenhaSe(dbg, Constantes.XTela, Constantes.YTela);
		
		gerenciadorDeRaids.DesenhaSe(dbg, Constantes.XTela, Constantes.YTela);
		for(int i = 0; i < Constantes.projeteis.size();i++){
			
			Projetil proj = (Projetil) Constantes.projeteis.get(i);
			proj.DesenhaSe(dbg, Constantes.XTela, Constantes.YTela);
			
		}	
		gerenciadorTorre.DesenhaSe(dbg,  Constantes.XTela,  Constantes.YTela);
		
		heroi.DesenhaSe(dbg, Constantes.XTela, Constantes.YTela);
		gerenciadorEfeitos.DesenhaLayerDois(dbg, Constantes.XTela, Constantes.YTela);
		gerenciadorRespawn.DesenhaSe(dbg, Constantes.XTela, Constantes.YTela);
		minimap.DesenhaSe(dbg, Constantes.XTela, Constantes.YTela);

		gerenciadorHud.DesenhaSe(dbg, Constantes.XTela, Constantes.YTela);

		gerenciadorXP.DesenhaSe(dbg, Constantes.XTela, Constantes.YTela);

		gameManager.DesenhaSe(dbg, Constantes.XTela, Constantes.XTela);

		if (getMiraAtiva()!=null)
			getMiraAtiva().DesenhaSe(dbg, Constantes.XTela, Constantes.YTela);
		
	}
	
	

	public void SimulaSe(long DiffTime) {


		verificaFimDoJogo();
		trataMiraDoJogo();
		
		gameManager.SimulaSe((int)DiffTime,velocidadeJogo);
		
		miraAtiva.SimulaSe((int)(DiffTime));
		heroi.SimulaSe((int)(DiffTime*velocidadeJogo));

		if(!GerenciadorRespawn.isRespawn()){
			tela.Posiciona((int)(heroi.X-(GamePanel.PWIDTH/2)), (int)heroi.Y-(GamePanel.PHEIGHT/2));
			
		}
		if (velocidadeJogo==0) {
			
		}
		

		Iterator<Objeto> itO = objetos.iterator();
		while(itO.hasNext()){
			Objeto inim = itO.next();
			inim.SimulaSe((int)(DiffTime*velocidadeJogo));
			if(inim.isVivo()==false){
				itO.remove();
				}
		}
		base.SimulaSe((int)(DiffTime*velocidadeJogo));
		

		
		Iterator<Projetil> itP = Constantes.projeteis.iterator();
		while(itP.hasNext()){
			Projetil proj = itP.next();
			proj.SimulaSe((int)(DiffTime*velocidadeJogo));
			if(proj.isVivo()==false){
				itP.remove();
				}		
		}	

		gerenciadorObstaculos.SimulaSe((int) (DiffTime*velocidadeJogo));
		if(!Constantes.menuDeObstaculos)
			gerenciadorDeRaids.SimulaSe((int)(DiffTime*velocidadeJogo));
		gerenciadorEfeitos.SimulaSe((int)(DiffTime*velocidadeJogo));
		gerenciadorTorre.SimulaSe((int)(DiffTime*velocidadeJogo));
		gerenciadorRespawn.SimulaSe((int)(DiffTime*velocidadeJogo));
		gerenciadorHud.SimulaSe((int)(DiffTime*velocidadeJogo));
		gerenciadorXP.SimulaSe((int)(DiffTime*velocidadeJogo));
		
		

	}
	
	private void verificaFimDoJogo() {
		
		
		if (endGame && Constantes.inimigos.isEmpty() ||base.getLife()<=0) {
		
			recarregaFase();
			GamePanel.setCanvasAtivo(new CanvasStart());
		}
		
	}





	private void trataMiraDoJogo() {
		// TODO Auto-generated method stub
//		if (!Constantes.hitMiraMenu&&!Constantes.hitMiraSelecionador) {
//			setMiraAtiva(miraJogo);
//			
//		}else {
//			setMiraAtiva(miraMenu);
//			
//		}
		Constantes.XTela=tela.XTela;
		Constantes.YTela=tela.YTela;
}





	@Override
	public
	void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		heroi.keyPressed(e);
		gameManager.keyPressed(e);
		if(keyCode == KeyEvent.VK_0){
			Constantes.testeGradeColisao=true;
			CanvasGame.separacaoInimigos=true;
		}		
		if(keyCode == KeyEvent.VK_8){
			for (int i=0;i<GerenciadorObstaculos.obstaculos.size();i++) {
				Obstaculo obs = GerenciadorObstaculos.obstaculos.get(i);
				System.out.println(i+";"+(int)obs.X+";"+(int)obs.Y+";"+(int)obs.sizeX+";"+(int)obs.sizeY+";"+(int)obs.tileSetColuna+";"+(int)obs.tileSetLinha);
				
			}	
		}	
		if(keyCode == KeyEvent.VK_7){
			for (int i=0;i<Constantes.wayPoints.size();i++) {
				WayPoint obs = Constantes.wayPoints.get(i);
				System.out.println(i+";"+(int)obs.X/16+";"+(int)obs.Y/16+";"+(int)obs.sizeX/16+";"+(int)obs.sizeY/16+";"+(int)obs.indexNextTarget);
				
			}	
		}	


		if(keyCode == KeyEvent.VK_3){
			GerenciadorTorre.adicionaTorre((int)mousex+tela.XTela,(int)mousey+tela.YTela,32,32);
		}	
		
		if(keyCode == KeyEvent.VK_4){
//			GamePanel.setCanvasAtivo(new CanvasGame());
			velocidadeJogo=2;
		}

		if(keyCode == KeyEvent.VK_SHIFT){
			Constantes.EVENT_contruirTorre=true;
		}	
	
	}

	@Override
	public void keyReleased(KeyEvent e ) {
		int keyCode = e.getKeyCode();
		gameManager.keyReleased(e);
		heroi.keyReleased(keyCode);
		
		if(keyCode == KeyEvent.VK_0){
			Constantes.testeGradeColisao=false;
			CanvasGame.separacaoInimigos=false;

		}	
		if(keyCode == KeyEvent.VK_4){
//			GamePanel.setCanvasAtivo(new CanvasGame());
			velocidadeJogo=1;
		}
		if(keyCode == KeyEvent.VK_F6){
			if (Constantes.menuDeObstaculos){
				Constantes.menuDeObstaculos=false;
				Constantes.hitMiraMenu=false;
				Constantes.miraDoJogo=true;
			}
			else {
				Constantes.menuDeObstaculos=true;
				Constantes.miraDoJogo=false;			}
		
		}
		if(keyCode == KeyEvent.VK_SHIFT){
			Constantes.EVENT_contruirTorre=false;
		}	

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		setMousex(e.getX());
		setMousey(e.getY());
		
		Constantes.mouseXTela=e.getX()+tela.XTela;
		Constantes.mouseYTela=e.getY()+tela.YTela;

		gerenciadorTorre.mouseMoved(e);
		gerenciadorObstaculos.mouseMoved(e);
		gameManager.mouseMoved(e);

		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		setMousex(e.getX());
		setMousey(e.getY());
		gerenciadorObstaculos. mouseDragged(e);
		gameManager.mouseDragged(e);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		int button = e.getButton();
		getMiraAtiva().released(button);
		if (button == MouseEvent.BUTTON1) {
			
			
			getMiraAtiva().trataClickMouse1();

			
		}
		
		if (button == MouseEvent.BUTTON3) {

			heroi.PRIMARIA=false;
		}
		
		gerenciadorTorre.mouseReleased(e);
		gerenciadorObstaculos.mouseReleased(e);
		gameManager.mouseReleased(e);

	}

	@Override
	public
	void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int button = e.getButton();
		gerenciadorTorre.mousePressed(e);
		gerenciadorObstaculos.mousePressed(e);
		gameManager.mousePressed(e);

		getMiraAtiva().pressed(button);
		
		if (button == MouseEvent.BUTTON1) {
			getMiraAtiva().trataClickMouse1();
			
			
			}
		else if (button == MouseEvent.BUTTON3) {
//			getMiraAtiva().trataClickMouse2();
			heroi.PRIMARIA=true;

		
}
		
	}

	@Override
	public
	void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public
	void mouseEntered(MouseEvent e) {
		setMousex(e.getX());
		setMousey(e.getY());
		
	}

	@Override
	public
	void mouseClicked(MouseEvent e) {
		gerenciadorTorre.mouseClicked(e);
		gerenciadorObstaculos.mouseClicked(e);
		gameManager.mouseClicked(e);

	}

public static void setMiraMenu() {
		// TODO Auto-generated method stub
		if (miraDoJogoSelecionada) {
			miraAtiva.pressed =(false);
			miraAtiva=(miraMenu);
			miraDoJogoSelecionada=false;
		}
	}

public static void setMiraJogo() {
		// TODO Auto-generated method stub
		if (!miraDoJogoSelecionada) {
			if (miraAtiva!=null)
				miraAtiva.pressed =(false);
			
			miraAtiva=(miraJogo);
			miraDoJogoSelecionada=true;
		}
	}


public static void setMiraAtiva(Mira _miraAtiva) {
	miraAtiva = _miraAtiva;
}
public static Mira getMiraAtiva() {
	return miraAtiva;
}
public static void setMousey(int _mousey) {
	mousey = _mousey;
}
public static double getMousey() {
	return mousey;
}
public static void setMousex(int _mousex) {
	mousex = _mousex;
}
public static double getMousex() {
	return mousex;
}
}

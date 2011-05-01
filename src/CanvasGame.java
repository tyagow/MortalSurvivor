import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;

import javax.swing.JFrame;




public class CanvasGame extends GCanvas {

	public static CanvasGame instance = null;
	
	static BufferedImage imagemcharsets;
	//public static BufferedImage mira1;
	
//	static int mousex,mousey; 
	static int torre;
	static boolean click;
	public static int Health = 20;
	
	private static boolean endGame=false;

	
	
	BufferedImage fundo;

	public static Base base;
	
	public static ArrayList<Projetil> projeteis = new ArrayList<Projetil>();

	public static ArrayList<Objeto> objetos = new ArrayList<Objeto>();
	
	//public static ArrayList<Inimigo> inimigos = new ArrayList<Inimigo>();
	
	public static ArrayList<Torre> torres = new ArrayList<Torre>();
	
	public static GerenciadorTorre gerenciadorTorre;
	public static GerenciadorEfeitos gerenciadorEfeitos;
	public static GerenciadorRespawn gerenciadorRespawn;
	public static GerenciadorHud gerenciadorHud;
	public static GerenciadorDeRaids gerenciadorDeRaids;

public static Minimap minimap;
	//static BufferedImage tileset;

//	private boolean menu;

//	public static int Torre=0;



	//private boolean TimerClick=true;

//	private long timermouse=0;

	//private boolean mouseRelease;

//	public static boolean dialogoativo = false;
//	public static int timerdialogo = 0;
//	public static String oDialogo = "";
	
//	public static GerenciadorDeRaids gerenciadorderaids;
//	
//	public static ControladorDeDirecao controladordedirecao;
//	
	static double timer = 0;


//	public static int Dinheiro=Constantes.DINHEIRO_INICIAL;

	Font fonte;

	Font fonte3;

	Font fonte2;

	public static Heroi heroi;

	public int MundoY=0;

	public int MundoX=0;

	private GerenciadorXP gerenciadorXP;

	public static GerenciadorObstaculos gerenciadorObstaculos;
	
	public static TileMap MAPA;

	public static int altura;
	public static int largura;

	//private static Mira miraAtiva;
	private static Mira miraJogo;

	public static boolean testeGradeColisao=false;

	protected static boolean ContiuaJogo;

	private static CursorMenuTorre miraMenu;
	
	static Imagem loadImagem;

	public static boolean miraDoJogoSelecionada;
	

	
	public CanvasGame() {
		// TODO Auto-generated constructor stub
		instance = this;
		carregaFontes();

		MAPA = new TileMap(Imagem.tileset, GamePanel.PWIDTH/16, GamePanel.PHEIGHT/16);
		MAPA.AbreMapa("60x601.map");
				
		gerenciadorTorre = new GerenciadorTorre();
		gerenciadorEfeitos = new GerenciadorEfeitos();
		gerenciadorRespawn= new GerenciadorRespawn();
		gerenciadorHud=new GerenciadorHud();
		gerenciadorObstaculos=new GerenciadorObstaculos();
		gerenciadorDeRaids= new GerenciadorDeRaids();
		gerenciadorXP=new GerenciadorXP();
		
		largura = MAPA.Largura*16;
		altura = MAPA.Altura*16;
	
		base = new Base(largura/2, altura/2,Constantes.BASE_SIZEX_1,Constantes.BASE_SIZEY_1);
		
		
		miraJogo= new MiraRedonda();

		miraMenu=new CursorMenuTorre();
		
		heroi=new Heroi(GamePanel.PWIDTH/2, GamePanel.PHEIGHT/2,Imagem.heroiUm);
		
		minimap=new Minimap();
		ContiuaJogo=true;
		
	}

	private void carregaFontes() {
		// TODO Auto-generated method stub
		
		fonte = new Font("Courier", Font.BOLD, 12);
		fonte2 = new Font("Courier", Font.BOLD, 13);
		fonte3 = new Font("Courier", Font.BOLD, 16);
	}

	private void carregaImagens() {
		// TODO Auto-generated method stub

	//	loadImagem = new Imagem();

	}

	@Override
	void DesenhaSe(Graphics2D dbg) {
		// TODO Auto-generated method stub
		dbg.setFont(fonte2);
		dbg.setColor(Color.LIGHT_GRAY);
		dbg.fillRect(0,0,GamePanel.PWIDTH, GamePanel.PHEIGHT);
	//	MAPA.DesenhaSe(dbg);

		dbg.setColor(Color.black);
		dbg.drawString(""+GamePanel.FPS, 10, 10);
		
		gerenciadorEfeitos.DesenhaSe(dbg, MAPA.MapX, MAPA.MapY);

		
		for(int i = 0; i < projeteis.size();i++){
			
			Projetil proj = (Projetil) projeteis.get(i);
			proj.DesenhaSe(dbg, MAPA.MapX, MAPA.MapY);
			
		}		

		for(int i = 0; i < objetos.size();i++){
			
			Objeto proj = (Objeto)objetos.get(i);
			proj.DesenhaSe(dbg, MAPA.MapX, MAPA.MapY);
			
		}
			
		gerenciadorDeRaids.DesenhaSe(dbg, MAPA.MapX, MAPA.MapY);
		heroi.DesenhaSe(dbg, MAPA.MapX, MAPA.MapY);
		gerenciadorTorre.DesenhaSe(dbg,  MAPA.MapX,  MAPA.MapY);

		gerenciadorHud.DesenhaSe(dbg, MAPA.MapX, MAPA.MapY);
		base.DesenhaSe(dbg, MAPA.MapX, MAPA.MapY);

		gerenciadorXP.DesenhaSe(dbg, MAPA.MapX, MAPA.MapY);
		minimap.DesenhaSe(dbg, MAPA.MapX, MAPA.MapY);
		getMiraAtiva().DesenhaSe(dbg, MAPA.MapX, MAPA.MapY);
		
		gerenciadorRespawn.DesenhaSe(dbg, MAPA.MapX, MAPA.MapY);
		
		gerenciadorObstaculos.DesenhaSe(dbg, MAPA.MapX, MAPA.MapY);
		
		
	}
	
	

	
	void SimulaSe(long DiffTime) {
		
		if (getMiraAtiva() !=(Mira)miraJogo&&!gerenciadorTorre.hitMiraMenu) {
			setMiraAtiva(miraJogo);
			
		}
		getMiraAtiva().SimulaSe((int)DiffTime);
		
		if(!GerenciadorRespawn.isRespawn()){
			MAPA.Posiciona((int)(heroi.getX()-(GamePanel.PWIDTH/2)), (int)heroi.getY()-(GamePanel.PHEIGHT/2));
		}
		
//		MAPA.PosiWciona((int)(mousex-(GamePanel.PWIDTH/2)), (int)mousey-(GamePanel.PHEIGHT/2));
//		MAPA.Posiciona((int)(mousex-(GamePanel.PWIDTH/2)), (int)mousey-(GamePanel.PHEIGHT/2));

		
		
		Iterator<Objeto> itO = objetos.iterator();
		while(itO.hasNext()){
			Objeto inim = itO.next();
			inim.SimulaSe((int)DiffTime);
			if(inim.isVivo()==false){
				itO.remove();
				}
		}
		base.SimulaSe((int)DiffTime);
		heroi.SimulaSe((int)DiffTime);
		
		Iterator<Projetil> itP = projeteis.iterator();
		while(itP.hasNext()){
			Projetil proj = itP.next();
			proj.SimulaSe((int)DiffTime);
			if(proj.isVivo()==false){
				itP.remove();
				}		
		}	

		gerenciadorObstaculos.SimulaSe((int) DiffTime);
		gerenciadorDeRaids.SimulaSe((int)DiffTime);
		gerenciadorEfeitos.SimulaSe((int)DiffTime);
		gerenciadorTorre.SimulaSe((int)DiffTime);
		gerenciadorRespawn.SimulaSe((int)DiffTime);
		gerenciadorHud.SimulaSe((int)DiffTime);
		gerenciadorXP.SimulaSe((int)DiffTime);
		
	}
	
	@Override
	void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_0){
			testeGradeColisao=true;
	}		
		if(keyCode == KeyEvent.VK_ESCAPE){
			GamePanel.setCanvasAtivo(new CanvasMenu());
		}
		if(keyCode == KeyEvent.VK_Q){
			heroi.ARMA_ANTERIOR=true;
		}
		if(keyCode == KeyEvent.VK_F){
			heroi.HE=true;
		}
		if(keyCode == KeyEvent.VK_W){
			heroi.UP=true;
		}
		if(keyCode == KeyEvent.VK_S){
			heroi.DOWN=true;
		}
		if(keyCode == KeyEvent.VK_A){
			heroi.LEFT=true;
		}
		if(keyCode == KeyEvent.VK_D){
			heroi.RIGHT=true;
		}
		if(keyCode == KeyEvent.VK_1){
			heroi.MELEE=true;
		}
		if(keyCode == KeyEvent.VK_2){
			heroi.SECUNDARIA=true;
		}
		if(keyCode == KeyEvent.VK_3){
			GerenciadorTorre.adicionaTorre((int)getMousex()+MAPA.MapX,(int)getMousey()+MAPA.MapY);
		}	
		if(keyCode == KeyEvent.VK_4){
			//inimigos.add(new Inimigo(Constantes.inimigoUm));
		}
		
	
	}

	@Override
	public void keyReleased(KeyEvent e ) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_0){
			testeGradeColisao=false;
	}	
		if(keyCode == KeyEvent.VK_W){
			heroi.UP=false;
		}
		if(keyCode == KeyEvent.VK_S){
			heroi.DOWN=false;
		}
		if(keyCode == KeyEvent.VK_A){
			heroi.LEFT=false;
		}
		if(keyCode == KeyEvent.VK_D){
			heroi.RIGHT=false;
		}
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
		if (button == MouseEvent.BUTTON3) {

			heroi.PRIMARIA=false;
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
			heroi.PRIMARIA=true;

		
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
	
	}

//	public static void setMiraAtiva(Mira miraAtiva) {
//		CanvasGame.miraAtiva = miraAtiva;
//	}
//
//	public static Mira getMiraAtiva() {
//		return miraAtiva;
//	}

	public static void setEndGame(boolean endGame) {
		CanvasGame.endGame = endGame;
	}

	public static boolean isEndGame() {
		return endGame;
	}

	public static void setMiraMenu() {
		// TODO Auto-generated method stub
		getMiraAtiva().setPressed(false);
		setMiraAtiva(miraMenu);
		miraDoJogoSelecionada=false;
	}

	public static void setMiraJogo() {
		// TODO Auto-generated method stub
		getMiraAtiva().setPressed(false);

		setMiraAtiva(miraJogo);
		miraDoJogoSelecionada=true;

	}

}

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
	
	static int mousex,mousey; 
	static int torre;
	static boolean click;
	public static int Health = 20;
	
	
	
	
	BufferedImage fundo;

	public static ArrayList<Projetil> projeteis = new ArrayList<Projetil>();

	public static ArrayList<Objeto> objetos = new ArrayList<Objeto>();
	
	public static ArrayList<Inimigo> inimigos = new ArrayList<Inimigo>();
	
	public static ArrayList<Torre> torres = new ArrayList<Torre>();
	
	public static GerenciadorTorre gerenciadorTorre;
	public static GerenciadorEfeitos gerenciadorEfeitos;
	public static GerenciadorRespawn gerenciadorRespawn;
	public static GerenciadorHud gerenciadorHud;


	BufferedImage tileset;

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
	
	public static TileMap MAPA;

	public static int altura;
	public static int largura;
	private static  Mira miraAtiva;
	private static Mira miraUm;
	
	public CanvasGame() {
		// TODO Auto-generated constructor stub
		instance = this;
		tileset = Constantes.LoadImage("Bridge.png");

		Constantes.mira1= Constantes.LoadImage("mira1.png");		
		MAPA = new TileMap(tileset, GamePanel.PWIDTH/16, GamePanel.PHEIGHT/16);
		MAPA.AbreMapa("60x60.map");
		
		gerenciadorTorre = new GerenciadorTorre();
		gerenciadorEfeitos = new GerenciadorEfeitos();
		gerenciadorRespawn= new GerenciadorRespawn();
		gerenciadorHud=new GerenciadorHud();
		
		largura = MAPA.Largura*16;
		altura = MAPA.Altura*16;

		setMiraAtiva(new Mira());
		//objetos.add(miraAtiva);
		
		
		inimigos.add(new Inimigo());
		heroi=new Heroi(GamePanel.PWIDTH/2, GamePanel.PHEIGHT/2);
		

		
		fonte = new Font("Courier", Font.BOLD, 12);
		fonte2 = new Font("Courier", Font.BOLD, 13);
		fonte3 = new Font("Courier", Font.BOLD, 16);

		
	}

	@Override
	void DesenhaSe(Graphics2D dbg) {
		// TODO Auto-generated method stub
		dbg.setFont(fonte2);
		dbg.setColor(Color.white);
		dbg.fillRect(0,0,GamePanel.PWIDTH, GamePanel.PHEIGHT);

//		MAPA.DesenhaSe(dbg);
		dbg.setColor(Color.black);
		dbg.drawString(""+GamePanel.FPS, 10, 10);
		
		gerenciadorEfeitos.DesenhaSe(dbg, MAPA.MapX, MAPA.MapY);


		for(int i = 0; i < projeteis.size();i++){
			
			Projetil proj = (Projetil) projeteis.get(i);
			proj.DesenhaSe(dbg, MAPA.MapX, MAPA.MapY);
			
		}		
		gerenciadorTorre.DesenhaSe(dbg,  MAPA.MapX,  MAPA.MapY);

		for(int i = 0; i < objetos.size();i++){
			
			Objeto proj = (Objeto)objetos.get(i);
			proj.DesenhaSe(dbg, MAPA.MapX, MAPA.MapY);
			
		}
		
		for(int i = 0; i < inimigos.size();i++){
			Inimigo inim = (Inimigo)inimigos.get(i);
			inim.DesenhaSe(dbg, MAPA.MapX, MAPA.MapY);
		}
		
		heroi.DesenhaSe(dbg, MAPA.MapX, MAPA.MapY);

		gerenciadorHud.DesenhaSe(dbg, MAPA.MapX, MAPA.MapY);
		
		getMiraAtiva().DesenhaSe(dbg, MAPA.MapX, MAPA.MapY);
		
		gerenciadorRespawn.DesenhaSe(dbg, MAPA.MapX, MAPA.MapY);
		
		
	}
	
	

	
	void SimulaSe(long DiffTime) {
		
		getMiraAtiva().SimulaSe((int)DiffTime);

		MAPA.Posiciona((int)(heroi.getX()-(GamePanel.PWIDTH/2)), (int)heroi.getY()-(GamePanel.PHEIGHT/2));

		Iterator<Objeto> itO = objetos.iterator();
		while(itO.hasNext()){
			Objeto inim = itO.next();
			inim.SimulaSe((int)DiffTime);
			if(inim.isVivo()==false){
				itO.remove();
				}
		}
		
		heroi.SimulaSe((int)DiffTime);
		
		Iterator<Projetil> itP = projeteis.iterator();
		while(itP.hasNext()){
			Projetil inim = itP.next();
			inim.SimulaSe((int)DiffTime);
			if(inim.isVivo()==false){
				itP.remove();
				
				}		
		}	

		Iterator<Inimigo> it = inimigos.iterator();
		while(it.hasNext()){
			Inimigo inim = it.next();
			inim.SimulaSe((int)DiffTime);
			if(inim.isVivo()==false){
				it.remove();
				gerenciadorEfeitos.ganhouXp(inim.getX(), inim.getY(),inim.getTipoAssasino() );
				}
		}

		gerenciadorTorre.SimulaSe((int)DiffTime);
		gerenciadorEfeitos.SimulaSe((int)DiffTime);
		gerenciadorRespawn.SimulaSe((int)DiffTime);
		gerenciadorHud.SimulaSe((int)DiffTime);
	}
	
	@Override
	void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
				
		if(keyCode == KeyEvent.VK_ESCAPE){
			GamePanel.CanvasAtivo = new CanvasMenu();
		}
		if(keyCode == KeyEvent.VK_Q){
			heroi.ARMA_ANTERIOR=true;
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
			GerenciadorTorre.adicionaTorre(mousex+MAPA.MapX,mousey+MAPA.MapY);
		}	
		if(keyCode == KeyEvent.VK_4){
			inimigos.add(new Inimigo());
		}
		
	
	}

	@Override
	public void keyReleased(KeyEvent e ) {
		int keyCode = e.getKeyCode();
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
		mousex = e.getX();
		mousey = e.getY();
		
	}

	@Override
	void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		mousex = e.getX();
		mousey = e.getY();
	}

	@Override
	void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		int button = e.getButton();
		getMiraAtiva().released();
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
		mousex = e.getX();
		mousey = e.getY();
	}

	@Override
	void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int button = e.getButton();

		
	}
//	public static void main(String args[])
//	{
//		GamePanel ttPanel = new GamePanel();
//
//	  // create a JFrame to hold the timer test JPanel
//	  JFrame app = new JFrame("Swing Timer Test");
//	  app.getContentPane().add(ttPanel, BorderLayout.CENTER);
//	  app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	  
//	  int[] pixels = new int[16 * 16];
//	    Image image = Toolkit.getDefaultToolkit().createImage(
//	        new MemoryImageSource(16, 16, pixels, 0, 16));
//	    Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(
//	        image, new Point(0, 0), "invisibleCursor");
//	    
//	  app.setCursor(transparentCursor);
//	  app.pack();
//	  app.setResizable(false);  
//	  app.setVisible(true);
//	} // end of main()

	public static void setMiraAtiva(Mira miraAtiva) {
		CanvasGame.miraAtiva = miraAtiva;
	}

	public static Mira getMiraAtiva() {
		return miraAtiva;
	}

}

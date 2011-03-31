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
//
	public static LinkedList<Particula> particulas = new LinkedList<Particula>();

	public static ArrayList<Objeto> objetos = new ArrayList<Objeto>();

	

	BufferedImage tileset;

	private boolean menu;

	public static int Torre=0;



	//private boolean TimerClick=true;

	private long timermouse=0;

	//private boolean mouseRelease;

	public static boolean dialogoativo = false;
	public static int timerdialogo = 0;
	public static String oDialogo = "";
	
//	public static GerenciadorDeRaids gerenciadorderaids;
//	
//	public static ControladorDeDirecao controladordedirecao;
//	
	static double timer = 0;

//	public static int Dinheiro=Constantes.DINHEIRO_INICIAL;

	Font fonte;

	Font fonte3;

	Font fonte2;

	public Personagem heroi;

	public int MundoY=0;

	public int MundoX=0;

	
	public CanvasGame() {
		// TODO Auto-generated constructor stub
		instance = this;

		Constantes.mira1= Constantes.LoadImage("mira1.png");		


		objetos.add(new Mira());
		heroi=new Personagem(GamePanel.PWIDTH/2, GamePanel.PHEIGHT/2);
		

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
		heroi.DesenhaSe(dbg, 0, 0);
		dbg.setColor(Color.black);
		dbg.drawString(""+GamePanel.FPS, 10, 10);
		for(int i = 0; i < projeteis.size();i++){
			
			Projetil proj = (Projetil) projeteis.get(i);
			proj.DesenhaSe(dbg,0,0);
			
		}
		for(int i = 0; i < particulas.size();i++){
			
			Particula proj = (Particula) particulas.get(i);
			proj.DesenhaSe(dbg,0,0);
			
		}
		
		for(int i = 0; i < objetos.size();i++){
			
			Objeto proj = (Objeto)objetos.get(i);
			proj.DesenhaSe(dbg,0,0);
			
		}
	}
	
	

	
	void SimulaSe(long DiffTime) {
		

		
		for(int i = 0; i < objetos.size();i++){
			
			Objeto proj = (Objeto)objetos.get(i);
			proj.SimulaSe((int)DiffTime);
			
		}
		heroi.SimulaSe((int)DiffTime);
		for(int i = 0; i < projeteis.size();i++){
			
			Projetil proj = (Projetil)projeteis.get(i);
			proj.SimulaSe((int)DiffTime);
			if(proj.vivo==false){
				projeteis.remove(i);
			}
			
		}
//		
		Iterator<Particula> it = particulas.iterator();
		while(it.hasNext()){
			Particula part = it.next();
			part.SimulaSe((int)DiffTime);
			if(part.vivo==false){
				it.remove();
			}
		}
	
	}
	
	@Override
	void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
				
		if(keyCode == KeyEvent.VK_ESCAPE){
			GamePanel.CanvasAtivo = new CanvasMenu();
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

		if (button == MouseEvent.BUTTON1) {
			
			heroi.ATIRA=false;
			}


	}

	@Override
	void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int button = e.getButton();

		if (button == MouseEvent.BUTTON1) {
			
			heroi.ATIRA=true;
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
	public static void main(String args[])
	{
		GamePanel ttPanel = new GamePanel();

	  // create a JFrame to hold the timer test JPanel
	  JFrame app = new JFrame("Swing Timer Test");
	  app.getContentPane().add(ttPanel, BorderLayout.CENTER);
	  app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  
	  int[] pixels = new int[16 * 16];
	    Image image = Toolkit.getDefaultToolkit().createImage(
	        new MemoryImageSource(16, 16, pixels, 0, 16));
	    Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(
	        image, new Point(0, 0), "invisibleCursor");
	    
	  app.setCursor(transparentCursor);
	  app.pack();
	  app.setResizable(false);  
	  app.setVisible(true);
	} // end of main()

}

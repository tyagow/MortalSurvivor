//import java.awt.*;
//import java.awt.event.*;
//import java.awt.geom.AffineTransform;
//
//import javax.swing.*;
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.Random;
//import java.awt.image.*;
//import javax.imageio.ImageIO;
//
//
//public class GamePanel extends JPanel implements Runnable
//{
//
//public static GamePanel instance;
//
//public static final int PWIDTH = 800;
//public static final int PHEIGHT = 600;
//private Thread animator;
//private boolean running = false;
//private boolean gameOver = false; 
//
//private BufferedImage dbImage;
//private Graphics2D dbg;
//
//static int FPS;
//int SFPS;
//int fpscount;
//
//static Random rnd = new Random();
//
//public static GCanvas CanvasAtivo = null;
//
//long tempoinicial = 0;
//long tempototal = 0;
//
//public GamePanel()
//{
//	instance = this;
//
//	setBackground(Color.white);
//	setPreferredSize( new Dimension(PWIDTH, PHEIGHT));
//
//	// create game components
//	setFocusable(true);
//
//	requestFocus(); // JPanel now receives key events
//	
//	if (dbImage == null){
//		dbImage = new BufferedImage(PWIDTH, PHEIGHT,BufferedImage.TYPE_INT_ARGB);
//		if (dbImage == null) {
//			System.out.println("dbImage is null");
//			return;
//		}else{
//			dbg = (Graphics2D)dbImage.getGraphics();
//		}
//	}	
//	
//	
//	// Adiciona um Key Listner
//	addKeyListener( new KeyAdapter() {
//		public void keyPressed(KeyEvent e)
//			{ 
//				if(CanvasAtivo!=null){
//					CanvasAtivo.keyPressed(e);
//				}
//			}
//		@Override
//			public void keyReleased(KeyEvent e ) {
//				if(CanvasAtivo!=null){
//					CanvasAtivo.keyReleased(e);
//				}
//			}
//	});
//	
//	addMouseMotionListener(new MouseMotionListener() {
//		
//		@Override
//		public void mouseMoved(MouseEvent e) {
//			// TODO Auto-generated method stub
//			
//			if(CanvasAtivo!=null){
//				CanvasAtivo.mouseMoved(e);
//			}
//
//		}
//		
//		@Override
//		public void mouseDragged(MouseEvent e) {
//			// TODO Auto-generated method stub
//			if(CanvasAtivo!=null){
//				CanvasAtivo.mouseDragged(e);
//			}
//		}
//	});
//	
//	addMouseListener(new MouseListener() {
//		
//		@Override
//		public void mouseReleased(MouseEvent e) {
//			// TODO Auto-generated method stub
//			if(CanvasAtivo!=null){
//				CanvasAtivo.mouseReleased(e);
//			}
//		}
//		
//		@Override
//		public void mousePressed(MouseEvent e) {
//			// TODO Auto-generated method stub
//			if(CanvasAtivo!=null){
//				CanvasAtivo.mousePressed(e);
//			}
//		}
//		
//		@Override
//		public void mouseExited(MouseEvent e) {
//			// TODO Auto-generated method stub
//			if(CanvasAtivo!=null){
//				CanvasAtivo.mouseExited(e);
//			}	
//		}
//		
//		@Override
//		public void mouseEntered(MouseEvent e) {
//			// TODO Auto-generated method stub
//			if(CanvasAtivo!=null){
//				CanvasAtivo.mouseEntered(e);
//			}
//		}
//		
//		@Override
//		public void mouseClicked(MouseEvent e) {
//			// TODO Auto-generated method stub
//			if(CanvasAtivo!=null){
//				CanvasAtivo.mouseClicked(e);
//			}
//		}
//	});
//
//	CanvasAtivo = new CanvasGame();
//	
//} // end of GamePanel()
//
//public void addNotify()
//{
//	super.addNotify(); // creates the peer
//	startGame(); // start the thread
//}
//
//void startGame()
//// initialise and start the thread
//{
//	if (animator == null || !running) {
//		animator = new Thread(this);
//		animator.start();
//	}
//} // end of startGame()
//
//public void stopGame()
//// called by the user to stop execution
//{ running = false; }
//
//
//public void run()
///* Repeatedly update, render, sleep */
//{
//	running = true;
//	
//	long DifTime,TempoAnterior;
//	
//	int segundo = 0;
//	DifTime = 0;
//	TempoAnterior = System.currentTimeMillis();
//	
//	tempoinicial =  System.currentTimeMillis();
//	
//	while(running) {
//	
//		gameUpdate(DifTime); // game state is updated
//		gameRender(); // render to a buffer
//		paintImmediately(0, 0, PWIDTH, PHEIGHT); // paint with the buffer
//	
//		try {
//			Thread.sleep(0); // sleea bit
//		}	
//		catch(InterruptedException ex){}
//		
//		DifTime = System.currentTimeMillis() - TempoAnterior;
//		TempoAnterior = System.currentTimeMillis();
//		
//		if(segundo!=((int)(TempoAnterior/1000))){
//			FPS = SFPS;
//			SFPS = 1;
//			segundo = ((int)(TempoAnterior/1000));
//		}else{
//			SFPS++;
//		}
//	
//	}
//System.exit(0); // so enclosing JFrame/JApplet exits
//} // end of run()
//
//int timerfps = 0;
//private void gameUpdate(long DiffTime)
//{ 
//	if(CanvasAtivo!=null){
//		CanvasAtivo.SimulaSe(DiffTime);
//	}	
//}
//
//private void gameRender()
//// draw the current frame to an image buffer
//{
//	if(CanvasAtivo!=null){
//		CanvasAtivo.DesenhaSe(dbg);
//	}	
//}
//
//
//public void paintComponent(Graphics g)
//{
//	super.paintComponent(g);
//	if (dbImage != null){
//		g.drawImage(dbImage, 0, 0, null);
//	}
//}
//
////
//public static void main(String args[])
//{
//	GamePanel ttPanel = new GamePanel();
//
//  // create a JFrame to hold the timer test JPanel
//  JFrame app = new JFrame("Mortal Survivor");
//  int[] pixels = new int[16 * 16];
////  
//  Image image = Toolkit.getDefaultToolkit().createImage(
//      new MemoryImageSource(16, 16, pixels, 0, 16));
//  Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(
//      image, new Point(0, 0), "invisibleCursor");
// app.setCursor(transparentCursor);  
// 
//  app.getContentPane().add(ttPanel, BorderLayout.CENTER);
//  app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//  app.pack();
//  app.setResizable(false);  
//  app.setVisible(true);
//} // end of main()
//
//
//
//} // end of GamePanel class

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.awt.image.*;

import javax.imageio.ImageIO;
import java.applet.*;



public class GamePanel extends Canvas implements Runnable
{
static final int PWIDTH = 800;
static final int PHEIGHT = 600;
private Thread animator;
private boolean running = false;
private boolean gameOver = false; 

//public  AudioClip tiro= this.getAudioClip(new URL("path/to/tiro.wav"));

static int FPS;
int SFPS;
int fpscount;

public static Random rnd = new Random();

BufferedImage imagemcharsets;

boolean LEFT, RIGHT,UP,DOWN;

public static int mousex,mousey; 


public static GamePanel instance;



private BufferedImage dbImage;
private Graphics2D dbg;


public static GCanvas CanvasAtivo = null;

public static int score=0;

public static int scoreUltimaJogada;

long tempoinicial = 0;
long tempototal = 0;

CanvasGame canvasGame;

//public static ArrayList<Agente> listadeagentes = new ArrayList<Agente>();

public GamePanel()
{
	instance = this;
	setBackground(Color.white);
	setPreferredSize( new Dimension(PWIDTH, PHEIGHT));

	// create game components
	setFocusable(true);
	
	int[] pixels = new int[16 * 16];
	Image image = Toolkit.getDefaultToolkit().createImage(
	        new MemoryImageSource(16, 16, pixels, 0, 16));
	Cursor transparentCursor =
	        Toolkit.getDefaultToolkit().createCustomCursor
	             (image, new Point(0, 0), "invisibleCursor");
	setCursor(transparentCursor);
	
	
	requestFocus(); // JPanel now receives key events	
	

	
	// Adiciona um Key Listner
	addKeyListener( new KeyAdapter() {
		@Override
			
		public void keyPressed(KeyEvent e)
			{ 
				if(CanvasAtivo!=null){
					CanvasAtivo.keyPressed(e);
				}
			}
		@Override
			public void keyReleased(KeyEvent e ) {
				if(CanvasAtivo!=null){
					CanvasAtivo.keyReleased(e);
				}
			}
	});
addMouseMotionListener(new MouseMotionListener() {
	
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

		if(CanvasAtivo!=null){
			CanvasAtivo.mouseMoved(e);
		}
	}

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(CanvasAtivo!=null){
			CanvasAtivo.mouseDragged(e);
		}
	}
});
	
addMouseListener(new MouseListener() {

		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

			if(CanvasAtivo!=null){
				CanvasAtivo.mouseReleased(e);
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if(CanvasAtivo!=null){
				CanvasAtivo.mousePressed(e);
			}
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			if(CanvasAtivo!=null){
				CanvasAtivo.mouseExited(e);
			}	
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			if(CanvasAtivo!=null){
				CanvasAtivo.mouseEntered(e);
			}
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(CanvasAtivo!=null){
				CanvasAtivo.mouseClicked(e);
			}
		}
	
	});

	CanvasAtivo = new CanvasMenu();
	canvasGame = new CanvasGame();

	
} // end of GamePanel()

public void startGame()
// initialise and start the thread
{
	if (animator == null || !running) {
		animator = new Thread(this);
		animator.start();
	}
} // end of startGame()

public void stopGame()
// called by the user to stop execution
{ running = false; }


public void run()
/* Repeatedly update, render, sleep */
{
	running = true;
	
	long DifTime,TempoAnterior;
	
	int segundo = 0;
	DifTime = 0;
	TempoAnterior = System.currentTimeMillis();
	
	this.createBufferStrategy(2);
	BufferStrategy strategy = this.getBufferStrategy();
	
	while(running) {
	
		gameUpdate(DifTime); // game state is updated
		Graphics g = strategy.getDrawGraphics();
		gameRender((Graphics2D)g); // render to a buffer
		strategy.show();
	
		try {
			Thread.sleep(0); // sleep a bit
		}	
		catch(InterruptedException ex){}
		
		DifTime = System.currentTimeMillis() - TempoAnterior;
		TempoAnterior = System.currentTimeMillis();
		
		if(segundo!=((int)(TempoAnterior/1000))){
			FPS = SFPS;
			SFPS = 1;
			segundo = ((int)(TempoAnterior/1000));
		}else{
			SFPS++;
		}
	
	}
System.exit(0); // so enclosing JFrame/JApplet exits
} // end of run()

int timerfps = 0;
private void gameUpdate(long DiffTime)
{ 
	//System.out.println(mousex);
	if(CanvasAtivo!=null){
		CanvasAtivo.SimulaSe(DiffTime);
	}	
}

private void gameRender(Graphics2D dbg)
// draw the current frame to an image buffer
{
	if(CanvasAtivo!=null){
		CanvasAtivo.DesenhaSe(dbg);
	}	
}


public void paintComponent(Graphics g)
{

	if (dbImage != null){
		g.drawImage(dbImage, 0, 0, null);
	}
}

}

package GameState;



import java.awt.*;
import java.awt.event.*;

import java.util.Random;
import java.awt.image.*;

import Canvas.CanvasGame;
import Canvas.CanvasStart;
import Canvas.GCanvas;



public class GamePanel extends Canvas implements Runnable
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public static final int PWIDTH = 800;
public static final int PHEIGHT = 600;
private Thread animator;
private boolean running = false;

public static int FPS;
int SFPS;
int fpscount;

public static Random rnd = new Random();

BufferedImage imagemcharsets;

boolean LEFT, RIGHT,UP,DOWN;

public static int mousex,mousey; 


public static GamePanel instance;



private BufferedImage dbImage;
private Graphics2D dbg;


private static GCanvas CanvasAtivo = null;

public static int score=0;

public static int scoreUltimaJogada;

long tempoinicial = 0;
long tempototal = 0;

private static CanvasGame canvasGame;
private static CanvasStart canvasInicio;

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
				if(getCanvasAtivo()!=null){
					getCanvasAtivo().keyPressed(e);
				}
			}
		@Override
			public void keyReleased(KeyEvent e ) {
				if(getCanvasAtivo()!=null){
					getCanvasAtivo().keyReleased(e);
				}
			}
	});
addMouseMotionListener(new MouseMotionListener() {
	
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

		if(getCanvasAtivo()!=null){
			getCanvasAtivo().mouseMoved(e);
		}
		
	}

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(getCanvasAtivo()!=null){
			getCanvasAtivo().mouseDragged(e);
		}
	}
});
	
addMouseListener(new MouseListener() {

		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

			if(getCanvasAtivo()!=null){
				getCanvasAtivo().mouseReleased(e);
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if(getCanvasAtivo()!=null){
				getCanvasAtivo().mousePressed(e);
			}
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			if(getCanvasAtivo()!=null){
				getCanvasAtivo().mouseExited(e);
			}	
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			if(getCanvasAtivo()!=null){
				getCanvasAtivo().mouseEntered(e);
			}
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(getCanvasAtivo()!=null){
				getCanvasAtivo().mouseClicked(e);
			}
		}
	
	});

	
	setCanvasInicio(new CanvasStart());
	setCanvasGame(new CanvasGame());
	setCanvasAtivo(getCanvasInicio());
	
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
	if(getCanvasAtivo()!=null){
		getCanvasAtivo().SimulaSe(DiffTime);
	}	
}

private void gameRender(Graphics2D dbg)
// draw the current frame to an image buffer
{
	if(getCanvasAtivo()!=null){
		getCanvasAtivo().DesenhaSe(dbg);
	}	
	
	
	
	
}


public void paintComponent(Graphics g)
{

	if (dbImage != null){
		g.drawImage(dbImage, 0, 0, null);
	}
}

public static void setCanvasAtivo(GCanvas canvasAtivo) {
	CanvasAtivo = canvasAtivo;
}

public static GCanvas getCanvasAtivo() {
	return CanvasAtivo;
}

public void setDbg(Graphics2D dbg) {
	this.dbg = dbg;
}

public Graphics2D getDbg() {
	return dbg;
}

public static void setCanvasGame(CanvasGame canvasGame) {
	GamePanel.canvasGame = canvasGame;
}

public static CanvasGame getCanvasGame() {
	return canvasGame;
}

public static void setCanvasInicio(CanvasStart canvasInicio) {
	GamePanel.canvasInicio = canvasInicio;
}

public static CanvasStart getCanvasInicio() {
	return canvasInicio;
}

}

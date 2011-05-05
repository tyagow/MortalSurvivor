import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;




public class CanvasGame extends GCanvas {

	public static CanvasGame instance = null;
	
	static BufferedImage imagemcharsets;
	static int torre;
	static boolean click;
	public static int Health = 20;
	
	private static boolean endGame=false;
	public static Mira miraAtiva;

	
	private static double mousex;
	private static double mousey;
	BufferedImage fundo;

	public static Base base;
	
	public static ArrayList<Projetil> projeteis = new ArrayList<Projetil>();
	public static ArrayList<Objeto> objetos = new ArrayList<Objeto>();
	public static ArrayList<Torre> torres = new ArrayList<Torre>();
	
	public static GerenciadorTorre gerenciadorTorre;
	public static GerenciadorEfeitos gerenciadorEfeitos;
	public static GerenciadorRespawn gerenciadorRespawn;
	public static GerenciadorHud gerenciadorHud;
	public static GerenciadorDeRaids gerenciadorDeRaids;
	private static  GerenciadorXP gerenciadorXP;


	public static Minimap minimap;
	static double timer = 0;
	
	public static Heroi heroi;

	public int YTela=0;
	public int XTela=0;


	public static GerenciadorObstaculos gerenciadorObstaculos;
	
	public static TileMap tela;
	public static int altura;
	public static int largura;

	private static Mira miraJogo;

	public static boolean testeGradeColisao=false;

	protected static boolean ContiuaJogo;

	private static CursorMenuTorre miraMenu;
	
	static Imagem loadImagem;

	public static boolean miraDoJogoSelecionada;
	

	
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
		gerenciadorXP=new GerenciadorXP();
		miraJogo= new MiraRedonda();
		miraMenu=new CursorMenuTorre();
		minimap=new Minimap();

	}





	private void recarregaFase() {
		
		
		gerenciadorTorre.reset();

		gerenciadorObstaculos.reset();

		gerenciadorDeRaids.reset();
		gerenciadorHud.reset();
		gerenciadorEfeitos.reset();
		gerenciadorXP.reset();
		
		base.life=Constantes.BASE_LIFE_1;
		heroi=new Heroi(GamePanel.PWIDTH/2, GamePanel.PHEIGHT/2,Imagem.heroiUm);
		
	
		ContiuaJogo=true;
	}





	@Override
	void DesenhaSe(Graphics2D dbg) {
		// TODO Auto-generated method stub
		dbg.setFont(Constantes.FonteNormal);
		dbg.setColor(Color.white);
		dbg.fillRect(0,0,GamePanel.PWIDTH, GamePanel.PHEIGHT);

		dbg.setColor(Color.black);
		dbg.drawString(""+GamePanel.FPS, 10, 10);
		

		
		for(int i = 0; i < projeteis.size();i++){
			
			Projetil proj = (Projetil) projeteis.get(i);
			proj.DesenhaSe(dbg, tela.XTela, tela.YTela);
			
		}		

		for(int i = 0; i < objetos.size();i++){
			
			Objeto proj = (Objeto)objetos.get(i);
			proj.DesenhaSe(dbg, tela.XTela, tela.YTela);
			
		}
			
		
		gerenciadorEfeitos.DesenhaSe(dbg, tela.XTela, tela.YTela);
		base.DesenhaSe(dbg, tela.XTela, tela.YTela);


		minimap.DesenhaSe(dbg, tela.XTela, tela.YTela);
		
		
		
		gerenciadorObstaculos.DesenhaSe(dbg, tela.XTela, tela.YTela);
		
		gerenciadorDeRaids.DesenhaSe(dbg, tela.XTela, tela.YTela);
		gerenciadorTorre.DesenhaSe(dbg,  tela.XTela,  tela.YTela);
		heroi.DesenhaSe(dbg, tela.XTela, tela.YTela);
		if (getMiraAtiva()!=null)
			getMiraAtiva().DesenhaSe(dbg, tela.XTela, tela.YTela);
		gerenciadorRespawn.DesenhaSe(dbg, tela.XTela, tela.YTela);

		gerenciadorHud.DesenhaSe(dbg, tela.XTela, tela.YTela);

		gerenciadorXP.DesenhaSe(dbg, tela.XTela, tela.YTela);


	}
	
	

	
	void SimulaSe(long DiffTime) {
		
		verificaFimDoJogo();
		trataMiraDoJogo();
		getMiraAtiva().SimulaSe((int)DiffTime);
		
		if(!GerenciadorRespawn.isRespawn()){
			tela.Posiciona((int)(heroi.X-(GamePanel.PWIDTH/2)), (int)heroi.Y-(GamePanel.PHEIGHT/2));
		
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
	
	private void verificaFimDoJogo() {
		
		
		if (isEndGame() && GerenciadorDeRaids.acabouRaids()||base.getLife()<=0) {
		
			recarregaFase();
			GamePanel.setCanvasAtivo(new CanvasStart());
		}
		
	}





	private void trataMiraDoJogo() {
		// TODO Auto-generated method stub
		if (!GerenciadorTorre.isHitMiraMenu()&&!GerenciadorTorre.hitMiraSelecionador) {
			setMiraAtiva(miraJogo);
			
		}else {
			setMiraAtiva(miraMenu);
			
		}
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
			GerenciadorTorre.adicionaTorre((int)getMousex()+tela.XTela,(int)getMousey()+tela.YTela);
		}	
		if(keyCode == KeyEvent.VK_4){
			GamePanel.setCanvasAtivo(new CanvasGame());
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
		gerenciadorTorre.mouseMoved(e);

		XTela=tela.XTela;
		YTela=tela.YTela;
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
		
		gerenciadorTorre.mouseReleased(e);

	}

	@Override
	void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int button = e.getButton();
		gerenciadorTorre.mousePressed(e);
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
		setMousex(e.getX());
		setMousey(e.getY());
	}

	@Override
	void mouseClicked(MouseEvent e) {
		gerenciadorTorre.mouseClicked(e);
	}


	public static void setEndGame(boolean _endGame) {
		CanvasGame.endGame = _endGame;
	}

	public static boolean isEndGame() {
		return endGame;
	}

	public static void setMiraMenu() {
		// TODO Auto-generated method stub
		if (miraDoJogoSelecionada) {
			getMiraAtiva().setPressed(false);
			setMiraAtiva(miraMenu);
			miraDoJogoSelecionada=false;
		}
	}

	public static void setMiraJogo() {
		// TODO Auto-generated method stub
		if (!miraDoJogoSelecionada) {
			getMiraAtiva().setPressed(false);
			setMiraAtiva(miraJogo);
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

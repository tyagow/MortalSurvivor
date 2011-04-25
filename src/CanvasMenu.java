import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


public class CanvasMenu extends GCanvas{
	
	static BufferedImage imagemlogo;
	
	private Font fonteLogo, fonteMenu, fonteAutores;
	GerenciadorDeSom loader;
	boolean instrucao = false;
	BufferedImage img;
	Imagem imgloader;
	public CanvasMenu() {
		// TODO Auto-generated constructor stub
		
		
		//Constantes.logo=
		
		loader = new GerenciadorDeSom();
		
		GerenciadorDeSom.fundo.start();
		
		imgloader = new Imagem();
		fonteLogo = new Font("Courier", Font.BOLD, 60);
		
		fonteMenu = new Font("Courier", Font.BOLD, 30);
		
		fonteAutores = new Font("Courier", Font.BOLD, 14);

	}
	
	@Override
	void DesenhaSe(Graphics2D dbg) {
		// TODO Auto-generated method stub
		
		if(!instrucao){
			dbg.setFont(fonteLogo);
			
			dbg.setColor(Color.black);
			dbg.fillRect(0, 0, GamePanel.PWIDTH, GamePanel.PHEIGHT);
			
			dbg.drawImage(Imagem.logo, 0, 0,Imagem.logo.getWidth(),Imagem.logo.getHeight(),null);
//			
			dbg.setColor(Color.yellow);
//			dbg.drawString("TD", 525, 75);
//			
			dbg.setFont(fonteAutores);
//					
			dbg.drawString("<Enter> Start/Continue", 380, 50);
			dbg.drawString("<W> Frente", 410, 100);
			dbg.drawString("<S> Traz", 410, 130);
			dbg.drawString("<A> Esquerda", 410, 160);
			dbg.drawString("<D> Direita", 410, 190);
			
			dbg.drawString("<1> Faca", 410, 220);
			dbg.drawString("<2> Secundaria", 410, 250);
			dbg.drawString("<3> Cria Torre na Mira", 410, 280);
			dbg.drawString("<Mouse2> Arma Primaria / Edit Torre", 410, 350);
			dbg.drawString("<Mouse1> Atira", 410, 380);

			
			
			

//			
//			dbg.setColor(Color.white);
//			
//			dbg.setFont(fonteAutores);
//			
//			dbg.drawString("Autores: Paulo Douglas Tefili Filho", 350, 430);
//			dbg.drawString("Tiago Lima Almeida", 422, 450);

	
		} else{
			dbg.setFont(fonteLogo);
			
			dbg.setColor(Color.black);
			dbg.fillRect(0, 0, GamePanel.PWIDTH, GamePanel.PHEIGHT);

			
		}

	}

	@Override
	void SimulaSe(long diftime) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("deprecation")
	@Override
	void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_ENTER && !instrucao){
			GamePanel.CanvasAtivo = CanvasGame.instance;

		}else if(keyCode == KeyEvent.VK_ESCAPE && !instrucao){
			System.exit(0);
		}
		
		if(keyCode == KeyEvent.VK_F1 && !instrucao){
			instrucao = true;
		}
		
		if(keyCode == KeyEvent.VK_ESCAPE && instrucao){
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
		
	}

	@Override
	void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

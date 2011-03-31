import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


public class CanvasMenu extends GCanvas{
	
	static BufferedImage imagemlogo;
	
	private Font fonteLogo, fonteMenu, fonteAutores;
	
	boolean instrucao = false;


	public CanvasMenu() {
		// TODO Auto-generated constructor stub
//		imagemlogo = Constantes.LoadImage("logo.png");
		
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
			
//			dbg.drawImage(imagemlogo, 110, 0,imagemlogo.getWidth(),imagemlogo.getHeight(),null);
			
			dbg.setColor(Color.yellow);
			dbg.drawString("TD", 525, 75);
			
			dbg.setFont(fonteMenu);
					
			dbg.drawString("<Enter> Start/Continue", 10, 200);
			dbg.drawString("<F1> Instruções", 10, 300);
			
			dbg.setColor(Color.white);
			
			dbg.setFont(fonteAutores);
			
			dbg.drawString("Autores: Paulo Douglas Tefili Filho", 350, 430);
			dbg.drawString("Tiago Lima Almeida", 422, 450);

	
		} else{
			dbg.setFont(fonteLogo);
			
			dbg.setColor(Color.black);
			dbg.fillRect(0, 0, GamePanel.PWIDTH, GamePanel.PHEIGHT);
			
			dbg.setColor(Color.yellow);
			dbg.drawString("INSTRUÇÕES", 125, 50);
			
			dbg.setFont(fonteAutores);
			
			dbg.setColor(Color.white);
			dbg.drawString("Para criar torres, segure SHIFT e clique na torre (Pac-Man) desejada.", 40, 100);
			dbg.drawString("As torres só poderão ser criadas fora da estrada onde os inimigos caminham.", 30, 130);
			dbg.drawString("As torres, quando selecionadas, são passivas de upgrades em certos atributos.", 20, 160);

			dbg.setColor(Color.yellow);
			dbg.drawString("Pac-Mans amarelos possuem força média.", 40, 190);
			dbg.drawString("Fantasmas amarelos possuem resistência média e velocidade média.", 40, 220);

			dbg.setColor(Color.red);
			dbg.drawString("Pac-Mans vermelhos possuem força alta.", 40, 250);
			dbg.drawString("Fantasmas vermelhos possuem resistência alta e velocidade baixa.", 40, 280);

			dbg.setColor(Color.blue);
			dbg.drawString("Pac-Mans azuis não possuem força, mas diminuem a velocidade de seus alvos.", 40, 310);
			dbg.drawString("Fantasmas azuis possuem resistência baixa e velocidade alta.", 40, 340);
			
			dbg.setColor(Color.white);
			dbg.drawString("As cartas são itens com efeito temporário em torres/inimigos.", 40, 370);
			dbg.drawString("Efeitos: diminuir velocidade de inimigos; aumentar firerate e firepower.", 40, 400);
			
		}

	}

	@Override
	void SimulaSe(long diftime) {
		// TODO Auto-generated method stub
		
	}

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

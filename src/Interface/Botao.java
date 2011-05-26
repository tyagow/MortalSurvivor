package Interface;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import AbstractClasses.Objeto;
import Constantes.Constantes;
import GameState.GamePanel;


public class Botao extends Objeto {

	
	
	public boolean ativo;
	public  boolean selecionado;
	public  String name;
	public  boolean isOval = true;
	public  boolean oldIsMousePressed;
	BufferedImage imagem=null;
	Font font;
	private int timerAtivo;
	private boolean oldPressed;
	public int mousex;
	public int mousey;
	public Color cor;
	public Color corText;
	
	public Botao(BufferedImage img,String _name, int _x,int _y,int _sizeX,int _fontSize, boolean _isOval) {
		
		imagem = img;
		isOval=_isOval;
		setVivo(true);
		ativo=false;
		selecionado=false;
		sizeX=(_sizeX);

		font =  new Font("SansSerif", Font.BOLD, (int) _fontSize);
		
		if (isOval) 
			sizeY=(_sizeX/2);
		else 
			sizeY=(_sizeX/3);

		name=_name;
		X=_x;
		Y=_y;
		
		
	}
	
	
	@Override
	public void SimulaSe(int DiffTime) {
	
		
		if (ativo) {
			
			timerAtivo +=DiffTime;
			
			if (timerAtivo > 50) {
				ativo=false;
				timerAtivo=0;
			}
		}
		
		caluculaIA(DiffTime);
	}

	
	
	public void caluculaIA(int DiffTime) {
		// TODO Auto-generated method stub
		
	}


	private void trataClickBotao() {
//		if(!oldIsMousePressed)
//			ativaBotao();	
//
	
	}




	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		Font temp = dbg.getFont();
		dbg.setFont(font);
		if (imagem == null) {
			if (isOval) { // desenha botao com imagem
				
				dbg.setColor(Color.red);
				dbg.drawOval((int)X-XMundo,(int)Y-YMundo, getSizeX(), getSizeY());
				dbg.setColor(new Color(50,50,50,200));
				dbg.fillOval((int)X+1-XMundo,(int)Y-YMundo+1, getSizeX()-2, getSizeY()-2);
				if (selecionado) {
					dbg.setColor(Color.gray);
					dbg.setColor(new Color(50,50,50,200));
					dbg.fillOval((int)X+1-XMundo,(int)Y-YMundo+1, getSizeX()-2, getSizeY()-2);
					dbg.setColor(Color.red);
				}else {
					
					dbg.setColor(Color.white);
				}
		
		
				dbg.drawString(name,(int) X+getSizeX()/2-XMundo-name.length()/2*(dbg.getFont().getSize()),(int) Y+getSizeY()/2+(dbg.getFont().getSize())-YMundo);

			}
			else { // desenha botao com imagem
				
				dbg.setColor(Color.WHITE);
//				if (selecionado)
//					dbg.fillRect((int)X-XMundo-getSizeX(),(int)Y-YMundo, GamePanel.PWIDTH, getSizeY());
//				dbg.drawRect((int)X-XMundo,(int)Y-YMundo, sizeX,sizeY);
				if (selecionado)
					dbg.setColor(new Color(250,250,250,200));	
//					dbg.setColor(Color.white);
				else
					dbg.setColor(new Color(50,50,50,200));
				
				if (cor !=null)
					dbg.setColor(cor);
				
				dbg.fillRect((int)X+1-XMundo,(int)Y-YMundo+1, getSizeX()-2, getSizeY()-2);
				
		
				if (selecionado) {
					dbg.setColor(Color.gray);

				dbg.setColor(Color.red );
				}else {
					
					dbg.setColor(Color.white);
				}
		
				if (corText!=null)
					dbg.setColor(corText);
				
				dbg.drawString(name,(int) X+getSizeX()/2-2-XMundo-name.length()/2*((dbg.getFont().getSize()*2)/3),(int) Y+getSizeY()/2+(dbg.getFont().getSize()/2)-YMundo);

			}
		} else{
			dbg.drawImage(imagem,(int) X,(int) Y,(int) X+30, (int) Y+30, 0, 0, imagem.getWidth(), imagem.getHeight(), null);
		}			//dbg.drawImage(imagem, -sizeX/2,-sizeX/2,sizeX/2,sizeY/2,sizeX*frameX,sizeY*frameY,sizeX*frameX+sizeX,sizeY*frameY+sizeY,null);

		dbg.setFont(temp);
	}
public void mouseClicked(MouseEvent e) {

	if (selecionado)
		ativo=true;
}
	
	public void mousePressed(MouseEvent e) {
//		if (Constantes.colideQuadrado((int)X,(int)Y,getSizeX(),getSizeY(), (int)e.X,(int)e.Y ,2,2 )) {
//
//			if (!oldPressed)
//				ativo=true;
//	
//		}
	}
	public void mouseReleased(MouseEvent e) {
		
		oldPressed=false;
		
	
	
	}


	public void mouseMoved(MouseEvent e) {

		if (Constantes.colideQuadrado((int)X,(int)Y,getSizeX(),getSizeY(),e.getX()+Constantes.XTela,e.getY()+Constantes.YTela,2,2 )) {
			selecionado=true;
			
		}
		else {
			selecionado=false;
		}
	}
	
	
}

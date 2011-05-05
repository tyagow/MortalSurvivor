import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


public class BotaoMenu extends Objeto {

	
	
	private boolean ativo;
	private boolean selecionado;
	private String name;
	private boolean isOval = true;
	private boolean oldIsMousePressed;
	BufferedImage imagem=null;
	Font font;
	public BotaoMenu(BufferedImage img,String _name, int _x,int _y,int _sizeX,int _fontSize, boolean _isOval) {
		
		imagem = img;
		isOval=_isOval;
		setVivo(true);
		setAtivo(false);
		selecionado=false;
		setSizeX(_sizeX);

		font =  new Font("SansSerif", Font.BOLD, (int) _fontSize);
		
		if (_isOval) 
			setSizeY(_sizeX/2);
		else 
			setSizeY(_sizeX/3);

		setName(_name);
		X=_x;
		setY(_y);
		
		
	}
	
	
	@Override
	public void SimulaSe(int DiffTime) {
	
				if (Constantes.colideQuadrado((int)getX(),(int)getY(),getSizeX(),getSizeY(), (int)CanvasStart.getMiraAtiva().getXMundo(),(int)CanvasStart.getMiraAtiva().getYMundo() ,2,2 )) {
					if (!isAtivo()) {
						if (CanvasStart.getMiraAtiva().isPressed()) {
			
							trataClickBotao();
						}
						selecionado=true;
					}
				}else {
					selecionado = false;
				}

		oldIsMousePressed = CanvasStart.getMiraAtiva().isPressed();
	}

	
	
	private void trataClickBotao() {
		if(!oldIsMousePressed)
			ativaBotao();	

	
	}




	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		Font temp = dbg.getFont();
		dbg.setFont(font);
		if (imagem == null) {
			if (isOval) { // desenha botao com imagem
				
				dbg.setColor(Color.red);
				dbg.drawOval((int)getX()-XMundo,(int)getY()-YMundo, getSizeX(), getSizeY());
				dbg.setColor(new Color(50,50,50,200));
				dbg.fillOval((int)getX()+1-XMundo,(int)getY()-YMundo+1, getSizeX()-2, getSizeY()-2);
				if (selecionado) {
					dbg.setColor(Color.gray);
					dbg.setColor(new Color(50,50,50,200));
					dbg.fillOval((int)getX()+1-XMundo,(int)getY()-YMundo+1, getSizeX()-2, getSizeY()-2);
					dbg.setColor(Color.red);
				}else {
					
					dbg.setColor(Color.white);
				}
		
		
				dbg.drawString(name,(int) getX()+getSizeX()/2-XMundo-name.length()/2*((dbg.getFont().getSize()*2)/3),(int) getY()+getSizeY()/2+(dbg.getFont().getSize()/2)-YMundo);

			}
			else { // desenha botao com imagem
				
				dbg.setColor(Color.WHITE);
				
				dbg.drawRect((int)getX()-XMundo,(int)getY()-YMundo, getSizeX(), getSizeY());
				
				dbg.setColor(new Color(50,50,50,200));
				
				dbg.fillRect((int)getX()+1-XMundo,(int)getY()-YMundo+1, getSizeX()-2, getSizeY()-2);
				
		
				if (selecionado) {
					dbg.setColor(Color.gray);

					dbg.setColor(Color.red);
				}else {
					
					dbg.setColor(Color.white);
				}
		
		
				dbg.drawString(name,(int) getX()+getSizeX()/2-XMundo-name.length()/2*((dbg.getFont().getSize()*2)/3),(int) getY()+getSizeY()/2+(dbg.getFont().getSize()/2)-YMundo);


			}
		}
		dbg.setFont(temp);
	}
	private void ativaBotao() {
		setAtivo(true);
	}

	
	public void desativaBotao() {
		setAtivo(false);
		
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}


	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}


	public boolean isAtivo() {
		return ativo;
	}


	public void mouseClicked(MouseEvent e) {
		
		
		ativo=true;
	
	
	}


	public void mouseMoved(MouseEvent e) {
		
	}
	
}

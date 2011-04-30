import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Botao extends Objeto {

	
	
	private boolean ativo;
	private boolean selecionado;
	private boolean click;
	private String name;
	private boolean isOval = true;
	private boolean oldIsMousePressed;
	BufferedImage imagem=null;
	Font font;
	public Botao(BufferedImage img,String _name, int _x,int _y,int _sizeX,int _fontSize, boolean _isOval) {
		
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

		
		click=false;
		setName(_name);
		setX(_x);
		setY(_y);
		
		
	}
	
	
	@Override
	public void SimulaSe(int DiffTime) {
		//System.out.println("botao ativo?"+isAtivo());
		// TODO Auto-generated method stub
			int raioMedio=(getSizeX()/2+getSizeY()/2)/2;
				GamePanel.getCanvasAtivo();
				//System.out.println(GamePanel.getCanvasAtivo().getMiraAtiva().isPressed());

				if (Constantes.colideQuadrado((int)getX(),(int)getY(),getSizeX(),getSizeY(), (int)GCanvas.getMiraAtiva().getXMundo(),(int)GCanvas.getMiraAtiva().getYMundo() ,2,2 )) {
					if (!isAtivo()) {
						if (GCanvas.getMiraAtiva().isPressed()) {
			
							trataClickBotao();
						}
						selecionado=true;
					}
				}else {
					selecionado = false;
				}

		oldIsMousePressed = GCanvas.getMiraAtiva().isPressed();
	}

	
	
	private void trataClickBotao() {
		// TODO Auto-generated method stub
		if(!oldIsMousePressed)
			ativaBotao();	

	
	}




	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		Font temp = dbg.getFont();
		dbg.setFont(font);
		if (imagem == null) {
//			if (isOval) {
//				if (selecionado)
//					dbg.fillOval((int)getX()-XMundo+1-getSizeX()/2,(int)getY()-YMundo+1-getSizeY()/2, getSizeX()-2, getSizeY()-2);
//		
//				
//				dbg.drawOval((int)getX()-XMundo-getSizeX()/2,(int)getY()-YMundo-getSizeY()/2, getSizeX(), getSizeY());
//				
//				dbg.drawString(name,(int) getX()-XMundo-dbg.getFont().getSize()/2,(int) getY()-YMundo);
//				
//			}
			if (isOval) { // desenha botao com imagem
				
				dbg.setColor(Color.red);
				dbg.drawOval((int)getX()-XMundo,(int)getY()-YMundo, getSizeX(), getSizeY());
				dbg.setColor(Color.white);
				if (selecionado) {
					dbg.setColor(Color.gray);

					//dbg.fillRect((int)getX()-XMundo+2-getSizeX()/2,(int)getY()-YMundo+2-getSizeY()/2, getSizeX()-3, getSizeY()-3);
					dbg.setColor(Color.red);
				}
		
		
				dbg.drawString(name,(int) getX()+getSizeX()/2-XMundo-name.length()/2*((dbg.getFont().getSize()*2)/3),(int) getY()+getSizeY()/2+dbg.getFont().getSize()/2-YMundo);

//				dbg.drawString(name,(int) getX()+getSizeX()/2-XMundo-name.length()/4*dbg.getFont().getSize(),(int) getY()+getSizeY()/2+dbg.getFont().getSize()/2-YMundo);
			}
			else { // desenha botao com imagem
				
				dbg.setColor(Color.yellow);
				dbg.drawRect((int)getX()-XMundo,(int)getY()-YMundo, getSizeX(), getSizeY());
				dbg.setColor(Color.white);
				if (selecionado) {
					dbg.setColor(Color.gray);

					//dbg.fillRect((int)getX()-XMundo+2-getSizeX()/2,(int)getY()-YMundo+2-getSizeY()/2, getSizeX()-3, getSizeY()-3);
					dbg.setColor(Color.red);
				}
		
		
				//dbg.drawOval((int) getX()+getSizeX()/2-XMundo-name.length()/2*dbg.getFont().getSize()-2, (int) getY()+dbg.getFont().getSize()-YMundo-2, 4, 4);

				dbg.drawString(name,(int) getX()+getSizeX()/2-XMundo-name.length()/2*((dbg.getFont().getSize()*2)/3),(int) getY()+getSizeY()/2+dbg.getFont().getSize()/2-YMundo);

			}
		}
		dbg.setFont(temp);
	}
	private void ativaBotao() {
		// TODO Auto-generated method stub
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
	
}

import java.awt.Graphics2D;


public class Botao extends Objeto {

	
	
	private boolean ativo;
	private boolean selecionado;
	private boolean click;
	private String name;


	public Botao(/*BufferedImage img,*/String name, int _x,int _y) {
		
		setVivo(true);
		setAtivo(false);
		selecionado=false;
		setSizeX(20);
		setSizeY(10);
		click=false;
		setName(name);
		setX(_x);
		setY(_y);
		
		
	}
	
	
	@Override
	public void SimulaSe(int DiffTime) {
		System.out.println("botao ativo?"+isAtivo());
		// TODO Auto-generated method stub
			int raioMedio=(getSizeX()/2+getSizeY()/2)/2;
			if (isAtivo()) 
				setAtivo(false);
			
				if (Constantes.colidecircular(getX(), getY(), raioMedio, CanvasGame.getMiraAtiva().getXMundo(),CanvasGame.getMiraAtiva().getYMundo() ,3 )) {
					if (!isAtivo()) {
						if (CanvasGame.getMiraAtiva().isPressed()) {
			
							trataClickBotao();
						}
						selecionado=true;
					}
				}else {
					selecionado = false;
				}

		
	}

	
	
	private void trataClickBotao() {
		// TODO Auto-generated method stub
	   //  if (CanvasGame.getMiraAtiva().isReleased()) {
			ativaBotao();	
	     //}
	
	}




	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		if (selecionado)
			dbg.fillOval((int)getX()-XMundo+1-getSizeX()/2,(int)getY()-YMundo+1-getSizeY()/2, getSizeX()-2, getSizeY()-2);

		
		dbg.drawOval((int)getX()-XMundo-getSizeX()/2,(int)getY()-YMundo-getSizeY()/2, getSizeX(), getSizeY());

		
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

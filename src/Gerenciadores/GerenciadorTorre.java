package Gerenciadores;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import AbstractClasses.Objeto;
import AbstractClasses.ObjetoImage;
import Armas.Arma;
import Armas.ArmaDoisTorre;
import Armas.ArmaTresTorre;
import Armas.ArmaUmTorre;
import Canvas.CanvasGame;
import Constantes.Constantes;
import Data.Imagem;
import Interface.FrameTorre;
import Torre.SelecionadorDeTorre;
import Torre.Torre;


public class GerenciadorTorre extends Objeto {
	
	
	public static SelecionadorDeTorre selecionadorDeTorre;
	public static ArrayList<Torre> torres = new ArrayList<Torre>();
	
	public static Arma torreTemp;
	
	
	
	
	private static int rangeMouse=20;
	private static long tempoUltimaTorre=0;
	private Color corGridTorre;
	
	public GerenciadorTorre() {
		 selecionadorDeTorre = new SelecionadorDeTorre();
		 torres.clear();
	}


	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		
//			Iterator<Torre> it = torres.iterator();
//			while(it.hasNext()){
//				Torre torre = it.next();
//				torre.SimulaSe((int)DiffTime);
//				if(torre.isVivo()==false){
//					it.remove();
//					}
//			}
		
		if(Constantes.EVENT_contruirTorre) 	{
			corGridTorre = Color.cyan;
			
			for(int i = 0; i < 4; i++){
							
					if (SelecionadorDeTorre.slotsTorre[i].ativo)  {
						
						torreTemp=SelecionadorDeTorre.slotsTorre[i].armaAtiva;
						torreTemp.X=CanvasGame.miraAtiva.X+Constantes.XTela;
						torreTemp.Y=CanvasGame.miraAtiva.Y+Constantes.YTela;
						System.out.println(Constantes.mouseXTela);
						break;
						
						
					}
					
				
			}
			
			if (!posicaoValida(torreTemp.X,torreTemp.Y,torreTemp.sizeX,torreTemp.sizeY)) {
				
				corGridTorre=Color.red;
				
			}
		}
			
		for(int i = 0; i < torres.size();i++){
			Torre proj = (Torre)torres.get(i);
			proj.SimulaSe((int)DiffTime);
			if(!proj.isVivo()){
				torres.remove(i);
			}
		}
		
		selecionadorDeTorre.SimulaSe(DiffTime);
		
		trataMouse();
	}




	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		for(int i = 0; i < torres.size();i++){
			
			Torre proj = (Torre) torres.get(i);
			proj.DesenhaSe(dbg, CanvasGame.tela.XTela,CanvasGame.tela.YTela);
			
		}
		
		if (Constantes.EVENT_contruirTorre&&torreTemp!=null) {
			
			torreTemp.DesenhaSe(dbg, XMundo, YMundo);
			dbg.setColor(corGridTorre);
			dbg.drawRect((int)((torreTemp.X)/32)*32-XMundo,(int) ((torreTemp.Y-10)/32)*32-YMundo, 32, 32);
			Constantes.miraDoJogo=false;
		}
		
		selecionadorDeTorre.DesenhaSe(dbg, XMundo, YMundo);

	}

	public void click(int mousex, int mousey) {
		// TODO Auto-generated method stub
		
		//System.out.println(mousex);
//
//		System.out.println(mousey);
		
	
		
			
	}

	public static void adicionaTorre(int x, int y,int _sizeX,int _sizeY) {
		// TODO Auto-generated method stub
		
		if (posicaoValida(x,y,_sizeY,_sizeY))
			if (System.currentTimeMillis()-tempoUltimaTorre >Constantes.TEMPO_ENTRE_ADD_TORRES) {
				tempoUltimaTorre=System.currentTimeMillis();
				
				
			//	selecionadorDeTorre.torreAtiva.setX(x);
				//selecionadorDeTorre.torreAtiva.setY(y);
				
		
				for (int i=0;i<4;i++) {
				
					if (selecionadorDeTorre.slotsTorre[i].ativo){
						
						switch (i) {
						case 0: // torre um
							
							torres.add(new Torre(Imagem.TORRE_UM_ANIMESET,new ArmaUmTorre(Imagem.TORRE_UM_ANIMESET, null, null, null),x,y));

								break;	
						case 1: // torre dois
									
							torres.add(new Torre(Imagem.TORRE_DOIS_ANIMESET,new ArmaDoisTorre(Imagem.TORRE_DOIS_ANIMESET, null, null, null),x,y));


						break;
						case 2: // torre tres
							
							torres.add(new Torre(Imagem.TORRE_TRES_ANIMESET,new ArmaTresTorre(Imagem.TORRE_TRES_ANIMESET, null, null, null),x,y));


								break;	
						case 3: // torre um
							
							torres.add(new Torre(Imagem.TORRE_UM_ANIMESET,new ArmaUmTorre(Imagem.TORRE_UM_ANIMESET, null, null, null),x,y));


								break;	

						default:
							break;
						}
						
						
						//Torre aux =  new Torre(selecionadorDeTorre.getSlotsTorre()[i].getAnimeSet(),selecionadorDeTorre.getSlotsTorre()[i].getArmaAtiva(),x,y);
						
						//torres.add(aux);
					
					}
				}

				
			}
				//Imagem.TORRE_DOIS_ANIMESET
	}
	
	
	public void mouseClicked(MouseEvent e) {
		for (int i = 0;i<torres.size();i++) {
			torres.get(i).mouseClicked(e);
		
			
		}
	
		selecionadorDeTorre.mouseClicked(e);

	}
	public void mousePressed(MouseEvent e) {
		for (int i = 0;i<torres.size();i++) {
			torres.get(i).mousePressed(e);
			
		}

		if (e.getButton()==MouseEvent.BUTTON3) {
			Iterator<Torre> it = torres.iterator();
			while(it.hasNext()){
				Torre torre = it.next();
	
				if (Constantes.colidecircular(e.getX()+Constantes.XTela, e.getY()+Constantes.YTela,rangeMouse/2,torre.getX(),torre.getY(),torre.getSizeX()/2)) {
					torre.seleciona();
					break;
				}	
			
			}
		}
	}
	public void mouseReleased(MouseEvent e) {
		for (int i = 0;i<torres.size();i++) {
			torres.get(i).mouseReleased(e);
			
		}
		
		if (Constantes.EVENT_contruirTorre)
			adicionaTorre((int)torreTemp.X,(int) torreTemp.Y,torreTemp.sizeY,torreTemp.sizeY);

		selecionadorDeTorre.mouseReleased(e);
	}


	public void mouseMoved(MouseEvent e) {
		for (int i = 0;i<torres.size();i++) {
			torres.get(i).mouseMoved(e);
			
		}
//		if (Constantes.EVENT_contruirTorre) {
//
//			if (torreTemp!=null){
//				
//				torreTemp.X=e.getX()+Constantes.XTela;
//				torreTemp.Y=e.getY()+Constantes.YTela;
//			}
//				
//		}
		
		selecionadorDeTorre.mouseMoved(e);
	}
		
		
	private static boolean posicaoValida(double x, double y,int _sizeX,int _sizeY) {
		// TODO Auto-generated method stub
		boolean aux=true;
		Iterator<Torre> it = torres.iterator();
		while(it.hasNext()&&aux){
			Torre torre = it.next();
			

			if (Constantes.colidecircular(x, y,rangeMouse/2,torre.X,torre.Y,torre.sizeY)
				||GerenciadorObstaculos.colidiuObstaculo(x,y,(int)_sizeY,(int)_sizeY)
					||Constantes.colidecircular(x, y,rangeMouse/2,CanvasGame.heroi.X,CanvasGame.heroi.Y,CanvasGame.heroi.sizeX)) {
				aux= false;
				}
		
			}

		
	
		

		return aux;

	}
	private static boolean posicaoValida(Objeto o) {
		// TODO Auto-generated method stub
		boolean aux=true;
		Iterator<Torre> it = torres.iterator();
		while(it.hasNext()&&aux){
			Torre torre = it.next();
			

			if (Constantes.colidecircular(o.X, o.Y,rangeMouse/2,torre.X,torre.Y,torre.sizeY)) {
				aux= false;
				}
		
			}
		if (GerenciadorObstaculos.colidiuObstaculo(o.X,o.Y,(int)o.sizeY,(int)o.sizeY)) {
			System.out.println("aa");
			aux=false;
		}
		
		
		
	
		

		return aux;

	}
	
	private void trataMouse() {
		// TODO Auto-generated method stub
		
		if (Constantes.colideQuadrado((int) selecionadorDeTorre.getX(),(int) selecionadorDeTorre.getY(), selecionadorDeTorre.getSizeX(), selecionadorDeTorre.getSizeY(), (int) CanvasGame.miraAtiva.getX(),(int) CanvasGame.miraAtiva.getY(), 1, 1)) {
			Constantes.miraDoJogo = false;
		}

		for(int i = 0; i < torres.size(); i++){
			FrameTorre m = torres.get(i).menuAtivo;
			if(m != null){
				if (Constantes.colideQuadrado((int)m.getX(),(int) m.getY(),(int) m.getSizeX(),(int) m.getSizeY(), (int)CanvasGame.miraAtiva.getXMundo(),(int) CanvasGame.miraAtiva.getYMundo(), 1, 1)) {
					Constantes.miraDoJogo = false;
					break;
				}
			}
		}
	}





	public void reset() {
		torres.clear();
		 selecionadorDeTorre = new SelecionadorDeTorre();
		
	}
}

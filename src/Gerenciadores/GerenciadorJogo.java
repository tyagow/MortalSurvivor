package Gerenciadores;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import shop.FrameShop;

import AbstractClasses.Objeto;
import Base.Base;
import Canvas.CanvasGame;
import Constantes.Constantes;
import Data.Imagem;
import GameState.GamePanel;
import Interface.FrameBase;
import Interface.FramePause;
import Map.MapaData;
import Map.Minimap;
import Personagem.Heroi;

public class GerenciadorJogo extends Objeto  {

	static FramePause framePause;
	static FrameShop frameShop;
	public static FrameBase frameAtivo;
		
	public static float velocidadeJogo;
	
	public static ArrayList<MapaData> mapaData = new ArrayList<MapaData>();
	public static int mapaSelecionado=0;
	
	static int[] respaw; //= new int [mapaData.size()]
	
	public GerenciadorJogo(){
		
		inicializaFrames();
		
		mapaData.add(new MapaData("mapaUm.map", "data/wayFaseUm.csv", "data/obstaculosFaseUm.csv","data/raid1.csv",Imagem.tileSetMapaUm));
		mapaData.add(new MapaData("mapaDois.map", "data/wayFaseDois.csv", "data/obstaculosFaseDois.csv","data/raid2.csv",Imagem.tileSetMapaDois ));

		//carregaMapa(mapaSelecionado);
		respaw = new int [mapaData.size()];	
		
		respaw[0] =3; //mapa um quantidade respaw inimigos
		respaw[1] =3;//mapa dois quantidade respaw inimigos
		
	}
	
	private void inicializaFrames() {
		int _sizeX= GamePanel.PWIDTH/2;
		int _sizeY= GamePanel.PHEIGHT;
		
		int _x= GamePanel.PWIDTH/2 + Constantes.XTela - _sizeX/2;
		int _y= 0;
		
		
		framePause= new FramePause(_x, _y, _sizeX, _sizeY, Color.DARK_GRAY, -1);	
		
		
		 _sizeX= GamePanel.PWIDTH;
		 _sizeY= GamePanel.PHEIGHT;
		
		 _x= 0;
		 _y= 0;
		
		
		 frameShop= new FrameShop(_x, _y, _sizeX, _sizeY, Color.darkGray, -1);	
		
		
		
	}

	public void SimulaSe(int DiffTime, float _velocidadeJogo2) {
		velocidadeJogo=_velocidadeJogo2;
		if (frameAtivo!=null) {
			frameAtivo.SimulaSe(DiffTime);
			
		}
		trataMiraMouse();
		
		
	}

	private void trataMiraMouse() {
		
		if (Constantes.miraDoJogo)
			CanvasGame.setMiraJogo();
		else {
			CanvasGame.setMiraMenu();

		}
		Constantes.miraDoJogo=true;
			
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		if (frameAtivo!=null) {
			frameAtivo.DesenhaSe(dbg, 0, 0);
		}
		
		
	}



	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_ESCAPE){
			
			frameAtivo=framePause;
			CanvasGame.velocidadeJogo = 0;
			//inicializaFramePause();
			
		}
		
//		if(keyCode == KeyEvent.VK_B){
//			
//			frameAtivo=frameShop;
//			CanvasGame.velocidadeJogo = 0;
//			//inicializaFramePause();
//			
//		}
		
		if (frameAtivo!=null) {
			frameAtivo.keyPressed(e);
		}
	}

	
	public void keyReleased(KeyEvent e) {
		
		int keyCode = e.getKeyCode();

		if (frameAtivo!=null) {
			frameAtivo.keyReleased(e);
		}
		if(keyCode == KeyEvent.VK_B){
			if (frameAtivo==(FrameBase)frameShop) {
				frameAtivo=null;
				CanvasGame.velocidadeJogo = 1;
				velocidadeJogo=1;
			}
			else {
				frameAtivo=frameShop;
				CanvasGame.velocidadeJogo = 0;
				velocidadeJogo=0;
			}
				
		}
	}

	public void mouseMoved(MouseEvent e) {
		if (frameAtivo!=null) {
			frameAtivo.mouseMoved(e);
		}
	}

	public void mouseDragged(MouseEvent e) {
		if (frameAtivo!=null) {
			frameAtivo.mouseDragged(e);
		}		
	}

	
	public void mouseReleased(MouseEvent e) {
		if (frameAtivo!=null) {
			frameAtivo.mouseReleased(e);
		}			
	}

	
	public void mousePressed(MouseEvent e) {
		if (frameAtivo!=null) {
			frameAtivo.mousePressed(e);
		}		
	}

	

	
	public void mouseClicked(MouseEvent e) {
		if (frameAtivo!=null) {
			frameAtivo.mouseClicked(e);
		}		
	}

	@Override
	public void SimulaSe(int DiffTime) {
		
	}

	public static void criaBase(int _x, int _y) {
		CanvasGame.base = new Base(_x, _y, Imagem.base);
		CanvasGame.base.life= Constantes.BASE_LIFE_1;
	}
	
	public static void carregaMapa(int indiceMapa){
		
		CanvasGame.tela.AbreMapa(mapaData.get(indiceMapa).fileNameMap,(mapaData.get(indiceMapa).tileSet));
		CanvasGame.largura = CanvasGame.tela.Largura*16;
		CanvasGame.altura = CanvasGame.tela.Altura*16;

		
		CanvasGame.minimap = new Minimap();
		CanvasGame.heroi = new Heroi(GamePanel.PWIDTH,GamePanel.PHEIGHT,Imagem.heroiUm);
		GerenciadorObstaculos.carregaGradeColisao();
		GerenciadorObstaculos.loadObstaculos(mapaData.get(indiceMapa).fileNameObstaculos);
		GerenciadorObstaculos.loadWayPoints(mapaData.get(indiceMapa).fileNameWaypoints);
		GerenciadorObstaculos.recarregaGrade();
		
		
		
		
		Constantes.quantidadeRespawInimigo=respaw[indiceMapa];
		
		GerenciadorDeRaids.loadInimigos(mapaData.get(indiceMapa).fileNameInimigos);
		
		System.out.println(mapaData.get(indiceMapa).fileNameObstaculos);

	}
	

}

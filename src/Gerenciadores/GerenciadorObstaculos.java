package Gerenciadores;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

import AbstractClasses.Objeto;
import Canvas.CanvasGame;
import Constantes.Constantes;
import Data.Imagem;
import Interface.FrameBase;
import Interface.MenuObstaculos;
import Interface.FrameOptions;
import Map.Obstaculo;
import Map.WayPoint;
import Map.WayPoint;


public class GerenciadorObstaculos extends Objeto  {

	public static ArrayList<Obstaculo> obstaculos = new ArrayList<Obstaculo>();
	
	private static int mapa[][];

	private static int largura;

	private static int altura;

	private MenuObstaculos menuObstaculos = new MenuObstaculos(400,400, Imagem.obstaculos.getWidth(), Imagem.obstaculos.getHeight(), Color.darkGray, -1, Imagem.obstaculos);
	
	private static boolean xMaiorQueUm;
	private MenuObstaculos menuAtivo;
	private static boolean yMaiorQueUm;

	private static boolean xIgualUm;
	
	private static boolean yIgualUm;
	public GerenciadorObstaculos() {
		// TODO Auto-generated constructor stub
		largura=CanvasGame.tela.Largura;
		altura=CanvasGame.tela.Altura;
		obstaculos.clear();
		carregaObstaculos();
		carregaGradeColisao();
	
	}


	private void carregaObstaculos() {
		
		addObstaculos(20*16, 10*16, 32, 32, 0, 1);
		addObstaculos(22*16, 10*16, 32, 32, 13, 1);
		addObstaculos(20*16, 12*16, 32, 32, 15, 0);
		

		loadWayPoints();
		
	}


	@Override
	public void SimulaSe(int DiffTime) {
		if (Constantes.menuDeObstaculos) {
			
			menuAtivo=menuObstaculos ;
			
		}else {
			menuAtivo=null;
		}
		
		if (menuAtivo!=null) {
			menuAtivo.SimulaSe(DiffTime);
		
			trataMouse();
		}
		
		
		
		Iterator<Obstaculo> it = getObstaculos().iterator();
		while(it.hasNext()){
			Obstaculo part = it.next();
			part.SimulaSe((int)DiffTime);
			
			if(part.isVivo()==false) {
				it.remove();
				removeObstaculoGrade((int)part.getX(),(int)part.getY());
			}
		}
		
	}
	private void trataMouse() {
		
		if (menuAtivo!=null) {
			Constantes.hitMiraMenu= true;
			CanvasGame.setMiraMenu();
		}
		else {
			CanvasGame.setMiraJogo();
		}		
	}


	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		
		//System.out.println(obstaculos.size());
		if (menuAtivo!=null) {
			menuAtivo.DesenhaSe(dbg, XMundo, YMundo);
		}
		for (int i = 0; i<obstaculos.size();i++) {
			obstaculos.get(i).DesenhaSe(dbg, XMundo, YMundo);
			
			
		}
		for (int i = 0; i<Constantes.wayPoints.size();i++) {
			Constantes.wayPoints.get(i).DesenhaSe(dbg, XMundo, YMundo);
			
			//System.out.println("ola");
		} 
//		for (int i=0;i<largura;i++) {
//		
//			for (int j=0;j<altura;j++) {
//				if (mapa[i][j]==1) {
//					dbg.setColor(Color.red);
//					dbg.drawRect(i*16-XMundo, j*16-YMundo, 16, 16);
//				}else {
//					dbg.setColor(Color.blue);
//					dbg.drawRect(i*16-XMundo, j*16-YMundo, 16, 16);
//				}
//				
//			}
//		}
//		
	
		
		
		
	
	
	}
	private void removeObstaculoGrade(int x, int y) {
		// TODO Auto-generated method stub
		
		mapa[x>>4][y>>4]=0;
		
		
	}

	
	private void carregaGradeColisao() {
		// TODO Auto-generated method stub
	
		setMapa(new int [getLargura()][getAltura()]);
		//System.out.println(CanvasGame.MAPA.Largura);
		resetaGradeColisao();
		
	}

	private static void resetaGradeColisao() {
		// TODO Auto-generated method stub
		for (int i=0;i<getLargura();i++ ) {
			for (int j=0;j<getAltura();j++ ) {
				getMapa()[i][j]=0;
	
			}
		}
	}

	public static void setObstaculos(ArrayList<Obstaculo> obstaculos) {
		GerenciadorObstaculos.obstaculos = obstaculos;
	}
	
	public static void recarregaGrade(){
		
		resetaGradeColisao();
		Iterator<Obstaculo> it = getObstaculos().iterator();
		while(it.hasNext()){
			Obstaculo part = it.next();
			getMapa()[(int)part.getX()][(int)part.getY()]=1;

		}
		
	}
	public static void addObstaculos(int _x,int _y,int _sizeX,int _sizeY,int tileSetColuna, int tileSetLinha) {
		
		obstaculos.add(new Obstaculo(_x, _y, _sizeX, _sizeY, tileSetColuna, tileSetLinha));
	
		
		
//		for (int i =0;i< _sizeX/16;i++) {
//			for (int j =0;j< _sizeY/16;i++) {
//				
//				mapa[_x/16+i][_y/16+j] = 0;
//				
//				
//			}
//			
//		}
//		
//		
		
		
		
//		
//		int x = _x;
//		int y = _y;
//			
//		int sizeX;
//		int sizeY;
////		
////		if (_sizeX%16>0)
////			sizeX= _sizeX>>4+1;
////			else
////				sizeX= _sizeX>>4;
////				
////		if (_sizeY%16>0)
////			sizeY= _sizeY>>4+1;
////		else
////			sizeY= _sizeY>>4;
////						
//
//			
//			sizeX= _sizeX;
//			sizeY= _sizeY;
//
//		if (sizeX>2){
//			x=x-sizeX/2;
//			setxIgualUm(false);
//		}
//		else setxIgualUm(true);
//		
//		if (sizeY>1){
//			y=y-sizeY/2;
//			setyIgualUm(false);
//		}
//		else setyIgualUm(true);
//		
//		int indX;
//		if (_sizeX<33)
//			indX=0;
//		else 
//			indX=1;
//		
//		
//		int indY;
//		if (_sizeY<33)
//			indY=0;
//		else 
//			indY=1;
//		
//		for (int i = 0;i<=sizeY-indY;i++) {
//			for (int j = 0;j<=sizeX-indX;j++) {
//			
//				getObstaculos().add(new Obstaculo(x+j, y+i,0,0));
//				getMapa()[x+j][y+i]=1;
//			}
//		
//			
//		}
//		
				

			
		}
		
		
		
			
		
		
		
//			int d =0;
//			int e=0;
//		
//		Obstaculo obs;
//		for (int i=0;i<sizeX;i++) {
//			for (int j=0;j<sizeY;j++) {
//				if (i==0&&j==0) {
//					obs = new Obstaculo(x, _y); 
//				}else { 
//					if (i%2==0) {
//						d++;
//						obs =new Obstaculo(x+d,y);
//						
//						}
//					else /*(i%2!=0)*/ {
//						e++;
//						obs =new Obstaculo(x-e,y);
//						
//						}
//					obstaculos.add(obs);	
//				}
//				
//				
//				}
//			
//		}
		
		
	public void loadWayPoints() {
		WayPoint aux;

		int wayX = (largura*16)/2-21;
		int wayY = 100;
		int waySizeX = 42;
		int waySizeY = 42;
		 aux = new WayPoint( wayX,wayY,waySizeX,waySizeY );
		 aux.indexNextTarget = 1;

		Constantes.wayPoints.add(aux);
		
		 wayX = largura*16-42-100;
		 wayY = (altura*16)/2-21;
		 waySizeX = 42;
		 waySizeY = 42;
		 aux = new WayPoint( wayX,wayY,waySizeX,waySizeY );
		 aux.indexNextTarget = 2;

			Constantes.wayPoints.add(aux);		
		
		 wayX = (largura*16)/2-21;
		 wayY = (altura*16)-42;
		 waySizeX = 42;
		 waySizeY = 42;
		 aux = new WayPoint( wayX,wayY,waySizeX,waySizeY );
		 aux.indexNextTarget = 3;

			Constantes.wayPoints.add(aux);		
		
			wayX = 0;
			 wayY = (altura*16)/2;
			 waySizeX = 42;
			 waySizeY = 42;
			 aux = new WayPoint( wayX,wayY,waySizeX,waySizeY );
			 aux.indexNextTarget = 0;

				Constantes.wayPoints.add(aux);
				
		
			
			
//			for (int i = 0 ; i <Constantes.Constantes.wayPoints.size();i++) {
//				
//				Constantes.Constantes.wayPoints.get(i).indexNextTarget=i;
//			}
	}


	public static boolean colidiuObstaculo(int mousex, int mousey) {
		// TODO Auto-generated method stub
		if (getMapa()[mousex>>4][mousey>>4]==0) {
			return false;
		}else 
			return true;
		
		
		
	}


	public static ArrayList<Obstaculo> getObstaculos() {
		return obstaculos;
	}


	public static void setAltura(int altura) {
		GerenciadorObstaculos.altura = altura;
	}


	public static int getAltura() {
		return altura;
	}


	public static void setLargura(int largura) {
		GerenciadorObstaculos.largura = largura;
	}


	public static int getLargura() {
		return largura;
	}


	public static void setMapa(int _mapa[][]) {
		mapa = _mapa;
	}


	public static int[][] getMapa() {
		return mapa;
	}


	public static void setxMaiorQueUm(boolean xMaiorQueUm) {
		GerenciadorObstaculos.xMaiorQueUm = xMaiorQueUm;
	}


	public static boolean isxMaiorQueUm() {
		return xMaiorQueUm;
	}


	public static void setyMaiorQueUm(boolean yMaiorQueUm) {
		GerenciadorObstaculos.yMaiorQueUm = yMaiorQueUm;
	}


	public static boolean isyMaiorQueUm() {
		return yMaiorQueUm;
	}


	public static void setxIgualUm(boolean xIgualUm) {
		GerenciadorObstaculos.xIgualUm = xIgualUm;
	}


	public static boolean isxIgualUm() {
		return xIgualUm;
	}


	public static void setyIgualUm(boolean yIgualUm) {
		GerenciadorObstaculos.yIgualUm = yIgualUm;
	}


	public static boolean isyIgualUm() {
		return yIgualUm;
	}


	public void reset() {
//TODO pensar em como resetar pois os obstaculos sao adicionados por fora e se eu limpar a lista vai dar merda para carregar a grade...		
	}


	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if (menuAtivo!=null)
			menuAtivo.mouseMoved(e);
	}


	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (menuAtivo!=null)
			menuAtivo.mouseReleased(e);
	}


	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (menuAtivo!=null)
			menuAtivo.mouseClicked(e);
	}


	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (menuAtivo!=null)
			menuAtivo.mousePressed(e);
	}


	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if (menuAtivo!=null)
			menuAtivo.mouseDragged(e);
	}
	
	public void saveObstaculos(int cod, String nome) throws IOException  
		   {  
		
		FileOutputStream in = new FileOutputStream("obstaculos");
		
		OutputStreamWriter out= new OutputStreamWriter(in);
		
		
		
//		for (int i=0;i<obstaculos.size();i++) {
//		
//			
//			String str = obstaculos.get(i).toSave();
//			out.write(str)
//			out.w
//		}
//		      FileOutputStream out;  
//		        
//		      PrintStream p;  
//		        
//		      try  
//		      {  
//		         out = new FileOutputStream("arq.txt");  
//		        
//		         p = new PrintStream(out);  
//		           
//		         p.write(cod + " " + nome + '\n');  
//		        
//		         p.close();  
//		      }  
//		      catch(Exception e)     
//		      {  
//		         System.err.println(e);  
//		      }     
  }  

	



}

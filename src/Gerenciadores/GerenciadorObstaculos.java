package Gerenciadores;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import AbstractClasses.Objeto;
import Canvas.CanvasGame;
import Constantes.Constantes;
import Data.Imagem;
import GameState.GamePanel;
import Interface.FrameBase;
import Interface.MenuObstaculos;
import Interface.FrameOptions;
import Map.Obstaculo;
import Map.WayPoint;
import Map.WayPoint;


public class GerenciadorObstaculos extends Objeto  {

	public static ArrayList<Obstaculo> obstaculos = new ArrayList<Obstaculo>();
	
	public static int mapa[][];

	
	
	public static int largura;

	public static int altura;

	private MenuObstaculos menuObstaculos = new MenuObstaculos(400,400, Imagem.obstaculos.getWidth(), Imagem.obstaculos.getHeight(), Color.darkGray, -1, Imagem.obstaculos);
	private MenuObstaculos menuAtivo;

	public GerenciadorObstaculos() {
		// TODO Auto-generated constructor stub
		largura=CanvasGame.tela.Largura/2+1;
		altura=CanvasGame.tela.Altura/2+1;
		mapa = new int[largura][altura];
		obstaculos.clear();
		
		carregaGradeColisao();
		carregaObstaculos();
		 recarregaGrade();
		
	}


	private void carregaObstaculos() {
//		

//		
		loadObstaculos("data/obstaculosFaseUm.csv");
		
		loadWayPoints("data/wayFaseUm.csv");
		
	}


	@Override
	public void SimulaSe(int DiffTime) {
		if (Constantes.menuDeObstaculos) {
			
				for (int i = 0; i<Constantes.wayPoints.size();i++) {
					Constantes.wayPoints.get(i).SimulaSe(DiffTime);
				} 
			
			menuAtivo=menuObstaculos ;
			
		}else {
			menuAtivo=null;
		}
		
		if (menuAtivo!=null) {
			menuAtivo.SimulaSe(DiffTime);
		
			trataMouse();
		}
		
		
		
/*		Iterator<Obstaculo> it = obstaculos.iterator();
		while(it.hasNext()){
			Obstaculo part = it.next();
			part.SimulaSe((int)DiffTime);
			
			if(part.isVivo()==false) {
				it.remove();
				removeObstaculoGrade((int)part.getX(),(int)part.getY());
			}
		}	*/	
		
	}
	private void trataMouse() {
		
		if (menuAtivo!=null) {
			Constantes.hitMiraMenu= true;
			Constantes.miraDoJogo=false;
		}
//		else {
////			CanvasGame.setMiraJogo();
//			Constantes.miraDoJogo=true;
//		}		
	}


	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		
		//System.out.println(obstaculos.size());

		for (int i = 0; i<obstaculos.size();i++) {
			obstaculos.get(i).DesenhaSe(dbg, XMundo, YMundo);
			
			
		}

		if(Constantes.menuDeObstaculos){
			for (int i = 0; i<Constantes.wayPoints.size();i++) {
				Constantes.wayPoints.get(i).DesenhaSe(dbg, XMundo, YMundo);
				
				//System.out.println("ola");
			} 
		}
		
		if (menuAtivo!=null) {
			menuAtivo.DesenhaSe(dbg, XMundo, YMundo);
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
	
		
		for (int i=0;i<largura;i++)
			for (int j =0;j<altura;j++)
				if (mapa[i][j]== 1 ) {
					dbg.fillRect(i*32-XMundo, j*32-YMundo, 32, 32);
				}
		
	
	
	}
	private void removeObstaculoGrade(int x, int y) {
		// TODO Auto-generated method stub
		
		mapa[x>>5][y>>5]=0;
		
		
	}

	
	private void carregaGradeColisao() {
		// TODO Auto-generated method stub
	
		mapa=(new int [largura][altura]);
		
		resetaGradeColisao();
		
	}

	private static void resetaGradeColisao() {
		// TODO Auto-generated method stub
		for (int i=0;i<largura;i++ ) {
			for (int j=0;j<altura;j++ ) {
				mapa[i][j]=0;
	
			}
		}
	}

	public static void setObstaculos(ArrayList<Obstaculo> obstaculos) {
		GerenciadorObstaculos.obstaculos = obstaculos;
	}
	
	public static void recarregaGrade(){
		
		resetaGradeColisao();
		Iterator<Obstaculo> it = obstaculos.iterator();
		while(it.hasNext()){
			Obstaculo part = it.next();
			mapa[(int)part.X/32][(int)part.Y/32]=1;

		}
		
	}
	public static void addObstaculos(int _x,int _y,int _sizeX,int _sizeY,int tileSetColuna, int tileSetLinha) {
	
		obstaculos.add(new Obstaculo(_x, _y, _sizeX, _sizeY, tileSetColuna, tileSetLinha));
	}
		
		
	public void loadWayPoints(String filename) {
		
		

		InputStream in = Data.Imagem.class.getResourceAsStream(filename);
		
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(in));
		
		String str = "";
		
	
		WayPoint aux;
		//
		int i =0;

		try {
			while((str = bf.readLine())!=null){
				if(str.charAt(0)!='#'){
					String strs[] = str.split(";");
					
					int codigo = Integer.parseInt(strs[0]); 
					int wayX = Integer.parseInt(strs[1])*16;
					int wayY = Integer.parseInt(strs[2])*16;
					int waySizeX = Integer.parseInt(strs[3])*16;
					int waySizeY = Integer.parseInt(strs[4])*16;
					int _indexNextTarget = Integer.parseInt(strs[5]);
				
					 aux = new WayPoint( wayX,wayY,waySizeX,waySizeY );
					 aux.indexNextTarget = _indexNextTarget;
					 aux.index = i;
					 i++;
					 Constantes.wayPoints.add (aux);
					System.out.println(Integer.parseInt(strs[0])+";"+Integer.parseInt(strs[1])+";"+Integer.parseInt(strs[2])+";"+Integer.parseInt(strs[3])+";"+Integer.parseInt(strs[4])+";"+Integer.parseInt(strs[5])); //					addObstaculos(20*16, 10*16, 32, 32, 0, 1);

				
				}
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());		
}

	
	}
	public void loadObstaculos(String filename){
		int i=0;
		InputStream in = Data.Imagem.class.getResourceAsStream(filename);
		
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(in));
		String str = "";
		
	
	

		try {
			while((str = bf.readLine())!=null){
				if(str.charAt(0)!='#'){
					String strs[] = str.split(";");
					
					int codigo = Integer.parseInt(strs[0]); 
					int _x = Integer.parseInt(strs[1]);
					int _y = Integer.parseInt(strs[2]);
					int _sizeX = Integer.parseInt(strs[3]);
					int _sizeY = Integer.parseInt(strs[4]);
					int tileSetColuna = Integer.parseInt(strs[5]);
					int tileSetLinha = Integer.parseInt(strs[6]);

					if (codigo == -1) {
						
						GerenciadorJogo.criaBase(_x,_y);
					}
					
					addObstaculos(_x, _y, _sizeX, _sizeY, tileSetColuna, tileSetLinha);
					mapa[(int)_x/32][(int)_y/32] = 1;
		

					//System.out.println(i);
					i++;
				
				}
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());		
}
	}


	public static boolean colidiuObstaculo(double _x, double _y,int _sizeX,int _sizeY) {
		// TODO Auto-generated method stub
		
		for (int i=0;i<obstaculos.size();i++) {
			if (Constantes.colideQuadrado(_x, _y, _sizeX, _sizeY,obstaculos.get(i).X, obstaculos.get(i).Y, obstaculos.get(i).sizeX, obstaculos.get(i).sizeY)) {
				return true;
			}
		
		}
		
				return false;
			
		
		
	}
	
	


	public void reset() {
//TODO pensar em como resetar pois os obstaculos sao adicionados por fora e se eu limpar a lista vai dar merda para carregar a grade...		
	}


	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if (menuAtivo!=null)
			menuAtivo.mouseMoved(e);
		
		if (Constantes.menuDeObstaculos) {
			
			for (int i =0;i<Constantes.wayPoints.size();i++) {
				
				Constantes.wayPoints.get(i).mouseMoved(e);
				
			}
			
		}
			
			
			
	}


	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (menuAtivo!=null)
			menuAtivo.mouseReleased(e);
		
		
		if (Constantes.menuDeObstaculos) {
			
			for (int i =0;i<Constantes.wayPoints.size();i++) {
				
				Constantes.wayPoints.get(i).mouseReleased(e);
				
			}
			
		}
	}


	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (menuAtivo!=null)
			menuAtivo.mouseClicked(e);
		
		if (Constantes.menuDeObstaculos) {
			
			for (int i =0;i<Constantes.wayPoints.size();i++) {
				
				Constantes.wayPoints.get(i).mouseClicked(e);
				
			}
			
		}
	}


	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (menuAtivo!=null)
			menuAtivo.mousePressed(e);
		
		if (Constantes.menuDeObstaculos) {
			
			for (int i =0;i<Constantes.wayPoints.size();i++) {
				
				Constantes.wayPoints.get(i).mousePressed(e);
				
			}
			
		}
	}


	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if (menuAtivo!=null)
			menuAtivo.mouseDragged(e);
		
		if (Constantes.menuDeObstaculos) {
			
			for (int i =0;i<Constantes.wayPoints.size();i++) {
				
				Constantes.wayPoints.get(i).mouseDragged(e);
				
			}
			
		}
	}
	

	public static void saveObstaculosInFile() {
		JFileChooser fc = new JFileChooser();
	
	    fc.setCurrentDirectory(new File("tmp.tmp"));//new File(getClass().getResource("palm.png").getFile()));
	
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de Caminhos", "csv");
		
		fc.setFileFilter(filter);
	

		int returnVal = fc.showSaveDialog(GamePanel.instance);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
	   
			File file = fc.getSelectedFile();
		
			System.out.println(" file "+file);
	
			
		    FileOutputStream out = null;
		    try {
					out =  new FileOutputStream(file);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				
			
				DataOutputStream dataout = new DataOutputStream(out);
				try {		
					dataout.writeBytes("#Codigo;posX;posY;sizeX;sizeY;tileSetColuna;tileSetLinha"+""+System.getProperty("line.separator"));
					for (int i=0;i<GerenciadorObstaculos.obstaculos.size();i++) {
						
						
						Obstaculo obs = GerenciadorObstaculos.obstaculos.get(i);
						
						if (obs.X!=CanvasGame.base.X&&obs.Y!=CanvasGame.base.Y) {
						
							if (i<GerenciadorObstaculos.obstaculos.size()-1)
								dataout.writeBytes(i+";"+(int)obs.X+";"+(int)obs.Y+";"+(int)obs.sizeX+";"+(int)obs.sizeY+";"+(int)obs.tileSetColuna+";"+(int)obs.tileSetLinha+""+System.getProperty("line.separator"));
							else 
								dataout.writeBytes(i+";"+(int)obs.X+";"+(int)obs.Y+";"+(int)obs.sizeX+";"+(int)obs.sizeY+";"+(int)obs.tileSetColuna+";"+(int)obs.tileSetLinha);

						}else {
							if (i<GerenciadorObstaculos.obstaculos.size()-1)
								dataout.writeBytes(-1+";"+(int)obs.X+";"+(int)obs.Y+";"+(int)obs.sizeX+";"+(int)obs.sizeY+";"+(int)obs.tileSetColuna+";"+(int)obs.tileSetLinha+""+System.getProperty("line.separator"));
							else 
								dataout.writeBytes(-1+";"+(int)obs.X+";"+(int)obs.Y+";"+(int)obs.sizeX+";"+(int)obs.sizeY+";"+(int)obs.tileSetColuna+";"+(int)obs.tileSetLinha);

						}
						
						
					}
					
					dataout.close();
					out.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
				
			} else {
				System.out.println("Open command cancelled by user.");
			}

	}
	
	
	public static void saveWayPointInFile() {
		JFileChooser fc = new JFileChooser();
	
	    fc.setCurrentDirectory(new File("tmp.tmp"));//new File(getClass().getResource("palm.png").getFile()));
	
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de Caminhos", "csv");
		
		fc.setFileFilter(filter);
	

		int returnVal = fc.showSaveDialog(GamePanel.instance);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
	   
			File file = fc.getSelectedFile();
		
			System.out.println(" file "+file);
		
			
		    FileOutputStream out = null;
		    try {
					out =  new FileOutputStream(file);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				
			
				DataOutputStream dataout = new DataOutputStream(out);
				try {		
					dataout.writeBytes("#Codigo;wayX;wayY;wausizeX;waysizeY;_indexNextTarget"+""+System.getProperty("line.separator"));
					for (int i=0;i<Constantes.wayPoints.size();i++) {
						
						
						WayPoint obs = Constantes.wayPoints.get(i);
						if (i<Constantes.wayPoints.size()-1) {
							dataout.writeBytes(i+";"+(int)obs.X+";"+(int)obs.Y+";"+(int)obs.sizeX+";"+(int)obs.sizeY+";"+(int)obs.indexNextTarget+""+System.getProperty("line.separator"));
						}else {
							dataout.writeBytes(i+";"+(int)obs.X+";"+(int)obs.Y+";"+(int)obs.sizeX+";"+(int)obs.sizeY+";"+(int)obs.indexNextTarget);

						}
					}
					
					dataout.close();
					out.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
				
			} else {
				System.out.println("Open command cancelled by user.");
			}

	}


}

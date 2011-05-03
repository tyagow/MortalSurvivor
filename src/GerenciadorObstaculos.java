import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;


public class GerenciadorObstaculos extends Objeto {

	private static ArrayList<Obstaculo> obstaculos = new ArrayList<Obstaculo>();
	
	private static int mapa[][];

	private static int largura;

	private static int altura;

	private static boolean xMaiorQueUm;

	private static boolean yMaiorQueUm;

	private static boolean xIgualUm;

	private static boolean yIgualUm;
	public GerenciadorObstaculos() {
		// TODO Auto-generated constructor stub
		
		carregaGradeColisao();
	}


	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		//System.out.println(obstaculos.size());
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
	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		
		//System.out.println(obstaculos.size());

		if (CanvasGame.testeGradeColisao)
		for (int i=0;i<getLargura();i++) {
		
			for (int j=0;j<getAltura();j++) {
				if (getMapa()[i][j]==1) {
					dbg.setColor(Color.red);
					dbg.drawRect(i*16-XMundo, j*16-YMundo, 16, 16);
				}else {
					dbg.setColor(Color.blue);
					dbg.drawRect(i*16-XMundo, j*16-YMundo, 16, 16);
				}
				
			}
		}
	}
	private void removeObstaculoGrade(int x, int y) {
		// TODO Auto-generated method stub
		
		getMapa()[x>>4][y>>4]=0;
		
		
	}

	
	private void carregaGradeColisao() {
		// TODO Auto-generated method stub
		setLargura(CanvasGame.MAPA.Largura);
		setAltura(CanvasGame.MAPA.Altura);
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
	public static void addObstaculos(int _x,int _y,int _sizeX,int _sizeY) {
		// TODO Auto-generated method stub
		//System.out.println(obstaculo.getY());
		//System.out.println(_sizeY);

		int x = _x>>4;
		int y = _y>>4;
		
		
		
		int sizeX;
		int sizeY;

		if (_sizeX%16>0)
			sizeX= _sizeX>>4+1;
			else
				sizeX= _sizeX>>4;
				
		if (_sizeY%16>0)
			sizeY= _sizeY>>4+1;
		else
			sizeY= _sizeY>>4;
						
				
		//System.out.println(sizeX+"  opa");


		if (sizeX>2){
			x=x-sizeX/2;
			xIgualUm=false;
		}
		else xIgualUm=true;
		
		if (sizeY>1){
			y=y-sizeY/2;
			yIgualUm=false;
		}
		else yIgualUm=true;
		
		int indX;
		if (_sizeX<33)
			indX=0;
		else 
			indX=1;
		
		
		int indY;
		if (_sizeY<33)
			indY=0;
		else 
			indY=1;
		
		for (int i = 0;i<=sizeY-indY;i++) {
			for (int j = 0;j<=sizeX-indX;j++) {
			
				getObstaculos().add(new Obstaculo(x+j, y+i));
				getMapa()[x+j][y+i]=1;
			}
		
			
		}
		
				

			
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


	public static void setMapa(int mapa[][]) {
		GerenciadorObstaculos.mapa = mapa;
	}


	public static int[][] getMapa() {
		return mapa;
	}



}


public class Nodo {
	int X;
	int Y;
	int Energia;
	int Euristica;
	Nodo pai;
	

	public Nodo(Nodo nodo,int x, int y, int energia, int objX, int objY) {
		// TODO Auto-generated constructor stub
		this.pai = nodo;
		this.X = x;
		this.Y = y;
		int difX=0;
		int difY=0;
		if(objX<x){
			difX= x-objX;					
		}else{
			difX = objX-x;
		}
		if(objY<y){
			difY = y-objY;
		}else{
			difY = objY-Y;
		}
		this.Euristica = (difX+difY);
		this.Energia = energia;

	}	
}

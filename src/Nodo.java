
class Nodo{
	Nodo pai;
	int x;
	int y;
	double g;
	double h;
	
	public Nodo(Nodo pai,int x,int y, double energia) {
		// TODO Auto-generated constructor stub
		this.pai = pai;
		this.x = x;
		this.y = y;
		this.g = energia;
	}
}
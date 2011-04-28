import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Raid {
	
	public boolean ativo = false;
	
	public ArrayList<Inimigo> inimigos = new ArrayList<Inimigo>();
	
	public Raid(String filename){
		
		InputStream in = GamePanel.instance.getClass().getResourceAsStream(filename);
		
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(in));
		
		
		String str = "";
		
	
		ativo=true;
		
		try {
			while((str = bf.readLine())!=null){
				if(str.charAt(0)!='#'){
					String strs[] = str.split(";");
			
					int codigo = Integer.parseInt(strs[0]); 
					int tipo = Integer.parseInt(strs[1]);
					int ix = Integer.parseInt(strs[2]);
					int iy = Integer.parseInt(strs[3]);
					int iv = Integer.parseInt(strs[4]);
					int ilife = Integer.parseInt(strs[5]);
					
					Inimigo inim = new Inimigo(Imagem.inimigoUm);//, CanvasGame.controladordedirecao);
					
//					inim.setX(ix);
//					inim.setY(iy);
//
//					inim.setVel(iv);
//					
//					inim.setLife(ilife);
//
//					inim.setLife(ilife);
//					inim.setTipoAssasino(tipo);
					Inimigo inim2 = new Inimigo(Imagem.inimigoUm);
					inimigos.add(inim2);
				
					inimigos.add(inim);
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		for(int i = 0; i < inimigos.size();i++){
			inimigos.get(i).DesenhaSe(dbg, CanvasGame.MAPA.MapX, CanvasGame.MAPA.MapY);

			
		}
	}
	
	public void SimulaSe(int DiffTime){
		
//		System.out.println(inimigos.size());
		
		
		for(int i = 0; i < inimigos.size();i++){
			Inimigo inim = inimigos.get(i);
			
			inim.SimulaSe((int)DiffTime);
			
			if(!inim.isVivo()){
				inimigos.remove(i);
				GerenciadorXP.ganhouXp(inim.getX(), inim.getY(),inim.getTipoAssasino() );
			}
			if(inimigos.size() == 0)
				this.ativo = false;
		}
	}

}

package Gerenciadores;

import java.awt.Graphics2D;
import java.util.ArrayList;

import sun.security.provider.PolicyParser.GrantEntry;

import AbstractClasses.Objeto;
import Armas.Meele;
import Armas.Metralhadora;
import Armas.Pistola;
import Armas.He;
import Data.Imagem;



public class GerenciadorArma extends Objeto {

	
	
	public static ArrayList<Metralhadora> primaria = new ArrayList<Metralhadora>();
	public static ArrayList<Pistola> secundaria = new ArrayList<Pistola>();

	public static ArrayList<Meele> meele = new ArrayList<Meele>();

	public static Metralhadora primariaAtiva;

	public static Pistola secundariaAtiva;

	public static Meele meeleAtiva;

	public static He especialAtiva;

	public static final int m4 = 0;	
	public static final int ak47 = 1;
	public static final int FACA = 0;
	public static final int DE = 0;

	
	public static void carregaArmas() {
		
		primaria.clear();
		primaria.add(new Metralhadora(Imagem.m4, Imagem.m4_hud,GerenciadorDeSom.m4a));
		primaria.add(new Metralhadora(Imagem.ak47, Imagem.ak47_hud,GerenciadorDeSom.ak));
		
		primariaAtiva=primaria.get(m4);
		
		secundaria.clear();

		secundaria.add(new Pistola(Imagem.deagle, Imagem.deagle_hud, GerenciadorDeSom.de));
		secundariaAtiva=secundaria.get(DE);
		
		meele.clear();
		meele.add(new Meele(Imagem.faca, Imagem.faca, null, null));
		meeleAtiva=meele.get(FACA);
		
		
	}
	
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		
	}

}

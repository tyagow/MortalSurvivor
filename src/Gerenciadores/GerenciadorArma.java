package Gerenciadores;

import java.awt.Graphics2D;
import java.util.ArrayList;

import sun.security.provider.PolicyParser.GrantEntry;

import AbstractClasses.Objeto;
import Armas.Arma;
import Armas.Meele;
import Armas.Metralhadora;
import Armas.Pistola;
import Armas.He;
import Data.Imagem;



public class GerenciadorArma extends Objeto {

	
	
	public static ArrayList<Arma> armas = new ArrayList<Arma>();
	public static ArrayList<Pistola> secundaria = new ArrayList<Pistola>();

	public static ArrayList<Meele> meele = new ArrayList<Meele>();

	public static Arma primariaAtiva;

	public static Arma secundariaAtiva;

	public static Arma meeleAtiva;

	public static Arma especialAtiva;

	public static final int m4 = 0;	
	public static final int ak47 = 1;
	public static final int DE = 2;
	public static final int FACA = 3;
	public static final int HE = 4;

	
	public static void carregaArmas() {
		
		armas.clear();
		
		armas.add(new Metralhadora(Imagem.m4, Imagem.m4_hud,GerenciadorDeSom.m4a, null));
		armas.add(new Metralhadora(Imagem.ak47, Imagem.ak47_hud,GerenciadorDeSom.ak, null));
		
		armas.add(new Pistola(Imagem.deagle, Imagem.deagle_hud, GerenciadorDeSom.de, null));
		
		armas.add(new Meele(Imagem.faca, Imagem.faca, null, null));
		armas.add(new He(Imagem.he, Imagem.he_hud, null, null));

		
		primariaAtiva=armas.get(m4);
		secundariaAtiva=armas.get(2);	
		meeleAtiva=armas.get(FACA);
		especialAtiva=armas.get(HE);
		
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

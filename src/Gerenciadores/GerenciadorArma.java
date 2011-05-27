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

	public static Arma meeleAtiva;
	public static Arma primariaAtiva;
	public static Arma secundariaAtiva;
	public static Arma especialAtiva;

	public static final int M4 = 0;	
	public static final int AK47 = 1;
	public static final int DEAGLE = 2;
	public static final int FACA = 3;
	public static final int HE = 4;
	public static final int MP5 = 5;


	
	public static void carregaArmas() {
		
		armas.clear();
		
		armas.add(new Metralhadora(Imagem.m4, Imagem.m4_hud, GerenciadorDeSom.m4a, null));
		armas.add(new Metralhadora(Imagem.ak47, Imagem.ak47_hud, GerenciadorDeSom.ak, null));
		armas.add(new Pistola(Imagem.deagle, Imagem.deagle_hud, GerenciadorDeSom.de, null));
		armas.add(new Meele(Imagem.faca, Imagem.faca, null, null));
		armas.add(new He(Imagem.he, Imagem.he_hud, null, null));
		armas.add(new Metralhadora(Imagem.mp5, Imagem.mp5_hud, GerenciadorDeSom.m4a, null));

		
		primariaAtiva = armas.get(AK47);
		secundariaAtiva = armas.get(DEAGLE);	
		meeleAtiva = armas.get(FACA);
		especialAtiva = armas.get(HE);
		
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

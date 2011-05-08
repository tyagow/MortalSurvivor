package Gerenciadores;

import java.awt.Graphics2D;

import Som.ThreadSom;

import AbstractClasses.Objeto;


public class GerenciadorDeSom extends Objeto {

	
	public static ThreadSom ak;
	public static ThreadSom m4a;
	public static  ThreadSom de;
	public static  ThreadSom fundo;

	
	
	public GerenciadorDeSom() {
	
		ak=new ThreadSom("/sound/ak47.wav");
		m4a=new ThreadSom("/sound/m4a.wav");
		de=new ThreadSom("/sound/de.wav");
		//fundo =new ThreadSom("mainMusic.wav");
		
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

package Gerenciadores;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics2D;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;

import Som.Sound;
import Som.ThreadSom;

import AbstractClasses.Objeto;


public class GerenciadorDeSom extends Objeto {

	
	public static ThreadSom ak;
	public static  ThreadSom de;
	public static  ThreadSom fundo;
	public static ThreadSom ump45;
	public static ThreadSom m4a;
	public static ThreadSom m4a_silenciador;

	public static ThreadSom knife;
	public static ThreadSom knifeSlash;
	public static ThreadSom knifeSlash2;
	

	public static ThreadSom he[] = new ThreadSom[6];  

	 static SoundList soundList;  
	public static  AudioClip akA;

	 static AudioClip onceClip;
	AudioClip loopClip;  
	  
	  URL codeBase;
	  static String ak_ = "ak47.wav";
	  
	public GerenciadorDeSom() {
		
		ak=new ThreadSom("/sound/ak47.wav");
		m4a_silenciador= new ThreadSom("/sound/m4a_silenciador.wav");
		m4a=new ThreadSom("/sound/m4a.wav");
		de=new ThreadSom("/sound/de.wav");
		ump45=new ThreadSom("/sound/ump45.wav");

		knife=new ThreadSom("/sound/knife_hit.wav");
		knifeSlash=new ThreadSom("/sound/knife_slash.wav");
		knifeSlash2=new ThreadSom("/sound/knife_slash2.wav");

		he[0]=new ThreadSom("/sound/explode1.wav");
		he[1]=new ThreadSom("/sound/explode2.wav");
		he[2]=new ThreadSom("/sound/explode3.wav");
		he[3]=new ThreadSom("/sound/explode4.wav");
		he[4]=new ThreadSom("/sound/explode5.wav");
		he[5]=new ThreadSom("/sound/explode6.wav");
		
		
		
		
		//fundo =new ThreadSom("mainMusic.wav");
		
	//	startLoadingSounds();
		
	}
	public static void tiroAK() {
			onceClip = soundList.getClip(ak_);  
	      onceClip.loop(); //Play it once.  
	      if (onceClip == null) {  
	    	   System.out.println("Sound " + ak_ + " not loaded yet.");  
	      }  
		
	}
	 void startLoadingSounds() {  
		    //Start asynchronous sound loading.  
		    try {  
		      codeBase = new URL("http://" + System.getProperty("user.dir") + "/");  
		    } catch (MalformedURLException e) {  
		      System.err.println(e.getMessage());  
		    }  
		    soundList = new SoundList(codeBase);  
		    soundList.startLoading(ak_);  
//		    soundList.startLoading(aiffFile);  
//		    soundList.startLoading(midiFile);  
//		    soundList.startLoading(rmfFile);  
//		    soundList.startLoading(wavFile);  
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
@SuppressWarnings("rawtypes")
class SoundList extends java.util.Hashtable {  
	
	  
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	URL baseURL;  
	  
	  public SoundList(URL baseURL) {  
	    super(5); //Initialize Hashtable with capacity of 5 entries.  
	    this.baseURL = baseURL;  
	  }  
	  
	  public void startLoading(String relativeURL) {  
	    new SoundLoader(this, baseURL, relativeURL);  
	  }  
	  
	  public AudioClip getClip(String relativeURL) {  
	    return (AudioClip) get(relativeURL);  
	  }  
	  
	  public void putClip(AudioClip clip, String relativeURL) {  
	    put(relativeURL, clip);  
	  }  
	}  
	  
	class SoundLoader extends Thread {  
	  SoundList soundList;  
	  
	  URL completeURL;  
	  
	  String relativeURL;  
	  
	  public SoundLoader(SoundList soundList, URL baseURL, String relativeURL) {  
	    this.soundList = soundList;  
	    try {  
	      completeURL = new URL(baseURL, relativeURL);  
	    } catch (MalformedURLException e) {  
	      System.err.println(e.getMessage());  
	    }  
	    this.relativeURL = relativeURL;  
	    setPriority(MIN_PRIORITY);  
	    start();  
	  }  
	  
	  public void run() {  
	    AudioClip audioClip = Applet.newAudioClip(completeURL);  
	    soundList.putClip(audioClip, relativeURL);  
	  }  
	}
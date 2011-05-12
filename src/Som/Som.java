package Som;

import java.applet.Applet;
import java.io.*;

import sun.audio.AudioStream;

public class Som  {  
  
    public static AudioStream Sm(String caminho) throws Exception {  
        InputStream in = Applet.class.getResourceAsStream(caminho);  
        AudioStream audioStream = new AudioStream(in);  
      
        return audioStream;  
    }  
} 
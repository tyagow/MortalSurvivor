import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class Main extends JApplet
{

   private GamePanel gp;
   
   int mousecontext = 0;

   public static Main instance;

   public void init()
   {
     instance = this;
     
     setFocusable(true);
     
     Container c = getContentPane();
     c.setLayout( new BorderLayout() );   

     gp = new GamePanel();
     c.add(gp, "Center");
     
     resize(GamePanel.PWIDTH, GamePanel.PHEIGHT);
          
     gp.startGame();

   }
   
 
   public void start()
   {  
	  
   }

   public void stop()
   {  gp.stopGame();  }

   public void destroy()
   {  gp.stopGame();  }

} // end of WormChaseApplet class


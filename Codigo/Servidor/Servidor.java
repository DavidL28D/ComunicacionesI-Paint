import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics.*;

public class Servidor{

     public static void main(String args[]){

          frameServidor frame = new frameServidor();
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setResizable(false);

     }

}

class frameServidor extends JFrame{

     public frameServidor(){

          setTitle("Servidor - Lienzo");
          setBounds(400, 400, 400, 400);
		panelServidor panel = new panelServidor();
		add(panel);
		setVisible(true);

     }

}

class panelServidor extends JPanel implements MouseMotionListener, MouseListener{

     public int x, y;
     private final int radio = 2;
     
     public panelServidor(){

          addMouseMotionListener(this);
          addMouseListener(this);

     }

     public void mouseDragged(MouseEvent e){
          Graphics g = this.getGraphics();
          x = e.getX();
          y = e.getY();
          g.fillOval(e.getX()-radio, e.getY()-radio, 2*radio, 2*radio);
          g.dispose();
     }
     
     public void mouseClicked(MouseEvent e) {
          Graphics g = this.getGraphics();
          x = e.getX();
          y = e.getY();
          g.fillOval(e.getX()-radio, e.getY()-radio, 2*radio, 2*radio);
          g.dispose();
     }

     public void mouseMoved(MouseEvent e){}
     public void mousePressed(MouseEvent e) {}
     public void mouseReleased(MouseEvent e) {}
     public void mouseEntered(MouseEvent e) {}
     public void mouseExited(MouseEvent e) {}

}
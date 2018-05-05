import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.Graphics.*;
import java.io.*;
import java.net.*;

public class Servidor{

     public static void main(String args[]){
          
          System.out.print("Ingresa la direccion ip: ");
          Scanner escaner = new Scanner (System.in);
          String IP = escaner.nextLine ();
          
          frameServidor frame = new frameServidor(IP);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setResizable(false);
          
     }

}

class frameServidor extends JFrame{

     public frameServidor(String IP){

          setTitle("Servidor - Lienzo");
          setBounds(400, 400, 400, 400);
		panelServidor panel = new panelServidor(IP);
		add(panel);
		setVisible(true);

     }

}

class panelServidor extends JPanel implements MouseMotionListener, MouseListener{

     public int x, y, x1, y1;
     private String IP;
     
     public panelServidor(String IP){

          this.IP = IP;
          x = x1 = y = y1 = -1;
          addMouseMotionListener(this);
          addMouseListener(this);
          System.out.println ("Conectado con... " + this.IP);
          
     }

     public void pintar(Graphics g){

          Graphics2D g2 = (Graphics2D) g;
          g2.setStroke(new BasicStroke(2));
          g2.setColor(Color.black);

          if(x != -1 && x1 != -1 && y != -1 && y1 != -1){
               g2.drawLine(x, y, x1, y1);
          }
          
     }

     public void mousePressed(MouseEvent e) {

          x = x1 = e.getX();
          y = y1 = e.getY();
          pintar(this.getGraphics());
          enviar();

     }

     public void mouseDragged(MouseEvent e){

          x1 = e.getX();
          y1 = e.getY();
          pintar(this.getGraphics());
          x = x1;
          y = y1;
          enviar();

     }
     
     public void enviar(){

          try{ 

               Socket s = new Socket(this.IP, 9999);
               DataOutputStream sX = new DataOutputStream(s.getOutputStream());
               DataOutputStream sY = new DataOutputStream(s.getOutputStream());
               sX.writeInt(x);
               sY.writeInt(y);
               s.close();

          }catch(IOException e){
               //System.out.println("Error de IO enviando.");
          }

     }

     public void mouseMoved(MouseEvent e){}
     public void mouseClicked(MouseEvent e) {}
     public void mouseReleased(MouseEvent e) {}
     public void mouseEntered(MouseEvent e) {}
     public void mouseExited(MouseEvent e) {}

}
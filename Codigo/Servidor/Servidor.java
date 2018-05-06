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

class panelServidor extends JPanel implements MouseMotionListener, MouseListener, Runnable{

     int x, y, x1, y1;
     boolean flag;

     ServerSocket ss;
     Socket s;
     DataOutputStream sX, sY;
     
     public panelServidor(){

          x = x1 = y = y1 = -1;
          flag = false;

          setLayout(null);
          addMouseMotionListener(this);
          addMouseListener(this);
          

          Thread hilo = new Thread(this);
          hilo.start();

     }

     public void pintar(Graphics g){

          Graphics2D g2 = (Graphics2D) g;
          g2.setStroke(new BasicStroke(2));
          g2.setColor(Color.black);

          if(flag){
               if(x != -1 && x1 != -1 && y != -1 && y1 != -1){
                    g2.drawLine(x, y, x1, y1);
               }
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

     public void mouseReleased(MouseEvent e) {
          x = -1;
          y = -1;
          enviar();
     }
     
     public void mouseMoved(MouseEvent e){}
     public void mouseClicked(MouseEvent e) {}
     public void mouseEntered(MouseEvent e) {}
     public void mouseExited(MouseEvent e) {}

     public void enviar(){  

          if(flag){

               try{
               
                    sX = new DataOutputStream(s.getOutputStream());
                    sY = new DataOutputStream(s.getOutputStream());
                    sX.writeInt(x);
                    sY.writeInt(y);
                    
               }catch(IOException e){
                    //System.out.println("Error en el ServerSocket.");
               }
               
          }

          

     }

     @Override
     public void run(){

          try{
               
               ss = new ServerSocket(6666);
               //System.out.println("Servidor encendido.");

               JLabel label = new JLabel();
               label.setText("Esperando Cliente...");
               label.setBounds(140, 160, 300, 30);
               add(label);

               s = ss.accept();
               //System.out.println("Conectado a Cliente.");
               removeAll();
               repaint();
               flag = true;

          }catch(IOException e){
               //System.out.println("Error en el ServerSocket.");
          }

     }

}
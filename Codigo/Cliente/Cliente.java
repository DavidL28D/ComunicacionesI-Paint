import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics.*;
import java.io.*;
import java.net.*;

public class Cliente{

     public static void main(String args[]){

          frameCliente frame = new frameCliente();
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setResizable(false);

     }

}

class frameCliente extends JFrame{

     public frameCliente(){

          setTitle("Cliente - Lienzo");
          setBounds(400, 400, 400, 400);
		panelCliente panel = new panelCliente();
		add(panel);
		setVisible(true);
          
     }

}

class panelCliente extends JPanel implements Runnable{

     int x, y, x1, y1;

     Socket s;
     DataInputStream eX, eY;


     public panelCliente(){

          x = x1 = y = y1 = -1;

          Thread hilo = new Thread(this);
          hilo.start();
          
     }

     public void pintar(Graphics g){

          Graphics2D g2 = (Graphics2D) g;
          g2.setStroke(new BasicStroke(2));
          g2.setColor(Color.black);

          if(x != -1 && x1 != -1 && y != -1 && y1 != -1){
               g2.drawLine(x, y, x1, y1);
          }

     }

     @Override
     public void run(){

          try{ 

               s = new Socket("192.168.1.100", 6666);
               System.out.println("Conectado a Servidor.");

               while(true){

                    eX = new DataInputStream(s.getInputStream()); 
                    eY = new DataInputStream(s.getInputStream());

                    if(x1 == -1 && y1 == -1){
                         x = x1 = eX.readInt();
                         y = y1 = eY.readInt();
                    }else{
                         x1 = eX.readInt();
                         y1 = eY.readInt();
                    }

                    pintar(getGraphics());
                    x = x1;
                    y = y1;

               }
               
          }catch(IOException e){
               //System.out.println("Error en conectar Cliente.");
          }

     } 

}
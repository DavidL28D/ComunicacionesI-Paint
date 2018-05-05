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

     private final int radio = 2;

     public panelCliente(){

          Thread hilo = new Thread(this);
          hilo.start();
          
     }

     @Override
     public void run(){

          try{
               
               ServerSocket ss = new ServerSocket(9999);
               while(true){

               Socket s = ss.accept();
               DataInputStream eX = new DataInputStream(s.getInputStream()); 
               DataInputStream eY = new DataInputStream(s.getInputStream());

               int x = eX.readInt();
               int y = eY.readInt();

               Graphics g = this.getGraphics();
               g.fillOval(x-radio, y-radio, 2*radio, 2*radio);
               g.dispose();
               s.close();

               }

          }catch(IOException e){
               //System.out.println("Error de IO recibiendo.");
          }

     }

}
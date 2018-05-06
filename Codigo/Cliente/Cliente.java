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
     String IP_ADRESS;

     Socket s;
     DataInputStream eX, eY;


     public panelCliente(){

          x = x1 = y = y1 = -1;

          setLayout(null);
          JButton boton = new JButton();
          JLabel label = new JLabel();
          JTextField texto = new JTextField(15);
          
          label.setText("Ingresar la direccion IP del servidor");
          boton.setText("Aceptar");
          
          label.setBounds(100, 120, 300, 30);
          texto.setBounds(130, 160, 140, 30);
          boton.setBounds(140, 220, 120, 40);

          add(label);
          add(texto);
          add(boton);

          boton.addActionListener(new ActionListener(){

               @Override
               public void actionPerformed(ActionEvent arg0){

                    if(texto.getText().isEmpty()){
                         JOptionPane.showMessageDialog(null, "IP requerida.");

                    }else{
                         
                         IP_ADRESS = texto.getText();
                         removeAll();
                         repaint();
                         cargar();
                         
                    }

               }

          });

     }

     public void cargar(){
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

               s = new Socket(IP_ADRESS, 6666);

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
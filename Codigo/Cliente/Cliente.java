import javax.swing.*;

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

class panelCliente extends JPanel{

     public panelCliente(){

     }

}
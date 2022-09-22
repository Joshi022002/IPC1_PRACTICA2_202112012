package snake;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Casilla extends JPanel {
    public Casilla(int x,int y,int w,int h) {
	this.setBounds(x,y,w,h);//posicion y dimensiones
	this.setBackground(Color.WHITE);//color de fondo
	this.setBorder(BorderFactory.createLineBorder(Color.BLACK));//color de borde
    }
}

package snake;

import java.awt.Color;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Fruta extends JPanel {
    Random rnd = new Random();
    int w;
    int h;
    public Fruta(int w,int h) {
        this.w = w;
        this.h = h;
        //posiciones (x,y) aleatorias
        int x = rnd.nextInt(10);
        int y = rnd.nextInt(10);
        /*ecuacion posicion
        (X,Y) = (x * (ancho - 1),y * (alto - 1))
        para quedar justamente sobre las casillas del mapa*/
        this.setBounds(x * (w - 1),y * (h - 1),w,h);//posicion y dimensiones
        this.setBackground(new Color(27,161,226));//color de la fruta
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));//color del borde
    }
    public void reubicar() {
        //reasignar una nueva posición aleatoria
        int x = rnd.nextInt(10);
        int y = rnd.nextInt(10);
        this.setLocation(x * (w - 1),y * (h - 1));//posicion
    }
}

package snake;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Segmento extends JPanel {
    //posiciones destino
    private int xf;
    private int yf;
    
    public Segmento(int w,int h) {
        this.setBounds(-45,-45,w,h);//posicion y dimensiones
        this.setBackground(new Color(0,138,0));//color del segmento de la serpiente
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));//color del borde
    }
    
    public Segmento(int x,int y,int w,int h) {
        this.setBounds(x,y,w,h);//posicion y dimensiones
        this.setBackground(new Color(0,138,0));//color del segmento de la serpiente
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));//color del borde
    }
    
    public void setXf(int xf) {
        this.xf = xf;
    }
    
    public int getXf() {
        return xf;
    }
    
    public void setYf(int yf) {
        this.yf = yf;
    }
    
    public int getYf() {
        return yf;
    }
}

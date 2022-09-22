package snake;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Movimiento extends Thread {
    //direcciones del movimiento inicial (quieto)
    private boolean arriba = false,abajo = false,izquierda = false,derecha = false;
    //intervalo de movimiento y tamaño de serpiente
    private int intervalo = 1000,tamano = 1;
    //fraccion del intervalo
    private double fraccion = 1;
    Fruta fruta;
    JPanel casillas;
    Segmento[] serpiente;
    JLabel tamanoSerpiente;
    JLabel velocidadSerpiente;
    Snake ventana;
    
    public Movimiento(Fruta fruta,JPanel casillas,Segmento[] serpiente,String dificultad,JLabel tamanoSerpiente,JLabel velocidadSerpiente,Snake ventana) {
        this.fruta = fruta;
        this.casillas = casillas;
        this.serpiente = serpiente;
        this.tamanoSerpiente = tamanoSerpiente;
        this.velocidadSerpiente = velocidadSerpiente;
        this.ventana = ventana;
        
        if(dificultad.equals("Fácil")) {
            fraccion = fraccion - 0.03;//nueva fraccion = 0.97 (-3%)
        }else if(dificultad.equals("Medio")) {
            fraccion = fraccion - 0.06;//nueva fraccion = 0.94 (-6%)
        }else if(dificultad.equals("Difícil")) {
            fraccion = fraccion - 0.09;//nueva fraccion = 0.91 (-9%)
        }
    }
    
    public void run() {
        try {
            //mientras esté dentro de los límites del JPanel casillas (jPanel2)
            while(sinChocar()) {
                /*asignacion del destino de la cabeza de la serpiente*/
                //si la direccion arriba es verdadera
                if(arriba == true) {
                    //se mantiene la misma posición x
                    serpiente[0].setXf(serpiente[0].getX());
                    //se resta a la posición actual en y
                    serpiente[0].setYf(serpiente[0].getY() - 39);
                    
                    moverSerpiente();
                }
                //si la direccion abajo es verdadera
                else if(abajo == true) {
                    //se mantiene la misma posición x
                    serpiente[0].setXf(serpiente[0].getX());
                    //se suma a la posición actual en y
                    serpiente[0].setYf(serpiente[0].getY() + 39);
                    
                    moverSerpiente();
                }
                //si la direccion izquierda es verdadera
                else if(izquierda == true) {
                    //se resta a la posición actual en x
                    serpiente[0].setXf(serpiente[0].getX() - 39);
                    //se mantiene la misma posición y
                    serpiente[0].setYf(serpiente[0].getY());
                    
                    moverSerpiente();
                }
                //si la direccion derecha es verdadera
                else if(derecha == true) {
                    //se suma a la posición actual en x
                    serpiente[0].setXf(serpiente[0].getX() + 39);
                    //se mantiene la misma posición y
                    serpiente[0].setYf(serpiente[0].getY());
                    
                    moverSerpiente();
                }
                /*si las coordenadas (x,y) de la cabeza de la serpiente son iguales
                a las de la fruta se agrega un nuevo segmento a la serpiente*/
                if(serpiente[0].getX() == fruta.getX() && serpiente[0].getY() == fruta.getY()) {
                    //nuevo segmento
                    serpiente[tamano] = new Segmento(40,40);
                    casillas.add(serpiente[tamano]);
                    //reubica la fruta
                    fruta.reubicar();
                    //incremento del tamaño
                    tamano = tamano + 1;
                    //reasignación del valor del intervalo
                    intervalo = (int) (intervalo * fraccion);
                    //actualización de las estadísticas
                    tamanoSerpiente.setText(String.valueOf(tamano));
                    velocidadSerpiente.setText(String.valueOf(intervalo));
                }
                //si se alcanza el tamaño máximo (25) se termina el bucle
                if(tamano == 25) {
                    JOptionPane.showMessageDialog(ventana,"La Serpiente ha alcanzado su\ntamaño máximo.");
                    tamanoSerpiente.setText("1");
                    velocidadSerpiente.setText("1000");
                    casillas.removeAll();
                    ventana.revalidate();
                    ventana.repaint();
                    /*borra el hilo de movimiento de la ventana del juego (Snake ventana)
                    para poder iniciar un nuevo juego después*/
                    ventana.juego = null;
                    return;
                }
                /*Si se encuentra fuera de los límites de JPanel casillas (jPanel2)
                termina el bucle*/
                else if(haChocado()) {
                    break;
                }
                
                sleep(intervalo);
            }
            //si hay choque
            JOptionPane.showMessageDialog(ventana,"La Serpiente ha chocado con un muro.");
            tamanoSerpiente.setText("1");
            velocidadSerpiente.setText("1000");
            casillas.removeAll();
            ventana.revalidate();
            ventana.repaint();
            /*borra el hilo de movimiento de la ventana del juego (Snake ventana)
            para poder iniciar un nuevo juego después*/
            ventana.juego = null;
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Movimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //direcciones del movimiento
    public void moverArriba() {
        arriba = true;//direccion indicada
        abajo = false;
        izquierda = false;
        derecha = false;
    }
    
    public void moverAbajo() {
        arriba = false;
        abajo = true;//direccion indicada
        izquierda = false;
        derecha = false;
    }
    
    public void moverIzquierda() {
        arriba = false;
        abajo = false;
        izquierda = true;//direccion indicada
        derecha = false;
    }
    
    public void moverDerecha() {
        arriba = false;
        abajo = false;
        izquierda = false;
        derecha = true;//direccion indicada
    }
    
    private boolean sinChocar() {
        /*funcion de verificación si se encuentra dentro de los límites de
        JPanel Casillas (jPanel2)*/
        if(serpiente[0].getX() >= 0 &&
                serpiente[0].getX() <= 352 &&
                serpiente[0].getY() >= 0 &&
                serpiente[0].getY() <= 352) {
            return true;
        }
        return false;
    }
    
    private boolean haChocado() {
        /*funcion de verificación si se encuentra fuera de los límites de
        JPanel Casillas (jPanel2)*/
        if(serpiente[0].getX() < 0 ||
                serpiente[0].getX() > 352 ||
                serpiente[0].getY() < 0 ||
                serpiente[0].getY() > 352) {
            return true;
        }
        return false;
    }
    
    //funcion de movimiento de cada segmento de la serpiente
    private void moverSerpiente() {
        for(int i = 0; i < serpiente.length; i ++) {
            //verifica si ya no hay un segmento en la posición actual
            if(serpiente[i] == null) {
                //detiene la iteración porque ya no hay nada que mover
                break;
            }
            
            //verifica si hay un siguiente segmento después del actual
            if(i + 1 < serpiente.length && serpiente[i + 1] != null) {
                //solo se asignan los destinos de los segmentos después de la cabeza
                /*asigna posición x final del segmento siguiente
                la posicion x final del segmento siguiente es igual
                a la posición actual del segmento actual*/
                serpiente[i + 1].setXf(serpiente[i].getX());
                /*asigna posición y final del segmento siguiente
                la posicion y final del segmento siguiente es igual
                a la posición actual del segmento actual*/
                serpiente[i + 1].setYf(serpiente[i].getY());
            }
            /*se asigna la nueva posición del segmento actual
            la nueva posición es igual a la posición del destino*/
            serpiente[i].setLocation(serpiente[i].getXf(),serpiente[i].getYf());
        }
    }
}

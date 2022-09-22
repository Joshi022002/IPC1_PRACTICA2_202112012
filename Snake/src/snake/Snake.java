package snake;

public class Snake extends javax.swing.JFrame {
    Movimiento juego; //hilo de movimiento
    Segmento[] serpiente; //arreglo de segmentos para formar la serpiente
    Fruta fruta; 
    /**
     * Crear Nuevo form Juego
     */
    public Snake() {
        initComponents();
        casillas();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Serpiente");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusableWindowState(false);
        setForeground(java.awt.Color.black);
        setPreferredSize(new java.awt.Dimension(850, 500));
        setResizable(false);
        getContentPane().setLayout(null);

        jButton1.setText("Nuevo Juego");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(670, 70, 150, 30);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fácil", "Medio", "Difícil" }));
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(470, 70, 150, 30);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("1");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(470, 140, 350, 15);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tamaño Serpiente");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(470, 160, 350, 15);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("1000");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(470, 200, 350, 15);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Intervalo");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(470, 230, 350, 15);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jButton2.setText("Arriba");
        jButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(610, 280, 70, 70);

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jButton3.setText("Abajo");
        jButton3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(610, 350, 70, 70);

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jButton4.setText("Izquierda");
        jButton4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(540, 350, 70, 70);

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jButton5.setText("Derecha");
        jButton5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(680, 350, 70, 70);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(391, 391));
        jPanel2.setLayout(null);
        getContentPane().add(jPanel2);
        jPanel2.setBounds(30, 30, 391, 391);

        jLabel5.setText("Dificultad");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(520, 40, 100, 15);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /*
    jPanel2 es colocado sobre las casillas para que se superponga a las casillas
    base, y también se superpone todo lo que se ponga en jPanel2 como la serpiente
    y las frutas
    */
    
    public void casillas() {
        //Mapa base de 10x10 casillas
        //10 filas
        for(int i = 0; i < 10; i ++) {
            //10 columnas
            for(int j = 0; j < 10; j ++) {
                //Casillas heredadas de JPanel de 40x40 px
                //j*39 y i*39 para sobreponer bordes internos de los Paneles
		this.add(new Casilla(j * 39 + 30,i * 39 + 30,40,40));
            }
	}
    }
    
    public void ubicarSerpiente() {
        //inicialización del arreglo con longitud máxima de 25
        serpiente = new Segmento[25];
        //inicialización de la cabeza de la serpiente en el centro del mapa de casillas
        serpiente[0] = new Segmento(195,195,40,40);
        //agregación de la cabeza en jPanel2
        jPanel2.add(serpiente[0]);
    }
    
    public void ubicarFruta() {
        //inicialización de la fruta (se asigna posición aleatoria en el constructor)
        fruta = new Fruta(40,40);
        //agregación de la fruta en una posición aleatoria en el mapa
        jPanel2.add(fruta);
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //si juego (hilo del movimiento) es null (sin inicializar) se inicia el juego
        if(juego == null) {
            //ubación de la cabeza de la serpiente y la fruta
            ubicarSerpiente();
            ubicarFruta();
            //Inicialización del hilo del movimiento
            juego = new Movimiento(
                    fruta,
                    jPanel2,
                    serpiente,
                    jComboBox1.getSelectedItem().toString(),
                    jLabel1,
                    jLabel3,
                    this
            );
            //arranque del hilo
            juego.start();
            //actualización del Panel
            jPanel2.revalidate();
            jPanel2.repaint();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //si juego (hilo) está inicializado mueve hacia arriba
        if(juego != null){
            juego.moverArriba();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //si juego (hilo) está inicializado mueve hacia abajo
        if(juego != null){
            juego.moverAbajo();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        //si juego (hilo) está inicializado mueve hacia izquierda
        if(juego != null){
            juego.moverIzquierda();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        //si juego (hilo) está inicializado mueve hacia derecha
        if(juego != null){
            juego.moverDerecha();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Snake.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Snake.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Snake.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Snake.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Snake().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}

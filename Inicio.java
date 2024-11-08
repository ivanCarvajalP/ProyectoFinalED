package Interface;

import Clases.Centro_Educativo;
import Clases.Curso;
import Clases.Estudiante;
import Estructuras.Cola;

import Estructuras.Lista_Doble;
import Estructuras.Nodo;
import Estructuras.Pila;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Graphics;
import java.awt.Image;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Inicio extends javax.swing.JFrame {

    Registrar_CentroEducativo ventanaRegistrar;
    Boolean bandera = false;
    Interface_Centro_Educativo ventanaCentroEduc;
    Modificar_CentroEducativo ventanaEditar;
    Lista_Doble centrosEducativos;
    DefaultListModel modelo;
    int indiceSeleccionadoCentroEdu;
    DecimalFormat f = new DecimalFormat("0.##");
    FondoPanel fondo = new FondoPanel();

    public Inicio() {
        FlatMacLightLaf.setup();
        this.setContentPane(fondo);
        initComponents();
        setLocationRelativeTo(null);

        this.centrosEducativos = new Lista_Doble();
        MontarCDAleatorios();
        MontarCursoAleatorios();
        MontarEstudianteAleatorios();

        MontarEstudianteAleatorios();
        MontarLista();
        System.out.println(centrosEducativos.MostrarPU());

    }

    public Inicio(Lista_Doble centrosEducativos) {

        FlatMacLightLaf.setup();
        initComponents();
        setLocationRelativeTo(null);

        this.centrosEducativos = centrosEducativos;

        MontarLista();
        System.out.println(centrosEducativos.MostrarPU());
    }

    public JButton getjButton2() {
        return jButton2;
    }

    public void setjButton2(JButton jButton2) {
        this.jButton2 = jButton2;
    }

    public JButton getjButton3() {
        return jButton3;
    }

    public void setjButton3(JButton jButton3) {
        this.jButton3 = jButton3;
    }

    public JButton getjButton5() {
        return jButton5;
    }

    public void setjButton5(JButton jButton5) {
        this.jButton5 = jButton5;
    }

    public void MontarLista() {
        modelo = new DefaultListModel();
        String elementos[] = centrosEducativos.MostrarPU().split("\n");
        for (int i = 0; i < elementos.length; i++) {
            modelo.addElement(elementos[i]);
            if (elementos[0].equalsIgnoreCase("Vacia")) {
                jButton2.setEnabled(false);
                jButton3.setEnabled(false);
                jButton5.setEnabled(false);
            } else {
                jButton2.setEnabled(true);
                jButton3.setEnabled(true);
                jButton5.setEnabled(true);
            }
        }
        jList1.setModel(modelo);
    }

    public String[] barajarNombres(String[] nombres) {
        String[] nombresBarajados = new String[nombres.length];
        boolean[] usado = new boolean[nombres.length];

        for (int i = 0; i < nombres.length; i++) {
            int n;
            do {
                n = (int) (Math.random() * nombres.length);
            } while (usado[n]);

            nombresBarajados[i] = nombres[n];
            usado[n] = true;
        }

        return nombresBarajados;
    }

    public void MontarCDAleatorios() {

        String nombresCentros[] = {"Siglo xll", "Distrial", "Migani", "Los Pinos", "Jorge Liecer"};
        String[] nombresBarajados = barajarNombres(nombresCentros);
        int nombreIte = 0;

        for (int i = 1; i < nombresCentros.length; i++) {
            Centro_Educativo CD = new Centro_Educativo(i, nombresBarajados[nombreIte].toUpperCase(), new Cola(), 0.0);
            centrosEducativos.Insertar_Ultimo(CD);
            nombreIte++;

            if (nombreIte == nombresBarajados.length) {
                nombreIte = 0;
            }

        }

    }

    public void MontarCursoAleatorios() {

        String nombresCursos[] = {"Matemáticas", "Física", "Ciencias Naturales", "Español", "Ingles", "Religión"};
        String[] nombresBarajados = barajarNombres(nombresCursos);
        int nombreIte = 0;

        Nodo aux = centrosEducativos.getPrimero();

        while (aux != null) {
            for (int i = 1; i < nombresCursos.length; i++) {
                Curso curso = new Curso(i, nombresBarajados[nombreIte].toUpperCase(), new Pila(), 0);
                ((Centro_Educativo) aux.getDato()).getCursos().Encolar(curso);
                nombreIte++;

                if (nombreIte == nombresBarajados.length) {
                    nombreIte = 0;
                }

            }
            aux = aux.getSiguiente();
        }
    }

    public void MontarEstudianteAleatorios() {
        String[] nombres = {"Angela", "Marcelo", "Sofia", "Diego", "Antonio"};
        String[] nombresBarajados = barajarNombres(nombres);
        int nombreIte = 0;
        int edades[] = new int[nombres.length];
        for (int i = 0; i < edades.length; i++) {
            edades[i] = (int) (Math.random() * 10 + 10);
        }

        Nodo aux1 = centrosEducativos.getPrimero();

        while (aux1 != null) {
            Centro_Educativo CD = (Centro_Educativo) aux1.getDato();
            Nodo aux2 = CD.getCursos().getPrimero();
            while (aux2 != null) {

                Curso curso = (Curso) aux2.getDato();
                for (int i = 1; i <= nombres.length; i++) {
                    double promedio = (Math.random() * 4.0 + 1.0);
                    String genero = "";
                    if (nombresBarajados[nombreIte].endsWith("o")) {
                        genero = "Masculino";
                    } else if (nombresBarajados[nombreIte].endsWith("a")) {
                        genero = "Femenino";
                    } else {
                        genero = "Desconocido";
                    }

                    int edad = edades[nombreIte];
                    Estudiante estudiante = new Estudiante(i, nombresBarajados[nombreIte].toUpperCase(), genero, edad, promedio);
                    curso.getEstudiantes().Apilar(estudiante);
                    nombreIte++;

                    if (nombreIte == nombresBarajados.length) {
                        nombreIte = 0;
                    }
                }

                Double promedioCurso = ((Curso) (aux2.getDato())).getEstudiantes().promedioEstu();

                ((Curso) (aux2.getDato())).setPromedio(promedioCurso);

                aux2 = aux2.getSiguiente();
            }
            Double promedioCD = ((Centro_Educativo) (aux1.getDato())).getCursos().promedioCurso();
            ((Centro_Educativo) (aux1.getDato())).setPromedio(promedioCD);

            aux1 = aux1.getSiguiente();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new FondoPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jList1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LISTA DOBLE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 14), new java.awt.Color(204, 204, 204))); // NOI18N
        jList1.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jList1.setForeground(new java.awt.Color(51, 51, 51));
        jScrollPane1.setViewportView(jList1);

        jPanel4.setBackground(new java.awt.Color(0, 153, 0));

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/eliminar.png"))); // NOI18N
        jButton3.setText("Eliminar");
        jButton3.setActionCommand("jButton2");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 102, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Editar2.png"))); // NOI18N
        jButton2.setText("Modificar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 0, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/guardar.png"))); // NOI18N
        jButton1.setText("Registrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 153, 204));
        jButton5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Ingresar.png"))); // NOI18N
        jButton5.setText("Ingresar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 153, 0));

        label1.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        label1.setForeground(new java.awt.Color(255, 255, 255));
        label1.setText("FLORENCIA CAQUETÁ");

        label2.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        label2.setForeground(new java.awt.Color(204, 204, 204));
        label2.setText("Centros Educativos");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        if (jList1.isSelectionEmpty()) {

            JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento de la lista.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            indiceSeleccionadoCentroEdu = jList1.getSelectedIndex();
            Centro_Educativo aux1 = centrosEducativos.SelecCentroEduItem(indiceSeleccionadoCentroEdu);

            Centro_Educativo centroEdu = centrosEducativos.SelecCentroEduItem(indiceSeleccionadoCentroEdu);
            ventanaCentroEduc = new Interface_Centro_Educativo(centrosEducativos, this.indiceSeleccionadoCentroEdu);

            ventanaCentroEduc.getLabel1().setText(centroEdu.getNombre());
            ventanaCentroEduc.getLabel2().setText("" + centroEdu.getId());
            ventanaCentroEduc.getLabel4().setText("" + f.format(aux1.getPromedio()));

            ventanaCentroEduc.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (jList1.isSelectionEmpty()) {

            JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento de la lista.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            indiceSeleccionadoCentroEdu = jList1.getSelectedIndex();
            Centro_Educativo aux1 = centrosEducativos.SelecCentroEduItem(indiceSeleccionadoCentroEdu);

            ventanaEditar = new Modificar_CentroEducativo(centrosEducativos, indiceSeleccionadoCentroEdu);
            ventanaEditar.getjTextField1().setText(aux1.getNombre());

            ventanaEditar.setVisible(true);
            this.dispose();
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ventanaRegistrar = new Registrar_CentroEducativo(centrosEducativos);
        ventanaRegistrar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        indiceSeleccionadoCentroEdu = jList1.getSelectedIndex();

        if (indiceSeleccionadoCentroEdu != -1) {
            Centro_Educativo aux1 = centrosEducativos.SelecCentroEduItem(indiceSeleccionadoCentroEdu);

            int respuesta = (JOptionPane.showConfirmDialog(null, "¿Está seguro que quieres ELIMINAR el Centro Educativo?"));
            if (respuesta == 0) {

                centrosEducativos.EliminarCD(indiceSeleccionadoCentroEdu);
                aux1.setPromedio(aux1.getCursos().promedioCurso());

                MontarLista();
                JOptionPane.showMessageDialog(null, "El Centro Educativo se ha eliminado CORRECTAMENTE.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento de la lista", "Error", JOptionPane.ERROR_MESSAGE);

        }
        System.out.println(centrosEducativos.MostrarPU());


    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatMacLightLaf.setup();
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Inicio().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    // End of variables declaration//GEN-END:variables

    class FondoPanel extends JPanel {

        private Image imagen;

        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/IMG/fondo.jpg")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }

}

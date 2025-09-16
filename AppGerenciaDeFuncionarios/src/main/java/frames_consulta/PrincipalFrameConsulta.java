/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames_consulta;

import initial_frames.InitialFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author PabloCarrijo
 */
public class PrincipalFrameConsulta extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PrincipalFrameConsulta.class.getName());

    /**
     * Creates new form FormPrincipal
     */
    int flagCadastrar = 0;
    int flagAlterar = 0;
    int flagRemover = 0;
    int flagConsultar = 0;

    public PrincipalFrameConsulta() {

        setUndecorated(true);

        initComponents();

        // Configura o JDesktopPane para preencher a tela inteira
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        desktopPane.setPreferredSize(screenSize);

        // Aplica o modo de tela cheia
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (gd.isFullScreenSupported()) {
            gd.setFullScreenWindow(this);
        } else {
            // Se a tela cheia não for suportada, maximiza a janela
            setExtendedState(MAXIMIZED_BOTH);
        }

        // Outras configurações da janela
        setResizable(false);
        setJMenuBar(null);

        labelCadastrar.setOpaque(true);
        labelAlterar.setOpaque(true);
        labelConsultar.setOpaque(true);
        labelRemover.setOpaque(true);

        Color colorPink = new Color(255, 255, 255);
        Color colorBlack = new Color(51, 51, 51);

        logoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Fecha esta tela
                dispose();

                // Abre a tela inicial
                InitialFrame initialFrame = new InitialFrame();
                initialFrame.setVisible(true);
            }
        });

        labelCadastrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                flagConsultar = 0;
                flagRemover = 0;
                flagAlterar = 0;
                flagCadastrar = 1;
                if (flagCadastrar == 1) {
                    labelCadastrar.setForeground(colorBlack);
                    labelAlterar.setForeground(colorPink);
                    labelConsultar.setForeground(colorPink);
                    labelRemover.setForeground(colorPink);

                    labelCadastrar.setBackground(colorPink);
                    labelAlterar.setBackground(colorBlack);
                    labelConsultar.setBackground(colorBlack);
                    labelRemover.setBackground(colorBlack);
                }
                AddConsulta addConsulta = new AddConsulta();
                desktopPane.add(addConsulta);

                // 1. Dê à janela interna o tamanho do desktop pane primeiro.
                Dimension desktopSize = desktopPane.getSize();
                addConsulta.setSize(desktopSize);
                addConsulta.setLocation(
                        (desktopSize.width - desktopPane.getWidth()) / 2,
                        (desktopSize.height - desktopPane.getHeight()) / 2
                );

                addConsulta.setSize(desktopSize.width, desktopSize.height);
                addConsulta.setLocation(0, 0);

                // 3. Defina a localização da janela interna.
                addConsulta.setVisible(true);

            }
        });

        labelAlterar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                flagConsultar = 0;
                flagRemover = 0;
                flagAlterar = 1;
                flagCadastrar = 0;
                if (flagAlterar == 1) {
                    labelAlterar.setForeground(colorBlack);
                    labelCadastrar.setForeground(colorPink);
                    labelConsultar.setForeground(colorPink);
                    labelRemover.setForeground(colorPink);

                    labelAlterar.setBackground(colorPink);
                    labelCadastrar.setBackground(colorBlack);
                    labelConsultar.setBackground(colorBlack);
                    labelRemover.setBackground(colorBlack);
                }
                AlterarConsulta alterarConsulta = new AlterarConsulta();
                desktopPane.add(alterarConsulta);

                // 1. Dê à janela interna o tamanho do desktop pane primeiro.
                Dimension desktopSize = desktopPane.getSize();
                alterarConsulta.setSize(desktopSize);
                alterarConsulta.setLocation(
                        (desktopSize.width - desktopPane.getWidth()) / 2,
                        (desktopSize.height - desktopPane.getHeight()) / 2
                );

                alterarConsulta.setSize(desktopSize.width, desktopSize.height);
                alterarConsulta.setLocation(0, 0);

                // 3. Defina a localização da janela interna.
                alterarConsulta.setVisible(true);

            }
        });

        labelConsultar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                flagConsultar = 1;
                flagRemover = 0;
                flagAlterar = 0;
                flagCadastrar = 0;
                if (flagConsultar == 1) {
                    labelConsultar.setForeground(colorBlack);
                    labelAlterar.setForeground(colorPink);
                    labelCadastrar.setForeground(colorPink);
                    labelRemover.setForeground(colorPink);

                    labelConsultar.setBackground(colorPink);
                    labelAlterar.setBackground(colorBlack);
                    labelCadastrar.setBackground(colorBlack);
                    labelRemover.setBackground(colorBlack);
                }
                VisualizarConsulta visualizarConsulta = new VisualizarConsulta();
                desktopPane.add(visualizarConsulta);

                // 1. Dê à janela interna o tamanho do desktop pane primeiro.
                Dimension desktopSize = desktopPane.getSize();
                visualizarConsulta.setSize(desktopSize);
                visualizarConsulta.setLocation(
                        (desktopSize.width - desktopPane.getWidth()) / 2,
                        (desktopSize.height - desktopPane.getHeight()) / 2
                );

                visualizarConsulta.setSize(desktopSize.width, desktopSize.height);
                visualizarConsulta.setLocation(0, 0);

                // 3. Defina a localização da janela interna.
                visualizarConsulta.setVisible(true);

            }
        });

        labelRemover.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                flagConsultar = 0;
                flagRemover = 1;
                flagAlterar = 0;
                flagCadastrar = 0;
                if (flagRemover == 1) {
                    labelRemover.setForeground(colorBlack);
                    labelConsultar.setForeground(colorPink);
                    labelAlterar.setForeground(colorPink);
                    labelCadastrar.setForeground(colorPink);

                    labelRemover.setBackground(colorPink);
                    labelConsultar.setBackground(colorBlack);
                    labelAlterar.setBackground(colorBlack);
                    labelCadastrar.setBackground(colorBlack);
                }
                RemoveConsulta removeConsulta = new RemoveConsulta();
                desktopPane.add(removeConsulta);

                // 1. Dê à janela interna o tamanho do desktop pane primeiro.
                Dimension desktopSize = desktopPane.getSize();
                removeConsulta.setSize(desktopSize);
                removeConsulta.setLocation(
                        (desktopSize.width - desktopPane.getWidth()) / 2,
                        (desktopSize.height - desktopPane.getHeight()) / 2
                );

                removeConsulta.setSize(desktopSize.width, desktopSize.height);
                removeConsulta.setLocation(0, 0);

                // 3. Defina a localização da janela interna.
                removeConsulta.setVisible(true);

            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        logoLabel = new javax.swing.JLabel();
        labelCadastrar = new javax.swing.JLabel();
        labelConsultar = new javax.swing.JLabel();
        labelAlterar = new javax.swing.JLabel();
        labelRemover = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        AddNovoFuncionario = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        desktopPane.setBackground(new java.awt.Color(255, 255, 255));
        desktopPane.setForeground(new java.awt.Color(255, 255, 255));
        desktopPane.setPreferredSize(new java.awt.Dimension(1300, 541));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/appGerenciaFuncionarios/imagens/logo-sistema.jpg"))); // NOI18N

        desktopPane.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1300, Short.MAX_VALUE)
            .addGroup(desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(desktopPaneLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 521, Short.MAX_VALUE)
            .addGroup(desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(desktopPaneLayout.createSequentialGroup()
                    .addGap(0, 64, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addGap(0, 65, Short.MAX_VALUE)))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("X");
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 3)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/appGerenciaFuncionarios/imagens/logoGerencia2.jpg"))); // NOI18N

        logoLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        logoLabel.setForeground(new java.awt.Color(255, 255, 255));
        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/appGerenciaFuncionarios/imagens/logo-sistema-media.jpg"))); // NOI18N

        labelCadastrar.setBackground(new java.awt.Color(51, 51, 51));
        labelCadastrar.setForeground(new java.awt.Color(255, 255, 255));
        labelCadastrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCadastrar.setText("Cadastrar consulta");
        labelCadastrar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        labelCadastrar.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                labelCadastrarAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        labelConsultar.setBackground(new java.awt.Color(51, 51, 51));
        labelConsultar.setForeground(new java.awt.Color(255, 255, 255));
        labelConsultar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelConsultar.setText("Visualizar consulta");
        labelConsultar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        labelConsultar.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                labelConsultarAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        labelAlterar.setBackground(new java.awt.Color(51, 51, 51));
        labelAlterar.setForeground(new java.awt.Color(255, 255, 255));
        labelAlterar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAlterar.setText("Alterar consulta");
        labelAlterar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        labelAlterar.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                labelAlterarAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        labelRemover.setBackground(new java.awt.Color(51, 51, 51));
        labelRemover.setForeground(new java.awt.Color(255, 255, 255));
        labelRemover.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRemover.setText("Excluir consulta");
        labelRemover.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        labelRemover.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                labelRemoverAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 871, Short.MAX_VALUE)
                .addContainerGap(429, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(logoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(labelConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(logoLabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(labelConsultar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelCadastrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, 0, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jMenuBar1.setForeground(new java.awt.Color(0, 0, 0));
        jMenuBar1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jMenuBar1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jMenu1.setText("Funcionarios");

        AddNovoFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/appGerenciaFuncionarios/imagens/add.png"))); // NOI18N
        AddNovoFuncionario.setText("Adicionar Funcionario");
        AddNovoFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNovoFuncionarioActionPerformed(evt);
            }
        });
        jMenu1.add(AddNovoFuncionario);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/appGerenciaFuncionarios/imagens/basket_put.png"))); // NOI18N
        jMenuItem1.setText("Pesquisar Funcionario");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddNovoFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNovoFuncionarioActionPerformed


    }//GEN-LAST:event_AddNovoFuncionarioActionPerformed

    private void jMenuBar1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jMenuBar1AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuBar1AncestorAdded

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        System.exit(0);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void labelCadastrarAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_labelCadastrarAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_labelCadastrarAncestorAdded

    private void labelConsultarAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_labelConsultarAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_labelConsultarAncestorAdded

    private void labelAlterarAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_labelAlterarAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_labelAlterarAncestorAdded

    private void labelRemoverAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_labelRemoverAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_labelRemoverAncestorAdded

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new PrincipalFrameConsulta().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AddNovoFuncionario;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelAlterar;
    private javax.swing.JLabel labelCadastrar;
    private javax.swing.JLabel labelConsultar;
    private javax.swing.JLabel labelRemover;
    private javax.swing.JLabel logoLabel;
    // End of variables declaration//GEN-END:variables
}

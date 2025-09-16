/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package frames_paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.connector.myConnection;

/**
 *
 * @author PabloCarrijo
 */
public class AlterarFun extends javax.swing.JInternalFrame {

    /**
     * Creates new form AlterarFun
     */
    public AlterarFunPane alterarPane;
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    // 🔹 Componentes para autocomplete
    private DefaultListModel<String> listModel;
    private JList<String> sugestaoList;
    private JScrollPane scrollPane;
    private JPopupMenu popupSugestoes;

    public AlterarFun() {
        initComponents();
        this.setBorder(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);

        configurarAutoComplete();

    }

    // ================================
    //  Configuração do autocomplete
    // ================================
    private void configurarAutoComplete() {
        listModel = new DefaultListModel<>();
        sugestaoList = new JList<>(listModel);
        scrollPane = new JScrollPane(sugestaoList);

        popupSugestoes = new JPopupMenu();
        popupSugestoes.setBorder(null);
        popupSugestoes.add(scrollPane);
        scrollPane.setPreferredSize(new java.awt.Dimension(
                textNamePesquisaAltera.getWidth(), 120
        ));

        // Listener para capturar digitação
        textNamePesquisaAltera.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                buscarSugestoes();
            }

            public void removeUpdate(DocumentEvent e) {
                buscarSugestoes();
            }

            public void changedUpdate(DocumentEvent e) {
                buscarSugestoes();
            }

            private void buscarSugestoes() {
                String texto = textNamePesquisaAltera.getText().trim();
                if (texto.isEmpty()) {
                    popupSugestoes.setVisible(false);
                    return;
                }

                List<String> sugestoes = buscarNoBanco(texto);
                atualizarSugestoes(sugestoes);
            }
        });

        // Se clicar em uma sugestão
        sugestaoList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String selecionado = sugestaoList.getSelectedValue();
                    if (selecionado != null) {
                        // Divide em nome e cpf
                        String[] partes = selecionado.split(" - ");
                        if (partes.length == 2) {
                            textNamePesquisaAltera.setText(partes[1]); // coloca só o CPF no campo
                        } else {
                            textNamePesquisaAltera.setText(selecionado);
                        }
                        popupSugestoes.setVisible(false);
                    }
                }
            }
        });

    }

    //  Atualiza o popup com as sugestões
    private void atualizarSugestoes(List<String> sugestoes) {
        listModel.clear();
        if (!sugestoes.isEmpty()) {
            for (String s : sugestoes) {
                listModel.addElement(s);
            }

            // 🔹 Ajusta altura do scrollPane de acordo com a quantidade de itens
            int rowHeight = sugestaoList.getFixedCellHeight() > 0 ? sugestaoList.getFixedCellHeight() : 40;
            int altura = Math.min(sugestoes.size() * rowHeight, 150); // Máximo 150px
            scrollPane.setPreferredSize(new java.awt.Dimension(
                    textNamePesquisaAltera.getWidth(),
                    altura
            ));

            popupSugestoes.show(textNamePesquisaAltera, 0, textNamePesquisaAltera.getHeight());
            popupSugestoes.show(textNamePesquisaAltera, 0, textNamePesquisaAltera.getHeight());
            textNamePesquisaAltera.requestFocusInWindow(); // 🔹 devolve o foco ao campo

        } else {
            popupSugestoes.setVisible(false);
        }
    }

    //  Consulta no banco para autocomplete
    private List<String> buscarNoBanco(String texto) {
        List<String> sugestoes = new ArrayList<>();
        try (Connection conn = myConnection.getConexao(); PreparedStatement stmt = conn.prepareStatement(
                "SELECT nome, cpf FROM paciente WHERE nome LIKE ? OR cpf LIKE ? LIMIT 10")) {

            stmt.setString(1, texto + "%"); // busca por nome
            stmt.setString(2, texto + "%"); // busca também por cpf
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                sugestoes.add(nome + " - " + cpf); // junta nome e cpf
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sugestoes;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        textNamePesquisaAltera = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        desktopPaneAlterar = new javax.swing.JDesktopPane();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButton2.setBackground(new java.awt.Color(51, 51, 51));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Pesquisar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        textNamePesquisaAltera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNamePesquisaAlteraActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("CPF do Paciente");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(textNamePesquisaAltera, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(655, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textNamePesquisaAltera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );

        desktopPaneAlterar.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout desktopPaneAlterarLayout = new javax.swing.GroupLayout(desktopPaneAlterar);
        desktopPaneAlterar.setLayout(desktopPaneAlterarLayout);
        desktopPaneAlterarLayout.setHorizontalGroup(
            desktopPaneAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        desktopPaneAlterarLayout.setVerticalGroup(
            desktopPaneAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 344, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(desktopPaneAlterar)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(desktopPaneAlterar))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try {

            if (conn == null || conn.isClosed()) {
                conn = myConnection.getConexao();
            } else if (conn != null && !conn.isClosed()) {
                conn = myConnection.getConexao();
            }

            String sql = "SELECT * FROM paciente WHERE cpf = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, textNamePesquisaAltera.getText());
            rs = ps.executeQuery();

            if (!rs.next()) {
                JOptionPane.showInternalMessageDialog(getDesktopPane(), "Nenhuma paciente cadastrado...");
            } else {
                alterarPane = new AlterarFunPane(textNamePesquisaAltera.getText());
                desktopPaneAlterar.add(alterarPane);
                alterarPane.setSize(desktopPaneAlterar.getSize());
                alterarPane.setLocation(0, 0);
                alterarPane.show();
            }

        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(getDesktopPane(), "Erro ao conectar banco de dados..." + e.getMessage());
        } finally {
            myConnection.closeConnection(conn, ps, rs);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void textNamePesquisaAlteraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNamePesquisaAlteraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNamePesquisaAlteraActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPaneAlterar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField textNamePesquisaAltera;
    // End of variables declaration//GEN-END:variables
}

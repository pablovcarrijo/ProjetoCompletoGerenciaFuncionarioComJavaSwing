/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package frames_consulta;

import java.awt.Dimension;
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
public class AlterarConsulta extends javax.swing.JInternalFrame {

    /**
     * Creates new form AddConsulta
     */
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private DefaultListModel<String> listModel;
    private JList<String> sugestaoList;
    private JScrollPane scrollPane;
    private JPopupMenu popupSugestoes;

    public AlterarConsulta() {
        initComponents();
        this.setBorder(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);

        configurarAutoComplete();

    }

    //----------------------------------------------------------------------
    // AUTOCOMPLETE - DE ACORDO COM OQ COLOCO NO TEXT FIELD VAI DANDO OPÇÕES
    //----------------------------------------------------------------------
    private void configurarAutoComplete() {
        listModel = new DefaultListModel<>();
        sugestaoList = new JList<>(listModel);
        scrollPane = new JScrollPane(sugestaoList);

        popupSugestoes = new JPopupMenu();
        popupSugestoes.setBorder(null);
        popupSugestoes.add(scrollPane);
        scrollPane.setPreferredSize(new java.awt.Dimension(
                textFieldBuscaNome.getWidth(), 120
        ));

        textFieldBuscaNome.getDocument().addDocumentListener(new DocumentListener() {
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
                String texto = textFieldBuscaNome.getText().trim();
                if (texto.isEmpty()) {
                    popupSugestoes.setVisible(false);
                    return;
                }

                List<String> sugestoes = buscarNoBanco(texto);
                atualizarSugestoes(sugestoes);
            }
        });

        sugestaoList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String selecionado = sugestaoList.getSelectedValue();
                    if (selecionado != null) {
                        // "Nome - CPF"
                        String[] partes = selecionado.split(" - ");
                        if (partes.length == 2) {
                            textFieldBuscaNome.setText(partes[1]); // só CPF
                        } else {
                            textFieldBuscaNome.setText(selecionado);
                        }
                        popupSugestoes.setVisible(false);
                    }
                }
            }
        });

    }

    private void atualizarSugestoes(List<String> sugestoes) {
        listModel.clear();
        if (!sugestoes.isEmpty()) {
            for (String s : sugestoes) {
                listModel.addElement(s);
            }

            int rowHeight = sugestaoList.getFixedCellHeight() > 0 ? sugestaoList.getFixedCellHeight() : 40;
            int altura = Math.min(sugestoes.size() * rowHeight, 150);
            scrollPane.setPreferredSize(new java.awt.Dimension(textFieldBuscaNome.getWidth(), altura));

            popupSugestoes.show(textFieldBuscaNome, 0, textFieldBuscaNome.getHeight());
            textFieldBuscaNome.requestFocusInWindow();
        } else {
            popupSugestoes.setVisible(false);
        }
    }

    private List<String> buscarNoBanco(String texto) {
        List<String> nomes = new ArrayList<>();
        try (Connection conn = myConnection.getConexao(); PreparedStatement stmt = conn.prepareStatement(
                "SELECT nome, cpf FROM paciente WHERE nome LIKE ? OR cpf LIKE ? LIMIT 10")) {

            stmt.setString(1, texto + "%");  // pesquisa por nome
            stmt.setString(2, texto + "%");  // pesquisa também por CPF
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                nomes.add(nome + " - " + cpf); // concatena nome + cpf
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            myConnection.closeConnection(conn, ps, rs);
        }
        return nomes;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JPanel();
        panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textFieldBuscaNome = new javax.swing.JTextField();
        buscaBotao = new javax.swing.JButton();
        desktopPaneHorarios = new javax.swing.JDesktopPane();

        panel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nome ou cpf do paciente");

        textFieldBuscaNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldBuscaNomeActionPerformed(evt);
            }
        });

        buscaBotao.setBackground(new java.awt.Color(0, 0, 0));
        buscaBotao.setForeground(new java.awt.Color(255, 255, 255));
        buscaBotao.setText("Consultar");
        buscaBotao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscaBotaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textFieldBuscaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(36, 36, 36)
                .addComponent(buscaBotao, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(481, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldBuscaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(buscaBotao, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout desktopPaneHorariosLayout = new javax.swing.GroupLayout(desktopPaneHorarios);
        desktopPaneHorarios.setLayout(desktopPaneHorariosLayout);
        desktopPaneHorariosLayout.setHorizontalGroup(
            desktopPaneHorariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        desktopPaneHorariosLayout.setVerticalGroup(
            desktopPaneHorariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 356, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(desktopPaneHorarios)
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desktopPaneLayout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desktopPaneHorarios))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldBuscaNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldBuscaNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldBuscaNomeActionPerformed

    private void buscaBotaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaBotaoActionPerformed

        try {
            if (conn == null || conn.isClosed()) {
                conn = myConnection.getConexao();
            }

            String sql = "SELECT p.nome, c.id_consulta "
                    + "FROM paciente p "
                    + "LEFT JOIN consulta c ON c.id_paciente = p.id_paciente "
                    + "WHERE p.cpf = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, textFieldBuscaNome.getText());
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt("id_consulta") == 0) {
                    JOptionPane.showInternalMessageDialog(this, rs.getString("nome") + " não tem consultas cadastradas");

                } else {

                    AlterarConsultaHorario frame = new AlterarConsultaHorario(textFieldBuscaNome.getText());
                    Dimension desktopSize = desktopPaneHorarios.getSize();
                    frame.setSize(desktopSize);
                    frame.setLocation(
                            (desktopSize.width - desktopPaneHorarios.getWidth()) / 2,
                            (desktopSize.height - desktopPaneHorarios.getHeight()) / 2
                    );

                    frame.setSize(desktopSize.width, desktopSize.height);
                    frame.setLocation(0, 0);
                    desktopPaneHorarios.add(frame);
                    // 3. Defina a localização da janela interna.
                    frame.setVisible(true);
                }

            } else {
                JOptionPane.showInternalMessageDialog(this, "Erro, nenhuma paciente encontrado");
            }

        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(this, "Erro ao conectar com o banco de dados");
        } finally {
            myConnection.closeConnection(conn, ps, rs);
        }
    }//GEN-LAST:event_buscaBotaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscaBotao;
    private javax.swing.JPanel desktopPane;
    private javax.swing.JDesktopPane desktopPaneHorarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField textFieldBuscaNome;
    // End of variables declaration//GEN-END:variables
}

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
import javax.swing.DefaultDesktopManager;
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
public class RemoverFun extends javax.swing.JInternalFrame {

    /**
     * Creates new form RemoverFun
     */
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    RemoveFunPane removeFunPane;

    // 🔹 Componentes para autocomplete
    private DefaultListModel<String> listModel;
    private JList<String> sugestaoList;
    private JScrollPane scrollPane;
    private JPopupMenu popupSugestoes;

    public RemoverFun() {
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
                textFieldUsuarioDemitir.getWidth(), 120
        ));

        // Listener para capturar digitação
        textFieldUsuarioDemitir.getDocument().addDocumentListener(new DocumentListener() {
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
                String texto = textFieldUsuarioDemitir.getText().trim();
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
                            textFieldUsuarioDemitir.setText(partes[1]); // coloca só o CPF no campo
                        } else {
                            textFieldUsuarioDemitir.setText(selecionado);
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
                    textFieldUsuarioDemitir.getWidth(),
                    altura
            ));

            popupSugestoes.show(textFieldUsuarioDemitir, 0, textFieldUsuarioDemitir.getHeight());
            popupSugestoes.show(textFieldUsuarioDemitir, 0, textFieldUsuarioDemitir.getHeight());
            textFieldUsuarioDemitir.requestFocusInWindow(); // 🔹 devolve o foco ao campo

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        textFieldUsuarioDemitir = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        desktopPaneRemove = new javax.swing.JDesktopPane();

        setBackground(new java.awt.Color(80, 80, 80));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        textFieldUsuarioDemitir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldUsuarioDemitirActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Procurar");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("CPF do paciente a excluir");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(textFieldUsuarioDemitir, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldUsuarioDemitir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPaneRemove, javax.swing.GroupLayout.DEFAULT_SIZE, 1214, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(desktopPaneRemove, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldUsuarioDemitirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUsuarioDemitirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUsuarioDemitirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        removeFunPane = new RemoveFunPane();
        desktopPaneRemove.add(removeFunPane);
        removeFunPane.setSize(desktopPaneRemove.getSize());
        removeFunPane.setLocation(0, 0);
        removeFunPane.setBounds(0, 0, desktopPaneRemove.getWidth(), desktopPaneRemove.getHeight());

        removeFunPane.show();

        try {

            conn = myConnection.getConexao();
            if (conn == null || conn.isClosed()) {
                JOptionPane.showInternalConfirmDialog(getDesktopPane(), "Impossivel estabelecer conexao...");
            } else {
                System.out.println("Conexao estabelecida com sucesso...");
            }

            String cpfConsulta = textFieldUsuarioDemitir.getText();

            //DADOS PESSOAIS E OS IDs
            String sqlFuncionarioDados = "SELECT * FROM paciente WHERE cpf = ?";
            ps = conn.prepareStatement(sqlFuncionarioDados);
            ps.setString(1, cpfConsulta);
            rs = ps.executeQuery();

            int idEndereco = -1;
            int idFuncionario = -1;
            int idDadosBancarios = -1;

            if (rs.next()) {
                idEndereco = Integer.parseInt(rs.getString("id_endereco"));
                idFuncionario = Integer.parseInt(rs.getString("id_paciente"));
                idDadosBancarios = Integer.parseInt(rs.getString("numero_conta_bancaria"));

                removeFunPane.setLabelNameInput(rs.getString("nome"));
                removeFunPane.setLabelCPFInput(rs.getString("cpf"));
                removeFunPane.setLabelDataNascimentoInput(rs.getString("data_nascimento"));
                removeFunPane.setLabelEstadoCivilInput(rs.getString("estado_civil"));
                removeFunPane.setLabelCargoEmpresarialInput(rs.getString("cargo"));
            }

            // ENDERECO
            String sqlEndereco = "SELECT * FROM endereco WHERE id_endereco = ?";
            ps = conn.prepareStatement(sqlEndereco);
            ps.setInt(1, idEndereco);

            rs = ps.executeQuery();
            if (rs.next()) {
                removeFunPane.setLabelCEPInput(rs.getString("cep"));
                removeFunPane.setLabelCidadeInput(rs.getString("cidade"));
                removeFunPane.setLabelBairroInput(rs.getString("bairro"));
                removeFunPane.setLabelRuaInput(rs.getString("rua"));
                removeFunPane.setLabelNumeroEnderecoInput(rs.getString("numero"));
                removeFunPane.setLabelComplementoEnderecoInput(rs.getString("complemento"));
            }

            // CONTATO
            String sqlContato = "SELECT * FROM contato WHERE id_paciente = ?";
            ps = conn.prepareStatement(sqlContato);
            ps.setInt(1, idFuncionario);

            rs = ps.executeQuery();
            if (rs.next()) {
                removeFunPane.setLabelTelefoneInput(rs.getString("telefone"));
                removeFunPane.setLabelEmailInput(rs.getString("email"));
            }

            // DADOS BANCÁRIOS
            String sqlDadosBancarios = "SELECT * FROM dados_bancarios WHERE numero_conta = ?";
            ps = conn.prepareStatement(sqlDadosBancarios);
            ps.setInt(1, idDadosBancarios);

            rs = ps.executeQuery();
            if (rs.next()) {
                removeFunPane.setLabelNumeroContaInput(rs.getString("numero_conta"));
                removeFunPane.setLabelNomeBancoInput(rs.getString("nome_banco"));
                removeFunPane.setLabelAgenciaInput(rs.getString("agencia"));
                removeFunPane.setLabelSalarioInput(rs.getString("salario"));
            }

            textFieldUsuarioDemitir.setText("");

        } catch (SQLException e) {
            JOptionPane.showInternalConfirmDialog(getDesktopPane(), "Erro com banco de dados" + e.getMessage());
        } finally {
            myConnection.closeConnection(conn, ps);
        }

    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPaneRemove;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField textFieldUsuarioDemitir;
    // End of variables declaration//GEN-END:variables
}

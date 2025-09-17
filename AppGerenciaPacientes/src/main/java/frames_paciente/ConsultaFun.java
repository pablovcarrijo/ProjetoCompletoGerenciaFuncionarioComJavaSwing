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
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.connector.myConnection;

public class ConsultaFun extends javax.swing.JInternalFrame {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    ConsultaFunPane consultaFunPane = null;

    // 🔹 Componentes para autocomplete
    private DefaultListModel<String> listModel;
    private JList<String> sugestaoList;
    private JScrollPane scrollPane;
    private JPopupMenu popupSugestoes;

    public ConsultaFun() {
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
                textFieldConsultaFun.getWidth(), 120
        ));

        // Listener para capturar digitação
        textFieldConsultaFun.getDocument().addDocumentListener(new DocumentListener() {
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
                String texto = textFieldConsultaFun.getText().trim();
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
                            textFieldConsultaFun.setText(partes[1]); // coloca só o CPF no campo
                        } else {
                            textFieldConsultaFun.setText(selecionado);
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
                    textFieldConsultaFun.getWidth(),
                    altura
            ));

            popupSugestoes.show(textFieldConsultaFun, 0, textFieldConsultaFun.getHeight());
            popupSugestoes.show(textFieldConsultaFun, 0, textFieldConsultaFun.getHeight());
            textFieldConsultaFun.requestFocusInWindow(); // 🔹 devolve o foco ao campo

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
        textFieldConsultaFun = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        desktopPaneConsulta = new javax.swing.JDesktopPane();
        buttonConsulta = new javax.swing.JButton();

        setBackground(new java.awt.Color(80, 80, 80));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("CPF para consulta");

        desktopPaneConsulta.setLayout(new javax.swing.BoxLayout(desktopPaneConsulta, javax.swing.BoxLayout.LINE_AXIS));

        buttonConsulta.setBackground(new java.awt.Color(51, 51, 51));
        buttonConsulta.setForeground(new java.awt.Color(255, 255, 255));
        buttonConsulta.setText("Consultar");
        buttonConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConsultaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(textFieldConsultaFun, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(buttonConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(805, Short.MAX_VALUE))
            .addComponent(desktopPaneConsulta, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldConsultaFun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buttonConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(desktopPaneConsulta))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConsultaActionPerformed

        if(consultaFunPane != null){
            consultaFunPane.dispose();
        }
        consultaFunPane = new ConsultaFunPane();
        desktopPaneConsulta.add(consultaFunPane);
        consultaFunPane.setSize(desktopPaneConsulta.getSize());
        consultaFunPane.setLocation(0, 0);
        consultaFunPane.setBounds(0, 0, desktopPaneConsulta.getWidth(), desktopPaneConsulta.getHeight());

        consultaFunPane.show();

        try {

            conn = myConnection.getConexao();
            if (conn == null || conn.isClosed()) {
                System.out.println("Impossivel estabelecer conexao...");
            } else {
                System.out.println("Conexao estabelecida com sucesso...");
            }

            String nameConsulta = textFieldConsultaFun.getText();

            //DADOS PESSOAIS E OS IDs
            String sqlFuncionarioDados = "SELECT * FROM paciente WHERE cpf = ?";
            ps = conn.prepareStatement(sqlFuncionarioDados);
            ps.setString(1, nameConsulta);
            rs = ps.executeQuery();

            int idEndereco = -1;
            int idFuncionario = -1;
            int idDadosBancarios = -1;

            if (rs.next()) {
                idEndereco = Integer.parseInt(rs.getString("id_endereco"));
                idFuncionario = Integer.parseInt(rs.getString("id_paciente"));
                idDadosBancarios = Integer.parseInt(rs.getString("numero_conta_bancaria"));

                consultaFunPane.setLabelNameInput(rs.getString("nome"));
                consultaFunPane.setLabelCPFInput(rs.getString("cpf"));
                consultaFunPane.setLabelDataNascimentoInput(rs.getString("data_nascimento"));
                consultaFunPane.setLabelEstadoCivilInput(rs.getString("estado_civil"));
                consultaFunPane.setLabelCargoEmpresarialInput(rs.getString("cargo"));
            }

            // ENDERECO
            String sqlEndereco = "SELECT * FROM endereco WHERE id_endereco = ?";
            ps = conn.prepareStatement(sqlEndereco);
            ps.setInt(1, idEndereco);

            rs = ps.executeQuery();
            if (rs.next()) {
                consultaFunPane.setLabelCEPInput(rs.getString("cep"));
                consultaFunPane.setLabelCidadeInput(rs.getString("cidade"));
                consultaFunPane.setLabelBairroInput(rs.getString("bairro"));
                consultaFunPane.setLabelRuaInput(rs.getString("rua"));
                consultaFunPane.setLabelNumeroEnderecoInput(rs.getString("numero"));
                consultaFunPane.setLabelComplementoEnderecoInput(rs.getString("complemento"));
            }

            // CONTATO
            String sqlContato = "SELECT * FROM contato WHERE id_paciente = ?";
            ps = conn.prepareStatement(sqlContato);
            ps.setInt(1, idFuncionario);

            rs = ps.executeQuery();
            if (rs.next()) {
                consultaFunPane.setLabelTelefoneInput(rs.getString("telefone"));
                consultaFunPane.setLabelEmailInput(rs.getString("email"));
            }

            // DADOS BANCÁRIOS
            String sqlDadosBancarios = "SELECT * FROM dados_bancarios WHERE numero_conta = ?";
            ps = conn.prepareStatement(sqlDadosBancarios);
            ps.setInt(1, idDadosBancarios);

            rs = ps.executeQuery();
            if (rs.next()) {
                consultaFunPane.setLabelNumeroContaInput(rs.getString("numero_conta"));
                consultaFunPane.setLabelNomeBancoInput(rs.getString("nome_banco"));
                consultaFunPane.setLabelAgenciaInput(rs.getString("agencia"));
                consultaFunPane.setLabelSalarioInput(rs.getString("salario"));
            }

            textFieldConsultaFun.setText("");

        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(getDesktopPane(), "Erro com banco de dados" + e.getMessage());
        } finally {
            myConnection.closeConnection(conn, ps);
        }

    }//GEN-LAST:event_buttonConsultaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonConsulta;
    private javax.swing.JDesktopPane desktopPaneConsulta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField textFieldConsultaFun;
    // End of variables declaration//GEN-END:variables
}

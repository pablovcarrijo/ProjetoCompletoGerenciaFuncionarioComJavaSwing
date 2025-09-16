package frames_consulta;

import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.connector.myConnection;

public class AddConsulta extends javax.swing.JInternalFrame {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private DefaultListModel<String> listModel;
    private JList<String> sugestaoList;
    private JScrollPane scrollPane;
    private JPopupMenu popupSugestoes;

    private int crmAtual = -1; // guarda o CRM do médico selecionado
    private String[][] datasTabela = new String[5][6];
    private int[][] pacientesTabela = new int[5][6];
    private String nomeSelecionado = null;

    public AddConsulta() {
        initComponents();
        this.setBorder(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        configurarAutoComplete();

        tableHorarios.setVisible(false);
        jScrollPane2.setVisible(false);

        //Adicionando as especialidades e os médicos nos campos após selecionar
        comboBox1.addActionListener(e -> {
            String especialidadeSelecionada = (String) comboBox1.getSelectedItem();
            if (especialidadeSelecionada != null && !especialidadeSelecionada.equals("Selecione")) {
                carregarMedicos(especialidadeSelecionada);

            } else {
                medicosList.setModel(new DefaultListModel<>()); 
            }
        });
        carregarEspecialidades();

        // duplo clique no médico -> carrega agenda
        medicosList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 1) {
                    String nomeMedico = medicosList.getSelectedValue();
                    try {

                        if (conn == null || conn.isClosed()) {
                            conn = myConnection.getConexao();
                        }

                        ps = conn.prepareStatement("SELECT CRM FROM medico WHERE nome = ?");

                        ps.setString(1, nomeMedico);
                        rs = ps.executeQuery();

                        if (rs.next()) {
                            crmAtual = rs.getInt("CRM");
                            tableHorarios.setVisible(true);
                            jScrollPane2.setVisible(true);
                            desktopPane.revalidate();
                            desktopPane.repaint();
                            mostrarAgendaMedico(crmAtual);
                        }

                    } catch (SQLException e) {
                        JOptionPane.showInternalMessageDialog(getDesktopPane(), "Erro ao buscar CRM: " + e.getMessage());
                    } finally {
                        myConnection.closeConnection(conn, ps, rs);
                    }
                }
            }
        });

        // pinta a tabela de verde/vermelho
        tableHorarios.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                if (column > 0 && value != null) {
                    int status = Integer.parseInt(value.toString());
                    if (status == 1) {
                        c.setBackground(java.awt.Color.GREEN); // disponível
                        c.setForeground(java.awt.Color.GREEN);
                    } else {
                        // ocupado: vermelho se for o paciente atual, cinza se for outro
                        int idPacienteCelula = pacientesTabela[row][column];
                        int idPacienteAtual = getIdPacienteAtual();

                        if (idPacienteCelula == idPacienteAtual) {
                            c.setBackground(java.awt.Color.RED);
                            c.setForeground(java.awt.Color.RED);
                        } else {
                            c.setBackground(java.awt.Color.LIGHT_GRAY);
                            c.setForeground(java.awt.Color.LIGHT_GRAY);
                        }
                    }
                } else {
                    c.setBackground(java.awt.Color.WHITE);
                    c.setForeground(java.awt.Color.BLACK);
                }
                return c;
            }
        });

        // clique no horário
        tableHorarios.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tableHorarios.getSelectedRow();
                int col = tableHorarios.getSelectedColumn();

                if (col > 0 && crmAtual != -1) {
                    Object val = tableHorarios.getValueAt(row, col);
                    if (val != null && Integer.parseInt(val.toString()) == 1) {
                        String hora = tableHorarios.getValueAt(row, 0).toString();
                        int confirmar = JOptionPane.showInternalConfirmDialog(getDesktopPane(),
                                "Deseja agendar este horário (" + hora + ")?");

                        if (confirmar == JOptionPane.YES_OPTION) {
                            agendarConsulta(crmAtual, row, col);
                            mostrarAgendaMedico(crmAtual); // recarregar tabela
                        }
                    } else {
                        JOptionPane.showInternalMessageDialog(getDesktopPane(), "Horário ocupado!");
                    }
                }
            }
        });
    }

    //---------------------------------------------
    //CÓDIGO RELACIONADO A PESQUISA DE ESPECIALIDADE E MÉDICO
    //---------------------------------------------   
    // carregar especialidades no comboBox
    private void carregarEspecialidades() {
        try {

            if (conn == null || conn.isClosed()) {
                conn = myConnection.getConexao();
            }
            ps = conn.prepareStatement("SELECT especialidade FROM especialidade");
            rs = ps.executeQuery();

            while (rs.next()) {
                comboBox1.addItem(rs.getString("especialidade"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(getDesktopPane(), "Erro ao carregar especialidades: " + e.getMessage());
        } finally {
            myConnection.closeConnection(conn, ps, rs);
        }
    }

    // carrega médicos no list
    private void carregarMedicos(String especialidade) {
        DefaultListModel<String> model = new DefaultListModel<>();
        medicosList.setModel(model);
        try {
            if (conn == null || conn.isClosed()) {
                conn = myConnection.getConexao();
            }
            String sql = "SELECT nome FROM medico m "
                    + "INNER JOIN especialidade e ON m.id_especialidade = e.id_especialidade "
                    + "WHERE e.especialidade = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, especialidade);
            rs = ps.executeQuery();

            while (rs.next()) {
                model.addElement(rs.getString("nome"));
            }

        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(getDesktopPane(), "Erro ao carregar médicos...");
        } finally {
            myConnection.closeConnection(conn, ps, rs);
        }
    }

    //---------------------------------------------
    //CÓDIGOS RELACIONADOS A TABELA DE CONSULTAR
    //---------------------------------------------
    // agenda consulta no banco
    private void agendarConsulta(int crm, int row, int col) {
        try {
            String hora = tableHorarios.getValueAt(row, 0).toString();
            String dataSelecionada = datasTabela[row][col]; // <- pega a data da célula clicada

            if (dataSelecionada == null) {
                JOptionPane.showInternalMessageDialog(getDesktopPane(),
                        "Não foi possível identificar a data deste horário.");
                return;
            }

            if (conn == null || conn.isClosed()) {
                conn = myConnection.getConexao();
            }

            // agora busca pelo CRM + hora + data
            String sql = "SELECT a.id_agenda FROM agenda a "
                    + "WHERE a.CRM = ? AND a.hora_agenda = ? AND a.data_agenda = ?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, crm);
            ps.setString(2, hora + ":00");
            ps.setString(3, dataSelecionada);
            rs = ps.executeQuery();

            if (rs.next()) {
                int idAgenda = rs.getInt("id_agenda");
                String paciente = textPaciente.getText();
                int idPaciente = -1;

                PreparedStatement stmtPac = conn.prepareStatement(
                        "SELECT id_paciente FROM paciente WHERE nome = ?");
                stmtPac.setString(1, paciente);
                ResultSet rsPac = stmtPac.executeQuery();
                if (rsPac.next()) {
                    idPaciente = rsPac.getInt("id_paciente");
                }

                //Verifica se o paciente já tem consulta com o médico
                PreparedStatement stmtCheck = conn.prepareStatement(
                        "SELECT COUNT(*) FROM consulta c "
                        + "JOIN agenda a ON c.id_agenda = a.id_agenda "
                        + "WHERE c.id_paciente = ? AND a.CRM = ?");
                stmtCheck.setInt(1, idPaciente);
                stmtCheck.setInt(2, crm); // crm do médico selecionado
                ResultSet rsCheck = stmtCheck.executeQuery();

                if (rsCheck.next() && rsCheck.getInt(1) > 0) {
                    JOptionPane.showInternalMessageDialog(getDesktopPane(),
                            "Este paciente já possui uma consulta marcada com este médico.");
                    return; // cancela o agendamento
                }

                if (idPaciente != -1) {
                    // insere consulta
                    PreparedStatement stmtInsert = conn.prepareStatement(
                            "INSERT INTO consulta (id_agenda, id_paciente) VALUES (?, ?)");
                    stmtInsert.setInt(1, idAgenda);
                    stmtInsert.setInt(2, idPaciente);
                    stmtInsert.executeUpdate();

                    // marca horário como ocupado
                    PreparedStatement stmtUpdate = conn.prepareStatement(
                            "UPDATE agenda SET disponivel = 0 WHERE id_agenda = ?");
                    stmtUpdate.setInt(1, idAgenda);
                    stmtUpdate.executeUpdate();

                    JOptionPane.showInternalMessageDialog(getDesktopPane(),
                            "Consulta agendada com sucesso!");
                } else {
                    JOptionPane.showInternalMessageDialog(getDesktopPane(),
                            "Paciente não encontrado.");
                }
            }

        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(getDesktopPane(),
                    "Erro ao agendar: " + e.getMessage());
        } finally {
            myConnection.closeConnection(conn, ps, rs);
        }
    }

    // carregar agenda do médico
    private void mostrarAgendaMedico(int crm) {
        try {
            if (conn == null || conn.isClosed()) {
                conn = myConnection.getConexao();
            }

            String sql = "SELECT a.id_agenda, a.data_agenda, a.hora_agenda, a.disponivel, c.id_paciente "
                    + "FROM agenda a "
                    + "LEFT JOIN consulta c ON a.id_agenda = c.id_agenda "
                    + "WHERE a.CRM = ? "
                    + "ORDER BY a.data_agenda, a.hora_agenda";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, crm);
            rs = ps.executeQuery();

            DefaultTableModel model = (DefaultTableModel) tableHorarios.getModel();

            // limpa antes do while
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 1; j < model.getColumnCount(); j++) {
                    model.setValueAt(null, i, j);
                    datasTabela[i][j] = null;
                    pacientesTabela[i][j] = 0;
                }
            }

            while (rs.next()) {
                String hora = rs.getString("hora_agenda").substring(0, 5);
                String data = rs.getString("data_agenda");
                int status = rs.getInt("disponivel");
                int idPacienteConsulta = rs.getInt("id_paciente");

                LocalDate date = LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                DayOfWeek dow = date.getDayOfWeek();

                int col = switch (dow) {
                    case MONDAY ->
                        1;
                    case TUESDAY ->
                        2;
                    case WEDNESDAY ->
                        3;
                    case THURSDAY ->
                        4;
                    case FRIDAY ->
                        5;
                    default ->
                        -1;
                };

                if (col != -1) {
                    for (int i = 0; i < model.getRowCount(); i++) {
                        if (hora.equals(model.getValueAt(i, 0))) {
                            model.setValueAt(status, i, col);
                            datasTabela[i][col] = data;
                            pacientesTabela[i][col] = idPacienteConsulta; // aqui salva
                        }
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(getDesktopPane(), "Erro ao carregar agenda: " + e.getMessage());
        } finally {
            myConnection.closeConnection(conn, ps, rs);
        }
    }

    private int getIdPacienteAtual() {
        try (Connection conn = myConnection.getConexao(); PreparedStatement stmt = conn.prepareStatement("SELECT id_paciente FROM paciente WHERE nome = ?")) {
            stmt.setString(1, textPaciente.getText());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_paciente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    //---------------------------------------------
    //CÓDIGO RELACIONADO AO AUTOCOMPLETE ENQUANTO DIGITA O NOME
    //---------------------------------------------
    private void configurarAutoComplete() {
        listModel = new DefaultListModel<>();
        sugestaoList = new JList<>(listModel);
        scrollPane = new JScrollPane(sugestaoList);

        popupSugestoes = new JPopupMenu();
        popupSugestoes.setBorder(null);
        popupSugestoes.add(scrollPane);
        scrollPane.setPreferredSize(new java.awt.Dimension(
                textPaciente.getWidth(), 120
        ));

        textPaciente.getDocument().addDocumentListener(new DocumentListener() {
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
                String texto = textPaciente.getText().trim();
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
                            textPaciente.setText(partes[0]); // mostra só o nome
                            nomeSelecionado = partes[0];     // guarda também o nome
                        } else {
                            textPaciente.setText(selecionado);
                            nomeSelecionado = selecionado;
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
            scrollPane.setPreferredSize(new java.awt.Dimension(textPaciente.getWidth(), altura));

            popupSugestoes.show(textPaciente, 0, textPaciente.getHeight());
            textPaciente.requestFocusInWindow();
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
        jLabel1 = new javax.swing.JLabel();
        textPaciente = new javax.swing.JTextField();
        comboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        medicosList = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableHorarios = new javax.swing.JTable();

        desktopPane.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nome ou cpf do paciente");

        comboBox1.setForeground(new java.awt.Color(0, 0, 0));
        comboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Especialidade");

        medicosList.setForeground(new java.awt.Color(0, 0, 0));
        medicosList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Médicos", " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(medicosList);

        tableHorarios.setForeground(new java.awt.Color(0, 0, 0));
        tableHorarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"08:00", null, null, null, null, null},
                {"09:00", null, null, null, null, null},
                {"10:00", null, null, null, null, null},
                {"11:00", null, null, null, null, null},
                {"12:00", null, null, null, null, null}
            },
            new String [] {
                "Horários", "Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira"
            }
        ));
        tableHorarios.setRowHeight(40);
        tableHorarios.setShowGrid(true);
        jScrollPane2.setViewportView(tableHorarios);

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desktopPaneLayout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addGroup(desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(desktopPaneLayout.createSequentialGroup()
                        .addGroup(desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(textPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(219, Short.MAX_VALUE))
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desktopPaneLayout.createSequentialGroup()
                .addGroup(desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(desktopPaneLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(desktopPaneLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(79, 79, 79)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(135, Short.MAX_VALUE))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBox1;
    private javax.swing.JPanel desktopPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> medicosList;
    private javax.swing.JTable tableHorarios;
    private javax.swing.JTextField textPaciente;
    // End of variables declaration//GEN-END:variables
}

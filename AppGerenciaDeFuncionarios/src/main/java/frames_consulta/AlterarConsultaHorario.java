/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package frames_consulta;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.connector.myConnection;

/**
 *
 * @author PabloCarrijo
 */
public class AlterarConsultaHorario extends javax.swing.JInternalFrame {

    /**
     * Creates new form AlterarConsultaHorario
     */
    private String cpf;
    private String nome;
    private int crmAtual = -1; // guarda o CRM do médico selecionado
    private String[][] datasTabela = new String[5][6];
    private int[][] pacientesTabela = new int[5][6]; // guarda id_paciente de cada célula

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private PreparedStatement ps2 = null;
    private ResultSet rs2 = null;

    public AlterarConsultaHorario(String cpf) {
        initComponents();
        this.setBorder(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);

        try {
            if (conn == null || conn.isClosed()) {
                conn = myConnection.getConexao();
            }
            String sql = "SELECT nome FROM paciente WHERE cpf = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, cpf);
            rs = ps.executeQuery();

            if (rs.next()) {
                this.nome = rs.getString("nome");
                textFieldPaciente.setText(nome);
            }
        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(this, "Erro inesperados");
        }

        textFieldPaciente.setEditable(false);

        carregarEspecialidades();

        //Adicionando as especialidades e os médicos nos campos após selecionar
        comboBox1.addActionListener(e -> {
            String especialidadeSelecionada = (String) comboBox1.getSelectedItem();
            if (especialidadeSelecionada != null && !especialidadeSelecionada.equals("Selecione")) {
                carregarMedicos(especialidadeSelecionada);
            } else {
                DefaultListModel<String> vazio = new DefaultListModel<>();
                medicosList.setModel(vazio);
            }
        });

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

        // Carrega as informações do médico e da sua especialidade
        try {
            if (conn == null || conn.isClosed()) {
                conn = myConnection.getConexao();
            }

            String sql = "SELECT m.nome AS medico, e.especialidade AS especialidade "
                    + "FROM consulta c "
                    + "INNER JOIN agenda a ON c.id_agenda = a.id_agenda "
                    + "INNER JOIN medico m ON a.CRM = m.CRM "
                    + "INNER JOIN especialidade e ON m.id_especialidade = e.id_especialidade "
                    + "INNER JOIN paciente p ON c.id_paciente = p.id_paciente "
                    + "WHERE p.nome = ? "
                    + "ORDER BY c.id_consulta DESC LIMIT 1";

            ps = conn.prepareStatement(sql);
            ps.setString(1, this.nome);
            rs = ps.executeQuery();

            if (rs.next()) {
                String especialidade = rs.getString("especialidade");
                String medico = rs.getString("medico");

                comboBox1.setSelectedItem(especialidade);
                carregarMedicos(especialidade);
                medicosList.setSelectedValue(medico, true);

                // mostra agenda
                try {
                    if (conn == null || conn.isClosed()) {
                        conn = myConnection.getConexao();
                    }
                    ps2 = conn.prepareStatement("SELECT CRM FROM medico WHERE nome = ?");

                    ps2.setString(1, medico);
                    rs2 = ps2.executeQuery();
                    if (rs2.next()) {
                        crmAtual = rs2.getInt("CRM");
                        mostrarAgendaMedico(crmAtual);
                    }
                } catch (SQLException e) {
                    JOptionPane.showInternalMessageDialog(this, "Erro inesperado");
                } finally {
                    myConnection.closeConnection(conn, ps2, rs2);
                }
            } else {
                JOptionPane.showInternalMessageDialog(getDesktopPane(),
                        "Paciente " + nome + " não possui consultas cadastradas.");
            }

        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(getDesktopPane(), "Erro ao buscar dados da consulta: " + e.getMessage());
        } finally {
            myConnection.closeConnection(conn, ps, rs);
        }

        // pinta a tabela de verde/vermelho
        tableHorarios.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (column > 0 && value != null) {
                    int status = Integer.parseInt(value.toString());

                    if (status == 1) {
                        // Disponível -> verde
                        c.setBackground(Color.GREEN);
                        c.setForeground(Color.GREEN);
                    } else {
                        // Ocupado -> vermelho se for o paciente atual, cinza se for outro
                        int idPacienteCelula = pacientesTabela[row][column];
                        if (idPacienteCelula == getIdPacienteAtual()) {
                            c.setBackground(Color.RED);   // consulta do paciente pesquisado
                            c.setForeground(Color.RED);
                        } else {
                            c.setBackground(Color.LIGHT_GRAY); // consulta de outro paciente
                            c.setForeground(Color.LIGHT_GRAY);
                        }
                    }
                } else {
                    // Cabeçalho (coluna de horas)
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });

        // clique no horário para agendar o horário
        tableHorarios.addMouseListener(
                new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt
            ) {
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
        }
        );

    }
    //-------------------------------
    // CARREGA ESPECIALIDADE E MÉDICOS NO LIST E NO COMBO BOX
    //-------------------------------
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

    // carrega os médicos no list
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

    //-------------------------------
    // CARREGA AGENDA DO MÉDICO SELECIONADO E FAZ AGENDA PARA CONSULTA
    //-------------------------------
    // Carrega a agenda do médico
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
                    pacientesTabela[i][j] = 0; // limpa também os pacientes
                }
            }

            while (rs.next()) {
                String hora = rs.getString("hora_agenda").substring(0, 5);
                String data = rs.getString("data_agenda");
                int status = rs.getInt("disponivel");
                int idPacienteConsulta = rs.getInt("id_paciente"); // 0 se não houver consulta

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
                            pacientesTabela[i][col] = idPacienteConsulta; // <-- agora preenche
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

    private void agendarConsulta(int crm, int row, int col) {
        try {
            // Pega a hora da linha da tabela (coluna 0 sempre guarda a hora, ex: "08:00")
            String hora = tableHorarios.getValueAt(row, 0).toString();

            // Pega a data correspondente à célula clicada
            String dataSelecionada = datasTabela[row][col];

            // Se a célula não tiver data, não é possível marcar
            if (dataSelecionada == null) {
                JOptionPane.showInternalMessageDialog(getDesktopPane(),
                        "Não foi possível identificar a data deste horário.");
                return;
            }

            // Garante que a conexão está aberta
            if (conn == null || conn.isClosed()) {
                conn = myConnection.getConexao();
            }

            // 1. Busca o id_agenda do horário selecionado (data + hora + médico)
            String sql = "SELECT a.id_agenda FROM agenda a "
                    + "WHERE a.CRM = ? AND a.hora_agenda = ? AND a.data_agenda = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, crm);                // médico atual
            ps.setString(2, hora + ":00");    // hora formatada
            ps.setString(3, dataSelecionada); // data
            rs = ps.executeQuery();

            if (rs.next()) {
                int idAgendaNovo = rs.getInt("id_agenda"); // novo horário a ser marcado

                // 2. Busca o id_paciente pelo nome do paciente
                int idPaciente = -1;
                PreparedStatement stmtPac = conn.prepareStatement(
                        "SELECT id_paciente FROM paciente WHERE nome = ?"
                );
                stmtPac.setString(1, nome);
                ResultSet rsPac = stmtPac.executeQuery();
                if (rsPac.next()) {
                    idPaciente = rsPac.getInt("id_paciente");
                }

                if (idPaciente != -1) {
                    // 3. Descobre a consulta atual desse paciente com este médico
                    int idConsultaAtual = -1;
                    int idAgendaAntigo = -1;

                    PreparedStatement stmtBuscaConsulta = conn.prepareStatement(
                            "SELECT c.id_consulta, c.id_agenda "
                            + "FROM consulta c "
                            + "JOIN agenda a ON c.id_agenda = a.id_agenda "
                            + "WHERE c.id_paciente = ? AND a.CRM = ? "
                            + "ORDER BY c.id_consulta DESC LIMIT 1"
                    );
                    stmtBuscaConsulta.setInt(1, idPaciente); // paciente
                    stmtBuscaConsulta.setInt(2, crm);        // médico
                    ResultSet rsConsulta = stmtBuscaConsulta.executeQuery();

                    if (rsConsulta.next()) {
                        idConsultaAtual = rsConsulta.getInt("id_consulta"); // consulta específica
                        idAgendaAntigo = rsConsulta.getInt("id_agenda");   // horário antigo
                    }

                    // Se não achou nenhuma consulta, não dá para alterar
                    if (idConsultaAtual == -1) {
                        JOptionPane.showInternalMessageDialog(getDesktopPane(),
                                "Nenhuma consulta encontrada para este paciente neste médico.");
                        return;
                    }

                    // 4. Atualiza a consulta para o novo horário
                    PreparedStatement stmtUpdateConsulta = conn.prepareStatement(
                            "UPDATE consulta SET id_agenda = ? WHERE id_consulta = ?"
                    );
                    stmtUpdateConsulta.setInt(1, idAgendaNovo);   // novo horário
                    stmtUpdateConsulta.setInt(2, idConsultaAtual); // consulta exata
                    stmtUpdateConsulta.executeUpdate();

                    // 5. Libera o horário antigo (fica disponível novamente)
                    if (idAgendaAntigo != -1) {
                        PreparedStatement stmtLibera = conn.prepareStatement(
                                "UPDATE agenda SET disponivel = 1 WHERE id_agenda = ?"
                        );
                        stmtLibera.setInt(1, idAgendaAntigo);
                        stmtLibera.executeUpdate();
                    }

                    // 6. Ocupa o novo horário (marca como indisponível)
                    PreparedStatement stmtOcupar = conn.prepareStatement(
                            "UPDATE agenda SET disponivel = 0 WHERE id_agenda = ?"
                    );
                    stmtOcupar.setInt(1, idAgendaNovo);
                    stmtOcupar.executeUpdate();

                    // 7. Mensagem de sucesso
                    JOptionPane.showInternalMessageDialog(getDesktopPane(),
                            "Consulta alterada com sucesso!");
                } else {
                    // Caso o paciente não seja encontrado
                    JOptionPane.showInternalMessageDialog(getDesktopPane(),
                            "Paciente não encontrado.");
                }
            }

        } catch (SQLException e) {
            // Trata qualquer erro de SQL
            JOptionPane.showInternalMessageDialog(getDesktopPane(),
                    "Erro ao alterar consulta: " + e.getMessage());
        } finally {
            // Fecha recursos (boa prática)
            myConnection.closeConnection(conn, ps, rs);
        }
    }

    private int getIdPacienteAtual() {
        try (Connection conn = myConnection.getConexao(); PreparedStatement stmt = conn.prepareStatement("SELECT id_paciente FROM paciente WHERE nome = ?")) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_paciente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textFieldPaciente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        comboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        medicosList = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableHorarios = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Paciente");

        textFieldPaciente.setBackground(new java.awt.Color(102, 102, 102));
        textFieldPaciente.setForeground(new java.awt.Color(255, 255, 255));
        textFieldPaciente.setCaretColor(new java.awt.Color(255, 255, 255));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Especialidade");

        comboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        medicosList.setForeground(new java.awt.Color(0, 0, 0));
        medicosList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Medicos" };
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(145, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(155, 155, 155)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 766, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(229, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(189, Short.MAX_VALUE))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> medicosList;
    private javax.swing.JTable tableHorarios;
    private javax.swing.JTextField textFieldPaciente;
    // End of variables declaration//GEN-END:variables
}

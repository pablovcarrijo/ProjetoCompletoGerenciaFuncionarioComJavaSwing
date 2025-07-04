/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package frames;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.connector.myConnection;

/**
 *
 * @author PabloCarrijo
 */
public class AlterarFunContato extends javax.swing.JInternalFrame {

    /**
     * Creates new form AlterarFunContato
     */
    private Connection conn = null;
    private PreparedStatement ps = null;
    private String nameConsulta;    
    
    public AlterarFunContato(String nameConsulta) {
        initComponents();
        this.setBorder(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.nameConsulta = nameConsulta;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textFieldAlterarTelefone = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        textFieldAlterarEmail = new javax.swing.JTextField();

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Email :");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Telefone :");

        jButton1.setText("Alterar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        textFieldAlterarEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldAlterarEmailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textFieldAlterarEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                    .addComponent(textFieldAlterarTelefone))
                .addGap(82, 82, 82)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(377, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldAlterarEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(textFieldAlterarTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(379, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldAlterarEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldAlterarEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldAlterarEmailActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        try{
            
            if(conn == null || conn.isClosed()){
                conn = myConnection.getConexao();
            }
            else if(conn != null || !conn.isClosed()){
                myConnection.closeConnection(conn, ps);
                conn = myConnection.getConexao();
            }
            
            String SQL = "UPDATE funcionarios SET "
                    + "email = ?, telefone = ? "
                    + "WHERE nome = ?";
            
            ps = conn.prepareStatement(SQL);
            
            ps.setString(1, textFieldAlterarEmail.getText());
            ps.setString(2, textFieldAlterarTelefone.getText());
            ps.setString(3, nameConsulta);
            
            int n = ps.executeUpdate();
            
            if(n > 0){
                JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso");
            }
            else{
                JOptionPane.showMessageDialog(null, "Erro ao atualizar usuário");
            }
            
        }
        catch(SQLException e){
            System.out.println("Erro ao conectar com o banco " + e.getMessage());
        }
        finally{
            myConnection.closeConnection(conn, ps);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    public JTextField getTextFieldAlterarEmail() {
        return textFieldAlterarEmail;
    }

    public void setTextFieldAlterarEmail(String value) {
        textFieldAlterarEmail.setText(value);
    }

    public JTextField getTextFieldAlterarTelefone() {
        return textFieldAlterarTelefone;
    }

    public void setTextFieldAlterarTelefone(String value) {
        textFieldAlterarTelefone.setText(value);
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField textFieldAlterarEmail;
    private javax.swing.JTextField textFieldAlterarTelefone;
    // End of variables declaration//GEN-END:variables
}

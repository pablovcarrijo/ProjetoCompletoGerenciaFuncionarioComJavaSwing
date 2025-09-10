/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author PabloCarrijo
 */
public class myConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/sistema_medico?zeroDateTimeBehaviour=CONVERT_TO_NULL";
    private static final String USUARIO = "root";
    private static final String SENHA = "mysql";
    
    public static Connection getConexao() {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados!\n" + erro.getMessage(), "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        }
        return conexao;
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao fechar a conexão com o banco de dados!");
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {
        closeConnection(con);
        try {
            if (stmt != null) {
                stmt.close();
                System.out.println("Statement fechado.");
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao fechar o PreparedStatement!");
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        closeConnection(con, stmt);
        try {
            if (rs != null) {
                rs.close();
                System.out.println("ResultSet fechado.");
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao fechar o ResultSet!");
        }
    }

    public static Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

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

    private static final String URL = "jdbc:mysql://localhost:3306/empresa";
    private static final String USUARIO = "developer";
    private static final String SENHA = "010805";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConexao() {
        Connection conexao = null;
        try {
            Class.forName(DRIVER);
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Estabelecida conexão com Banco de Dados");
            //JOptionPane.showMessageDialog(null, "Estabelecida conexão com Banco de Dados");
        } catch (ClassNotFoundException erro) {
            System.out.println("Driver JDBC não encontrado!");
            //JOptionPane.showMessageDialog(null, "Driver JDBC não encontrado!", "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException erro) {
            System.out.println("Erro ao conectar com o banco de dados!");
            //JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados!\n" + erro.getMessage(), "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
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

    // O método conectar() parece redundante, pois getConexao() já faz a conexão.
    // Se você realmente precisar de um método separado, ele faria a mesma coisa que getConexao().
    public static Connection conectar() {
        return getConexao();
    }

    // O método fecharConexao(Connection conexao) é um alias para closeConnection(Connection con).
    public static void fecharConexao(Connection conexao) {
        closeConnection(conexao);
    }

    public static Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

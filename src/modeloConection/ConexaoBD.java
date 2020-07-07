/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloConection;

import java.sql.*;
import javax.swing.JOptionPane;



/**
 *
 * @author Note
 */
public class ConexaoBD {
    
    public Statement stm;
    public ResultSet rs;
    private final String driver = "org.postgresql.Driver";
    private final String caminho = "jdbc:postgresql://localhost:5432/projetogeapo";
    private final String usuario= "postgres";
    private final String senha = "toor2019";
    public Connection con;
    
    public void conexao(){
        
        try {
            System.setProperty("jdbc.Drivers", driver);
            con=DriverManager.getConnection(caminho, usuario, senha);
            JOptionPane.showMessageDialog(null,"Conexão Efetuada com Sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao se conectar com o banco de dados:\n"+ex);
        }
    } 
    
    public void executaSql (String sql){
        try {
            stm = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao ExecutaSql:\n"+ex.getMessage());
        }
        
    }
    
    
    public void desconecta() {
        try {
            con.close();
            JOptionPane.showMessageDialog(null, "BD Desconectado com Sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Fechar Conexão com o BD:\n"+ex.getMessage());
        }
    }
}

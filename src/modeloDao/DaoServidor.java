/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDao;

import modeloConection.ConexaoBD;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modeloBeans.BeansServidor;

/**
 *
 * @author Note
 */
public class DaoServidor {
    
    ConexaoBD conex = new ConexaoBD();
    BeansServidor mod = new BeansServidor();
    
    public void Salvar(BeansServidor mod){
        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement("insert into servidores(ser_nome,ser_cpf,ser_matricula,ser_celular,ser_email,ser_funcao) values(?,?,?,?,?,?)");
            pst.setString(1, mod.getSerNome());
            pst.setString(2, mod.getSerCpf());
            pst.setString(3, mod.getSerMatricula());
            pst.setString(4, mod.getSerCelular());
            pst.setString(5, mod.getSerEmail());
            pst.setString(6, mod.getSerFuncao());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados Inseridos Com Sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Inserir Dados!");
        }
        
        conex.desconecta();
    }
    
    public void Editar(BeansServidor mod){
        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement("update servidores set ser_nome=?, ser_cpf=?, ser_matricula=?, ser_celular=?, ser_email=?, ser_funcao=? where ser_cod=?");
            pst.setString(1, mod.getSerNome());
            pst.setString(2, mod.getSerCpf());
            pst.setString(3, mod.getSerMatricula());
            pst.setString(4, mod.getSerCelular());
            pst.setString(5, mod.getSerEmail());
            pst.setString(6, mod.getSerFuncao());
            pst.setInt(7, mod.getSerCod());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados Alterados com Sucesso!");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na Alteração dos Dados!/nErro:"+ex);
        }
        
        conex.desconecta();
    }
    
    public void Excluir(BeansServidor mod){
        conex.conexao();
         PreparedStatement pst;
        try {
            pst = conex.con.prepareStatement("delete from servidores where ser_cod=?");
            pst.setInt(1, mod.getSerCod());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados Excluidos com Sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na Exclusão dos Dados!/nErro:"+ex);
        }
         
         
    }
    
    public BeansServidor buscaServidor(BeansServidor mod){
        conex.conexao();
        conex.executaSql("select *from servidores where ser_nome like'%"+mod.getSerPesquisa()+"%'");
        try {
            conex.rs.first();
            mod.setSerCod(conex.rs.getInt("ser_cod"));
            mod.setSerNome(conex.rs.getString("ser_nome"));
            mod.setSerFuncao(conex.rs.getString("ser_funcao"));
            mod.setSerCelular(conex.rs.getString("ser_celular"));
            mod.setSerMatricula(conex.rs.getString("ser_matricula"));
            mod.setSerEmail(conex.rs.getString("ser_email"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar Servidor!/nErro:"+ex);
        }
        conex.desconecta();
            return mod;
    }
}

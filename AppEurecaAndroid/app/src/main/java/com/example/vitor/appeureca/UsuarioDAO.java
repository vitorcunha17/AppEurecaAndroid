package com.example.vitor.appeureca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioDAO {

    public boolean iserirUsuario(Usuario usuario){
        try {
            Connection conn = ConectaMySql.obtemConexao();
            String queryInserir = "INSERT INTO usuarios VALUES (null,?,?,?,?)";
            PreparedStatement ppStm = conn.prepareStatement(queryInserir);
            ppStm.setString(1, usuario.getNome());
            ppStm.setString(2, usuario.getUsuario());
            ppStm.setString(3, usuario.getEmail());
            ppStm.setString(4, usuario.getSenha());
            ppStm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean atualizarUsuario(Usuario usuario){
        try {
            Connection conn = ConectaMySql.obtemConexao();
            String queryInserir = "UPDATE usuarios SET nome = ?, usuario = ?, email = ?, senha = ? WHERE id = ?)";
            PreparedStatement ppStm = conn.prepareStatement(queryInserir);
            ppStm.setString(1, usuario.getNome());
            ppStm.setString(2, usuario.getUsuario());
            ppStm.setString(3, usuario.getEmail());
            ppStm.setString(4, usuario.getSenha());
            ppStm.setInt(5, usuario.getId());
            ppStm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean excluirUsuario(Usuario usuario){
        try {
            Connection conn = ConectaMySql.obtemConexao();
            String queryInserir = "DELETE FROM usuarios WHERE id = ?";
            PreparedStatement ppStm = conn.prepareStatement(queryInserir);
            ppStm.setInt(1, usuario.getId());
            ppStm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<Usuario> buscarTodosUsuarios(){
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        try {
            Connection conn = ConectaMySql.obtemConexao();
            String queryInserir = "SELECT * FROM usuarios";
            PreparedStatement ppStm = conn.prepareStatement(queryInserir);
            ResultSet rSet = ppStm.executeQuery();
            while(rSet.next()){
                Usuario usr = new Usuario();
                usr.setId(rSet.getInt(1));
                usr.setNome(rSet.getString(2));
                usr.setUsuario(rSet.getString(3));
                usr.setEmail(rSet.getString(4));
                usr.setSenha(rSet.getString(5));
                lista.add(usr);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Usuario buscarUsuarioPorID(int id){
        Usuario usr = null;
        try {
            Connection conn = ConectaMySql.obtemConexao();
            String queryInserir = "SELECT * FROM usuarios WHERE id = ?";
            PreparedStatement ppStm = conn.prepareStatement(queryInserir);
            ppStm.setInt(1, id);
            ResultSet rSet = ppStm.executeQuery();
            if(rSet.next()){
                usr = new Usuario();
                usr.setId(rSet.getInt(1));
                usr.setNome(rSet.getString(2));
                usr.setUsuario(rSet.getString(3));
                usr.setEmail(rSet.getString(4));
                usr.setSenha(rSet.getString(5));
            }else {
                return usr;
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usr;
    }

    public boolean excluirUsuario(int id){
        return excluirUsuario(new Usuario(id, "","","",""));
    }
}

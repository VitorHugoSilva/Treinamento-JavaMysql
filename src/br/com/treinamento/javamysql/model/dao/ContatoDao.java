package br.com.treinamento.javamysql.model.dao;

import br.com.treinamento.javamysql.conexao.ConnectionFactory;
import br.com.treinamento.javamysql.model.beans.Contato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDao {

    private final Connection CONNECTION;

    public ContatoDao() {
        this.CONNECTION = new ConnectionFactory()
                .getConnection();
    }
    private void finalizarConexao(){
        try{
            this.CONNECTION.close();
        } catch (SQLException e){
            System.out.println("Erro ao finalizar conexão");
            throw new RuntimeException(e);
        }
    }
    public void adicionar(Contato contato) {
        String sql = "insert into contatos(nome, email, endereco) value (?,?,?)";
        try (PreparedStatement stmt = this.CONNECTION.prepareStatement(sql)) {
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getEmail());
            stmt.setString(3, contato.getEndereco());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados");
            throw new RuntimeException(e);
        } finally {
            this.finalizarConexao();
        }

    }
    
  

    public List<Contato> getLista() {

        List<Contato> contatos = new ArrayList();
        try (
                PreparedStatement stmt = this.CONNECTION.prepareStatement("select * from contatos");
                ResultSet rs = stmt.executeQuery()
            ) {
            
            while (rs.next()) {
                // criando o objeto Contato
                Contato contato = new Contato();
                contato.setId(rs.getLong("id"));
                contato.setNome(rs.getString("nome"));
                contato.setEmail(rs.getString("email"));
                contato.setEndereco(rs.getString("endereco"));
                // adicionando o objeto à lista
                contatos.add(contato);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.finalizarConexao();
        }
        return contatos;
    }

    public void altera(Contato contato) {
        String sql = "update contatos set nome=?, email=?, endereco=? where id=?";
        try (PreparedStatement stmt = CONNECTION.prepareStatement(sql)) {
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getEmail());
            stmt.setString(3, contato.getEndereco());
            stmt.setLong(4, contato.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.finalizarConexao();
        }
    }

    public void remove(Contato contato) {
        try (PreparedStatement stmt = CONNECTION
                .prepareStatement("delete from contatos where id=?")) {
            stmt.setLong(1, contato.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.finalizarConexao();
        }
        
    }

    public Contato getContatoById(long id) {
        String sql = "select * from contatos where id = ?";
        Contato contato = null;
        try (PreparedStatement stmt = CONNECTION.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                contato = new Contato();
                contato.setId(rs.getLong("id"));
                contato.setNome(rs.getString("nome"));
                contato.setEmail(rs.getString("email"));
                contato.setEndereco(rs.getString("endereco"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.finalizarConexao();
        }
        return contato;
    }
}

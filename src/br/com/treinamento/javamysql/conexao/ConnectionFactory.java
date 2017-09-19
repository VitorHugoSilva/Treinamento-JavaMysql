package br.com.treinamento.javamysql.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
    private final String USER = "root";
    private final String PASS = "desenv";
    private final String DRIVER = "jdbc:mysql:";
    private final String PATH = "//localhost/";
    private final String DATABASE = "treinamento";
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                DRIVER+PATH+DATABASE,USER,PASS);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

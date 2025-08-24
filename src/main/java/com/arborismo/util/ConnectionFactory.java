package com.arborismo.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static Properties props;

    static {
        props = new Properties();
        try (InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                System.err.println("Desculpe, o arquivo db.properties não foi encontrado.");
                System.exit(1);
            }
            props.load(input);
        } catch (IOException ex) {
            System.err.println("Erro ao carregar o arquivo de propriedades: " + ex.getMessage());
            System.exit(1);
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver JDBC carregado com sucesso!");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC não encontrado.", e);
        }

        Connection conn = DriverManager.getConnection(
            props.getProperty("db.url"),
            props.getProperty("db.user"),
            props.getProperty("db.password")
        );
        return conn;
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexão com o banco de dados fechada com sucesso!");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = getConnection();
            System.out.println("Teste de conexão concluído. Se esta mensagem apareceu, está OK!");
        } catch (SQLException e) {
            System.err.println("Falha ao testar a conexão: " + e.getMessage());
            System.err.println("Verifique:");
            System.err.println("1. Se o MySQL Server está rodando.");
            System.err.println("2. Se o arquivo db.properties está na pasta src/main/resources.");
            System.err.println("3. Se as credenciais no arquivo db.properties estão corretas.");
        } finally {
            closeConnection(conn);
        }
    }
}
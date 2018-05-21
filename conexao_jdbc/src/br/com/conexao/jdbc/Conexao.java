package br.com.conexao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/trabalhoJDBC","postgres","postgres");
			System.out.println("Conectado com sucesso!");
			} catch (ClassNotFoundException e) {
				System.out.println("Erro : Driver"+e.getMessage());
			} catch (SQLException e) {
				System.out.println("Erro : Conexão"+e.getMessage());
		}return con;
	}
}

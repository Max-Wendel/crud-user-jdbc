package br.com.conexao.usuarioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.conexao.jdbc.Conexao;
import br.com.conexao.model.Usuario;

public class UsuarioDAO {
	private Connection connection;
	
	public UsuarioDAO() {
		this.connection = new Conexao().getConnection();
	}
	
	public void adiciona(Usuario usuario) {
		String sql = "insert into usuario "+"(nome,email,senha)"+" values (?,?,?)";
		
		try {
			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setString(1, usuario.getNome());
			stm.setString(2, usuario.getEmail());
			stm.setString(3, usuario.getSenha());
			
			stm.execute();
			System.out.println("Dados adicionados com sucesso!");
			stm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ERRO: Erro ao adicionar - "+e.getMessage());
		}
		
	}
	
	public ArrayList<Usuario> getList() {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			PreparedStatement stm = this.connection.prepareStatement("select * from usuario");
			ResultSet rs = stm.executeQuery();

			Usuario usuario;
			
			while (rs.next()) {
				usuario = new Usuario(rs.getInt("id"),rs.getString("nome"), rs.getString("email"), rs.getString("senha"));

				usuarios.add(usuario);
			}
			
			rs.close();
			stm.close();			
		} catch (SQLException e) {
			System.out.println("ERRO : Não foi possivel pegar os dados - "+e.getMessage());
		}
		
		return usuarios;

	}
	
	public void visualizar() {
		ArrayList<Usuario> usuarios = getList();
		
		for (Usuario usuario : usuarios) {
			usuario.toString();
			
		}
	}
	
	public void alterar(Usuario usuario) {
		String sql = "update usuario set nome=?, email=?, senha=? where id=?";
		
		try {
			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setString(1, usuario.getNome());
			stm.setString(2, usuario.getEmail());
			stm.setString(3, usuario.getSenha());
			stm.setLong(4, usuario.getId());
			stm.execute();
			stm.close();
		} catch (SQLException e) {
			System.out.println("ERRO: Erro ao alterar - "+e.getMessage());
		}
		
	}
	
	public void remove(Usuario usuario) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete from usuario where id=?");
			stmt.setLong(1, usuario.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
}

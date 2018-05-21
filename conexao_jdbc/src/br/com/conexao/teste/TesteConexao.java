package br.com.conexao.teste;

import br.com.conexao.jdbc.*;
import br.com.conexao.model.Usuario;
import br.com.conexao.usuarioDAO.UsuarioDAO;

public class TesteConexao {
	public static void main(String[] args) {
		Conexao con = new Conexao();
		Usuario user = new Usuario(12, "robson", "chorandorios@email.com", "chorão");
		UsuarioDAO dao = new UsuarioDAO();
		
		con.getConnection();
		
		dao.adiciona(user);
		
		dao.visualizar();
		
		user.setNome("Ítalo");
		
		dao.alterar(user);
		
		dao.visualizar();
		
		dao.remove(user);
	
		dao.visualizar();		
		
	}
}

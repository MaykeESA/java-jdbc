package br.com.alura.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Querys implements AutoCloseable{

	protected ConFactory factory;
	protected Connection con;
	
	public Querys() throws SQLException {
		this.factory = new ConFactory(); 
		this.con = this.factory.getCon();
	}
	
	// Selects
	public void bdSelectAll() throws SQLException {
		Statement stm = this.con.createStatement();
		stm.execute("SELECT * FROM lojavirtual.produto;");
		
		ResultSet rst = stm.getResultSet();
		while(rst.next()) {
			Integer id = rst.getInt("id");
			String nome = rst.getString("nome");
			String descricao = rst.getString("descricao");
			
			System.out.println("Id: " + id + "\n"
							 + "Nome: " + nome + "\n"
							 + "Desc: " + descricao + "\n");
		}
	}
	
	public void bdSelectDescricao() throws SQLException {
		Statement stm = this.con.createStatement();
		stm.execute("SELECT descricao FROM lojavirtual.produto;");
		
		ResultSet rst = stm.getResultSet();
		while(rst.next()) {
			String descricao = rst.getString("descricao");
			
			System.out.println("Descrição produto: " + descricao);
		}
	}
	
	// Insert
	public void bdInsertProduto(String nome, String descricao) throws SQLException {
		PreparedStatement pstm = this.con.prepareStatement("INSERT INTO lojavirtual.produto(nome, descricao) VALUES ( ? , ?)", Statement.RETURN_GENERATED_KEYS);
		pstm.setString(1, nome);
		pstm.setString(2, descricao);
		pstm.execute();
		
		ResultSet rst = pstm.getGeneratedKeys();
		while(rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println("O produto com ID " + id + " foi registrado.");
		}
	}
	
	// Delete
	public void bdDeleteProduto(int id) throws SQLException {
		Statement stm = this.con.createStatement();
		stm.execute("DELETE FROM lojavirtual.produto WHERE id = " + id);
		int linhasMod = stm.getUpdateCount();
		
		System.out.println("Quantidade de linhas removidas: " + linhasMod + "\n");
	}
	
	@Override
	public void close() throws Exception {
		factory.getCon().close();
		System.out.println("\nConexão Fechada...");
	}
}

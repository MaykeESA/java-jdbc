package br.com.alura.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Querys implements AutoCloseable{

	protected ConFactory factory;
	protected Connection con;
	protected PreparedStatement pstm;
	
	public Querys() throws SQLException {
		this.factory = new ConFactory(); 
		this.con = this.factory.getCon();
		this.con.setAutoCommit(false);
	}
	
	// Selects
	public void bdSelectAll() throws SQLException {
		this.pstm = this.con.prepareStatement("SELECT * FROM lojavirtual.produto;");
		this.pstm.execute();
		
		ResultSet rst = pstm.getResultSet();
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
		this.pstm = this.con.prepareStatement("SELECT descricao FROM lojavirtual.produto;");
		this.pstm.execute();
		
		ResultSet rst = this.pstm.getResultSet();
		while(rst.next()) {
			String descricao = rst.getString("descricao");
			
			System.out.println("Descrição produto: " + descricao);
		}
		rst.close();
	}
	
	// Insert
	public void bdInsertProduto(String nome, String descricao) throws SQLException {
		this.pstm = this.con.prepareStatement("INSERT INTO lojavirtual.produto(nome, descricao) VALUES ( ? , ?)", Statement.RETURN_GENERATED_KEYS);
		this.pstm.setString(1, nome);
		this.pstm.setString(2, descricao);
		this.pstm.execute();
		
		ResultSet rst = this.pstm.getGeneratedKeys();
		while(rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println("O produto com ID " + id + " foi registrado.");
		}
		rst.close();
	}
	
	// Delete
	public void bdDeleteProduto(int id) throws SQLException {
		this.pstm = this.con.prepareStatement("DELETE FROM lojavirtual.produto WHERE id = " + id);
		this.pstm.execute();
		
		int linhasMod = pstm.getUpdateCount();
		
		System.out.println("Quantidade de linhas removidas: " + linhasMod + "\n");
	}
	
	@Override
	public void close() throws Exception {
		this.con.commit();
		factory.getCon().close();
		System.out.println("\nConexão Fechada...");
	}
	
	public Connection getCon() {
		return con;
	}
}

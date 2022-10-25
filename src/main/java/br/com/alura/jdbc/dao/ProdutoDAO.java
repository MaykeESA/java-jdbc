package br.com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.alura.jdbc.controller.ProdutoController;
import br.com.alura.jdbc.factory.ConFactory;
import br.com.alura.jdbc.model.Produto;

public class ProdutoDAO {

	protected ConFactory factory;
	protected Connection con;

	public ProdutoDAO(Connection con) throws SQLException {
		this.con = con;
		this.con.setAutoCommit(false);
	}

	// Selects
	public void buscar() throws SQLException {
		try (PreparedStatement pstm = this.con.prepareStatement("SELECT * FROM lojavirtual.produto;")) {
			pstm.execute();
			
			ProdutoController.clearArray();
			
			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {
					Integer id = rst.getInt("id");
					String nome = rst.getString("nome");
					String descricao = rst.getString("descricao");
					Integer categoria = rst.getInt("categoria_id");

					System.out.println("Id: " + id + "\n" + 
									   "Nome: " + nome + "\n" + 
									   "Desc: " + descricao + "\n" +
									   "Categoria: " + categoria + "\n");
					
					Produto produtoArray = new Produto(nome, descricao);
					produtoArray.setId(id);
					ProdutoController.addArray(produtoArray);
				}
				this.con.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
			this.con.rollback();
			System.out.println("Rollback da aplicação!");
		}
	}

	public void buscarComCategoria() throws SQLException {
		try (PreparedStatement pstm = this.con.prepareStatement(
				"SELECT lojavirtual.categoria.nome, lojavirtual.produto.nome, descricao FROM lojavirtual.produto "
						+ "JOIN lojavirtual.categoria ON lojavirtual.categoria.id = categoria_id;")) {
			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {
					String categoria = rst.getString(1);
					String tipo = rst.getString(2);
					String nome = rst.getString(3);
					System.out.println("Categoria: " + categoria + "\n"
									   + "Produto: " + tipo + "\n"
									   + "Nome: " + nome + "\n");
				}
				this.con.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
			this.con.rollback();
			System.out.println("Rollback da aplicação!");
		}

	}
	
	// Insert
	public void salvar(Produto produto) throws SQLException {
		try (PreparedStatement pstm = this.con.prepareStatement(
				"INSERT INTO lojavirtual.produto(nome, descricao) " + "VALUES ( ? , ?)",
				Statement.RETURN_GENERATED_KEYS);) {

			pstm.setString(1, produto.getNome());
			pstm.setString(2, produto.getDescricao());
			pstm.execute();

			try (ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					Integer id = rst.getInt(1);
					produto.setId(id);
					System.out.println("O ID " + id + " foi registrado com SUCESSO no banco de dados.\n"
									 + "Nome: " + produto.getNome() + "\n"
									 + "Descricão: " + produto.getDescricao());
					
					ProdutoController.addArray(produto);
				}
				this.con.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
			this.con.rollback();
			System.out.println("Rollback da aplicação!");
		}

	}

	// Delete
	public void deletar(int id) throws SQLException {
		try (PreparedStatement pstm = this.con.prepareStatement("DELETE FROM lojavirtual.produto WHERE id = " + id);) {
			pstm.execute();

			int linhasMod = pstm.getUpdateCount();
			System.out.println("Quantidade de linhas removidas: " + linhasMod + "\n");

			this.con.commit();

		} catch (Exception e) {
			e.printStackTrace();
			this.con.rollback();
			System.out.println("Rollback da aplicação!");
		}
	}

}

package br.com.alura.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import br.com.alura.model.Produto;

public class Querys implements AutoCloseable {

	protected ConFactory factory;
	protected Connection con;

	public Querys() throws SQLException {
		this.factory = new ConFactory();
		this.con = this.factory.getCon();
		this.con.setAutoCommit(false);
	}

	// Selects
	public void bdSelectAll() throws SQLException {
		try (PreparedStatement pstm = this.con.prepareStatement("SELECT * FROM lojavirtual.produto;")) {
			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {
					Integer id = rst.getInt("id");
					String nome = rst.getString("nome");
					String descricao = rst.getString("descricao");

					System.out.println("Id: " + id + "\n" + "Nome: " + nome + "\n" + "Desc: " + descricao + "\n");
				}
				this.con.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
			this.con.rollback();
			System.out.println("Rollback da aplicação!");
		}
	}

	public void bdSelectDescricao() throws SQLException {
		try (PreparedStatement pstm = this.con.prepareStatement("SELECT descricao FROM lojavirtual.produto;")) {
			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {
					String descricao = rst.getString("descricao");
					System.out.println("Descrição produto: " + descricao);
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
	public void bdInsertProduto(Produto produto, String nome, String descricao) throws SQLException {
		try (PreparedStatement pstm = this.con.prepareStatement(
				"INSERT INTO lojavirtual.produto(nome, descricao) " + "VALUES ( ? , ?)",
				Statement.RETURN_GENERATED_KEYS);) {

			pstm.setString(1, nome);
			pstm.setString(2, descricao);
			pstm.execute();

			try (ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					Integer id = rst.getInt(1);
					produto.setId(id);
					System.out.println("O ID " + id + " foi registrado com SUCESSO no banco de dados.");
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
	public void bdDeleteProduto(int id) throws SQLException {
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

	@Override
	public void close() throws Exception {
		this.con.close();
		System.out.println("\nConexão Fechada...");
	}

	public Connection getCon() {
		return this.con;
	}
}

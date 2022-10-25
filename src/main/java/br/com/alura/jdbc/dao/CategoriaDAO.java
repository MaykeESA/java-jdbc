package br.com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import br.com.alura.jdbc.controller.CategoriaController;
import br.com.alura.jdbc.factory.ConFactory;
import br.com.alura.jdbc.model.Categoria;

public class CategoriaDAO {

	protected ConFactory factory;
	protected Connection con;

	public CategoriaDAO(Connection con) throws SQLException {
		this.con = con;
		this.con.setAutoCommit(false);
	}

	// Selects
	public void buscar() throws SQLException {
		try (PreparedStatement pstm = this.con.prepareStatement("SELECT * FROM lojavirtual.categoria;")) {
			pstm.execute();

			CategoriaController.clearArray();

			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {
					Integer id = rst.getInt("id");
					String nome = rst.getString("nome");

					System.out.println("Id: " + id + "\n" + "Nome: " + nome + "\n");

					Categoria categoriaArray = new Categoria(nome);
					categoriaArray.setId(id);
					CategoriaController.addArray(categoriaArray);
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
	public void salvar(Categoria categoria) throws SQLException {
		try (PreparedStatement pstm = this.con.prepareStatement(
				"INSERT INTO lojavirtual.categoria(nome) " + "VALUES ( ? )", Statement.RETURN_GENERATED_KEYS);) {

			pstm.setString(1, categoria.getNome());
			pstm.execute();

			try (ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					Integer id = rst.getInt(1);
					categoria.setId(id);
					System.out.println("O ID " + id + " foi registrado com SUCESSO no banco de dados.\n" + "Nome: "
							+ categoria.getNome() + "\n");

					CategoriaController.addArray(categoria);
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
		try (PreparedStatement pstm = this.con
				.prepareStatement("DELETE FROM lojavirtual.categoria WHERE id = " + id);) {
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

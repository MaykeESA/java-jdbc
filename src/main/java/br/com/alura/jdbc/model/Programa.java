package br.com.alura.jdbc.model;

import java.sql.Connection;

import br.com.alura.jdbc.controller.CategoriaController;
import br.com.alura.jdbc.controller.ProdutoController;
import br.com.alura.jdbc.dao.CategoriaDAO;
import br.com.alura.jdbc.dao.ProdutoDAO;
import br.com.alura.jdbc.factory.ConFactory;

public class Programa {

	public static void main(String[] args) {

		try (Connection con = new ConFactory().getCon()) {

			ProdutoDAO produtoDao = new ProdutoDAO(con);
			CategoriaDAO categoriaDAO = new CategoriaDAO(con);

			System.out.println("X---Produtos---X");
			produtoDao.buscar();

			System.out.println("X--------------X");
			for (Produto p : ProdutoController.listarProdutos()) {
				System.out.println(p.getNome());
			}

			System.out.println("X---Categorias---X");
			categoriaDAO.buscar();

			System.out.println("X----------------X");
			for (Categoria c : CategoriaController.listarProdutos()) {
				System.out.println(c.getNome());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

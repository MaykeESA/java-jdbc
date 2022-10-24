package br.com.alura.jdbc.model;

import java.sql.Connection;

import br.com.alura.jdbc.dao.CategoriaDAO;
import br.com.alura.jdbc.dao.ProdutoDAO;
import br.com.alura.jdbc.factory.ConFactory;

public class Programa {

	public static void main(String[] args) {

		try (Connection con = new ConFactory().getCon()) {
			
			ProdutoDAO produtoDao = new ProdutoDAO(con);
			CategoriaDAO categoriaDAO = new CategoriaDAO(con);
			
			categoriaDAO.buscar();
			System.out.println("X-=-=-=-=-=-=-=-=-=-=-=-X");
			produtoDao.buscarComCategoria();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

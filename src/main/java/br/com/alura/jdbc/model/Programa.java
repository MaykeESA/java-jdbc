package br.com.alura.jdbc.model;

import br.com.alura.jdbc.dao.ProdutoDAO;

public class Programa {

	public static void main(String[] args) {

		try (ProdutoDAO produtoDao = new ProdutoDAO()) {
			
			Produto p = new Produto("Simulador", "Logitech G27");
			produtoDao.salvar(p);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

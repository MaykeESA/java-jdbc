package br.com.alura.jdbc.controller;

import java.util.ArrayList;
import br.com.alura.jdbc.model.Produto;

public class ProdutoController {

	private static ArrayList<Produto> produtos = new ArrayList<Produto>();

	public static void addArray(Produto p) {
		produtos.add(p);
	}

	public static void rmArray(Produto p) {
		produtos.remove(p);
	}

	public static void clearArray() {
		produtos.clear();
	}

	public static ArrayList<Produto> listarProdutos() {
		return produtos;
	}
}

package br.com.alura.jdbc.controller;

import java.util.ArrayList;
import br.com.alura.jdbc.model.Categoria;

public class CategoriaController {

	private static ArrayList<Categoria> categorias = new ArrayList<Categoria>();

	public static void addArray(Categoria c) {
		categorias.add(c);
	}

	public static void rmArray(Categoria c) {
		categorias.remove(c);
	}

	public static void clearArray() {
		categorias.clear();
	}

	public static ArrayList<Categoria> listarProdutos() {
		return categorias;
	}
}

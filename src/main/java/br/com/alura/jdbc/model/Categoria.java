package br.com.alura.jdbc.model;

import java.util.ArrayList;

public class Categoria {

	private int id;
	private String nome;
	private static ArrayList<Categoria> categorias = new ArrayList<Categoria>();
	
	public Categoria(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public static void addArray(Categoria c) {
		categorias.add(c);
	}
	
	@Override
	public String toString() {
		return "Id: " + this.id + "\nNome Categoria: " + this.nome + "\n";
	}
}

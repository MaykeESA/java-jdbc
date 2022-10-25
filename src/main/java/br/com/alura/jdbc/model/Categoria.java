package br.com.alura.jdbc.model;

public class Categoria {

	private int id;
	private String nome;

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

	@Override
	public String toString() {
		return "Id: " + this.id + "\nNome Categoria: " + this.nome + "\n";
	}
}

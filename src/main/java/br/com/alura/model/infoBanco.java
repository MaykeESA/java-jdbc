package br.com.alura.model;

public class infoBanco {

	private String urlBanco;
	private String usuario;
	private String senha;

	public infoBanco() {
		this.urlBanco = "jdbc:mysql://localhost:3306/LojaVirtual";
		this.usuario = "root";
		this.senha = "root";
	}

	public String getUrlBanco() {
		return urlBanco;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getSenha() {
		return senha;
	}

}

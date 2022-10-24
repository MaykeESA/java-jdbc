package br.com.alura.jdbc.factory;

public class InfoBanco {

	private String urlBanco;
	private String usuario;
	private String senha;

	public InfoBanco() {
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

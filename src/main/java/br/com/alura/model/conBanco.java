package br.com.alura.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conBanco implements AutoCloseable{

	private Connection con;
	private infoBanco infoDb = new infoBanco();
	
	public conBanco() throws SQLException {
		this.con = DriverManager
				.getConnection(infoDb.getUrlBanco(), infoDb.getUsuario(), infoDb.getSenha());
	}
	
	@Override
	public void close() throws Exception {
		con.close();
		System.out.println("Conexão Fechada...");
	}
	
	public Connection getCon() {
		return con;
	}
}

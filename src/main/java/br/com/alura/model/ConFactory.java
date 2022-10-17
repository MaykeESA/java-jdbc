package br.com.alura.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConFactory {

	private Connection con;
	private InfoBanco infoDb = new InfoBanco();
	
	public ConFactory() throws SQLException {
		this.con = DriverManager
				.getConnection(infoDb.getUrlBanco(), infoDb.getUsuario(), infoDb.getSenha());
	}
	
	public Connection getCon() throws SQLException{
		return con;
	}
}

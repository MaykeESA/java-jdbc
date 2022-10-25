package br.com.alura.jdbc.factory;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConFactory implements AutoCloseable {

	private DataSource dataSource;
	private InfoBanco infoDb = new InfoBanco();

	public ConFactory() throws SQLException {
		ComboPooledDataSource poolConDS = new ComboPooledDataSource();
		poolConDS.setJdbcUrl(infoDb.getUrlBanco());
		poolConDS.setUser(infoDb.getUsuario());
		poolConDS.setPassword(infoDb.getSenha());
		poolConDS.setMaxPoolSize(15);

		this.dataSource = poolConDS;
	}

	public Connection getCon() throws SQLException {
		return this.dataSource.getConnection();
	}

	@Override
	public void close() throws Exception {
	}
}

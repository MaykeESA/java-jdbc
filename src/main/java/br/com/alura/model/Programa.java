package br.com.alura.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Programa {
	
	public static void main(String[] args) {
		try(conBanco conBanco = new conBanco()){
			
			Connection con = conBanco.getCon();
			Statement stm = con.createStatement();
			stm.execute("SELECT * FROM lojavirtual.produto;");
			
			ResultSet rst = stm.getResultSet();
			while(rst.next()) {
				Integer id = rst.getInt("id");
				String nome = rst.getString("nome");
				String descricao = rst.getString("descricao");
				
				System.out.println("Id: " + id + "\n"
								 + "Nome: " + nome + "\n"
								 + "Desc: " + descricao + "\n");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

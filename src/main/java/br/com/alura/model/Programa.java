package br.com.alura.model;

import br.com.alura.jdbc.Querys;

public class Programa {

	public static void main(String[] args) {

		try (Querys q = new Querys()) {
			
			q.bdSelectAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

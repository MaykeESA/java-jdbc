package br.com.alura.model;

public class Programa {

	public static void main(String[] args) {
		
		try (Querys q = new Querys()){
			
			q.bdSelectAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package br.com.alura.model;

public class Programa {

	public static void main(String[] args) {
		try (Querys q = new Querys()){
			
			q.bdInsertProduto("Simulador", "Logitech G923");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

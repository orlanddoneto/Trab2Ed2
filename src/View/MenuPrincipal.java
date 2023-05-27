package View;

import AlgoritmosHash.HashTable;
import Entidade.Arquivo;
import ManagerCSV.GeradorCSV;
import ManagerCSV.LeitorCSV;
import Servicos.Gerenciador;

public class MenuPrincipal {

	public static void main(String[] args) {
		GeradorCSV csv = new GeradorCSV();
		csv.gerarCSV();
		LeitorCSV lerCSV = new LeitorCSV();
		lerCSV.lerCSV();
		
		int metodoCol = 1;
		int tam = 10;
		
		Gerenciador gerente = new Gerenciador(tam, metodoCol);
		gerente.setStrategy(2);
		HashTable hashTable = gerente.getHashTable();
		
		int cont = 0;
		for(Arquivo obj : lerCSV.getLines()) {
			System.out.println(Math.abs((obj.getNome()).hashCode()) %tam);
			System.out.println(Math.abs(obj.getNome().hashCode()));
			hashTable.insert(obj.getNome(), obj);
			cont++;
			if (cont==tam)
				break;
			System.out.println("inseriu");
		}
		

	}

}

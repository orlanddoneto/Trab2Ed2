package View;

import java.util.LinkedList;

import AlgoritmosHash.HashTable;
import Entidade.Arquivo;
import ManagerCSV.LeitorCSV;
import Servicos.Gerenciador;

public class MenuPrincipal {

	public static void main(String[] args) {
		//GeradorCSV csv = new GeradorCSV();
		//csv.gerarCSV();
		LeitorCSV lerCSV = new LeitorCSV();
		lerCSV.lerCSV();
		
		int tam = 10;
		
		Gerenciador gerente = new Gerenciador(tam);
		gerente.setStrategy(2);
		HashTable hashTable = gerente.getHashTable();
		
		int cont = 0;
		for(Arquivo obj : lerCSV.getLines()) {
			hashTable.insert(obj.getNome(), obj);
			cont++;
			System.out.println("inseriu");
			if (cont==tam)
				break;
		}
		System.out.println(hashTable.get("PegueUmNomeAleatórioNoCSVqueVaiSerGerado"));
		hashTable.insert("gvlkksdo", hashTable.get("PegueUmNomeAleatórioNoCSVqueVaiSerGerado"));
		System.out.println(hashTable.remove("PegueUmNomeAleatórioNoCSVqueVaiSerGerado"));
		System.out.println(hashTable.get("PegueUmNomeAleatórioNoCSVqueVaiSerGerado"));
		
		
		
		
		
		

	}

}

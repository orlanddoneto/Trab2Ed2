package Servicos;

import java.util.ArrayList;
import java.util.List;

import AlgoritmosHash.HashTable;
import AlgoritmosHash.Hash_Encadeamento;
import AlgoritmosHash.Hash_Quadratico;

public class Gerenciador {
	private HashTable hashTable;
	private int tamanhoIni = 100;
	
	
	
	public Gerenciador(int tamanhoIni) {
		this.tamanhoIni = tamanhoIni;
	}
	public Gerenciador() {}

	public void setStrategy(int metodoHash) {
		List<HashTable> metodos = new ArrayList<>();
		metodos.add(new Hash_Quadratico(tamanhoIni));
		metodos.add(new Hash_Encadeamento(tamanhoIni));
		hashTable = metodos.get(metodoHash - 1);
	}

	public HashTable getHashTable() {
		return hashTable;
	}

}

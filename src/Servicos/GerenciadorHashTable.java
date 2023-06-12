package Servicos;

import java.util.ArrayList;
import java.util.List;

import TabelasHash.HashTable;
import TabelasHash.Hash_Encadeamento;
import TabelasHash.Hash_Quadratico;

public class GerenciadorHashTable {
	private HashTable hashTable;
	private int tamanhoIni = 100;
	
	public GerenciadorHashTable(int tamanhoIni) {
		this.tamanhoIni = tamanhoIni;
	}
	public GerenciadorHashTable() {}

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

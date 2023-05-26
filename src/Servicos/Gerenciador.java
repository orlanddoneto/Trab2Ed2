package Servicos;

import java.util.ArrayList;
import java.util.List;

import AlgoritmosHash.HashTable;
import AlgoritmosHash.Hash_AVL;
import AlgoritmosHash.Hash_Bin;
import AlgoritmosHash.Hash_RN;

public class Gerenciador {
	private HashTable hashTable;
	private int tamanhoIni = 100;
	private int metodoCol = 1;
	
	
	
	public Gerenciador(int tamanhoIni, int metodo) {
		this.tamanhoIni = tamanhoIni;
		this.metodoCol = metodo;
	}

	public void setStrategy(int metodoHash) {
		List<HashTable> metodos = new ArrayList<>();
		metodos.add(new Hash_AVL());
		metodos.add(new Hash_Bin(tamanhoIni, metodoCol));
		metodos.add(new Hash_RN());
		hashTable = metodos.get(metodoHash - 1);
	}

	public HashTable getHashTable() {
		return hashTable;
	}

}

package Servicos;

import java.util.ArrayList;
import java.util.List;

import AlgoritmosHash.HashTable;
import AlgoritmosHash.Hash_AVL;
import AlgoritmosHash.Hash_Bin;
import AlgoritmosHash.Hash_RN;

public class Gerenciador {
	private HashTable hashTable;

	public void setStrategy(int metodo) {
		List<HashTable> metodos = new ArrayList<>();
		metodos.add(new Hash_AVL());
		metodos.add(new Hash_Bin());
		metodos.add(new Hash_RN());
		hashTable = metodos.get(metodo - 1);
	}

	public HashTable getHashTable() {
		return hashTable;
	}

}

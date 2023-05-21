package Servicos;

import java.util.ArrayList;
import java.util.List;

import AlgoritmosHash.HashTable;
import AlgoritmosHash.Hash_AVL;
import AlgoritmosHash.Hash_Bin;
import AlgoritmosHash.Hash_RN;

public class StrategyHashTable {
	private HashTable hashTable;
	
	public void setSort(int metodo) {
		List<HashTable> metodos = new ArrayList<>();
		metodos.add(new Hash_AVL());
		metodos.add(new Hash_Bin());
		metodos.add(new Hash_RN());
		hashTable = metodos.get(metodo-1);
	}
	
	public HashTable hashTable() {
		return hashTable;
	}
	
}

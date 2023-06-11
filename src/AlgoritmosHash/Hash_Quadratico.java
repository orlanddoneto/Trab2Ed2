package AlgoritmosHash;

import Algoritmos.BinTree;
import Entidade.Arquivo;

public class Hash_Quadratico implements HashTable {

	private int elementosInseridos;
	private int tamanho;
	private long resizes = 0L;
	private double fatorResize = 70;
	private Entry[] table;
	
	
	public class Entry {
		private String key;
		private Arquivo value;

		public Entry(String key, Arquivo value) {
			this.key = key;
			this.value = value;
		}
	}

	public Hash_Quadratico(int tamanho) {
		this.tamanho = tamanho;
		table = new Entry[tamanho];
	}

	@Override
	public int size() {
		return elementosInseridos;
	}

	@Override
	public Arquivo get(String key) {
		int i = hash(key);
		int j = 0;
		Entry temp = table[i];
		while (temp != null) {
			if (temp.key.equals(key)) {
				return temp.value;
			}
			j++;
			i = (i + j * j) % tamanho;
			if (i < 0) {
				i = (i + tamanho) % tamanho; // Ajuste para um valor negativo
			}
			temp = table[i];
		}
		//System.out.println("Elemento inexistente!");
		return null;
	}

	@Override
	public boolean insert(String key, Arquivo val) {
		int hash = hash(key);
		int i = hash;
		boolean result;
		if (table[i] == null) {
			table[i] = new Entry(key, val);
			this.elementosInseridos++;
			if (this.elementosInseridos >= this.tamanho *(fatorResize/100))
				resize(this.tamanho * 2);
			return true;
		}
		// trata colisão metodo quadratico
		int j = 0;
		while (table[i] != null) {
			if (table[i].key.equals(key)) {
				//System.out.println("Elemento existente!");
				return false;
			}
			j++;
			i = (hash + j * j) % tamanho;
			if (i < 0) {
				i = (i + tamanho) % tamanho; // Ajuste para um valor não negativo
			}
		}
		table[i] = new Entry(key, val);
		this.elementosInseridos++;
		if (this.elementosInseridos >= this.tamanho *(fatorResize/100))
			resize(this.tamanho * 2);
		return true;
	}

	@Override
	public boolean remove(String key) {
		int i = hash(key);
		boolean result = false;
		int j = 0;
		while (table[i] != null) {
			if (table[i].key.equals(key)) {
				table[i] = null;
				result = true;
				this.elementosInseridos--;
				return result;
			} else {
				j++;
				i = (i + j * j) % tamanho;
				if (i < 0)
					i = (i + tamanho) % tamanho; // Ajuste para um valor não negativo
			}
		}
		//System.out.println("Não foi possível remover!");
		return result;
	}

	private int hash(String key) {
		return Math.abs(key.hashCode()) % this.tamanho;

	}

	@Override
	public boolean resize(int novo_tam) {
		Hash_Quadratico novoHash = new Hash_Quadratico(novo_tam);
		for (int i = 0; i < this.tamanho; i++) {
			if (this.table[i] != null)
				novoHash.insert(table[i].key, table[i].value);
		}
		this.resizes++;
		this.table = novoHash.table;
		this.tamanho = novoHash.tamanho;
		return true;
	}

	@Override
	public long getResizes() {
		return resizes;
	}
	
	

}



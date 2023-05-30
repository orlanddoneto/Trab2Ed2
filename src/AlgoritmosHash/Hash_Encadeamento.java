package AlgoritmosHash;

import java.util.LinkedList;

import Entidade.Arquivo;

public class Hash_Encadeamento implements HashTable {

	private int elementosInseridos;
	private int tamanho;
	private Entry[] table;
	private int resizes;

	public class Entry {
		private String key;
		private Arquivo value;
		private LinkedList<Entry> ListProx = new LinkedList<>();

		public Entry(String key, Arquivo value) {
			this.key = key;
			this.value = value;
		}
	}

	public Hash_Encadeamento(int tamanho) {
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
		if (table[i] != null) {
			if (table[i].key.equals(key)) {
				return table[i].value;
			} else {
				for (Entry node : table[i].ListProx) {
					if (node.key.equals(key)) {
						return node.value;
					}
				}
			}

		}
		System.out.println("Elemento inexistente!");
		return null;
	}

	@Override
	public boolean insert(String key, Arquivo val) {
		int hash = hash(key);
		int i = hash;
		if (table[i] == null) {
			table[i] = new Entry(key, val);
			this.elementosInseridos++;
			if (this.elementosInseridos >= this.tamanho / 2)
				resize(this.tamanho * 2);
			return true;
		}
		// se chegou nesse ponto é porquê table[i] != null
		if (table[i].key.equals(key)) {
			System.out.println("Elemento existente!");
			return false;
		} else {// trata colisão com metodo encadeamento
			table[i].ListProx.add(new Entry(key, val));
			this.elementosInseridos++;
			if (this.elementosInseridos >= this.tamanho / 2)
				resize(this.tamanho * 2);
			return true;
		}
	}

	@Override
	public boolean remove(String key) {
		int i = hash(key);
		Entry temp = table[i];
		if (temp != null) {
			if (temp.key.equals(key)) {
				if (temp.ListProx.isEmpty()) {
					table[i] = null;
					return true;
				} else {
					Entry temp2 = temp.ListProx.getFirst();
					temp.ListProx.remove(temp2);
					temp2.ListProx = temp.ListProx;
					table[i] = temp2;
					this.elementosInseridos--;
					return true;
				}

			} else {
				for (Entry node : temp.ListProx) {
					if (node.key.equals(key)) {
						temp.ListProx.remove(node);
						this.elementosInseridos--;
						return true;
					}
				}
			}
		}
		System.out.println("Não foi possível remover!");
		return false;
	}

	private int hash(String key) {
		return Math.abs(key.hashCode()) % this.tamanho;

	}

	@Override
	public boolean resize(int novo_tam) {
		Hash_Encadeamento novoHash = new Hash_Encadeamento(novo_tam);
		for (int i = 0; i < this.tamanho; i++) {
			if (this.table[i] != null)
				novoHash.insert(table[i].key, table[i].value);
		}
		this.resizes++;
		this.table = novoHash.table;
		this.tamanho = novoHash.tamanho;
		return true;
	}

}

package TabelasHash;

import java.util.LinkedList;

import Entidade.Arquivo;

public class Hash_Encadeamento implements HashTable {

	private int elementosInseridos;
	private int tamanho;
	private long resizes = 0L;
	private double fatorResize = 70;
	private LinkedList<Entry> [] table;
	
	public class Entry {
		private String key;
		private Arquivo value;

		public Entry(String key, Arquivo value) {
			this.key = key;
			this.value = value;
		}
	}

	public Hash_Encadeamento(int tamanho) {
		this.tamanho = tamanho;
		this.table = new LinkedList[tamanho];
		for (int i = 0; i < tamanho; i++) {
			table[i] = new LinkedList<Entry>();
		}
	}

	@Override
	public int size() {
		return elementosInseridos;
	}
                  
	@Override
	public Arquivo get(String key) {
		int i = hash(key);
		LinkedList<Entry> list = table[i];
		if (list != null) {
			for (Entry no : list) {
				if (no.key.equals(key)) {
					return no.value;
				}
			}
		}

		//System.out.println("Elemento inexistente!");
		return null;
	}

	@Override
	public boolean insert(String key, Arquivo val) {
		int hash = hash(key);
		int i = hash;
		if (table[i] == null) {
			///table[i] = new LinkedList<Entry>();
			table[i].add(new Entry(key, val));
			this.elementosInseridos++;
			if (this.elementosInseridos >= this.tamanho*(fatorResize/100))
				resize(this.tamanho * 2);
			return true;
		}
		// se chegou nesse ponto é porquê table[i] != null
		else{
			for(Entry no : table[i]) {
				if(no.key.equals(key)) {
					//System.out.println("Elemento existente!");
					return false;
				}
			}
			table[i].add(new Entry(key,val));
			this.elementosInseridos++;
			if (this.elementosInseridos >= this.tamanho*(fatorResize/100))
				resize(this.tamanho * 2);
			return true;
		}
	}

	@Override
	public boolean remove(String key) {
		int i = hash(key);
		for(Entry no : table[i]) {
			if (no.key.equals(key)) {
				table[i].remove(no);
				return true;
			}
		}
		return false;
	}

	private int hash(String key) {
		return Math.abs(key.hashCode()) % this.tamanho;

	}

	@Override
	public boolean resize(int novo_tam) {
		Hash_Encadeamento novoHash = new Hash_Encadeamento(novo_tam);
		for (LinkedList<Entry> ListNo : table) {
			for (int i = 0; i<ListNo.size();i++) {
				Entry no = ListNo.get(i);
				int j = hash(no.key); 
				novoHash.table[j].add(no);
			}
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

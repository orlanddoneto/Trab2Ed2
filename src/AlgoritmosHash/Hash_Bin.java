package AlgoritmosHash;

import Algoritmos.BinTree;

public class Hash_Bin<K extends Comparable<K>, V> implements HashTable<K,V>{

	private int elementosInseridos;
	private int tamanho;
	private BinTree<K,V>[]vetor;
	private int resizes;
	private int metodo;
	
	
	public Hash_Bin(int tamanho, int metodo) {
		this.tamanho = tamanho;
		this.metodo = metodo;
		this.vetor = (BinTree<K,V>[]) new BinTree[tamanho];
	}

	@Override
	public int size() {
		return elementosInseridos;
	}
	
	@Override
	public V get(K key) {
		int i = hash(key);
    	V resul = null;
    	if (vetor[i]!=null) {
    		resul = vetor[i].get(key);
    	}
    	return resul;
	}

	@Override
	public boolean insert(K key, V val) {
		int hash = hash(key);
		int i = hash;
    	boolean result;
    	if (vetor[i]==null) {
    		vetor[i] = new BinTree<K,V>();
    		result = vetor[i].inserir(key, val);
    		if (result)
    			this.elementosInseridos++;
    		if (this.elementosInseridos >= this.tamanho/2)
    			resize(this.tamanho*2);
    		return true;
    	}
    	int j = 0;
    	if(metodo == 1) {
    		
    		while (vetor[i] != null) {
                j++;
                i =(hash + j * j) % tamanho;
            }
    		vetor[i] = new BinTree<K,V>();
    	}
    	else if(metodo == 2) {
    		BinTree.NodeBin current = vetor[i].getRaiz();
    	    while (current.getProx() != null) {
    	        current = current.getProx();
    	    }
    	    current.setProx(key,val);
    	}
    	
    	result = vetor[i].inserir(key, val);
    	if (result)
    		this.elementosInseridos++;
    	if (this.elementosInseridos >= this.tamanho/2)
    		resize(this.tamanho*2);
    	return result;
	}

	@Override
	public boolean remove(K key) {
		int i = hash(key);
    	boolean result = false;
    	if (vetor[i]!=null) {
    		vetor[i].remover(key);
    		result = !vetor[i].contains(key);
    		if (result)
    			this.elementosInseridos--;
    	}
    	return result;
	}
	
	private int hash(K key) {
        return Math.abs(key.hashCode()) % this.tamanho;
		
    }

	@Override
	public boolean resize(int novo_tam) {
		Hash_Bin <K, V> novoHash = new Hash_Bin<K,V> (novo_tam,metodo);
        for (int i=0; i<this.tamanho; i++) {
            if (this.vetor[i]!=null)
            	for (int j=0; j<tamanho; j++) {
            		BinTree.NodeBin temp = vetor[i].removeQualquer(vetor[i].getRaiz());
            		if (temp==null)
            			break;
            		novoHash.insert((K) temp.getKey(), (V)temp.getValue());
            	}
        }
        this.resizes++;
        this.vetor = novoHash.vetor;
        this.tamanho = novoHash.tamanho;
        return true;
	}

	
}

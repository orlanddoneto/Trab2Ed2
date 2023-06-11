package Algoritmos;

import Algoritmos.BinTree.NodeBin;

public abstract class Tree<K,V> {
	public abstract boolean inserir(K key, V value);
	public abstract void inorderTraversal();
	public abstract boolean remover(K key);
	public abstract V get(K key);

}

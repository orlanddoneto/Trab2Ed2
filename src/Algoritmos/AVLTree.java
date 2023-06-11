package Algoritmos;

import java.util.Comparator;

import Algoritmos.BinTree.NodeBin;

public class AVLTree<K extends Comparable<K>, V> extends Tree<K, V> {
	public class NodeAVL {
		K key;
		V value;
		NodeAVL esq, dir;
		int height;

		public NodeAVL(K key, V value) {
			this.key = key;
			this.value = value;
			this.height = 1;
		}
	}

	public NodeAVL raiz;

	public NodeAVL getRaiz() {
		return raiz;
	}

	public boolean ehVazio() {
		return raiz == null;
	}

	public int height(NodeAVL node) {
		return node == null ? -1 : node.height;
	}
	
	@Override
	public boolean inserir(K key, V value) {
		raiz = put(raiz, key, value);
		if(raiz != null)
			return true;
		else
			return false;
	}
	
	
	private NodeAVL put(NodeAVL raiz, K key, V value) {
		NodeAVL newNode = new NodeAVL(key, value);
		if (raiz == null) {
			return newNode;
		}

		int compare = key.compareTo(raiz.key);
		if (compare < 0) {
			raiz.esq = put(raiz.esq, key, value);
		} else if (compare > 0) {
			raiz.dir = put(raiz.dir, key, value);
		}
		else if (compare == 0) {
			raiz.dir = put(raiz.dir, key, value); // Insere o novo nó à direita, mesmo se a chave for igual
		}

		raiz.height = Math.max(height(raiz.esq), height(raiz.dir)) + 1;

		int balanceamento = getBalanceamento(raiz);

		if (balanceamento > 2 && key.compareTo(raiz.esq.key) < 0) {
			return rotacaoDir(raiz);
		}

		if (balanceamento < -2 && key.compareTo(raiz.dir.key) >= 0) {
			return rotacaoEsq(raiz);
		}

		if (balanceamento > 2 && key.compareTo(raiz.esq.key) > 0) {
			return duplaRotacaoDir(raiz);
			
		}

		if (balanceamento < -2 && key.compareTo(raiz.dir.key) <= 0) {
			return duplaRotacaoEsq(raiz);
		}

		return raiz;
	}

	public V get(K key) {
		NodeAVL node = getRecursivo(raiz, key);

		if (node != null) {
			return node.value;
		} else {
			return null;
		}
	}

	private NodeAVL getRecursivo(NodeAVL raiz, K key) {
		if (raiz == null || key.compareTo(raiz.key) == 0) {
			return raiz;
		}

		if (key.compareTo(raiz.key) < 0) {
			return getRecursivo(raiz.esq, key);
		}

		return getRecursivo(raiz.dir, key);
	}

	private int getBalanceamento(NodeAVL N) {
		if (N == null) {
			return 0;
		}
		return height(N.esq) - height(N.dir);
	}

	private NodeAVL rotacaoEsq(NodeAVL k1) {
		NodeAVL y = k1.dir;
		NodeAVL T2 = y.esq;

		y.esq = k1;
		k1.dir = T2;

		k1.height = Math.max(height(k1.esq), height(k1.dir)) + 1;
		y.height = Math.max(height(y.esq), height(y.dir)) + 1;

		return y;
	}

	private NodeAVL rotacaoDir(NodeAVL k2) {
		NodeAVL x = k2.esq;
		NodeAVL T2 = x.dir;

		x.dir = k2;
		k2.esq = T2;

		k2.height = Math.max(height(k2.esq), height(k2.dir)) + 1;
		x.height = Math.max(height(x.esq), height(x.dir)) + 1;

		return x;
	}

	private NodeAVL duplaRotacaoEsq(NodeAVL k1) {
		k1.dir = rotacaoDir(k1.dir);
		return rotacaoEsq(k1);
	}

	private NodeAVL duplaRotacaoDir(NodeAVL k3) {
		k3.esq = rotacaoEsq(k3.esq);
		return rotacaoDir(k3);
	}

	@Override
	public void inorderTraversal() {
		inorderTraversal(raiz);
	}

	private void inorderTraversal(NodeAVL node) {
		if (node != null) {
			inorderTraversal(node.esq);
			System.out.println(node.value);
			inorderTraversal(node.dir);
		}
	}

	@Override
	public boolean remover(K key) {
		// TODO Auto-generated method stub
		return false;
	}
}

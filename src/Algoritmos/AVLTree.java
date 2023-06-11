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
		if (raiz != null)
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
		} else if (compare == 0) {
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
	
	@Override
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
		int sizeAntesRemocao = size();
		raiz = removerRecursivo(raiz, key);
		return size() < sizeAntesRemocao;
	}

	private NodeAVL removerRecursivo(NodeAVL raiz, K key) {
		if (raiz == null) {
			return null;
		}

		int compare = key.compareTo(raiz.key);

		if (compare < 0) {
			raiz.esq = removerRecursivo(raiz.esq, key);
		} else if (compare > 0) {
			raiz.dir = removerRecursivo(raiz.dir, key);
		} else {
			if (raiz.esq == null || raiz.dir == null) {
				NodeAVL temp = null;
				if (raiz.esq == null) {
					temp = raiz.dir;
				} else {
					temp = raiz.esq;
				}

				if (temp == null) {
					raiz = null;
				} else {
					raiz = temp;
				}
			} else {
				NodeAVL temp = getMenorNode(raiz.dir);
				raiz.key = temp.key;
				raiz.value = temp.value;
				raiz.dir = removerRecursivo(raiz.dir, temp.key);
			}
		}

		if (raiz == null) {
			return null;
		}

		raiz.height = Math.max(height(raiz.esq), height(raiz.dir)) + 1;
		int balanceamento = getBalanceamento(raiz);

		if (balanceamento > 1 && getBalanceamento(raiz.esq) >= 0) {
			return rotacaoDir(raiz);
		}

		if (balanceamento > 1 && getBalanceamento(raiz.esq) < 0) {
			raiz.esq = rotacaoEsq(raiz.esq);
			return rotacaoDir(raiz);
		}

		if (balanceamento < -1 && getBalanceamento(raiz.dir) <= 0) {
			return rotacaoEsq(raiz);
		}

		if (balanceamento < -1 && getBalanceamento(raiz.dir) > 0) {
			raiz.dir = rotacaoDir(raiz.dir);
			return rotacaoEsq(raiz);
		}

		return raiz;
	}

	private NodeAVL getMenorNode(NodeAVL node) {
		NodeAVL current = node;
		while (current.esq != null) {
			current = current.esq;
		}
		return current;
	}

	private int size() {
		return size(raiz);
	}

	private int size(NodeAVL node) {
		if (node == null) {
			return 0;
		}
		return 1 + size(node.esq) + size(node.dir);
	}
}

package Algoritmos;

import java.util.Comparator;
import java.util.List;

public class BinTree<K extends Comparable<K>, V> extends Tree<K,V> {
	public class NodeBin {
		K key;
		V value;
		NodeBin esq, dir;

		public NodeBin(K key, V value) {
			this.key = key;
			this.value = value;

		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}
		
	}

	
	private NodeBin raiz;
	
	public BinTree() {}
	
	@Override
	public boolean inserir(K key, V value) {
		NodeBin novoNo = new NodeBin(key, value);
		if (raiz == null) {
			raiz = novoNo;
		} else {
			NodeBin temp1 = raiz;
			NodeBin temp2 = raiz;
			while (temp1 != null && key != temp2.key) {
				temp2 = temp1;

				int compare = key.compareTo(temp1.key);

				if (compare < 0) {
					temp1 = temp1.esq;
				} else if (compare > 0) {
					temp1 = temp1.dir;
				}
			}
			if (key == temp2.key) {
				novoNo.dir = temp2.dir;
				temp2.dir = novoNo;
				return true;
			} 
			else {
				int compare = key.compareTo(temp2.key);
				if (compare < 0) {
					temp2.esq = novoNo;
					return true;
				} else if (compare > 0) {
					temp2.dir = novoNo;
					return true;
				}
			}

		}
		return false;
	}
	
	@Override
	public boolean remover(K key) {
		if (key == null)
			return false;
		else { 
			remover(this.raiz, key);
			return true;
		}
	}

	private NodeBin remover(NodeBin atual, K key) {
		if (atual == null) {
			return null;
		}
		int comparaChave = key.compareTo(atual.key);

		if (comparaChave < 0) {
			atual.esq = remover(atual.esq, key);
		} else if (comparaChave > 0) {
			atual.dir = remover(atual.dir, key);
		}

		else {
			if (atual.esq == null && atual.dir == null) {
				// Nó não tem filhos
				return null;
			} else if (atual.esq == null) {
				// Nó tem somente um filho à direita
				return atual.dir;
			} else if (atual.dir == null) {
				// Nó tem somente um filho à esquerda
				return atual.esq;
			} else {
				// Nó tem dois filhos
				NodeBin sucessor = obterMenorNo(atual.dir);
				atual.key = sucessor.key;
				atual.value = sucessor.value;
				atual.dir = remover(atual.dir, sucessor.key);
			}
		}

		return atual;
	}

	public NodeBin removeQualquer(NodeBin no) {
		while (no.esq != null)
			no = no.esq;

		NodeBin result = remover(no, no.key);
		return result;
	}

	private NodeBin obterMenorNo(NodeBin no) {
		if (no == null || no.esq == null) {
			return no;
		}

		return obterMenorNo(no.esq);
	}
	
	private V get(NodeBin no, K key) {
		if (no == null || no.key == null || no.value == null) {
			return null;
		}

		int compare = key.compareTo(no.key);

		if (compare < 0) {
			return get(no.esq, key);
		} else if (compare > 0) {
			return get(no.dir, key);
		} else {
			return no.value;
		}
	}
	@Override
	public V get(K key) {
		if (key == null) {
			return null;
		}

		return get(raiz, key);
	}

	public boolean contains(K key) {
		if (key == null) {
			throw new IllegalArgumentException("Argument to contains() cannot be null");
		}
		return get(key) != null;
	}
	
	public NodeBin getRaiz() {
    	return this.raiz;
    }
	
	@Override
	public void inorderTraversal() {
		inorderTraversal(raiz);
	}

	private void inorderTraversal(NodeBin node) {
		if (node != null) {
			inorderTraversal(node.esq);
			System.out.println(node.value);
			inorderTraversal(node.dir);
		}
	}

}

package Algoritmos;

import java.util.Comparator;

public class BinTree<K extends Comparable<K>, V> {
	private class Node {
		K key;
		V value;
		Node esq, dir;

		Node(K key, V value) {
			this.key = key;
			this.value = value;

		}
	}

	private Integer itensAdd;
	private Node raiz;

	public void inserir(K key, V value) {
		Node novoNo = new Node(key, value);
		if (raiz == null) {
			raiz = novoNo;
		} else {
			Node temp1 = raiz;
			Node temp2 = raiz;
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
				System.out.println("Elemento existente na árvore.");
			} else {
				int compare = key.compareTo(temp2.key);
				if (compare < 0) {
					temp2.esq = novoNo;
				} else if (compare > 0) {
					temp2.dir = novoNo;
				}
			}

		}
	}

	public Node remover(K key) {
		if (key == null)
			return null;
		return remover(this.raiz, key);
	}

	private Node remover(Node atual, K key) {
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
				Node sucessor = obterMenorNo(atual.dir);
				atual.key = sucessor.key;
				atual.value = sucessor.value;
				atual.dir = remover(atual.dir, sucessor.key);
			}
		}

		return atual;
	}

	public Node removeQualquer(Node no) {
		while (no.esq != null)
			no = no.esq;

		Node result = remover(no, no.key);
		return result;
	}

	private Node obterMenorNo(Node no) {
		if (no == null || no.esq == null) {
			return no;
		}

		return obterMenorNo(no.esq);
	}

	private V get(Node no, K key) {
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

}

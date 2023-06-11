package Algoritmos;

import Algoritmos.AVLTree.NodeAVL;

public class RBNTree<K extends Comparable<K>, V> extends Tree<K,V> {	
	
	protected static final boolean RED = true;
    protected static final boolean BLACK = false;

    public class Node {
        public K chave;
        public V valor;
        public Node esq, dir, pai;

        boolean cor;
        int tamanho;

        public Node(K key, V value, int tamanho, boolean cor) {
            this.chave = key;
            this.valor = value;
            this.tamanho = tamanho;
            this.cor = cor;
        }
        
        public Node(K key, V valor) {
        	this.chave = key;
        	this.valor = valor;
        }
        
    }

    protected Node raiz;

    public Node getRaiz() {
    	return this.raiz;
    }
    
	private boolean isRed(Node h) {
		return h.cor == true;
	}
	
	private boolean isBlack(Node h) {
		return h.cor == false;
	}
	
	 public int tamanho() {
        return tamanho(raiz);
    }

    protected int tamanho(Node no) {
        if (no == null) {
            return 0;
        }

        return no.tamanho;
    }

    public boolean isEmpty() {
        return tamanho(raiz) == 0;
    }

	private void rotacaoDireita(Node h) {
		Node pai = h.pai;
		Node filhoEsq = h.esq;
		h.esq = filhoEsq.dir;
		if (filhoEsq.dir != null)
			filhoEsq.dir.pai = h;
		filhoEsq.dir = h;
		h.pai = filhoEsq;
		trocaPaiFilho(pai, h, filhoEsq);
	}
	
	private void rotacaoEsquerda(Node h) {
		Node pai = h.pai;
		Node filhoDir = h.dir;
		h.dir = filhoDir.esq;
		if (filhoDir.esq != null)
			filhoDir.esq.pai = h;
		filhoDir.esq = h;
		h.pai = filhoDir;
		trocaPaiFilho(pai, h, filhoDir);
	}
	
	private void trocaPaiFilho(Node pai, Node filhoAnt, Node filhoNov) {
		if (pai==null)
			this.raiz = filhoNov;
		else if (pai.esq == filhoAnt)
			pai.esq = filhoNov;
		else if (pai.dir == filhoAnt)
			pai.dir = filhoNov;
		
		if (filhoNov != null)
			filhoNov.pai = pai;
	}
	
	private void trocaCor(Node h) {
		if (h.cor)
			h.cor = false;
		else
			h.cor = true;
	}
	
	@Override
	public V get(K chave) {
		Node no = this.raiz;
		while (no!=null) {
			if ((int)chave == (int)no.chave)
				return no.valor;
			else if ((int)chave < (int)no.chave)
				no = no.esq;
			else
				no = no.dir;
		}
		return null;
	}
	@Override
	public boolean inserir(K chave, V val) {
		Node no = this.raiz;
		Node pai = null;
		
		while (no != null) {
			pai = no;
			int compare = chave.compareTo(no.chave);
			if (compare < 0)
				no = no.esq;
			else if (compare > 0)
				no = no.dir;
			else
				no = no.dir;
		}
		
		Node newnode = new Node(chave, val);
		newnode.cor = RED;
		if (pai==null) {
			this.raiz = newnode;
		}
		else {
			int compare = chave.compareTo(pai.chave); 
			if (compare < 0)
				pai.esq = newnode;
			else
				pai.dir = newnode;
		}
		newnode.pai = pai;
		arrumaInsert(newnode);
		return true;
	}
	
	private void arrumaInsert(Node no) {
		Node pai = no.pai;
		if (pai==null) {
			no.cor = BLACK;
			return;
		}
		if (pai.cor==BLACK) {
			return;
		}
		Node avo = pai.pai;
		if (avo==null) {
			pai.cor = BLACK;
			return;
		}
		Node tio = getTio(pai);
		if (tio!=null && tio.cor==RED) {
			pai.cor = BLACK;
			tio.cor = BLACK;
			avo.cor = RED;
			arrumaInsert(avo);
		}
		else if (pai==avo.esq) { // pai é filho esquerdo
			if (no == pai.dir) {
				rotacaoEsquerda(pai);
				pai = no;
			}
			rotacaoDireita(avo);
			pai.cor = BLACK;
			avo.cor = RED;
		}
		else { // pai é filho direito
			if (no == pai.esq) {
				rotacaoDireita(pai);
				pai = no;
			}
			rotacaoEsquerda(avo);
			pai.cor = BLACK;
			avo.cor = RED;
		}
		
	}
	
	private Node getTio(Node pai) {
		Node avo = pai.pai;
		if (pai == avo.esq)
			return avo.dir;
		else if (pai == avo.dir)
			return avo.esq;
		else
			return null;
	}
	
	@Override
	public boolean remover(K key) {
		if (key == null)
			return false;
		else { 
			deleteNo(key);
			return true;
		}
	}
	
	private void deleteNo(K chave) {
		Node no = this.raiz;
		while (no!=null && no.chave != chave) {
			int compare = chave.compareTo(no.chave);
			if (compare < 0)
				no = no.esq;
			else
				no = no.dir;
		}
		if (no==null)
			return;
		Node noMovido;
		boolean corNoDeletado;
		if (no.esq==null || no.dir==null) {
			noMovido = deletaNoTipoUm(no);
			corNoDeletado = no.cor;
		}
		else {
			Node sucessor = achaMenor(no.dir);
			no.chave = sucessor.chave;
			no.valor = sucessor.valor;
			noMovido = deletaNoTipoUm(sucessor);
			corNoDeletado = sucessor.cor;
		}
		if (corNoDeletado==BLACK) {
			arrumaDelete(noMovido);
			if (noMovido.getClass() == NilNode.class)
				trocaPaiFilho(noMovido.pai, noMovido, null);
		}
			
	}
	
	private Node deletaNoTipoUm(Node no) {
		if (no.esq != null) {
			trocaPaiFilho(no.pai, no, no.esq);
			return no.esq;
		}
		else if (no.dir != null) {
			trocaPaiFilho(no.pai, no, no.dir);
			return no.dir;
		}
		else {
			Node novFilho = no.cor == BLACK ? new NilNode() : null;
			trocaPaiFilho(no.pai, no, novFilho);
			return novFilho;
		}
	}
	
	private class NilNode extends Node {
		private NilNode() {
			super(null, null, 0, BLACK);
		}
	}
	
	private Node achaMenor(Node no) {
		while (no.esq != null)
			no = no.esq;
		return no;
	}

	private void arrumaDelete(Node no) {
		if (no==this.raiz) {
			no.cor = BLACK;
			return;
		}
		Node irmao = getIrmao(no);
		if (irmao.cor == RED) {
			arrumaIrmaoVermelho(no, irmao);
			irmao = getIrmao(no);
		}
		if (isBlack(irmao.esq) && isBlack(irmao.dir)) {
			irmao.cor = RED;
			if (no.pai.cor == RED)
				no.pai.cor = BLACK;
			else
				arrumaDelete(no.pai);
		}
		else {
			arrumaIrmaoPreto(no, irmao);
		}
	}
	
	private Node getIrmao(Node no) {
		Node pai = no.pai;
		if (no == pai.esq)
			return pai.dir;
		else if (no == pai.dir)
			return pai.esq;
		else 
			return null;
	}
	
	private void arrumaIrmaoVermelho(Node no, Node irmao) {
		irmao.cor = BLACK;
		no.pai.cor = RED;
		if (no == no.pai.esq)
			rotacaoEsquerda(no.pai);
		else 
			rotacaoDireita(no.pai);
	}
	
	private void arrumaIrmaoPreto(Node no, Node irmao) {
		boolean noFilhoEsq = no == no.pai.esq;
		if (noFilhoEsq && isBlack(irmao.dir)) {
			irmao.esq.cor = BLACK;
			irmao.cor = RED;
			rotacaoDireita(irmao);
			irmao = no.pai.dir;
		} else if (!noFilhoEsq && isBlack(irmao.esq)) {
			irmao.dir.cor = BLACK;
			irmao.cor = RED;
			rotacaoEsquerda(irmao);
			irmao = no.pai.esq;
		}
		irmao.cor = no.pai.cor;
		no.pai.cor = BLACK;
		if (noFilhoEsq) {
			irmao.dir.cor = BLACK;
			rotacaoEsquerda(no.pai);
		}
		else {
			irmao.esq.cor = BLACK;
			rotacaoDireita(no.pai);
		}
	}

	@Override
	public void inorderTraversal() {
		inorderTraversal(raiz);
	}

	private void inorderTraversal(Node node) {
		if (node != null) {
			inorderTraversal(node.esq);
			System.out.println(node.valor);
			inorderTraversal(node.dir);
		}
	}
	
}



package Servicos;

import java.util.ArrayList;
import java.util.List;

import Algoritmos.AVLTree;
import Algoritmos.BinTree;
import Algoritmos.RBNTree;
import Algoritmos.Tree;

public class GerenciadorArvores {
	private Tree tree;
	private int arvoreEscolhida;
	
	public GerenciadorArvores() {
	}
	
	public void setArvore(int arvoreEscolhida) {
		List<Tree> arvores = new ArrayList<>();
		arvores.add(new BinTree());
		arvores.add(new AVLTree());
		arvores.add(new RBNTree());
		tree = arvores.get(arvoreEscolhida - 1);
	}
	
	public Tree getTree() {
		return tree;
	}
	
	

}

package View;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import Algoritmos.Tree;
import AlgoritmosHash.HashTable;
import Entidade.Arquivo;
import ManagerCSV.LeitorCSV;
import Servicos.GerenciadorArvores;
import Servicos.GerenciadorHashTable;

public class MenuPrincipal {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		//GeradorCSV csv = new GeradorCSV();
		//csv.gerarCSV();
		LeitorCSV lerCSV = new LeitorCSV();
		lerCSV.lerCSV();
		
		System.out.printf("Qual tratamento de colisão?\n[1] - Quadrático\n[2] - Lista Encadeada");
		int trataColisao = sc.nextInt();
		
		int tam = 10; //Tamanho inicial (default)
		GerenciadorHashTable gerente = new GerenciadorHashTable(tam);
		gerente.setStrategy(trataColisao);
		HashTable hashTable = gerente.getHashTable();
		
		//insere elementos na hashtable
		int cont = 0;
		for(Arquivo obj : lerCSV.getLines()) {
			hashTable.insert(obj.getNome(), obj);
			cont++;
			System.out.println("inseriu");
			if (cont==tam)
				break;
		}
		System.out.printf("Qual árvore você vai guardar os arquivos?\n [1] - Arvore Binária");
		int arvoreEscolhida = sc.nextInt();
		GerenciadorArvores gerenteTree = new GerenciadorArvores();
		gerenteTree.setArvore(1);
		Tree arvore = gerenteTree.getTree();
		
		//Insere Elementos Para Teste
		hashTable.insert("Orlando", new Arquivo("Orlando", "Pasta/caminho/Orlando.pdf", "pdf", 36.00,LocalDate.parse("2023-01-01") ,LocalDate.parse("2023-02-01")));
		hashTable.insert("Isabelle", new Arquivo("Isabelle", "Pasta/caminho/Isabelle.pdf", "pdf", 37.00, LocalDate.parse("2022-01-01"),LocalDate.parse("2023-01-11")));
		hashTable.insert("Dalysson", new Arquivo("Dalysson", "Pasta/caminho/Dalysson.pdf", "pdf", 38.00, LocalDate.parse("2023-09-01"),LocalDate.parse("2024-01-01")));
		hashTable.insert("Estrutura", new Arquivo("Estrutura", "Pasta/caminho/Estrutura.pdf", "pdf", 39.00, LocalDate.parse("2023-01-05"),LocalDate.parse("2023-10-01")));
		hashTable.insert("Dados", new Arquivo("Dados", "Pasta/caminho/Dados.pdf", "pdf", 45.00, LocalDate.parse("2013-01-01"),LocalDate.parse("2023-06-01")));
		
		System.out.println("Você quer ordenar por qual critério? \n[1] - Nome\n[2] - Tipo\n[3] - Tamanho\n"
				+ "[4] - Data Modificação\n[5] - Data Criação");
		int criterioOrdem = sc.nextInt();
		
		List<String> list = new ArrayList<>();
		list.add("Orlando");
		list.add("Isabelle");
		list.add("Dalysson");
		list.add("Estrutura");
		list.add("Dados");
		for(String nome : list){
			arvore.inserir(hashTable.get(nome).chaveOrdem(criterioOrdem), hashTable.get(nome));
		}
		arvore.inorderTraversal(); //imprime os valores em ordem que estão contidos na árvore
		//System.out.println(hashTable.get("qxibxmoz"));
		//hashTable.insert("qxibxmoz", hashTable.get("qxibxmoz"));
		//System.out.println(hashTable.remove("qxibxmoz"));
		//System.out.println(hashTable.get("qxibxmoz"));

	}

}

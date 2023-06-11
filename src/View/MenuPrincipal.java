package View;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import Algoritmos.Tree;
import AlgoritmosHash.HashTable;
import Entidade.Arquivo;
import ManagerCSV.GeradorCSV;
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
		//int cont = 0;
		
		for(Arquivo obj : lerCSV.getLines()) {
			hashTable.insert(obj.getNome(), obj);
			//cont++;2
			//if (cont==tam)
				//break;
		}
		System.out.printf("Qual árvore você vai guardar os arquivos?\n[1] - Arvore Binária\n[2] - Árvore AVL\n[3] - Árvore Rubro-Negra");
		int arvoreEscolhida = sc.nextInt();
		GerenciadorArvores gerenteTree = new GerenciadorArvores();
		gerenteTree.setArvore(1);
		Tree arvore = gerenteTree.getTree();
		
		
		System.out.println("Você quer ordenar por qual critério? \n[1] - Nome\n[2] - Tipo\n[3] - Tamanho\n"
				+ "[4] - Data Criação\n[5] - Data Modificação");
		int criterioOrdem = sc.nextInt();
		
		//Insere na HashTable Elementos Para Teste
		hashTable.insert("Orlando", new Arquivo("Orlando", "Pasta/caminho/Orlando.pdf", "pdf", 36,LocalDateTime.parse("2023-01-14T01:30:00") ,LocalDateTime.parse("2023-01-14T02:30:00")));
		hashTable.insert("Dalysson", new Arquivo("Dalysson", "Pasta/caminho/Dalysson.doc", "doc", 38, LocalDateTime.parse("2023-01-14T01:30:20"),LocalDateTime.parse("2023-01-14T02:33:00")));
		hashTable.insert("Estrutura", new Arquivo("Estrutura", "Pasta/caminho/Estrutura.pdf", "pdf", 39, LocalDateTime.parse("2023-01-14T01:31:00"),LocalDateTime.parse("2023-01-14T02:40:00")));
		hashTable.insert("Dados", new Arquivo("Dados", "Pasta/caminho/Dados.txt", "txt", 45, LocalDateTime.parse("2023-01-14T01:30:24"),LocalDateTime.parse("2023-01-14T03:34:00")));
		hashTable.insert("Isabelle", new Arquivo("Isabelle", "Pasta/caminho/Isabelle.jpg", "jpg", 36, LocalDateTime.parse("2023-01-14T01:30:30"),LocalDateTime.parse("2023-01-14T05:30:00")));
		
		//Para Testes
		//Orlando;Dalysson;Estrutura;Dados;Isabelle
		
		System.out.println("Busque um arquivo: ");
		String busca = sc.next();
		String[] temp = busca.split(";");
		List<String> list = Arrays.asList(temp);
		
		for(String nome : list){
			if(hashTable.get(nome)!=null)
				arvore.inserir(hashTable.get(nome).chaveOrdem(criterioOrdem), hashTable.get(nome));
			else{
				System.out.println();
				System.out.println("Arquivo "+"'"+nome+"'"+" Inexistente");
			}
		}
		System.out.println();
		arvore.inorderTraversal(); //imprime os valores, em ordem, que estão contidos na árvore
		
		System.out.println("Você quer remover algum arquivo do resultado da busca? [y/n]");
		char decisaoRemov = sc.next().charAt(0);
		
		
		if(decisaoRemov == 'y') {
			System.out.println("Arquivo a ser removido: ");
			String arquivoMorto = sc.next();
			arvore.remover(arquivoMorto);
		}
		else
			System.out.println("Informações técnicas: "); // coisas do relatório

	}

}

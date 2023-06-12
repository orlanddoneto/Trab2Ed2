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
		// GeradorCSV csv = new GeradorCSV();
		// csv.gerarCSV();
		LeitorCSV lerCSV = new LeitorCSV();
		lerCSV.lerCSV();

		System.out.printf("Qual tratamento de colisão?\n[1] - Quadrático\n[2] - Lista Encadeada");
		int trataColisao = sc.nextInt();

		GerenciadorHashTable gerente = new GerenciadorHashTable();
		gerente.setStrategy(trataColisao);
		HashTable hashTable = gerente.getHashTable();

		// insere elementos na hashtable
		// int cont = 0;

		long inicioRelogio = System.currentTimeMillis();
		for (Arquivo obj : lerCSV.getLines()) {
			hashTable.insert(obj.getNome(), obj);

		}
		long FimRelogio = System.currentTimeMillis();
		long resultInsercaoHashTable = FimRelogio - inicioRelogio;
		System.out.printf(
				"Qual árvore você vai guardar os arquivos?\n[1] - Arvore Binária\n[2] - Árvore AVL\n[3] - Árvore Rubro-Negra");
		int arvoreEscolhida = sc.nextInt();
		GerenciadorArvores gerenteTree = new GerenciadorArvores();
		gerenteTree.setArvore(arvoreEscolhida);
		Tree arvore = gerenteTree.getTree();

		System.out.println("Você quer ordenar por qual critério? \n[1] - Nome\n[2] - Tipo\n[3] - Tamanho\n"
				+ "[4] - Data Criação\n[5] - Data Modificação");
		int criterioOrdem = sc.nextInt();

		// Insere na HashTable Elementos Para Teste
		hashTable.insert("Orlando", new Arquivo("Orlando", "Pasta/caminho/Orlando.pdf", "pdf", 36,
				LocalDateTime.parse("2023-01-14T01:30:00"), LocalDateTime.parse("2023-01-14T02:30:00")));
		hashTable.insert("Dalysson", new Arquivo("Dalysson", "Pasta/caminho/Dalysson.doc", "doc", 38,
				LocalDateTime.parse("2023-01-14T01:30:20"), LocalDateTime.parse("2023-01-14T02:33:00")));
		hashTable.insert("Estrutura", new Arquivo("Estrutura", "Pasta/caminho/Estrutura.pdf", "pdf", 39,
				LocalDateTime.parse("2023-01-14T01:31:00"), LocalDateTime.parse("2023-01-14T02:40:00")));
		hashTable.insert("Dados", new Arquivo("Dados", "Pasta/caminho/Dados.txt", "txt", 45,
				LocalDateTime.parse("2023-01-14T01:30:24"), LocalDateTime.parse("2023-01-14T03:34:00")));
		hashTable.insert("Isabelle", new Arquivo("Isabelle", "Pasta/caminho/Isabelle.jpg", "jpg", 36,
				LocalDateTime.parse("2023-01-14T01:30:30"), LocalDateTime.parse("2023-01-14T05:30:00")));

		// Para Testes
		// Orlando;Dalysson;Estrutura;Dados;Isabelle

		Object classe = null;
		long resultInsercaoTree = 0L;
		System.out.println("Buscar todos os 10.000 itens? [y/n]");
		char resposta = sc.next().charAt(0);

		if (resposta == 'y') {
			inicioRelogio = System.currentTimeMillis();
			for (Arquivo obj : lerCSV.getLines()) {
				try {
					String temp = obj.getNome();
					arvore.inserir(hashTable.get(temp).chaveOrdem(criterioOrdem), hashTable.get(obj.getNome()));
				} catch (NullPointerException e) {
					continue;
				}
			}
			FimRelogio = System.currentTimeMillis();
			resultInsercaoTree = FimRelogio - inicioRelogio;

		} else {
			System.out.println("Busque um arquivo: ");
			String busca = sc.next();
			String[] temp = busca.split(";");
			List<String> list = Arrays.asList(temp);

			inicioRelogio = System.currentTimeMillis();
			for (String nome : list) {
				if (hashTable.get(nome) != null)
					arvore.inserir(hashTable.get(nome).chaveOrdem(criterioOrdem), hashTable.get(nome));
				else {
					System.out.println();
					System.out.println("Arquivo " + "'" + nome + "'" + " Inexistente");
				}
				classe = hashTable.get(nome).chaveOrdem(criterioOrdem);
			}
			FimRelogio = System.currentTimeMillis();
			resultInsercaoTree = FimRelogio - inicioRelogio;
			System.out.println();
			
		}
		
		inicioRelogio = System.currentTimeMillis();
		arvore.inorderTraversal(); // imprime os valores, em ordem, que estão contidos na árvore
		FimRelogio = System.currentTimeMillis();
		long resultBuscaTree = FimRelogio - inicioRelogio;
		System.out.println("Você quer remover algum arquivo do resultado da busca? [y/n]");
		char decisaoRemov = sc.next().charAt(0);

		if (decisaoRemov == 'y') {
			System.out.println("Arquivo a ser removido (Informe o dado correspondente ao criterio de ordenação): ");
			String arquivoRemovido = sc.next();

			// temp2.getClass();

			arvore.remover(transformaClasse(arquivoRemovido, classe));
			arvore.inorderTraversal();
		}

		System.out.printf(
				"Informações técnicas: \nResizes da árvore: %d\nTempo de inserção da hashTable: %d\nTempo de inserção (10.000 itens) na árvore: %d\nTempo de busca na árvore: %d",
				hashTable.getResizes(), resultInsercaoHashTable, resultInsercaoTree, resultBuscaTree); // coisas do
																										// relatório

	}

	private static Object transformaClasse(String a, Object b) {
		if (b.getClass().equals(String.class)) {
			return (String) a;
		}

		if (b.getClass().equals(Integer.class)) {
			return Integer.parseInt(a);
		}

		if (b.getClass().equals(Double.class)) {
			return Double.parseDouble(a);
		}
		if (b.getClass().equals(LocalDateTime.class))
			return LocalDateTime.parse(a);
		return null;
	}

}

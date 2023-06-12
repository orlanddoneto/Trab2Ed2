package View;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import Algoritmos.Tree;
import Entidade.Arquivo;
import ManagerCSV.GeradorCSV;
import ManagerCSV.LeitorCSV;
import Servicos.GerenciadorArvores;
import Servicos.GerenciadorHashTable;
import TabelasHash.HashTable;

public class MenuPrincipal {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		// GeradorCSV csv = new GeradorCSV();  //Gera um csv, como ja temos um, est� comentado
		// csv.gerarCSV();
		LeitorCSV lerCSV = new LeitorCSV(); //l� o csv j� gerado
		lerCSV.lerCSV();

		System.out.printf("Qual tratamento de colis�o?\n[1] - Quadr�tico\n[2] - Lista Encadeada");
		int trataColisao = sc.nextInt();

		GerenciadorHashTable gerente = new GerenciadorHashTable(); // Classe que gerencia a HashTable
		gerente.setStrategy(trataColisao); //Escolhe qual � a hashTable que vai ser usada, com metodo quadr�tico ou Lista Ligada
		HashTable hashTable = gerente.getHashTable(); // guarda a hashTable escolhida nessa vari�vel hashTable

		
		// insere elementos na hashtable
		long inicioRelogio = System.currentTimeMillis();
		for (Arquivo obj : lerCSV.getLines()) {
			hashTable.insert(obj.getNome(), obj);

		}
		long FimRelogio = System.currentTimeMillis();
		long resultInsercaoHashTable = FimRelogio - inicioRelogio;
		
		
		System.out.printf(
				"Qual �rvore voc� vai guardar os arquivos?\n[1] - Arvore Bin�ria\n[2] - �rvore AVL\n[3] - �rvore Rubro-Negra");
		int arvoreEscolhida = sc.nextInt();
		
		
		GerenciadorArvores gerenteTree = new GerenciadorArvores();
		gerenteTree.setArvore(arvoreEscolhida); //Escolhe qual �rvore vai ser usada
		Tree arvore = gerenteTree.getTree(); // guarda na vari�vel "arvore" a �rvore escolhida

		
		System.out.println("Voc� quer ordenar por qual crit�rio? \n[1] - Nome\n[2] - Tipo\n[3] - Tamanho\n"
				+ "[4] - Data Cria��o\n[5] - Data Modifica��o");
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

		
		Object classe = null; //variavel auxiliar que ir� servir para pegar o tipo do dado que est� sendo usado como chave na �rvore
		// vai ser usado na hora de buscar um elemento para remove, a fim de resolver o problema de incompatibilidade de dados 
		
		long resultInsercaoTree = 0L; // inicializa��o fora pois a variavel � usada dentro e fora de um if
		
		
		System.out.println("Buscar todos os 10.000 itens? [y/n]");
		char resposta = sc.next().charAt(0);

		if (resposta == 'y') {
			inicioRelogio = System.currentTimeMillis();
			for (Arquivo obj : lerCSV.getLines()) { //Insere todos os itens dentro da �rvore
				try { //try catch caso algum item aleat�rio nao tenha conseguido entrar na hashtable
					String temp = obj.getNome();
					arvore.inserir(hashTable.get(temp).chaveOrdem(criterioOrdem), hashTable.get(obj.getNome()));
					classe = hashTable.get(temp).chaveOrdem(criterioOrdem); //pega o dado para criterio de ordem na arvore, para usar na fun��o transformaClasse()
				} catch (NullPointerException e) {
					continue; //caso aconte�a, o la�o segue para a pr�xima itera��o
				}
				
			}
			FimRelogio = System.currentTimeMillis();
			resultInsercaoTree = FimRelogio - inicioRelogio;

		} else {
			System.out.println("Busque um arquivo: ");
			String busca = sc.next();
			String[] temp = busca.split(";"); //cada elemento da busca � separado por um ;
			List<String> list = Arrays.asList(temp);

			inicioRelogio = System.currentTimeMillis();
			for (String nome : list) {
				if (hashTable.get(nome) != null)
					arvore.inserir(hashTable.get(nome).chaveOrdem(criterioOrdem), hashTable.get(nome));
				else {
					System.out.println();
					System.out.println("Arquivo " + "'" + nome + "'" + " Inexistente");
				}
				classe = hashTable.get(nome).chaveOrdem(criterioOrdem); //pega o dado para criterio de ordem na arvore, para usar na fun��o transformaClasse()
			}
			FimRelogio = System.currentTimeMillis();
			resultInsercaoTree = FimRelogio - inicioRelogio;
			System.out.println();
			
		}
		
		inicioRelogio = System.currentTimeMillis();
		arvore.inorderTraversal(); // imprime os valores, em ordem, que est�o contidos na �rvore
		FimRelogio = System.currentTimeMillis();
		long resultBuscaTree = FimRelogio - inicioRelogio;
		
		
		System.out.println("Voc� quer remover algum arquivo do resultado da busca? [y/n]");
		char decisaoRemov = sc.next().charAt(0);

		
		if (decisaoRemov == 'y') {
			System.out.println("Arquivo a ser removido (Informe o dado correspondente ao criterio de ordena��o): ");
			String arquivoRemovido = sc.next();
			arvore.remover(transformaClasse(arquivoRemovido, classe));
			arvore.inorderTraversal();
		}

		System.out.printf(
				"Informa��es t�cnicas: \nResizes da �rvore: %d\nTempo de inser��o da hashTable: %d\nTempo de inser��o (10.000 itens) na �rvore: %d\nTempo de busca na �rvore: %d",
				hashTable.getResizes(), resultInsercaoHashTable, resultInsercaoTree, resultBuscaTree); // coisas do
																										// relat�rio

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

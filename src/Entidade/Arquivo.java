package Entidade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {
	private String nome;
	private String caminho;
	private String tipo;
	private Integer tamanho;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataModificacao;
	
	public Arquivo(String nome, String caminho, String tipo, Integer tamanho, LocalDateTime dataCriacao,
			LocalDateTime dataModificacao) {
		
		this.nome = nome;
		this.caminho = caminho;
		this.tipo = tipo;
		this.tamanho = tamanho;
		this.dataCriacao = dataCriacao;
		this.dataModificacao = dataModificacao;
	}
	public Arquivo() {};
	
	public Object chaveOrdem(int criterio) {
    	List<Object> list = new ArrayList<>();
    	list.add(nome);
    	list.add(tipo);
    	list.add(tamanho);
    	list.add(dataCriacao);
    	list.add(dataModificacao);
    	
		return list.get(criterio-1);
    	
    }

	
	public String getNome() {
		return nome;
	}
    
	 public String toString(){
	        return "Nome: " + nome +
	                "\nCaminho: " + caminho +
	                "\nTipo: " + tipo +
	                "\nTamanho: " + tamanho +
	                "\nData Criação: " + dataCriacao +
	                "\nData Modificação: " + dataModificacao +"\n";
	    }
	
	
	
	

}

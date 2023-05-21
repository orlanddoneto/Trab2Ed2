package Entidade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {
	private String nome;
	private String caminho;
	private String tipo;
	private String tamanho;
	private LocalDate dataCriacao;
	private LocalDate dataModificacao;
	
	public Arquivo(String nome, String caminho, String tipo, String tamanho, LocalDate dataCriacao,
			LocalDate dataModificacao) {
		
		this.nome = nome;
		this.caminho = caminho;
		this.tipo = tipo;
		this.tamanho = tamanho;
		this.dataCriacao = dataCriacao;
		this.dataModificacao = dataModificacao;
	}
	
	public Object chave(int criterio) {
    	List<Object> list = new ArrayList<>();
    	list.add(nome);
    	list.add(caminho);
    	list.add(tipo);
    	list.add(tamanho);
    	list.add(dataCriacao);
    	list.add(dataModificacao);
   
		return list.get(criterio-1);
    	
    }
    
	
	
	

}

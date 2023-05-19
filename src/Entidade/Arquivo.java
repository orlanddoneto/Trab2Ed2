package Entidade;

import java.time.LocalDateTime;

public class Arquivo {
	private String nome;
	private String caminho;
	private String tipo;
	private String tamanho;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataModificacao;
	
	public Arquivo(String nome, String caminho, String tipo, String tamanho, LocalDateTime dataCriacao,
			LocalDateTime dataModificacao) {
		
		this.nome = nome;
		this.caminho = caminho;
		this.tipo = tipo;
		this.tamanho = tamanho;
		this.dataCriacao = dataCriacao;
		this.dataModificacao = dataModificacao;
	}
	
	
	

}
